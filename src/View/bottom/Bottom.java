package View.bottom;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Bottom extends AnchorPane {


    public Bottom(){
        super();
        try{
            FXMLLoader fxlBottom=new FXMLLoader();
            AnchorPane Bottom=fxlBottom.load(getClass().getResource("bottom.fxml").openStream());

            BottomController BottomController= fxlBottom.getController();

            this.getChildren().add(Bottom);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
