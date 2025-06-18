package Presentacion;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class BackupAutomatico {

    public void iniciarBackupDiario() {
        Timer timer = new Timer();


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 28);
        calendar.set(Calendar.SECOND, 0);

        Date horaEjecucion = calendar.getTime();


        if (horaEjecucion.before(new Date())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            horaEjecucion = calendar.getTime();
        }

        long periodo = 24 * 60 * 60 * 1000; // Cada 24 horas

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                realizarBackup();
            }
        }, horaEjecucion, periodo);
    }

    private void realizarBackup() {
        try {
            File carpeta = new File("C:\\backups");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String fechaHora = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String rutaBackup = "C:\\backups\\hotel_backup_" + fechaHora + ".sql";

            String comando = "mysqldump -u root hotel -r \"" + rutaBackup + "\"";

            Process proceso = Runtime.getRuntime().exec("cmd /c " + comando);
            int resultado = proceso.waitFor();

            if (resultado == 0) {
                String mensaje = "Backup automático creado exitosamente a las " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()) +
                        "\nArchivo: " + rutaBackup;
                System.out.println(mensaje);

                
                JOptionPane.showMessageDialog(null, mensaje, "Backup Diario", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.err.println("Error al realizar el backup automático.");
            }

        } catch (Exception e) {
            System.err.println("Error en backup automático: " + e.getMessage());
        }
    }
}
