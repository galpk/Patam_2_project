package View.joystick;

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
    public IntegerProperty sliderJoy1;
     @FXML public Slider sliderJoy2;
     @FXML public Canvas joyStick;
     @FXML public Circle circleJoy;

    public Joystick(){
        super();
        try{
            FXMLLoader fxlJoystick=new FXMLLoader();
            AnchorPane Joystick=fxlJoystick.load(getClass().getResource("joystick.fxml").openStream());

            JoystickController JoystickController= fxlJoystick.getController();

            sliderJoy1=new SimpleIntegerProperty();
            sliderJoy2=JoystickController.sliderJoy2;
            joyStick=JoystickController.joyStick;
            circleJoy=JoystickController.circleJoy;

            this.getChildren().add(Joystick);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
