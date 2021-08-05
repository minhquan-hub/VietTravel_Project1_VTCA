package Project1.Common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Loader {
    private Pane view;

    public Loader() {
        view = new Pane();
    }

    public Pane getPane(String titleName){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(titleName+".fxml"));
            if(loader == null){
                System.out.println("Don't see");
            }
            view = loader.load();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
