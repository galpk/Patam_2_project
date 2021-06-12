package View.bottom;

import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BottomController  implements Initializable {
     public static String[] headers = new String[0];
    public void OpenCSVFile()  {

        FileChooser fc=new FileChooser();
        fc.setTitle("Open CSV");
        fc.setInitialDirectory(new File("./resources"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files only","*.csv"));
        File chosen=fc.showOpenDialog(null);
        if(chosen!=null) {
            System.out.println(chosen.getName());

        }
    }
    public void play(){

    }
    public void stop(){

    }
    public void pause(){

    }
    public void FastRewind(){

    }
    public void FastForward(){

    }
    public void next(){

    }
    public void back(){

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = "./resources/anomaly_flight.csv";
        String line ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line =br.readLine())!= null){
                headers = line.split(",");
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.deepToString(headers));

    }
}
