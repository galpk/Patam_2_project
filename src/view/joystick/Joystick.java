package view.joystick;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import java.io.IOException;

public class Joystick extends AnchorPane {

    public DoubleProperty Rudder,throttle,aileron , elevator;

    public Joystick(){
        super();
        try{
            FXMLLoader fxlJoystick=new FXMLLoader();
           // AnchorPane Joystick=fxlJoystick.load(getClass().getResource("joystick.fxml").openStream());
            Parent root = fxlJoystick.load(getClass().getResource("joystick.fxml").openStream());
            JoystickController JoystickController= fxlJoystick.getController();

            JoystickController.paint();
            Rudder = new SimpleDoubleProperty();
            throttle = new SimpleDoubleProperty();
            aileron = new SimpleDoubleProperty();
            elevator = new SimpleDoubleProperty();


            JoystickController.throttle.valueProperty().bind(throttle);
            JoystickController.Rudder.valueProperty().bind(Rudder);
            JoystickController.elevator.bind(elevator);
            JoystickController.aileron.bind(aileron);
            elevator.addListener((o,ov,nv)-> JoystickController.paint());

            this.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
