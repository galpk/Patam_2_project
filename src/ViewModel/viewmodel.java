package ViewModel;

import Model.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Observable;
import java.util.Observer;

public class viewmodel extends Observable implements Observer {
    public IntegerProperty throttle;
    model model;

    public viewmodel() {
        throttle=new SimpleIntegerProperty();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.throttle.setValue(model.getThrottle());
    }
}
