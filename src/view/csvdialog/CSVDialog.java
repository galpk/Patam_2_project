package view.csvdialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CSVDialog extends AnchorPane {

    //public ListView listView;

    public ListView<String> listView;
    public CSVDialog() {
        super();
        try {
            FXMLLoader fxlCSVDialog = new FXMLLoader();
            AnchorPane CSVDialog = fxlCSVDialog.load(getClass().getResource("CSVDialog.fxml").openStream());

            CSVDialogController CSVDialogController = fxlCSVDialog.getController();

            listView = CSVDialogController.listView;

            this.getChildren().add(CSVDialog);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
