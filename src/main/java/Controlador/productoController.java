package Controlador;

import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import Modelo.habitacion;
import Modelo.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;



public class productoController {
    
    private final ProductoDAO productoDAO= new ProductoDAOImpl();
    public int totalregistros;
    
      public List<producto> mostrar(String buscar) {
        return productoDAO.mostrar(buscar);
    } 
        public boolean insertar(producto dts) {
        return productoDAO.insertar(dts);
    }

    public boolean editar(producto dts) {
        return productoDAO.editar(dts);
    }

    public boolean eliminar(producto dts) {
        return productoDAO.eliminar(dts);
    }
}
