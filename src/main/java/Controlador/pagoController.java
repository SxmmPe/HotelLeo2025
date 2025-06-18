package Controlador;

import DAO.PagoDAO;
import DAO.PagoDAOImpl;
import Modelo.habitacion;
import Modelo.pago;
import Modelo.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class pagoController {
    
   private final PagoDAO pagoDAO = new PagoDAOImpl();
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo = pagoDAO.mostrar(buscar);
        if (modelo != null) {
            totalregistros = modelo.getRowCount();
        } else {
            totalregistros = 0;
        }
        return modelo;
    }

    public boolean insertar(pago dts) {
        return pagoDAO.insertar(dts);
    }

    public boolean editar(pago dts) {
        return pagoDAO.editar(dts);
    }

    public boolean eliminar(pago dts) {
        return pagoDAO.eliminar(dts);
    }
    
    
    
    
}
