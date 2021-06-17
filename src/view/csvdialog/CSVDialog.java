package view.csvdialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class CSVDialog extends AnchorPane {
    //public File CSVnew = new File("null");
    //public ListView listView;

    @FXML public ListView listView;
    public CSVDialog() {
        super();
        try {
            FXMLLoader fxlCSVDialog = new FXMLLoader();
            AnchorPane CSVDialog = fxlCSVDialog.load(getClass().getResource("CSVDialog.fxml").openStream());

            CSVDialogController CSVDialogController = fxlCSVDialog.getController();

            listView = CSVDialogController.listView;
            CSVDialogController.getHeaders();
            this.getChildren().add(CSVDialog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
