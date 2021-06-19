package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import test.TimeSeries;
import java.beans.XMLDecoder;
import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Model extends Observable {

    Timer t = null;
    IntegerProperty timeStep;
    public StringProperty newpathM;
    private TimeSeries tm;
    Settings settingM;
    Runnable r;
    float Alt;
    float Speed;
    float Dire;
    float Roll;

    public void setAlt(float alt) {
        Alt = alt;
        this.setChanged();
        this.notifyObservers("Alt");

    }

    public void setSpeed(float speed) {
        Speed = speed;
        this.setChanged();
        this.notifyObservers("Speed");
    }

    public void setDire(float dire) {
        Dire = dire;
        this.setChanged();
        this.notifyObservers("Dire");
    }

    public void setRoll(float roll) {
        Roll = roll;
        this.setChanged();
        this.notifyObservers("Roll");
    }

    public void setPitch(float pitch) {
        Pitch = pitch;
        this.setChanged();
        this.notifyObservers("Pitch");
    }

    public void setYaw(float yaw) {
        Yaw = yaw;
        this.setChanged();
        this.notifyObservers("Yaw");
    }

    float Pitch;
    float Yaw;
    Thread tr;

    public float getAlt() {
        return Alt;
    }

    public float getSpeed() {
        return Speed;
    }

    public float getDire() {
        return Dire;
    }

    public float getRoll() {
        return Roll;
    }

    public float getPitch() {
        return Pitch;
    }

    public float getYaw() {
        return Yaw;
    }



    public Model() throws FileNotFoundException {
        settingM = new Settings();
        XMLDecoder xmlen = new XMLDecoder(new FileInputStream("newxml.xml"));
        settingM = (Settings) xmlen.readObject();
        xmlen.close();
    }

    public void setTm(String path) {
        this.tm = new TimeSeries(path);
    }

    public Model(IntegerProperty timeStep){
        this.timeStep = timeStep;
    }

    public void run(){
        tr =new Thread(()->this.play());
        tr.start();
    }
    public void play() {

        String line = "";
        Socket fg = null;

        try {
            fg = new Socket(settingM.getIp(), settingM.getPort());
            System.out.println(settingM.getIp() +" "+ settingM.port);
            PrintWriter out = new PrintWriter(fg.getOutputStream());
            for (int i = 0; i <tm.getDtable().get(0).Vlist.size(); i++) {
                int j =0;
                for (j = 0; j <tm.getDtable().size() ; j++) {
                    line = line + tm.getDtable().get(j).Vlist.get(i).toString() + ",";

                }
                this.setAlt(tm.getDtable().get(settingM.MyMap.get("altimeter_indicated-altitude-ft")).Vlist.get(i));
                this.setSpeed(tm.getDtable().get(settingM.MyMap.get("airspeed-indicator_indicated-speed-kt")).Vlist.get(i));
                this.setDire(tm.getDtable().get(settingM.MyMap.get("indicated-heading-deg")).Vlist.get(i));
                this.setRoll(tm.getDtable().get(settingM.MyMap.get("attitude-indicator_indicated-roll-deg")).Vlist.get(i));
                this.setPitch(tm.getDtable().get(settingM.MyMap.get("attitude-indicator_internal-pitch-deg")).Vlist.get(i));
                this.setYaw(tm.getDtable().get(settingM.MyMap.get("side-slip-deg")).Vlist.get(i));

                out.println(line);
                out.flush();
                line = "";
                Thread.sleep(settingM.getRate());
            }
            fg.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause(){

        if (t!= null){
            t.cancel();
            t=null;
        }


    }
    public void stop(){
        if (t != null) {
            t.cancel();
            t = null;
            timeStep.set(0);
        }
        timeStep.set(0);
    }
    public void FastRewind(){
        timeStep.set(timeStep.getValue()-15);

    }
    public void FastForward(){
        timeStep.set(timeStep.getValue()+15);

    }
    public void next(){


    }
    public void back(){


    }


    private int throttle;

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public void Read(){
        throttle=7;
        setChanged();
        notifyObservers();

    }

}
