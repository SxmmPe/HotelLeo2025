/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.producto;
import java.util.List;


/**
 *
 * @author Samir
 */
public interface ProductoDAO {
    List<producto> mostrar(String buscar);

    boolean insertar(producto cliente);

    boolean editar(producto cliente);

    boolean eliminar(producto cliente);
}
