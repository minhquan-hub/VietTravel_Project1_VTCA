package Project1.Hotel;

import Project1.Information.HotelPackage.SearchHotels;
import Project1.Information.HotelPackage.InformationHotelList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InterfaceHotel3 implements Initializable {
    @FXML
    private AnchorPane interfaceHotel3AnchorPane;
    @FXML
    private ScrollPane interfaceHotel3ScrollPane;
    @FXML
    private ImageView interfaceHotel3ImageKitchen;
    @FXML
    private ImageView interfaceHotel3ImageParking;
    @FXML
    private ImageView interfaceHotel3ImageBacolny;
    @FXML
    private ImageView interfaceHotel3ImageHairdryer;
    @FXML
    private ImageView interfaceHotel3ImageWifi;
    @FXML
    private ImageView interfaceHotel3ImageTV;
    @FXML
    private ImageView interfaceHotel3ImageLuggageStorage;
    @FXML
    private ImageView interfaceHotel3ImageCamera;
    @FXML
    private ImageView interfaceHotel3ImageHotel1;
    @FXML
    private ImageView interfaceHotel3ImageHotel2;
    @FXML
    private ImageView interfaceHotel3ImageHotel3;
    @FXML
    private ImageView interfaceHotel3ImageHotel4;
    @FXML
    private ImageView interfaceHotel3ImageHotel5;
    @FXML
    private Label interfaceHotel3LabelNameHotel;
    @FXML
    private Label interfaceHotel3LabelMap;
    @FXML
    private Label interfaceHotel3LabelPricePerNight;
    @FXML
    private Button interfaceHotel3ButtonBookRoom;
    @FXML
    private Button interfaceHotel3ButtonBack;
    @FXML
    private Label interfaceHotel3LabelDateCheckin;
    @FXML
    private Label interfaceHotel3LabelDateCheckout;
    @FXML
    private Label interfaceHotel3LabelNumberPerson;
    @FXML
    private Label interfaceHotel3LabelPriceTotalNights;
    @FXML
    private Label interfaceHotel3Label$TotalNights;
    @FXML
    private Label interfaceHotel3Label$TotalPayment;
    private static final String  PATH_NAME_INTERFACEHOTEL2 = "interfaceHotel2.fxml";
    private static final String  PATH_NAME_INTERFACEHOTELPAYMENT = "InterfaceHotelPayment.fxml";

    public InterfaceHotel3() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // call method insertImageInformation
        insertImageInformation();
        // call insertimageHotel
        insertInformationHotel();
        // call method searchInformation
        searchHotelsInformation();
    }

    public void bookRoomButtonOnAction(ActionEvent event){
       callInterface(interfaceHotel3ButtonBookRoom,PATH_NAME_INTERFACEHOTELPAYMENT);
    }
    public void backButtonOnAction(ActionEvent event){
        callInterface(interfaceHotel3ButtonBack,PATH_NAME_INTERFACEHOTEL2);
        InformationHotelList informationHotelList = new InformationHotelList();
        informationHotelList.deleteIdentificationIdHotel();
    }

    public void searchHotelsInformation(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (SearchHotels searchInformation : InformationHotelList.getSearchHotelsList()) {
            interfaceHotel3LabelDateCheckin.setText(searchInformation.getCheckIn().format(dateTimeFormatter));
            interfaceHotel3LabelDateCheckout.setText(searchInformation.getCheckOut().format(dateTimeFormatter));
            interfaceHotel3LabelNumberPerson.setText(searchInformation.getGuest());
        }
    }

    public void insertImageInformation(){
        // add icon in userinterface
        File kitchenImageFile = new File("src/ProjectFrame1/interfaceHotel3/kitchen.png");
        Image kitchenImage = new Image(kitchenImageFile.toURI().toString());
        interfaceHotel3ImageKitchen.setImage(kitchenImage);

        File parkingImageFile = new File("src/ProjectFrame1/interfaceHotel3/parking.png");
        Image parkingImage = new Image(parkingImageFile.toURI().toString());
        interfaceHotel3ImageParking.setImage(parkingImage);

        File bacolnyImageFile = new File("src/ProjectFrame1/interfaceHotel3/house.png");
        Image bacolnyImage = new Image(bacolnyImageFile.toURI().toString());
        interfaceHotel3ImageBacolny.setImage(bacolnyImage);

        File hairdryerImageFile = new File("src/ProjectFrame1/interfaceHotel3/hairdryer.png");
        Image hairdryerImage = new Image(hairdryerImageFile.toURI().toString());
        interfaceHotel3ImageHairdryer.setImage(hairdryerImage);

        File wifiImageFile = new File("src/ProjectFrame1/interfaceHotel3/wifi.png");
        Image wifiImage = new Image(wifiImageFile.toURI().toString());
        interfaceHotel3ImageWifi.setImage(wifiImage);

        File TVImageFile = new File("src/ProjectFrame1/interfaceHotel3/TV.png");
        Image TVImage = new Image(TVImageFile.toURI().toString());
        interfaceHotel3ImageTV.setImage(TVImage);

        File luggageStorageImageFile = new File("src/ProjectFrame1/interfaceHotel3/luggage.png");
        Image luggageStorageImage = new Image(luggageStorageImageFile.toURI().toString());
        interfaceHotel3ImageLuggageStorage.setImage(luggageStorageImage);

        File cameraImageFile = new File("src/ProjectFrame1/interfaceHotel3/camera.png");
        Image cameraImage = new Image(cameraImageFile.toURI().toString());
        interfaceHotel3ImageCamera.setImage(cameraImage);
    }

    public void insertInformationHotel(){
        String imageHotel[] = new String[5];
        for (int i = 0; i < InformationHotelList.getHotelList().size(); i++) {
            if (InformationHotelList.getIdentificationIdHotelList().get(0).getIdentificationId().equals(InformationHotelList.getHotelList().get(i).getIdHotel())){
                // add array image
                imageHotel[0] = InformationHotelList.getHotelList().get(i).getUrlLink().getLink1();
                imageHotel[1] = InformationHotelList.getHotelList().get(i).getUrlLink().getLink2();
                imageHotel[2] = InformationHotelList.getHotelList().get(i).getUrlLink().getLink3();
                imageHotel[3] = InformationHotelList.getHotelList().get(i).getUrlLink().getLink4();
                imageHotel[4] = InformationHotelList.getHotelList().get(i).getUrlLink().getLink5();
                // insert label name, place, price hotel
                interfaceHotel3LabelNameHotel.setText(InformationHotelList.getHotelList().get(i).getNameHotel());
                interfaceHotel3LabelMap.setText(InformationHotelList.getHotelList().get(i).getPlace());
                int priceHotel = InformationHotelList.getHotelList().get(i).getPrice();
                interfaceHotel3LabelPricePerNight.setText("$"+priceHotel+"/Night:");
                insertPriceHotel(priceHotel);
            }
        }
        // add image in userinterface
        File image1File = new File(imageHotel[0]);
        Image image1 = new Image(image1File.toURI().toString());
        interfaceHotel3ImageHotel1.setImage(image1);

        File image2File = new File(imageHotel[1]);
        Image image2 = new Image(image2File.toURI().toString());
        interfaceHotel3ImageHotel2.setImage(image2);

        File image3File = new File(imageHotel[2]);
        Image image3 = new Image(image3File.toURI().toString());
        interfaceHotel3ImageHotel3.setImage(image3);

        File image4File = new File(imageHotel[3]);
        Image image4 = new Image(image4File.toURI().toString());
        interfaceHotel3ImageHotel4.setImage(image4);

        File image5File = new File(imageHotel[4]);
        Image image5 = new Image(image5File.toURI().toString());
        interfaceHotel3ImageHotel5.setImage(image5);
    }

    public void insertPriceHotel(int price){
        String numberNightString = "";
        for (SearchHotels searchInformation : InformationHotelList.getSearchHotelsList()){
            numberNightString = searchInformation.getDuration();
        }
        char numberNightChar = numberNightString.charAt(0);
        int  numberNightInt = Integer.parseInt(String.valueOf(numberNightChar));
        int $TotalNights = price*numberNightInt;
        int $TotalPayment = $TotalNights+4;

        // show information up userinterface
        interfaceHotel3LabelPriceTotalNights.setText("$"+price+" x "+numberNightString);
        interfaceHotel3Label$TotalNights.setText("$"+$TotalNights);
        interfaceHotel3Label$TotalPayment.setText("$"+$TotalPayment);
    }

    public void callInterface(Button button, String pathname){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathname));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceHotel3AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceHotel3ScrollPane.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
