package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.Model;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ViewModel extends Observable implements Observer {
    public IntegerProperty timeStep;
    public StringProperty newpathVM , clock;
    Model model;
    private HashMap<String, DoubleProperty> displayVariables;
    public FloatProperty Alt, Speed , Dire,Roll, Pitch,Yaw;
    public FloatProperty Rudder , throttle, joyStick;
    //public final Runnable play,pause,stop,fastrewind,fastforward,next,back;




    public ViewModel(Model m){
        newpathVM = new SimpleStringProperty();
        this.timeStep = new SimpleIntegerProperty(0);
        this.model = m;
        m.addObserver(this);
        newpathVM.addListener((o,ov,nv)->this.model.setTm(newpathVM.getValue()));

        displayVariables = new HashMap<String, DoubleProperty>();

        Alt = new SimpleFloatProperty();
        Speed =new SimpleFloatProperty();
        Dire =new SimpleFloatProperty();
        Roll =new SimpleFloatProperty();
        Pitch =new SimpleFloatProperty();
        Yaw = new SimpleFloatProperty();
        Rudder= new SimpleFloatProperty();
        throttle= new SimpleFloatProperty();
        joyStick= new SimpleFloatProperty();
       //read from file minute 50 in last class

        displayVariables.put("alt",new SimpleDoubleProperty());

        timeStep.addListener((ob,old,nw)->{

            Platform.runLater(()->clock.set(model.clock));
        });
        clock = new SimpleStringProperty();
       /*play=()->m.play();
        pause=()->m.pause();
        stop=()->m.stop();
        fastrewind=()->m.FastRewind();
        fastforward=()->m.FastForward();
        next=()->m.next();
        back=()->m.back();*/
    }

    public void play(){
        model.run();
    }
    public void stop(){
        model.stop();
    }
//    public void pause(){
//        model.pause();
//    }
    public DoubleProperty getProperty(String name){
        return displayVariables.get(name);
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

               // this.throttle.setValue(model.getThrottle());
            });
        }
    }




}
