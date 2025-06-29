/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import java.util.function.Predicate;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Lenovo LOQ
 */
public class Validator {
    
    private static Predicate<String> getValidador(TipoValidacion tipo) {
        switch (tipo) {
            case NOMBRE:
                return Patrones::validarNombre;
            case ENTERO:
                return Patrones::validarEntero;
            case PRECIO:
                return Patrones::validarPrecio;
            case DNI:
                return Patrones::validarDNI;
            case EMAIL:
                return Patrones::validarEmail;
            case TELEFONO:
                return Patrones::validarTelefono;
            default:
                return s -> true; 
        }
    }

    
    public static void validarEnTiempoReal(JTextField jTextField, JLabel jLabel, String mensajeError, TipoValidacion tipoValidacion) {
        Predicate<String> validador = getValidador(tipoValidacion);

        jLabel.setText("");
        jLabel.setForeground(java.awt.Color.RED);

        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void validar() {
                String texto = jTextField.getText();
                if (texto.isEmpty() || validador.test(texto)) {
                    jLabel.setText("");
                } else {
                    jLabel.setText(mensajeError);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validar();
            }
        });
    }
}
