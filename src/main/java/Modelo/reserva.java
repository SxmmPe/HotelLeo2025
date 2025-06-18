
package Modelo;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class reserva {
    private int idreserva;
    private int idhabitacion;
    private int idcliente;
    private int idtrabajador;
    private String tipo_reserva;
    private Date fecha_reserva;
    private Date fecha_ingresa;
    private Date fecha_salida;
    private Double costo_alojamiento;
    private String estado;
 private habitacion habitacion;
    public reserva() {
    }

    public reserva(int idreserva, int idhabitacion, int idcliente, int idtrabajador, String tipo_reserva, Date fecha_reserva, Date fecha_ingresa, Date fecha_salida, Double costo_alojamiento, String estado, habitacion habitacion) {
        this.idreserva = idreserva;
        this.idhabitacion = idhabitacion;
        this.idcliente = idcliente;
        this.idtrabajador = idtrabajador;
        this.tipo_reserva = tipo_reserva;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ingresa = fecha_ingresa;
        this.fecha_salida = fecha_salida;
        this.costo_alojamiento = costo_alojamiento;
        this.estado = estado;
        this.habitacion = habitacion;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public String getTipo_reserva() {
        return tipo_reserva;
    }

    public void setTipo_reserva(String tipo_reserva) {
        this.tipo_reserva = tipo_reserva;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_ingresa() {
        return fecha_ingresa;
    }

    public void setFecha_ingresa(Date fecha_ingresa) {
        this.fecha_ingresa = fecha_ingresa;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Double getCosto_alojamiento() {
        return costo_alojamiento;
    }

    public void setCosto_alojamiento(Double costo_alojamiento) {
        this.costo_alojamiento = costo_alojamiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
  public void calcularCostoAlojamiento() {
        if (this.fecha_ingresa != null && this.fecha_salida != null && habitacion != null) {
            // Calcular la cantidad de días de estadía
            long diffInMillies = Math.abs(fecha_salida.getTime() - fecha_ingresa.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

          
            Double precioHabitacion = habitacion.getPrecio_diario();

    
            this.costo_alojamiento = precioHabitacion * diffInDays;
        } else {
            this.costo_alojamiento = 0.0;
        }
    }
    
}
