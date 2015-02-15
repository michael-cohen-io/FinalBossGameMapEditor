package panel;

import java.util.Observable;

/**
 *
 * @author ChrisMoscoso
 */
public class ObservableInt extends Observable {
    String title;

    int value;

    public ObservableInt(String title, int value) {
        this.title = title; 
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }
    
    public String getTitle() {
        return title;
    }
}
