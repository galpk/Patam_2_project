package View.bottom;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Bottom extends AnchorPane {

    public final BottomController bottomC;

    public Bottom(){
        super();
        FXMLLoader fxlBottom=new FXMLLoader();
        AnchorPane Bottom = null;
        try{
            Bottom=fxlBottom.load(getClass().getResource("bottom.fxml").openStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if(Bottom!=null){
            bottomC = fxlBottom.getController();
            this.getChildren().add(Bottom);
        }else
            bottomC = null;
    }
}
