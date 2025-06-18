package Vistas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderStockProductos extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        try {
            int stock = Integer.parseInt(table.getValueAt(row, 5).toString()); 

            if (stock < 5) {
                c.setBackground(new Color(255, 102, 102)); 
            } else if (stock <= 10) {
                c.setBackground(new Color(255, 255, 153)); 
            } else {
                c.setBackground(new Color(204, 255, 204)); 
            }

        } catch (Exception e) {
            c.setBackground(Color.WHITE); 
        }

        if (isSelected) {
            c.setBackground(new Color(51, 153, 255));
        }

        return c;
    }
}
