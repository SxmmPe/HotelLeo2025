package DAO;

import Modelo.habitacion;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public interface HabitacionDAO {

    List<habitacion> mostrar(String buscar);

    List<habitacion> mostrarvista(String buscar);
    
      List<habitacion> buscarPorEstado(String estado);

    public int contarHabitacionesTotales();

    public int contarHabitacionesOcupadas();

    boolean insertar(habitacion habitacion);

    boolean editar(habitacion habitacion);

    boolean desocupar(habitacion idHabitacion);

    boolean ocupar(habitacion idHabitacion);

    boolean eliminar(habitacion idHabitacion);

    habitacion buscarPorId(int idHabitacion);
}
