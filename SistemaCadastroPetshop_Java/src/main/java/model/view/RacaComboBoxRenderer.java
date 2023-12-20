package model.view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Raca;

public class RacaComboBoxRenderer extends JLabel implements ListCellRenderer<Raca> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Raca> list, Raca raca, int index,
            boolean isSelected, boolean cellHasFocus) {
        if (raca != null) {
            setText(raca.getNome());
        } else {
            setText("");
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setOpaque(true);
        return this; 
    }

    public static void configurarComboBox(JComboBox<Raca> comboBox) {
        comboBox.setRenderer(new RacaComboBoxRenderer());
    }
}
