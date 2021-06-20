package view.joystick;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import java.io.IOException;

public class Joystick extends AnchorPane {
//    @FXML public Slider sliderJoyX;
//    @FXML public Slider sliderJoyY;
//     @FXML public Canvas joyStick;
    public FloatProperty Rudder,throttle,joyStick;
    public Joystick(){
        super();
        try{
            FXMLLoader fxlJoystick=new FXMLLoader();
            AnchorPane Joystick=fxlJoystick.load(getClass().getResource("joystick.fxml").openStream());

            JoystickController JoystickController= fxlJoystick.getController();

//            sliderJoyX=JoystickController.sliderJoyX;
//            sliderJoyY=JoystickController.sliderJoyY;
//            joyStick=JoystickController.joyStick;
//            sliderJoyX.setValue(JoystickController.sliderJoyX.getValue());
//            sliderJoyY.setValue(JoystickController.sliderJoyY.getValue());
//            joyStick.setHeight(JoystickController.joyStick.getHeight());
//            joyStick.setWidth(JoystickController.joyStick.getWidth());
            JoystickController.paint();

            Rudder = new SimpleFloatProperty();
            throttle = new SimpleFloatProperty();
            joyStick = new SimpleFloatProperty();


//            JoystickController.Rudder.setValue(Rudder.getValue());
//            JoystickController.throttle.setValue(throttle.getValue());
//            JoystickController.joyStick.setWidth();
//            JoystickController.joyStick.setHeight();
            this.getChildren().add(Joystick);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
