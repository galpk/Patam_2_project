package view.joystick;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import java.io.IOException;

public class Joystick extends AnchorPane {
    public IntegerProperty sliderJoyY;
     @FXML public Slider sliderJoyX;
     @FXML public Canvas joyStick;

    public Joystick(){
        super();
        try{
            FXMLLoader fxlJoystick=new FXMLLoader();
            AnchorPane Joystick=fxlJoystick.load(getClass().getResource("joystick.fxml").openStream());

            JoystickController JoystickController= fxlJoystick.getController();
            JoystickController.paint();

            sliderJoyY=new SimpleIntegerProperty();
            sliderJoyX=JoystickController.sliderJoyX;
            joyStick=JoystickController.joyStick;


            this.getChildren().add(Joystick);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
