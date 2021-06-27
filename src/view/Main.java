package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import model.Model;
import model.Settings;
import view.joystick.JoystickController;
import viewmodel.ViewModel;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try{

           FXMLLoader Lo=new FXMLLoader();
           BorderPane root = Lo.load(getClass().getResource("sample.fxml").openStream());
           Controller con = Lo.getController();
           Settings setting = new Settings();
           setting.NewSettings();
           XMLEncoder xmlen = new XMLEncoder(new FileOutputStream(new File("newxml.xml")));
           xmlen.writeObject(setting);
           xmlen.flush();
           xmlen.close();
           Model m1 = new Model();
           ViewModel vm1= new ViewModel(m1);
           con.init(vm1);
           primaryStage.setTitle("Flight Gear");
           Scene scene = new Scene(root, 900, 580);
           scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
           primaryStage.setScene(scene);
           primaryStage.show();
          // con.configurePlayer();

       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
