package Controlador;

import DAO.ClienteDAO;
import DAO.ClienteDAOImpl;
import Modelo.cliente;
import Modelo.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class clienteController {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();
    public Integer totalregistros;

   public List<cliente> mostrar(String buscar) {
        List<cliente> lista = clienteDAO.mostrar(buscar);
        totalregistros = (lista != null) ? lista.size() : 0;
        return lista;
    }

    public boolean insertar(cliente dts) {
        return clienteDAO.insertar(dts);
    }

    public boolean editar(cliente dts) {
        return clienteDAO.editar(dts);
    }

    public boolean eliminar(cliente dts) {
        return clienteDAO.eliminar(dts);
    }
}
