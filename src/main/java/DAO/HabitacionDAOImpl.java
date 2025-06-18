/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.conexion;
import Modelo.habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samir
 */
public class HabitacionDAOImpl implements HabitacionDAO {
    
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
  

  
  @Override
public List<habitacion> mostrar(String buscar) {
    List<habitacion> lista = new ArrayList<>();
    sSQL = "SELECT * FROM habitacion WHERE piso LIKE ? ORDER BY idhabitacion";

    try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
        pst.setString(1, "%" + buscar + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            habitacion hab = new habitacion();
            hab.setIdhabitacion(rs.getInt("idhabitacion"));
            hab.setNumero(rs.getString("numero"));
            hab.setPiso(rs.getString("piso"));
            hab.setDescripcion(rs.getString("descripcion"));
            hab.setCaracteristicas(rs.getString("caracteristicas"));
            hab.setPrecio_diario(rs.getDouble("precio_diario"));
            hab.setEstado(rs.getString("estado"));
            hab.setTipo_habitacion(rs.getString("tipo_habitacion"));

            lista.add(hab);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar habitaciones: " + e.getMessage());
    }
    return lista;
}

@Override
public List<habitacion> mostrarvista(String buscar) {
    List<habitacion> lista = new ArrayList<>();
    sSQL = "SELECT * FROM habitacion WHERE piso LIKE ? AND estado='Disponible' ORDER BY idhabitacion";

    try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
        pst.setString(1, "%" + buscar + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            habitacion hab = new habitacion();
            hab.setIdhabitacion(rs.getInt("idhabitacion"));
            hab.setNumero(rs.getString("numero"));
            hab.setPiso(rs.getString("piso"));
            hab.setDescripcion(rs.getString("descripcion"));
            hab.setCaracteristicas(rs.getString("caracteristicas"));
            hab.setPrecio_diario(rs.getDouble("precio_diario"));
            hab.setEstado(rs.getString("estado"));
            hab.setTipo_habitacion(rs.getString("tipo_habitacion"));

            lista.add(hab);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar habitaciones disponibles: " + e.getMessage());
    }
    return lista;
}


    @Override
   public boolean insertar (habitacion dts){
       sSQL="insert into habitacion (numero,piso,descripcion,caracteristicas,precio_diario,estado,tipo_habitacion)" +
               "values (?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNumero());
           pst.setString(2, dts.getPiso());
           pst.setString(3, dts.getDescripcion());
           pst.setString(4, dts.getCaracteristicas());
           pst.setDouble(5, dts.getPrecio_diario());
           pst.setString(6, dts.getEstado());
           pst.setString(7, dts.getTipo_habitacion());
           
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
   
    @Override
   public boolean editar (habitacion dts){
       sSQL="update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=?"+
               " where idhabitacion=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNumero());
           pst.setString(2, dts.getPiso());
           pst.setString(3, dts.getDescripcion());
           pst.setString(4, dts.getCaracteristicas());
           pst.setDouble(5, dts.getPrecio_diario());
           pst.setString(6, dts.getEstado());
           pst.setString(7, dts.getTipo_habitacion());
           pst.setInt(8, dts.getIdhabitacion());
           
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
  
   public boolean desocupar (habitacion dts){
       sSQL="update habitacion set estado='Disponible'"+
               " where idhabitacion=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdhabitacion());
           
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
  
   
   public boolean ocupar (habitacion dts){
       sSQL="update habitacion set estado='Ocupado'"+
               " where idhabitacion=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
          
           pst.setInt(1, dts.getIdhabitacion());
           
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
  
   
   
   
   public boolean eliminar (habitacion dts){
       sSQL="delete from habitacion where idhabitacion=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdhabitacion());
           
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
  
 public habitacion buscarPorId(int idhabitacion) {
    habitacion habitacion = null;
    String sql = "SELECT * FROM habitacion WHERE idhabitacion = ?"; 

    try ( 
         PreparedStatement pst = cn.prepareStatement(sql)) {

        pst.setInt(1, idhabitacion);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            habitacion = new habitacion();
            habitacion.setIdhabitacion(rs.getInt("idhabitacion"));
            habitacion.setPrecio_diario(rs.getDouble("precio_diario"));
        }

    } catch (SQLException e) {
        System.err.println("Error al buscar habitación: " + e.getMessage());
    }
    return habitacion;
}
 
 public List<habitacion> buscarPorEstado(String estado) {
    List<habitacion> lista = new ArrayList<>();
    sSQL = "SELECT * FROM habitacion WHERE estado = ? ORDER BY idhabitacion";

    try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
        pst.setString(1, estado);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            habitacion hab = new habitacion();
            hab.setIdhabitacion(rs.getInt("idhabitacion"));
            hab.setNumero(rs.getString("numero"));
            hab.setPiso(rs.getString("piso"));
            hab.setDescripcion(rs.getString("descripcion"));
            hab.setCaracteristicas(rs.getString("caracteristicas"));
            hab.setPrecio_diario(rs.getDouble("precio_diario"));
            hab.setEstado(rs.getString("estado"));
            hab.setTipo_habitacion(rs.getString("tipo_habitacion"));

            lista.add(hab);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar por estado: " + e.getMessage());
    }

    return lista;
}

 
  @Override
    public int contarHabitacionesTotales() {
        int total = 0;
        sSQL = "SELECT COUNT(*) FROM habitacion";
        try {
            PreparedStatement ps = cn.prepareStatement(sSQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Error al contar habitaciones totales: " + e.getMessage());
        }
        return total;
    }

    @Override
    public int contarHabitacionesOcupadas() {
        int ocupadas = 0;
        sSQL = "SELECT COUNT(*) FROM habitacion WHERE estado = 'Ocupado'";
        try {
            PreparedStatement ps = cn.prepareStatement(sSQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ocupadas = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Error al contar habitaciones ocupadas: " + e.getMessage());
        }
        return ocupadas;
    }
    


  
    
}
