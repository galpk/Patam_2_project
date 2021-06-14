package view.panelclocks;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PanelClocks extends AnchorPane {
    @FXML public static TextField Altitude;
    @FXML public static TextField Speed;
    @FXML public static TextField Direction;
    @FXML public static TextField Roll;
    @FXML public static TextField Pitch;
    @FXML public static TextField Yaw;

    public PanelClocks(){
        super();
        try{
            FXMLLoader fxlPanelClocks=new FXMLLoader();
            AnchorPane PanelClocks=fxlPanelClocks.load(getClass().getResource("panelClocks.fxml").openStream());

            PanelClocksController PanelClocksController= fxlPanelClocks.getController();

            Altitude = PanelClocksController.Altitude;
            Speed = PanelClocksController.Speed;
            Direction = PanelClocksController.Direction;
            Roll = PanelClocksController.Roll;
            Pitch = PanelClocksController.Pitch;
            Yaw = PanelClocksController.Yaw;


            this.getChildren().add(PanelClocks);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Altitude.setText("altimeter: 0.0");
        Speed.setText("airspeed: 0.0");
        Direction.setText("direction: 0.0");
        Pitch.setText("pitch: 0.0");
        Roll.setText("roll: 0.0");
        Yaw.setText("yaw: 0.0");
    }
}
