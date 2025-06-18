package Controlador;

import DAO.TrabajadorDAO;
import DAO.TrabajadorDAOImpl;
import Modelo.cliente;
import Modelo.producto;
import Modelo.trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class trabajadorController {

    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAOImpl();
    public Integer totalregistros;

    public List<trabajador> mostrar(String buscar) {
        List<trabajador> lista = trabajadorDAO.mostrar(buscar);
        if (lista != null) {
            totalregistros = lista.size();
        } else {
            totalregistros = 0;
        }
        return lista;
    }

    public DefaultTableModel login(String login, String password) {
        DefaultTableModel modelo = trabajadorDAO.login(login, password);

        if (modelo != null ) {
           totalregistros = modelo.getRowCount();
        } else {
            totalregistros = 0;
        }
        return modelo;
    }

    public boolean insertar(trabajador dts) {
        return trabajadorDAO.insertar(dts);
    }

    public boolean editar(trabajador dts) {
        return trabajadorDAO.editar(dts);
    }

    public boolean eliminar(trabajador dts) {
        return trabajadorDAO.eliminar(dts);
    }


    }  