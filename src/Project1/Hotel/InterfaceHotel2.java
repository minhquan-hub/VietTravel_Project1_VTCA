package Project1.Hotel;

import Project1.Information.HotelPackage.IdentificationIdHotel;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InterfaceHotel2 implements Initializable {
    @FXML
    private AnchorPane interfaceHotel2AnchorPane;
    @FXML
    private ScrollPane interfaceHotel2ScrollPane;
    @FXML
    private Button interfaceHotel2ButtonBack;

    @FXML
    private ImageView interfaceHotel2Image1;
    @FXML
    private Label interfaceHotel2Name1;
    @FXML
    private Label interfaceHotel2Price1;

    @FXML
    private ImageView interfaceHotel2Image2;
    @FXML
    private Label interfaceHotel2Name2;
    @FXML
    private Label interfaceHotel2Price2;

    @FXML
    private ImageView interfaceHotel2Image3;
    @FXML
    private Label interfaceHotel2Name3;
    @FXML
    private Label interfaceHotel2Price3;

    @FXML
    private ImageView interfaceHotel2Image4;
    @FXML
    private Label interfaceHotel2Name4;
    @FXML
    private Label interfaceHotel2Price4;

    @FXML
    private ImageView interfaceHotel2Image5;
    @FXML
    private Label interfaceHotel2Name5;
    @FXML
    private Label interfaceHotel2Price5;

    @FXML
    private ImageView interfaceHotel2Image6;
    @FXML
    private Label interfaceHotel2Name6;
    @FXML
    private Label interfaceHotel2Price6;

    @FXML
    private ImageView interfaceHotel2Image7;
    @FXML
    private Label interfaceHotel2Name7;
    @FXML
    private Label interfaceHotel2Price7;

    @FXML
    private ImageView interfaceHotel2Image8;
    @FXML
    private Label interfaceHotel2Name8;
    @FXML
    private Label interfaceHotel2Price8;


    private InformationHotelList informationHotelList = new InformationHotelList();
    private String[] idHotel = null;
    private String[] nameHotel = null;
    private int[] priceHotel = null;
    private String[] imageHotel = null;

    public InterfaceHotel2() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idHotel = new String[8];
        nameHotel = new String[8];
        priceHotel = new int[8];
        imageHotel = new String[8];
        // insert date from list into array
        for (int i = 0; i < InformationHotelList.getHotelList().size(); i++) {
            idHotel[i] = InformationHotelList.getHotelList().get(i).getIdHotel();
            nameHotel[i] = InformationHotelList.getHotelList().get(i).getNameHotel();
            priceHotel[i] = InformationHotelList.getHotelList().get(i).getPrice();
            imageHotel[i] = InformationHotelList.getHotelList().get(i).getUrlLink().getLink1();
        }

        showImageInterface();

    }

    // click event when choose hotel
    public void interfaceHotel2Image1ClickMouse(MouseEvent event) {
        // add idHotel into identificationIdHotelList
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[0]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        // load interface next
        callInterfaceHotel3(interfaceHotel2Image1);
    }

    public void interfaceHotel2Image2ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[1]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image2);
    }
    public void interfaceHotel2Image3ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[2]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image3);
    }
    public void interfaceHotel2Image4ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[3]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image4);
    }
    public void interfaceHotel2Image5ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[4]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image5);
    }
    public void interfaceHotel2Image6ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[5]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image6);
    }
    public void interfaceHotel2Image7ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[6]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image7);
    }
    public void interfaceHotel2Image8ClickMouse(MouseEvent event) {
        IdentificationIdHotel identificationIdHotel = new IdentificationIdHotel(idHotel[7]);
        informationHotelList.addIdentificationIdHotel(identificationIdHotel);
        callInterfaceHotel3(interfaceHotel2Image8);
    }

    // back interfaceHotel1
    public void backButtonOnAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("interfaceHotel1.fxml"));
            Scene scene = interfaceHotel2ButtonBack.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceHotel2AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_OUT);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceHotel2ScrollPane.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InformationHotelList informationHotelList = new InformationHotelList();
        informationHotelList.deleteHotel();
        informationHotelList.deleteSearchHotels();
    }

    public void showImageInterface(){
        File image1File = new File(imageHotel[0]);
        Image image1 = new Image(image1File.toURI().toString());
        interfaceHotel2Image1.setImage(image1);
        interfaceHotel2Name1.setText(nameHotel[0]);
        interfaceHotel2Price1.setText("$"+priceHotel[0]+"/Night");

        File image2File = new File(imageHotel[1]);
        Image image2 = new Image(image2File.toURI().toString());
        interfaceHotel2Image2.setImage(image2);
        interfaceHotel2Name2.setText(nameHotel[1]);
        interfaceHotel2Price2.setText("$"+priceHotel[1]+"/Night");

        File image3File = new File(imageHotel[2]);
        Image image3 = new Image(image3File.toURI().toString());
        interfaceHotel2Image3.setImage(image3);
        interfaceHotel2Name3.setText(nameHotel[2]);
        interfaceHotel2Price3.setText("$"+priceHotel[2]+"/Night");

        File image4File = new File(imageHotel[3]);
        Image image4 = new Image(image4File.toURI().toString());
        interfaceHotel2Image4.setImage(image4);
        interfaceHotel2Name4.setText(nameHotel[3]);
        interfaceHotel2Price4.setText("$"+priceHotel[3]+"/Night");

        File image5File = new File(imageHotel[4]);
        Image image5 = new Image(image5File.toURI().toString());
        interfaceHotel2Image5.setImage(image5);
        interfaceHotel2Name5.setText(nameHotel[4]);
        interfaceHotel2Price5.setText("$"+priceHotel[4]+"/Night");

        File image6File = new File(imageHotel[5]);
        Image image6 = new Image(image6File.toURI().toString());
        interfaceHotel2Image6.setImage(image6);
        interfaceHotel2Name6.setText(nameHotel[5]);
        interfaceHotel2Price6.setText("$"+priceHotel[5]+"/Night");

        File image7File = new File(imageHotel[6]);
        Image image7 = new Image(image7File.toURI().toString());
        interfaceHotel2Image7.setImage(image7);
        interfaceHotel2Name7.setText(nameHotel[6]);
        interfaceHotel2Price7.setText("$"+priceHotel[6]+"/Night");

        File image8File = new File(imageHotel[7]);
        Image image8 = new Image(image8File.toURI().toString());
        interfaceHotel2Image8.setImage(image8);
        interfaceHotel2Name8.setText(nameHotel[7]);
        interfaceHotel2Price8.setText("$"+priceHotel[7]+"/Night");
    }

    // load interfaceHotel3
    public void callInterfaceHotel3(ImageView imageViewHotel){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("interfaceHotel3.fxml"));
            Scene scene = imageViewHotel.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceHotel2AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceHotel2ScrollPane.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
