package View.csvdialog;

import View.bottom.BottomController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class CSVDialogController extends BottomController {

    @FXML
    public static ListView<String> listView;

    public void insert(){
    listView.getItems().addAll(headers);
    }



}
