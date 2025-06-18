/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.consumo;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samir
 */
public interface ConsumoDAO {
    List<consumo> mostrar(String buscar);

    boolean insertar(consumo cliente);

    boolean editar(consumo cliente);

    boolean eliminar(consumo cliente);
}
