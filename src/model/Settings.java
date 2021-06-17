package model;

import java.io.Serializable;
import java.util.HashMap;

public class Settings implements Serializable {
    int port ;
    String ip;
    int rate;
    HashMap<String , Integer > MyMap;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public HashMap<String, Integer> getMyMap() {
        return MyMap;
    }

    public void setMyMap(HashMap<String, Integer> myMap) {
        MyMap = myMap;
    }

    public Settings() {

    }

    public void NewSettings(){
        port = 5400;
        ip = "localhost";
        rate = 100;
        MyMap = new HashMap<>();
        MyMap.put("aileron",1);
        MyMap.put("elevator",2);
        MyMap.put("rudder",3);
        MyMap.put("throttle",7);
        MyMap.put("side-slip-deg",21);
        MyMap.put("airspeed-indicator_indicated-speed-kt",25);
        MyMap.put("altimeter_indicated-altitude-ft",26);
        MyMap.put("attitude-indicator_indicated-roll-deg",29);
        MyMap.put("attitude-indicator_internal-pitch-deg",30);
        MyMap.put("indicated-heading-deg",37);
    }
}
