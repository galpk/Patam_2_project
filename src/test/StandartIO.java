package test;
import test.Commands.DefaultIO;

public class StandartIO implements DefaultIO {

    @Override
    public String readText() {
        return null;
    }

    @Override
    public void write(String text) {
        System.out.println(text);
    }

    @Override
    public float readVal() {
        return 0;
    }

    @Override
    public void write(float val) {

    }
}
