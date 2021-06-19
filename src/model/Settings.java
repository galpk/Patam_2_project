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
        MyMap.put("aileron",0);
        MyMap.put("elevator",1);
        MyMap.put("rudder",2);
        MyMap.put("throttle",6);
        MyMap.put("side-slip-deg",20);
        MyMap.put("airspeed-indicator_indicated-speed-kt",24);
        MyMap.put("altimeter_indicated-altitude-ft",25);
        MyMap.put("attitude-indicator_indicated-roll-deg",28);
        MyMap.put("attitude-indicator_internal-pitch-deg",29);
        MyMap.put("indicated-heading-deg",36);
    }
}
