package model.view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Animal;

public class AnimalComboBoxRenderer extends JLabel implements ListCellRenderer<Animal> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Animal> list, Animal animal, int index,
            boolean isSelected, boolean cellHasFocus) {
        if (animal != null) {
            setText(animal.getNome()); 
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

    public static void configurarComboBox(JComboBox<Animal> comboBox) {
        comboBox.setRenderer(new AnimalComboBoxRenderer());
    }
}
