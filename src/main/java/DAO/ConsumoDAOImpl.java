/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.conexion;
import Modelo.consumo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samir
 */
public class ConsumoDAOImpl implements ConsumoDAO{
    private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   public Double totalconsumo;
   
   
@Override
public List<consumo> mostrar(String buscar) {
    List<consumo> listaConsumos = new ArrayList<>();
    totalregistros = 0;
    totalconsumo = 0.0;

    String sSQL = "SELECT c.idconsumo, c.idreserva, c.idproducto, p.nombre, c.cantidad, c.precio_venta, c.estado "
                + "FROM consumo c INNER JOIN producto p ON c.idproducto = p.idproducto "
                + "WHERE c.idreserva = ? ORDER BY c.idconsumo DESC";

    try {
        PreparedStatement pst = cn.prepareStatement(sSQL);
        pst.setInt(1, Integer.parseInt(buscar)); // Asume que buscar es un ID numérico
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            consumo c = new consumo();
            c.setIdconsumo(rs.getInt("idconsumo"));
            c.setIdreserva(rs.getInt("idreserva"));
            c.setIdproducto(rs.getInt("idproducto"));
            c.setProducto(rs.getString("nombre")); 
            c.setCantidad(rs.getDouble("cantidad"));
            c.setPrecio_venta(rs.getDouble("precio_venta"));
            c.setEstado(rs.getString("estado"));

            listaConsumos.add(c);

            totalregistros++;
            totalconsumo += c.getCantidad() * c.getPrecio_venta();
        }

    } catch (Exception e) {
        JOptionPane.showConfirmDialog(null, e);
    }
    return listaConsumos;
}





   
    @Override
public boolean insertar(consumo dts) {
    double stockActual = obtenerStockProducto(dts.getIdproducto());

    if (dts.getCantidad() > stockActual) {
        JOptionPane.showMessageDialog(null, "Cantidad excede el stock disponible. Stock actual: " + stockActual);
        return false;
    }

    sSQL = "INSERT INTO consumo (idreserva, idproducto, cantidad, precio_venta, estado) VALUES (?, ?, ?, ?, ?)";

    try {
        PreparedStatement pst = cn.prepareStatement(sSQL);
        pst.setInt(1, dts.getIdreserva());
        pst.setInt(2, dts.getIdproducto());
        pst.setDouble(3, dts.getCantidad());
        pst.setDouble(4, dts.getPrecio_venta());
        pst.setString(5, dts.getEstado());

        int n = pst.executeUpdate();

        if (n != 0) {
            // Disminuir el stock del producto correspondiente
            if (!disminuirStock(dts.getIdproducto(), dts.getCantidad())) {
                JOptionPane.showMessageDialog(null, "Advertencia: No se pudo actualizar el stock del producto.");
            }
            return true;
        } else {
            return false;
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false;
    }
}


   
   @Override
public boolean editar(consumo dts) {
    String sSQL = "UPDATE consumo SET idreserva=?, idproducto=?, cantidad=?, precio_venta=?, estado=? WHERE idconsumo=?";
    double cantidadAnterior = obtenerCantidadAnterior(dts.getIdconsumo());

    if (cantidadAnterior == -1) {
        JOptionPane.showMessageDialog(null, "Error: No se pudo obtener la cantidad anterior del consumo.");
        return false;
    }

    double diferencia = dts.getCantidad() - cantidadAnterior;
    double stockActual = obtenerStockProducto(dts.getIdproducto());

    // Verificar que el nuevo stock no sea negativo
    if (stockActual - diferencia < 0) {
        JOptionPane.showMessageDialog(null, "Error: La cantidad solicitada excede el stock disponible. Stock actual: " + stockActual);
        return false;
    }

    try {
        PreparedStatement pst = cn.prepareStatement(sSQL);
        pst.setInt(1, dts.getIdreserva());
        pst.setInt(2, dts.getIdproducto());
        pst.setDouble(3, dts.getCantidad());
        pst.setDouble(4, dts.getPrecio_venta());
        pst.setString(5, dts.getEstado());
        pst.setInt(6, dts.getIdconsumo());

        int n = pst.executeUpdate();

        if (n != 0) {
            // Ajustar el stock del producto
            actualizarStock(dts.getIdproducto(), -diferencia);
            return true;
        } else {
            return false;
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false;
    }
}

  
    @Override
   public boolean eliminar (consumo dts){
       sSQL="delete from consumo where idconsumo=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdconsumo());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
    }

    public boolean disminuirStock(int idproducto, double cantidad) {
        String sql = "UPDATE producto SET stock = stock - ? WHERE idproducto = ? AND stock >= ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setDouble(1, cantidad);
            pst.setInt(2, idproducto);
            pst.setDouble(3, cantidad); // Para evitar valores negativos

            int n = pst.executeUpdate();
            return n > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede realizar la venta, stock insuficiente " + e);
            return false;
        }
    }
    
    public double obtenerStockProducto(int idproducto) {
    double stock = 0;
    String sql = "SELECT stock FROM producto WHERE idproducto = ?";

    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, idproducto);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            stock = rs.getDouble("stock");
        }
        rs.close();
        pst.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener el stock del producto: " + e.getMessage());
    }

    return stock;
}
    
    public double obtenerCantidadAnterior(int idconsumo) {
    double cantidad = -1;
    String sql = "SELECT cantidad FROM consumo WHERE idconsumo = ?";

    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, idconsumo);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            cantidad = rs.getDouble("cantidad");
        }

        rs.close();
        pst.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener la cantidad anterior: " + e.getMessage());
    }

    return cantidad;
}

    public boolean actualizarStock(int idproducto, double cantidad) {
    String sql = "UPDATE producto SET stock = stock + ? WHERE idproducto = ?";

    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setDouble(1, cantidad);
        pst.setInt(2, idproducto);

        int n = pst.executeUpdate();
        pst.close();

        return n != 0;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar el stock: " + e.getMessage());
        return false;
    }
}




}
