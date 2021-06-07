package View.graphs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Graphs extends AnchorPane {

    @FXML public Canvas graphleft;
    @FXML public Canvas graphright;
    
    public Graphs(){
        super();
        try{
            FXMLLoader fxlGraphs=new FXMLLoader();
            AnchorPane Graphs=fxlGraphs.load(getClass().getResource("graphs.fxml").openStream());

            GraphsController GraphsController= fxlGraphs.getController();

            graphleft= GraphsController.graphleft;
            graphright=GraphsController.graphright;

            this.getChildren().add(Graphs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
