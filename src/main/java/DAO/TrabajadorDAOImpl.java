/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.conexion;
import Modelo.trabajador;
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
public class TrabajadorDAOImpl implements TrabajadorDAO {
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

   @Override
public List<trabajador> mostrar(String buscar) {
    List<trabajador> lista = new ArrayList<>();

    sSQL = "SELECT p.idpersona, p.nombre, p.apaterno, p.amaterno, p.tipo_documento, p.num_documento, "
            + "p.direccion, p.telefono, p.email, t.sueldo, t.acceso, t.login, t.password, t.estado "
            + "FROM persona p INNER JOIN trabajador t ON p.idpersona = t.idpersona "
            + "WHERE p.num_documento LIKE ? ORDER BY p.idpersona DESC";

    try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
        pst.setString(1, "%" + buscar + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            trabajador t = new trabajador();

           t.setIdpersona(rs.getInt("idpersona")); 
            t.setNombre(rs.getString("nombre"));
            t.setApaterno(rs.getString("apaterno"));
            t.setAmaterno(rs.getString("amaterno"));
            t.setTipo_documento(rs.getString("tipo_documento"));  
            t.setNum_documento(rs.getString("num_documento"));    
            t.setDireccion(rs.getString("direccion"));
            t.setTelefono(rs.getString("telefono"));
            t.setEmail(rs.getString("email"));
            t.setSueldo(rs.getDouble("sueldo"));                 
            t.setAcceso(rs.getString("acceso"));
            t.setLogin(rs.getString("login"));
            t.setPassword(rs.getString("password"));
            t.setEstado(rs.getString("estado"));

            lista.add(t);
        }
        return lista;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar trabajadores: " + e.getMessage());
        return null;
    }
}

    @Override
    public boolean insertar(trabajador dts) {
        sSQL = "insert into persona (nombre,apaterno,amaterno,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into trabajador (idpersona,sueldo,acceso,login,password,estado)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            
            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    @Override
    public boolean editar(trabajador dts) {
        sSQL = "update persona set nombre=?,apaterno=?,amaterno=?,tipo_documento=?,num_documento=?,"
                + " direccion=?,telefono=?,email=? where idpersona=?";
        
        sSQL2 = "update trabajador set sueldo=?,acceso=?,login=?,password=?,estado=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getAmaterno());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdpersona());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    @Override
    public boolean eliminar(trabajador dts) {
        sSQL = "delete from trabajador where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            
            pst.setInt(1, dts.getIdpersona());

            
            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    @Override
    public DefaultTableModel login(String login,String password) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno","Acceso","Login","Clave","Estado"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombre,p.apaterno,p.amaterno,"
                + "t.acceso,t.login,t.password,t.estado from persona p inner join Trabajador t "
                + "on p.idpersona=t.idpersona where t.login='"
                + login + "' and t.password='" + password + "' and t.estado='A'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apaterno");
                registro[3] = rs.getString("amaterno");
                
                registro[4] = rs.getString("acceso");
                registro[5] = rs.getString("login");
                registro[6] = rs.getString("password");
                registro[7] = rs.getString("estado");
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
}
