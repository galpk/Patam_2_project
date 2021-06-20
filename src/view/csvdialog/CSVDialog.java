package view.csvdialog;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class CSVDialog extends AnchorPane {
    public StringProperty newpat;
    @FXML
    private Button openCSV;
    @FXML public ListView listView;
    public CSVDialog() {
        super();
        newpat = new SimpleStringProperty();
        try {
            FXMLLoader fxlCSVDialog = new FXMLLoader();
            AnchorPane CSVDialog = fxlCSVDialog.load(getClass().getResource("CSVDialog.fxml").openStream());

            CSVDialogController CSVDialogController = fxlCSVDialog.getController();
            newpat.bind(CSVDialogController.newPath);
            listView = CSVDialogController.listView;
//            CSVDialogController.getHeaders();
            this.getChildren().add(CSVDialog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
