package Project1.UserInterfaceMain;

import Project1.Information.FlightPackage.InformationFlightList;
import Project1.Information.HotelPackage.InformationHotelList;
import Project1.Common.Loader;
import Project1.Information.TaxiPackage.InformationTaxiList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInterface implements Initializable {
    @FXML
    private ImageView userInterfaceView;
    @FXML
    private AnchorPane userInterfaceAnchorPane;
    @FXML
    private BorderPane userInterfaceBorderPane ;

    public UserInterface() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File userInterfaceImageFile = new File("src/ProjectFrame1/UserInterface/Viewhotel.jpg");
        Image UserInterfaceImage = new Image(userInterfaceImageFile.toURI().toString());
        userInterfaceView.setImage(UserInterfaceImage);

    }

    public void loginButtonOnAction(ActionEvent event){
        Loader loadFXML = new Loader();
        Pane view = loadFXML.getPane("/Project1/UserInterfaceMain/Login");
        userInterfaceBorderPane.setCenter(view);
    }

    public void signupButtonOnAction(ActionEvent event){
        Loader loadFXML = new Loader();
        Pane view = loadFXML.getPane("/Project1/UserInterfaceMain/SignUp");
        userInterfaceBorderPane.setCenter(view);
    }
    public void hotelButtonOnAction(ActionEvent event){

        Loader loadFXML = new Loader();
        Pane view = loadFXML.getPane("/Project1/Hotel/interfaceHotel1");
        userInterfaceBorderPane.setCenter(view);
        // delete list if it has data
        deleteAll();

    }
    public void flightButtonOnAction(ActionEvent event){
        Loader loadFXML = new Loader();
        Pane view = loadFXML.getPane("/Project1/Flight/InterfaceFlight1");
        userInterfaceBorderPane.setCenter(view);
        deleteAll();
    }
    public void taxiButtonOnAction(ActionEvent event){
        Loader loadFXML = new Loader();
        Pane view = loadFXML.getPane("/Project1/Taxi/InterfaceTaxi1");
        userInterfaceBorderPane.setCenter(view);
        deleteAll();
    }

    public void deleteAll(){
        if(!InformationHotelList.getSearchHotelsList().isEmpty()) {
            InformationHotelList informationHotelList = new InformationHotelList();
            informationHotelList.deleteHotel();
            informationHotelList.deleteSearchHotels();
            if (!InformationHotelList.getIdentificationIdHotelList().isEmpty()) {
                informationHotelList.deleteIdentificationIdHotel();
            }
        }else if (!InformationFlightList.getSearchFlightList().isEmpty()){
            InformationFlightList informationFlightList = new InformationFlightList();
            informationFlightList.deleteFlight();
            informationFlightList.deleteSearchFlight();
            if(!InformationFlightList.getIdentificationIdFlightList().isEmpty()){
                informationFlightList.deleteIdentificationIdFlight();
            }
        } else if(!InformationTaxiList.getSearchTaxiList().isEmpty()){
            InformationTaxiList informationTaxiList = new InformationTaxiList();
            informationTaxiList.deleteTaxi();
            informationTaxiList.deleteSearchTaxi();
            if(!InformationTaxiList.getIdentificationIdTaxiList().isEmpty()){
                informationTaxiList.deleteIdentificationIdTaxi();
            }
        }
    }

}
