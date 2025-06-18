package Controlador;

import DAO.HabitacionDAO;
import DAO.HabitacionDAOImpl;
import Modelo.habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class habitacionController {
    
 private final HabitacionDAO habitacionDAO = new HabitacionDAOImpl();
    public int totalregistros;
    
      public List<habitacion> mostrar(String buscar) {
        return habitacionDAO.mostrar(buscar);
    }


    public List<habitacion> mostrarvista(String buscar) {
        return habitacionDAO.mostrarvista(buscar);
    }
    public List<habitacion> mostrarPorEstado(String estado) {
    return habitacionDAO.buscarPorEstado(estado);
}


    public boolean insertar(habitacion dts) {
        return habitacionDAO.insertar(dts);
    }

    public boolean editar(habitacion dts) {
        return habitacionDAO.editar(dts);
    }

    public boolean desocupar(habitacion idhabitacion) {
        return habitacionDAO.desocupar(idhabitacion);
    }

    public boolean ocupar(habitacion idhabitacion) {
        return habitacionDAO.ocupar(idhabitacion);
    }

    public boolean eliminar(habitacion idhabitacion) {
        return habitacionDAO.eliminar(idhabitacion);
    }

    public habitacion buscarPorId(int idhabitacion) {
        return habitacionDAO.buscarPorId(idhabitacion);
    }

      public int getTotalRegistros() {
        return totalregistros;
    }
    
    
}
