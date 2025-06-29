/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import java.util.regex.Pattern;

/**
 *
 * @author Lenovo LOQ
 */
public class Patrones {
    
    // Nombre: letras (mayúsculas y minúsculas) y espacios, sin caracteres especiales ni números
    private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");

    // Números enteros (positivos)
    private static final Pattern PATRON_ENTERO = Pattern.compile("^\\d+$");

    // Precio: números con hasta dos decimales (ej: 123, 123.4, 123.45)
    private static final Pattern PATRON_PRECIO = Pattern.compile("^\\d+(\\.\\d{1,2})?$");

    // DNI: exactamente 8 dígitos
    private static final Pattern PATRON_DNI = Pattern.compile("^\\d{8}$");

    // Correo electrónico con dominio gmail.com o outlook.com
    private static final Pattern PATRON_EMAIL = Pattern.compile("^[\\w.-]+@((gmail\\.com)|(outlook\\.com))$");

    // Teléfono: exactamente 9 dígitos
    private static final Pattern PATRON_TELEFONO = Pattern.compile("^\\d{9}$");

    // Métodos públicos para validar

    public static boolean validarNombre(String nombre) {
        return nombre != null && PATRON_NOMBRE.matcher(nombre.trim()).matches();
    }

    public static boolean validarEntero(String numero) {
        return numero != null && PATRON_ENTERO.matcher(numero.trim()).matches();
    }

    public static boolean validarPrecio(String precio) {
        return precio != null && PATRON_PRECIO.matcher(precio.trim()).matches();
    }

    public static boolean validarDNI(String dni) {
        return dni != null && PATRON_DNI.matcher(dni.trim()).matches();
    }

    public static boolean validarEmail(String email) {
        return email != null && PATRON_EMAIL.matcher(email.trim()).matches();
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && PATRON_TELEFONO.matcher(telefono.trim()).matches();
    }
    
    
}
