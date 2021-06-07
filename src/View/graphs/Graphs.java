package View.graphs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Graphs extends AnchorPane {

    @FXML public LineChart graphleft;
    @FXML public LineChart graphright;
    @FXML public LineChart graphbottom;
    
    public Graphs(){
        super();
        try{
            FXMLLoader fxlGraphs=new FXMLLoader();
            AnchorPane Graphs=fxlGraphs.load(getClass().getResource("graphs.fxml").openStream());

            GraphsController GraphsController= fxlGraphs.getController();

            graphleft= GraphsController.graphleft;
            graphright=GraphsController.graphright;
            graphbottom= GraphsController.graphbottom;

            this.getChildren().add(Graphs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
