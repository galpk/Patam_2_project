package view.bottom;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class Bottom extends AnchorPane {
    @FXML public static TextField TimeCounter;
    @FXML public Button Play;
    @FXML public Button Stop;
    @FXML public Button Pause;
    @FXML public Button FastRewind;
    @FXML public Button FastForward;
    @FXML public Button next;
    @FXML public Button back;
    @FXML public MenuButton PlaySpeed;
    @FXML public Slider BottomSlider;
    public BottomController BottomControler;
    public Bottom() {
        super();

        FXMLLoader fxlBottom = new FXMLLoader();
        AnchorPane Bottom = null;
        try {
            Bottom = fxlBottom.load(getClass().getResource("bottom.fxml").openStream());
            BottomControler = fxlBottom.getController();
            this.Play = BottomControler.Play;
            this.Stop = BottomControler.Stop;
            this.Pause = BottomControler.Pause;
            this.back = BottomControler.back;
            this.FastForward = BottomControler.FastForward;
            this.FastRewind = BottomControler.FastRewind;
            this.next = BottomControler.next;
            this.PlaySpeed = BottomControler.PlaySpeed;

            this.getChildren().add(Bottom);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
