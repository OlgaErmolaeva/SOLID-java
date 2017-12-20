package learning.solid.dip.bad;

import learning.solid.dip.externallibrary.JDatePicker;

import javax.swing.*;
import java.awt.*;

public class MyForm {

    JComponent build() {
        JPanel panel = new JPanel(new FlowLayout());
        JDatePicker datePicker = new JDatePicker();
        panel.add(datePicker);
        JButton button = new JButton("Do something with selected date");
        button.addActionListener(e -> System.out.println(datePicker.getSelectedDate()));
        panel.add(button);

        return panel;
    }
}
