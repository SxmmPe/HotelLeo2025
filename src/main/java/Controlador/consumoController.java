package Controlador;

import Conexion.conexion;
import DAO.ConsumoDAO;
import DAO.ConsumoDAOImpl;
import Modelo.consumo;
import Modelo.habitacion;
import Modelo.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class consumoController {
    
    private final ConsumoDAO consumoDAO= new ConsumoDAOImpl();
    public int totalregistros;
    public double totalconsumo;
    

    public boolean insertar(consumo dts) {
        return consumoDAO.insertar(dts);
    }

    public boolean editar(consumo dts) {
        return consumoDAO.editar(dts);
    }

    public boolean eliminar(consumo dts) {
        return consumoDAO.eliminar(dts);
    }
    private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";


   public List<consumo> mostrar(String buscar) {
        List<consumo> lista = new ArrayList<>();
        totalregistros = 0;
        totalconsumo = 0.0;

        sSQL = "SELECT c.idconsumo, c.idreserva, c.idproducto, p.nombre, c.cantidad, c.precio_venta, c.estado " +
               "FROM consumo c INNER JOIN producto p ON c.idproducto = p.idproducto " +
               "WHERE c.idreserva = " + buscar + " ORDER BY c.idconsumo DESC";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sSQL)) {
            while (rs.next()) {
                consumo c = new consumo();
                c.setIdconsumo(rs.getInt("idconsumo"));
                c.setIdreserva(rs.getInt("idreserva"));
                c.setIdproducto(rs.getInt("idproducto"));
                c.setProducto(rs.getString("nombre"));
                c.setCantidad(rs.getDouble("cantidad"));
                c.setPrecio_venta(rs.getDouble("precio_venta"));
                c.setEstado(rs.getString("estado"));

                totalregistros++;
                totalconsumo += rs.getInt("cantidad") * rs.getDouble("precio_venta");

                lista.add(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return lista;
    }
   
   
   public List<consumo> buscarConsumo(String idreserva) {
    List<consumo> lista = new ArrayList<>();
    totalregistros = 0;
    totalconsumo = 0.0;

    if (idreserva == null || idreserva.trim().isEmpty()) {
        // No se hace la consulta si no hay idreserva válido
        JOptionPane.showMessageDialog(null, "Error: ID de reserva vacío");
        return lista;
    }

    String sql = "SELECT c.idconsumo, c.idreserva, c.idproducto, p.nombre, c.cantidad, c.precio_venta, c.estado "
               + "FROM consumo c INNER JOIN producto p ON c.idproducto = p.idproducto "
               + "WHERE c.idreserva = ? ORDER BY c.idconsumo DESC";

    try {
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(idreserva));
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

            lista.add(c);
            totalregistros++;
            totalconsumo += c.getCantidad() * c.getPrecio_venta();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    return lista;
}

    
}

