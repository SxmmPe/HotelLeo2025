/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.conexion;
import Modelo.reserva;
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
public class ReservaDAOImpl implements ReservaDAO {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    @Override
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Idhabitacion", "Numero", "idcliente", "Cliente", "idtrabajador", "Trabajador", "Tipo Reserva", "Fecha Reserva", "Fecha Ingreso", "Fecha Salida", "Costo", "Estado"};

        String[] registro = new String[13];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select r.idreserva,r.idhabitacion,h.numero,r.idcliente,"
                + "(select nombre from persona where idpersona=r.idcliente)as clienten,"
                + "(select apaterno from persona where idpersona=r.idcliente)as clienteap,"
                + "r.idtrabajador,(select nombre from persona where idpersona=r.idtrabajador)as trabajadorn,"
                + "(select apaterno from persona where idpersona=r.idtrabajador)as trabajadorap,"
                + "r.tipo_reserva,r.fecha_reserva,r.fec"
                + "ha_ingresa,r.fecha_salida,"
                + "r.costo_alojamiento,r.estado from reserva r inner join habitacion h on r.idhabitacion=h.idhabitacion where r.fecha_reserva like '%" + buscar + "%' order by idreserva desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idreserva");
                registro[1] = rs.getString("idhabitacion");
                registro[2] = rs.getString("numero");
                registro[3] = rs.getString("idcliente");
                registro[4] = rs.getString("clienten") + " " + rs.getString("clienteap");
                registro[5] = rs.getString("idtrabajador");
                registro[6] = rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
                registro[7] = rs.getString("tipo_reserva");
                registro[8] = rs.getString("fecha_reserva");
                registro[9] = rs.getString("fecha_ingresa");
                registro[10] = rs.getString("fecha_salida");
                registro[11] = rs.getString("costo_alojamiento");
                registro[12] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(reserva dts) {
        sSQL = "INSERT INTO reserva (idhabitacion, idcliente, idtrabajador, tipo_reserva, fecha_reserva, fecha_ingresa, fecha_salida, costo_alojamiento, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getFecha_reserva());
            pst.setDate(6, dts.getFecha_ingresa());
            pst.setDate(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCosto_alojamiento());
            pst.setString(9, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                
                cambiarEstadoHabitacion(dts.getIdhabitacion(), "Ocupado");
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

@Override
public boolean editar(reserva dts) {
    sSQL = "UPDATE reserva SET idhabitacion=?, idcliente=?, idtrabajador=?, tipo_reserva=?, fecha_reserva=?, fecha_ingresa=?, fecha_salida=?, costo_alojamiento=?, estado=?"
            + " WHERE idreserva=?";

    try {
        // Obtener idHabitacion anterior desde la BD
        int idHabitacionAnterior = obtenerIdHabitacionAnterior(dts.getIdreserva());

        PreparedStatement pst = cn.prepareStatement(sSQL);
        pst.setInt(1, dts.getIdhabitacion());
        pst.setInt(2, dts.getIdcliente());
        pst.setInt(3, dts.getIdtrabajador());
        pst.setString(4, dts.getTipo_reserva());
        pst.setDate(5, dts.getFecha_reserva());
        pst.setDate(6, dts.getFecha_ingresa());
        pst.setDate(7, dts.getFecha_salida());
        pst.setDouble(8, dts.getCosto_alojamiento());
        pst.setString(9, dts.getEstado());
        pst.setInt(10, dts.getIdreserva());

        int n = pst.executeUpdate();

        if (n != 0) {
            // Si la habitación fue cambiada, actualizar estados
            if (idHabitacionAnterior != dts.getIdhabitacion()) {
                cambiarEstadoHabitacion(idHabitacionAnterior, "Disponible");
                cambiarEstadoHabitacion(dts.getIdhabitacion(), "Ocupado");
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


    public boolean pagar(reserva dts) {
        sSQL = "update reserva set estado='Pagada'"
                + " where idreserva=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdreserva());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(reserva dts) {
        sSQL = "DELETE FROM reserva WHERE idreserva=?";

        try {
 
            cambiarEstadoHabitacion(dts.getIdhabitacion(), "Disponible");

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdreserva());

            int n = pst.executeUpdate();

            return n != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean cambiarEstadoHabitacion(int idhabitacion, String nuevoEstado) {
        String sql = "UPDATE habitacion SET estado = ? WHERE idhabitacion = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idhabitacion);

            int n = pst.executeUpdate();
            pst.close();

            return n != 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado de la habitación: " + e.getMessage());
            return false;
        }
    }
    public int obtenerIdHabitacionAnterior(int idreserva) {
    try {
        String sql = "SELECT idhabitacion FROM reserva WHERE idreserva=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, idreserva);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("idhabitacion");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error obteniendo habitación anterior: " + e);
    }
    return -1;
}


 

}
