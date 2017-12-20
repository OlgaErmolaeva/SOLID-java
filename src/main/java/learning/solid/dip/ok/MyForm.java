package learning.solid.dip.ok;

import javax.swing.*;
import java.awt.*;

public class MyForm {
    //@Autowired
    DatePicker datePicker;

    JComponent build() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(datePicker.getComponent());
        JButton button = new JButton("Do something with selected date");
        button.addActionListener(e -> System.out.println(datePicker.getSelectedDate()));
        panel.add(button);

        return panel;
    }
}
