package shop_management.Controller;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton button;
    private String label;
    private int currentRow;
    private JTable table;

    public ButtonEditor(JTable table, String label, ActionListener actionListener) {
        this.table = table;
        this.label = label;
        button = new JButton(label);
        button.addActionListener(actionListener);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    public int getCurrentRow() {
        return currentRow;
    }
}


