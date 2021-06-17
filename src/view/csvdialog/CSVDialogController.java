package view.csvdialog;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CSVDialogController {

    @FXML
    public ListView<String> listView;

    public void getHeaders(){
        String[] headers = new String[0];
        String line ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/reg_flight.csv"));
            line =br.readLine();
            headers = line.split(",");
          listView.getItems().addAll(headers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
