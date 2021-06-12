package View;

import View.bottom.BottomController;
import View.csvdialog.CSVDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

import static View.bottom.BottomController.headers;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
       try{
           FXMLLoader Lo=new FXMLLoader();
           BorderPane root = Lo.load(getClass().getResource("sample.fxml").openStream());
           primaryStage.setTitle("Flight Gear");
           Scene scene = new Scene(root, 825, 500);
           scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
            primaryStage.setScene(scene);
           primaryStage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }

    }


    public static void main(String[] args) {
        launch(args);

    }
}
