package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import test.TimeSeries;
import view.bottom.Bottom;

import java.beans.XMLDecoder;
import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;


public class Model extends Observable {

    Timer t = null;
    IntegerProperty timeStep;
    public String clock = "00 : 00 : 00";
    public StringProperty newpathM;
    private TimeSeries tm;
    Settings settingM;
    ScheduledFuture<?> scheduledFuture = null;
    Thread tr , tr2;
    float Alt, Speed,Dire,Roll,Pitch,Yaw;
    float rudder, throttle,aileron , elevator;



    public float getRudder() {
        return rudder;
    }

    public void setRudder(float rudder) {
        this.rudder = rudder;
    }

    public float getThrottle() {
        return throttle;
    }

    public void setThrottle(float throttle) {
        this.throttle = throttle;
    }

    public float getAileron() {
        return aileron;
    }

    public void setAileron(float aileron) {
        this.aileron = aileron;
    }

    public float getElevator() {
        return elevator;
    }

    public void setElevator(float elevator) {
        this.elevator = elevator;
    }

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


    public void changeTimeStamp(int value) {
        view.Controller.injectTimeStamp(value);
    }
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
    public String getDurationString(int seconds) { //stopwatch func

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    public String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

    public void setTm(String path) {
        this.tm = new TimeSeries(path);
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
                System.out.println(settingM.getIp() + " " + settingM.port);
                PrintWriter out = new PrintWriter(fg.getOutputStream());
                for (int i = 0; i < tm.getDtable().get(0).Vlist.size(); i++) {
                    int j = 0;
                    for (j = 0; j < tm.getDtable().size(); j++) {
                        line = line + tm.getDtable().get(j).Vlist.get(i).toString() + ",";
                        //System.out.println(aileron+ " ,1 " + elevator+ " ,1 " + rudder+ " ,1 " +throttle);
                    }
                    this.setAlt(tm.getDtable().get(settingM.MyMap.get("altimeter_indicated-altitude-ft")).Vlist.get(i));
                    this.setSpeed(tm.getDtable().get(settingM.MyMap.get("airspeed-indicator_indicated-speed-kt")).Vlist.get(i));
                    this.setDire(tm.getDtable().get(settingM.MyMap.get("indicated-heading-deg")).Vlist.get(i));
                    this.setRoll(tm.getDtable().get(settingM.MyMap.get("attitude-indicator_indicated-roll-deg")).Vlist.get(i));
                    this.setPitch(tm.getDtable().get(settingM.MyMap.get("attitude-indicator_internal-pitch-deg")).Vlist.get(i));
                    this.setYaw(tm.getDtable().get(settingM.MyMap.get("side-slip-deg")).Vlist.get(i));
                    this.setThrottle(tm.getDtable().get(settingM.MyMap.get("throttle")).Vlist.get(i));
                    this.setRudder(tm.getDtable().get(settingM.MyMap.get("rudder")).Vlist.get(i));
                    this.setAileron(tm.getDtable().get(settingM.MyMap.get("aileron")).Vlist.get(i));
                    this.setElevator(tm.getDtable().get(settingM.MyMap.get("elevator")).Vlist.get(i));
                    this.setChanged();
                    this.notifyObservers();

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

    public void stop(){
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }

    }

    public void pause(){
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void FastRewind(){
       // timeStep.set(timeStep.getValue()-15);

    }
    public void FastForward(){
        //timeStep.set(timeStep.getValue()+15);

    }
    public void next(){


    }
    public void back(){


    }
    public void PlaySpeed(){


    }
}
