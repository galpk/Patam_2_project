package View.Bottom;

import javafx.stage.FileChooser;
import java.io.File;

public class BottomController {

    public void OpenCSVFile(){
        FileChooser fc=new FileChooser();
        fc.setTitle("Open CSV");
        fc.setInitialDirectory(new File("./resources"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                "CSV Files only","*.csv"));
        File chosen=fc.showOpenDialog(null);
        if(chosen!=null){
            System.out.println(chosen.getName());
        }
    }
}
