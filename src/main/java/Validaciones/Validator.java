/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import java.util.regex.Pattern;

/**
 *
 * @author Lenovo LOQ
 */
public class Validator {
    
    
    private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");

    private static final Pattern PATRON_ENTERO = Pattern.compile("^\\d+$");

    private static final Pattern PATRON_PRECIO = Pattern.compile("^\\d+(\\.\\d{1,2})?$");

    private static final Pattern PATRON_DNI = Pattern.compile("^\\d{8}$");

    private static final Pattern PATRON_EMAIL = Pattern.compile("^[\\w.-]+@((gmail\\.com)|(outlook\\.com))$");

    private static final Pattern PATRON_TELEFONO = Pattern.compile("^\\d{9}$");


    public static boolean validarNombre(String nombre) {
        return nombre != null && PATRON_NOMBRE.matcher(nombre).matches();
    }

    public static boolean validarEntero(String numero) {
        return numero != null && PATRON_ENTERO.matcher(numero).matches();
    }

    public static boolean validarPrecio(String precio) {
        return precio != null && PATRON_PRECIO.matcher(precio).matches();
    }

    public static boolean validarDNI(String dni) {
        return dni != null && PATRON_DNI.matcher(dni).matches();
    }

    public static boolean validarEmail(String email) {
        return email != null && PATRON_EMAIL.matcher(email).matches();
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && PATRON_TELEFONO.matcher(telefono).matches();
    }

    
    
    
    
}
