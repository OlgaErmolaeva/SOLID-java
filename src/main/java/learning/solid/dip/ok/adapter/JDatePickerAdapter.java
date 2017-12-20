package learning.solid.dip.ok.adapter;

import learning.solid.dip.externallibrary.JDatePicker;
import learning.solid.dip.ok.DatePicker;

import javax.swing.*;
import java.util.Date;

//@org.springframework.stereotype.Component
//@org.springframework.context.annotation.Scope("prototype")
public class JDatePickerAdapter implements DatePicker {
    JDatePicker externalImplementation;

    @Override
    public Date getSelectedDate() {
        return externalImplementation.getSelectedDate();
    }

    @Override
    public JComponent getComponent() {
        return externalImplementation;
    }
}
