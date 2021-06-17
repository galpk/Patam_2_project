package view.bottom;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Bottom extends AnchorPane {
    @FXML public static TextField TimeCounter;
    public StringProperty newpat;
    public  BottomController bottomC;
    @FXML public Button Play;

    public Bottom(){
        super();
        newpat = new SimpleStringProperty();
        FXMLLoader fxlBottom=new FXMLLoader();
        AnchorPane Bottom = null;
        try{
            Bottom=fxlBottom.load(getClass().getResource("bottom.fxml").openStream());
            BottomController BottomControler = fxlBottom.getController();
            TimeCounter = BottomControler.TimeCounter;
            newpat.bind(BottomControler.newPath);
            this.Play = BottomControler.Play;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TimeCounter.setText("00:00:00");
        if(Bottom!=null){
            bottomC = fxlBottom.getController();
            this.getChildren().add(Bottom);
        }else
            bottomC = null;
    }
}
