/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.conexion;
import Modelo.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Samir
 */
public class ProductoDAOImpl implements ProductoDAO{
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   


  @Override
public List<producto> mostrar(String buscar) {
    List<producto> lista = new ArrayList<>();
    
    sSQL = "select * from producto where nombre like '%" + buscar + "%' order by idproducto desc";
    
    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sSQL);
        
        while (rs.next()) {
            producto prod = new producto();
            prod.setIdproducto(rs.getInt("idproducto"));
            prod.setNombre(rs.getString("nombre"));
            prod.setDescripcion(rs.getString("descripcion"));
            prod.setUnidad_medida(rs.getString("unidad_medida"));
            prod.setPrecio_venta(rs.getDouble("precio_venta"));
            prod.setStock(rs.getInt("stock"));
            
            lista.add(prod);
        }
        
        return lista;
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return null;
    }
}
   
   @Override
   public boolean insertar (producto dts){
       sSQL="insert into producto (nombre,descripcion,unidad_medida,precio_venta,stock)" +
               "values (?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUnidad_medida());
           pst.setDouble(4, dts.getPrecio_venta());
             pst.setDouble(5, dts.getStock());
           
           
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
   public boolean editar (producto dts){
       sSQL="update producto set nombre=?,descripcion=?,unidad_medida=?,precio_venta=?,stock=?"+
               " where idproducto=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUnidad_medida());
           pst.setDouble(4, dts.getPrecio_venta());
            pst.setDouble(5, dts.getStock());
            
           pst.setInt(6, dts.getIdproducto());
           
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
   public boolean eliminar (producto dts){
       sSQL="delete from producto where idproducto=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdproducto());
           
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
   
   
   public List<producto> listarProductosBajoStock() {
    List<producto> productos = new ArrayList<>();
    sSQL = "SELECT * FROM producto WHERE stock < 4";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sSQL);

        while (rs.next()) {
            producto prod = new producto();
            prod.setIdproducto(rs.getInt("idproducto"));
            prod.setNombre(rs.getString("nombre"));
            prod.setDescripcion(rs.getString("descripcion"));
            prod.setUnidad_medida(rs.getString("unidad_medida"));
            prod.setPrecio_venta(rs.getDouble("precio_venta"));
            prod.setStock(rs.getInt("stock"));
            productos.add(prod);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }

    return productos;
}


}
