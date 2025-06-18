package DAO;

import Modelo.cliente;
import java.util.List;


public interface ClienteDAO {

   List<cliente> mostrar(String buscar);
   
 
    boolean insertar(cliente cliente);

    boolean editar(cliente cliente);

    boolean eliminar(cliente cliente);
}
