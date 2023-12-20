package model.view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Consulta;

public class ConsultaComboBoxRenderer extends JLabel implements ListCellRenderer<Consulta> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Consulta> list, Consulta consulta, int index,
            boolean isSelected, boolean cellHasFocus) {
        if (consulta != null) {
            setText(consulta.toString()); 
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

    public static void configurarComboBox(JComboBox<Consulta> comboBox) {
        comboBox.setRenderer(new ConsultaComboBoxRenderer());
    }
}
