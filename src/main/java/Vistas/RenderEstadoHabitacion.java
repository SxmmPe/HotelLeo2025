
package Vistas;

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;

class RenderEstadoHabitacion extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
       
        String estado = table.getValueAt(row, 6).toString(); 

        if (estado.equalsIgnoreCase("Disponible")) {
            c.setBackground(new Color(204, 255, 204)); 
        } else if (estado.equalsIgnoreCase("Ocupado")) {
            c.setBackground(new Color(255, 102, 102)); 
        } else if (estado.equalsIgnoreCase("Mantenimiento")) {
            c.setBackground(new Color(255, 255, 153)); 
        } else {
            c.setBackground(Color.WHITE); 
        }

        if (isSelected) {
            c.setBackground(new Color(51, 153, 255)); 
        }

        return c;
    }
}
