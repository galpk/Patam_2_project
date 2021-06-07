package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
       try{
           FXMLLoader Lo=new FXMLLoader();
           BorderPane root = Lo.load(getClass().getResource("sample.fxml").openStream());
           primaryStage.setTitle("Flight Gear");
           primaryStage.setScene(new Scene(root, 825, 500));
           primaryStage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
