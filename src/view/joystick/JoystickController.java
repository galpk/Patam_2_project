package view.joystick;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

import java.util.Observable;



public class JoystickController extends Observable {
    @FXML Slider throttle;
    @FXML Slider Rudder;
    @FXML Canvas joyStick;
    boolean mouseClicked;
    double jx,jy;
    double mx , my;

    double aileron , elevator;

    public double getAileron(){
        return aileron;
    }

    public double getElevator(){
        return elevator;
    }

    public void setAileron(double aileron) {
        this.aileron = aileron;
    }

    public void setElevator(double elevator) {
        this.elevator = elevator;
    }

    public JoystickController() {
        mouseClicked = false;
        jx= 0;
        jy = 0;
        aileron = 0;
        elevator = 0;
    }

    void paint() {
        GraphicsContext gc = joyStick.getGraphicsContext2D();
         mx = joyStick.getWidth() / 2;
         my = joyStick.getHeight() / 2;
        gc.clearRect(0,0,joyStick.getWidth(),joyStick.getHeight());
        gc.strokeOval(jx - 50, jy - 50, 100, 100);
        aileron = (jx-mx)/mx;
        elevator = (jy-my)/my;
       setChanged();
       notifyObservers();
        System.out.println(aileron + ","+ elevator);
    }

    public void mouseDown(MouseEvent me) {
        if (!mouseClicked) {
            mouseClicked = true;
            System.out.println("Mouse is down");
        }
    }

    public void mouseUp(MouseEvent me) {
        if (mouseClicked) {
            mouseClicked = false;
            System.out.println("Mouse is up");
            jx= mx;
            jy = my;
            paint();
        }
    }
    public void mouseMove(MouseEvent me) {
        if (mouseClicked) {
            System.out.println("Mouse move" + me.getX() + ","+ me.getY());
            jx= me.getX();
            jy = me.getY();

            paint();
        }
    }


}

