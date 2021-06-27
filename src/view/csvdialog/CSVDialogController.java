package view.csvdialog;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.*;


public class CSVDialogController {

    @FXML
    public ListView<String> listView;
    @FXML
    private Button openCSV;
    StringProperty newPath;

    public CSVDialogController() {
        newPath = new SimpleStringProperty();
    }

    public void OpenCSVFile1() throws IOException {
        String[] headers = new String[0];
        String line ="";
        FileChooser fc=new FileChooser();
        fc.setTitle("Open CSV");
        fc.setInitialDirectory(new File("resources"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files only","*.csv"));
        File chosen=fc.showOpenDialog(null);
        if(chosen!=null) {
            System.out.println(chosen.getName());
            newPath.setValue(chosen.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(chosen));
            line =br.readLine();
            headers = line.split(",");
            listView.getItems().addAll(headers);

        }

    }
}
