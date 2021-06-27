package view.graphs;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.Set;

public class Graphs extends AnchorPane {

    public LineChart graphleft;
    public LineChart graphright;
    public LineChart graphbottom;
    public XYChart.Series series;
    public Graphs(){
        super();
        try{
            FXMLLoader fxlGraphs=new FXMLLoader();
            AnchorPane Graphs=fxlGraphs.load(getClass().getResource("graphs.fxml").openStream());

            GraphsController GraphsController= fxlGraphs.getController();

            this.graphleft= GraphsController.graphleft;
            this.graphright=GraphsController.graphright;
            this.graphbottom= GraphsController.graphbottom;
            series = new XYChart.Series();

            graphleft.getData().add(series);
            GraphsController.setLine(graphleft,series);
            GraphsController.setLine(graphright,series);
            GraphsController.setLine(graphbottom,series);

            this.getChildren().add(Graphs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
