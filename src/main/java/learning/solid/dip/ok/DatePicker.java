package learning.solid.dip.ok;

import javax.swing.*;
import java.util.Date;

public interface DatePicker {

    Date getSelectedDate();
    JComponent getComponent();
}
