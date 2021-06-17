package view.panelclocks;


import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PanelClocks extends AnchorPane {
     public FloatProperty  Altitude, Speed, Direction, Roll, Pitch, Yaw;

    public PanelClocks(){
        super();
        try{
            FXMLLoader fxlPanelClocks=new FXMLLoader();
            AnchorPane PanelClocks=fxlPanelClocks.load(getClass().getResource("panelClocks.fxml").openStream());

            PanelClocksController PanelClocksController= fxlPanelClocks.getController();

            Altitude = new SimpleFloatProperty();
            Speed =new SimpleFloatProperty();
            Direction =new SimpleFloatProperty();
            Roll =new SimpleFloatProperty();
            Pitch =new SimpleFloatProperty();
            Yaw = new SimpleFloatProperty();

            PanelClocksController.Altitude.textProperty().bind(Altitude.asString());
            PanelClocksController.Speed.textProperty().bind(Speed.asString());
            PanelClocksController.Direction.textProperty().bind(Direction.asString());
            PanelClocksController.Roll.textProperty().bind(Roll.asString());
            PanelClocksController.Pitch.textProperty().bind(Pitch.asString());
            PanelClocksController.Yaw.textProperty().bind(Yaw.asString());
            this.getChildren().add(PanelClocks);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
