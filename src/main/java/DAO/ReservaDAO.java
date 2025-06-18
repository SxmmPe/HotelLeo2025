package DAO;

import Modelo.reserva;
import javax.swing.table.DefaultTableModel;


public interface ReservaDAO {
     DefaultTableModel mostrar(String buscar);

    boolean insertar(reserva cliente);

    boolean editar(reserva cliente);

    boolean eliminar(reserva cliente);
    
    boolean pagar(reserva cliente);
}
