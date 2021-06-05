package Model;

import java.util.Observable;

public class model extends Observable {
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
