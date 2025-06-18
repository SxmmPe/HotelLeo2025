package Conexion;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Base64;
import java.util.Properties;

public class conexion {

    private String dbEncrypted;
    private String urlEncrypted;
    private String userEncrypted;
    private String passPlain; 

    private final String secretKey = "0000000011111111";  
    private final String initVector = "012345678abcdefg";   

    public conexion() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db_config.properties")) {
            if (input == null) {
                System.out.println("No se encontró el archivo db_config.properties");
                return;
            }
            Properties prop = new Properties();
            prop.load(input);

            dbEncrypted = prop.getProperty("db");
            urlEncrypted = prop.getProperty("url");
            userEncrypted = prop.getProperty("user");
            passPlain = prop.getProperty("pass");  

        } catch (Exception ex) {
            System.out.println("Error leyendo archivo properties: " + ex);
        }
    }

    private String decrypt(String strToDecrypt) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decoded = Base64.getDecoder().decode(strToDecrypt);
            byte[] original = cipher.doFinal(decoded);

            return new String(original, "UTF-8");
        } catch (Exception ex) {
            System.out.println("Error al descifrar: " + ex.toString());
        }
        return null;
    }

    public Connection conectar() {
        Connection link = null;
        try {
            String db = decrypt(dbEncrypted);
            String urlBase = decrypt(urlEncrypted);
            String user = decrypt(userEncrypted);
            String pass = passPlain == null ? "" : passPlain;  

            if (db == null || urlBase == null || user == null) {
                System.out.println("Error al descifrar uno o más parámetros");
                return null;
            }
            if (!urlBase.endsWith("/")) {
                urlBase += "/";
            }

            String urlCompleta = urlBase + db;

            System.out.println("DB descifrada: " + db);
            System.out.println("URL completa: " + urlCompleta);
            System.out.println("Usuario descifrado: " + user);
            System.out.println("Contraseña: '" + pass + "'");

            link = DriverManager.getConnection(urlCompleta, user, pass);

        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
        return link;
    }

    public static void main(String[] args) {
        conexion con = new conexion();
        Connection c = con.conectar();
        if (c != null) {
            System.out.println("Conexión exitosa.");
        } else {
            System.out.println("Fallo al conectar.");
        }
    }
}
