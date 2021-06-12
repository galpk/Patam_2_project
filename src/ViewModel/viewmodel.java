package ViewModel;

import Model.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class viewmodel extends Observable implements Observer {
    public IntegerProperty throttle;
    model model;

    public viewmodel(){
        StringProperty CSVPath= new SimpleStringProperty();
        throttle=new SimpleIntegerProperty(0);
    };

    @Override
    public void update(Observable o, Object arg) {
        this.throttle.setValue(model.getThrottle());
    }


    public String[] getHeaders(String args ){
        String[] headers = new String[0];
        String path = args;
        String line ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line =br.readLine())!= null){
                headers = line.split(",");
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }
}
