package Controlador;

import DAO.ReservaDAO;
import DAO.ReservaDAOImpl;
import Modelo.habitacion;
import Modelo.producto;
import Modelo.reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class reservaController {
    
  private final ReservaDAO reservaDAO = new ReservaDAOImpl();
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo = reservaDAO.mostrar(buscar);
        if (modelo != null) {
            totalregistros = modelo.getRowCount();
        } else {
            totalregistros = 0;
        }
        return modelo;
    }

    public boolean insertar(reserva dts) {
        return reservaDAO.insertar(dts);
    }

    public boolean editar(reserva dts) {
        return reservaDAO.editar(dts);
    }

    public boolean eliminar(reserva dts) {
        return reservaDAO.eliminar(dts);
    }
    
     public boolean pagar(reserva dts) {
        return reservaDAO.pagar(dts);
    }
    
}
