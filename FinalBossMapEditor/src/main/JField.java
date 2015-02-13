package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ChrisMoscoso
 */
public class JField extends JPanel{    
    private ObservableInt oValue;
    private JLabel label;
    private JButton decrement , increment;
    
    public JField(String title, int value, Observer o){
        this.setBackground(Color.white);
        
        //Create observableInt and add observer
        this.oValue = new ObservableInt(title, value);
        oValue.addObserver(o);
        
        //Create the buttons and label
        this.add(new JLabel(title));
        decrement = new JButton("-");
        this.add(decrement);
        label = new JLabel(""+value);
        this.add(label);
        increment = new JButton("+");
        this.add(increment);
        
        //Add Listeners
        decrement.addActionListener(new ButtonClickListener());
        increment.addActionListener(new ButtonClickListener());
    }
    
    public int getValue() {
        return oValue.getValue();
    }

    public void setValue(int value) {
        this.oValue.setValue(value);
        this.label.setText(""+value);
    }
    
    private class ButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(increment)){
                setValue(getValue()+1);
            }else if(e.getSource().equals(decrement)){
                setValue(getValue()-1);
            }
        }
    }
}
