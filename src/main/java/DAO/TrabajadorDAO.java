
package DAO;

import Modelo.trabajador;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samir
 */
public interface TrabajadorDAO {
    List<trabajador> mostrar(String buscar);

    DefaultTableModel login(String login, String password);

    boolean insertar(trabajador cliente);

    boolean editar(trabajador cliente);

    boolean eliminar(trabajador cliente);

   
}
