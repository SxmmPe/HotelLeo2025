/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.conexion;
import Modelo.pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samir
 */
public class PagoDAOImpl implements PagoDAO{
     private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
     @Override
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Idreserva","Comprobante","Número","Igv","Total","Fecha Emisión","Fecha Pago"};
       
       String [] registro =new String [8];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select * from pago where idreserva="+ buscar + " order by idpago desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idpago");
               registro [1]=rs.getString("idreserva");
               registro [2]=rs.getString("tipo_comprobante");
               registro [3]=rs.getString("num_comprobante");
               registro [4]=rs.getString("igv");
               registro [5]=rs.getString("total_pago");
               registro [6]=rs.getString("fecha_emision");
               registro [7]=rs.getString("fecha_pago");
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
     @Override
   public boolean insertar (pago dts){
       sSQL="insert into pago (idreserva,tipo_comprobante,num_comprobante,igv,total_pago,fecha_emision,fecha_pago)" +
               "values (?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdreserva());
           pst.setString(2, dts.getTipo_comprobante());
           pst.setString(3, dts.getNum_comprobante());
           pst.setDouble(4, dts.getIgv());
           pst.setDouble(5, dts.getTotal_pago());
           pst.setDate(6, dts.getFecha_emision());
           pst.setDate(7, dts.getFecha_pago());
           
           
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
   public boolean editar (pago dts){
       sSQL="update pago set idreserva=?,tipo_comprobante=?,num_comprobante=?,igv=?,total_pago=?,fecha_emision=?,fecha_pago=?"+
               " where idpago=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdreserva());
           pst.setString(2, dts.getTipo_comprobante());
           pst.setString(3, dts.getNum_comprobante());
           pst.setDouble(4, dts.getIgv());
           pst.setDouble(5, dts.getTotal_pago());
           pst.setDate(6, dts.getFecha_emision());
           pst.setDate(7, dts.getFecha_pago());
           
           pst.setInt(8, dts.getIdpago());
           
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
   public boolean eliminar (pago dts){
       sSQL="delete from pago where idpago=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdpago());
           
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
    
}
