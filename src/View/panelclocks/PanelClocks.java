package View.panelclocks;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PanelClocks extends AnchorPane {
    @FXML public TextField Altitude;
    @FXML public TextField Speed;
    @FXML public TextField Direction;
    @FXML public TextField Roll;
    @FXML public TextField Pitch;
    @FXML public TextField Yaw;

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
    }
}
