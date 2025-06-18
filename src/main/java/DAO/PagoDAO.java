/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.pago;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Samir
 */
public interface PagoDAO {
     DefaultTableModel mostrar(String buscar);

    boolean insertar(pago cliente);

    boolean editar(pago cliente);

    boolean eliminar(pago cliente);
}
