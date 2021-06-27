package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.Model;
import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer {
    public IntegerProperty timeStep;
    public StringProperty newpathVM , clock;
    Model model;
    public FloatProperty Alt, Speed , Dire,Roll, Pitch,Yaw;
    public DoubleProperty Rudder , throttle,aileron , elevator;

    public ViewModel(Model m){
        newpathVM = new SimpleStringProperty();
        this.timeStep = new SimpleIntegerProperty(0);
        this.model = m;
        m.addObserver(this);
        newpathVM.addListener((o,ov,nv)->this.model.setTm(newpathVM.getValue()));

        Alt = new SimpleFloatProperty();
        Speed =new SimpleFloatProperty();
        Dire =new SimpleFloatProperty();
        Roll =new SimpleFloatProperty();
        Pitch =new SimpleFloatProperty();
        Yaw = new SimpleFloatProperty();
        Rudder= new SimpleDoubleProperty();
        throttle= new SimpleDoubleProperty();
        aileron= new SimpleDoubleProperty();
        elevator = new SimpleDoubleProperty();
        //read from file minute 50 in last class

        timeStep.addListener((ob,old,nw)->{

            Platform.runLater(()->clock.set(model.clock));
        });
        clock = new SimpleStringProperty();

    }
    public void play(){
        model.run();
    }
    public void stop(){
        model.stop();
    }
    public void pause(){
        model.pause();
    }
    public void FastRewind(){
        model.FastRewind();
    }
    public void FastForward(){
        model.FastForward();
    }
    public void next(){
        model.next();
    }
    public void back(){
        model.back();
    }
    public void PlaySpeed(){
        model.PlaySpeed();
    }



    @Override
    public void update(Observable o, Object arg) {
        if(o==this.model) {
            Platform.runLater(()->{
                if(arg!=null&& arg.equals("Alt")) {
                    this.Alt.setValue(model.getAlt());
                }
                if(arg!=null&& arg.equals("Dire")) {
                    this.Dire.setValue(model.getDire());
                }
                if(arg!=null&& arg.equals("Pitch")) {
                    this.Pitch.setValue(model.getPitch());
                }
                if(arg!=null&& arg.equals("Roll")) {
                    this.Roll.setValue(model.getRoll());
                }
                if(arg!=null&& arg.equals("Speed")) {
                    this.Speed.setValue(model.getSpeed());
                }
                if(arg!=null&& arg.equals("Yaw")) {
                    this.Yaw.setValue(model.getYaw());
                }
                this.Rudder.setValue(model.getRudder());
                this.throttle.setValue(model.getThrottle());
                this.aileron.setValue(model.getAileron());
                this.elevator.setValue(model.getElevator());
            });
        }
    }

}
