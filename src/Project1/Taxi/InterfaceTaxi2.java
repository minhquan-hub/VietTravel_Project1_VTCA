package Project1.Taxi;

import Project1.Information.TaxiPackage.IdentificationIdTaxi;
import Project1.Information.TaxiPackage.InformationTaxiList;
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
import java.util.ResourceBundle;

public class InterfaceTaxi2 implements Initializable {
    @FXML
    private AnchorPane interfaceTaxi2AnchorPane;
    @FXML
    private ScrollPane interfaceTaxi2ScrollPane;

    @FXML
    private ImageView InterfaceTaxi2ImageCar1;
    @FXML
    private Label interfaceTaxi2NameCar1;
    @FXML
    private Label interfaceTaxi2Passenger1;
    @FXML
    private Label interfaceTaxi2Baggage1;
    @FXML
    private Button interfaceTaxiButtonChoose1;
    @FXML
    private Label interfaceTaxi2Price1;
    @FXML
    private Label interfaceTaxi2NameCompany1;

    @FXML
    private ImageView InterfaceTaxi2ImageCar2;
    @FXML
    private Label interfaceTaxi2NameCar2;
    @FXML
    private Label interfaceTaxi2Passenger2;
    @FXML
    private Label interfaceTaxi2Baggage2;
    @FXML
    private Button interfaceTaxiButtonChoose2;
    @FXML
    private Label interfaceTaxi2Price2;
    @FXML
    private Label interfaceTaxi2NameCompany2;

    @FXML
    private ImageView InterfaceTaxi2ImageCar3;
    @FXML
    private Label interfaceTaxi2NameCar3;
    @FXML
    private Label interfaceTaxi2Passenger3;
    @FXML
    private Label interfaceTaxi2Baggage3;
    @FXML
    private Button interfaceTaxiButtonChoose3;
    @FXML
    private Label interfaceTaxi2Price3;
    @FXML
    private Label interfaceTaxi2NameCompany3;

    @FXML
    private ImageView InterfaceTaxi2ImageCar4;
    @FXML
    private Label interfaceTaxi2NameCar4;
    @FXML
    private Label interfaceTaxi2Passenger4;
    @FXML
    private Label interfaceTaxi2Baggage4;
    @FXML
    private Button interfaceTaxiButtonChoose4;
    @FXML
    private Label interfaceTaxi2Price4;
    @FXML
    private Label interfaceTaxi2NameCompany4;

    @FXML
    private ImageView InterfaceTaxi2ImageCar5;
    @FXML
    private Label interfaceTaxi2NameCar5;
    @FXML
    private Label interfaceTaxi2Passenger5;
    @FXML
    private Label interfaceTaxi2Baggage5;
    @FXML
    private Button interfaceTaxiButtonChoose5;
    @FXML
    private Label interfaceTaxi2Price5;
    @FXML
    private Label interfaceTaxi2NameCompany5;

    @FXML
    private ImageView InterfaceTaxi2ImageCar6;
    @FXML
    private Label interfaceTaxi2NameCar6;
    @FXML
    private Label interfaceTaxi2Passenger6;
    @FXML
    private Label interfaceTaxi2Baggage6;
    @FXML
    private Button interfaceTaxiButtonChoose6;
    @FXML
    private Label interfaceTaxi2Price6;
    @FXML
    private Label interfaceTaxi2NameCompany6;

    @FXML
    private ImageView InterfaceTaxi2ImageCar7;
    @FXML
    private Label interfaceTaxi2NameCar7;
    @FXML
    private Label interfaceTaxi2Passenger7;
    @FXML
    private Label interfaceTaxi2Baggage7;
    @FXML
    private Button interfaceTaxiButtonChoose7;
    @FXML
    private Label interfaceTaxi2Price7;
    @FXML
    private Label interfaceTaxi2NameCompany7;

    @FXML
    private ImageView InterfaceTaxi2ImageCar8;
    @FXML
    private Label interfaceTaxi2NameCar8;
    @FXML
    private Label interfaceTaxi2Passenger8;
    @FXML
    private Label interfaceTaxi2Baggage8;
    @FXML
    private Button interfaceTaxiButtonChoose8;
    @FXML
    private Label interfaceTaxi2Price8;
    @FXML
    private Label interfaceTaxi2NameCompany8;
    @FXML
    private Button interfaceTaxi2ButtonBack;

    private String idTaxi[] = null;
    private String carName[] = null;
    private String companyName[] = null;
    private int passengerAmount[] = null;
    private int baggageAmount[] = null;
    private int price[] = null;
    private String link[] = null;

    private static final String PATH_NAME_INTERFACETAXIPAYMENT = "InterfaceTaxiPayment.fxml";
    private static final String PATH_NAME_INTERFACETAXI1 = "InterfaceTaxi1.fxml";

    public InterfaceTaxi2() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       idTaxi = new String[8];
       carName = new String[8];
       companyName = new String[8];
       passengerAmount = new int[8];
       baggageAmount = new int[8];
       price = new int[8];
       link = new String[8];

        for (int i = 0; i < InformationTaxiList.getTaxiList().size(); i++) {
            idTaxi[i] = InformationTaxiList.getTaxiList().get(i).getIdTaxi();
            carName[i] = InformationTaxiList.getTaxiList().get(i).getCarName();
            companyName[i] = InformationTaxiList.getTaxiList().get(i).getCompanyName();
            passengerAmount[i] = InformationTaxiList.getTaxiList().get(i).getPassengerAmount();
            baggageAmount[i] = InformationTaxiList.getTaxiList().get(i).getBaggageAmount();
            price[i] = InformationTaxiList.getTaxiList().get(i).getPrice();
            link[i] = InformationTaxiList.getTaxiList().get(i).getLink();
        }

        showInformationTaxi1(link[0], carName[0], companyName[0], passengerAmount[0], baggageAmount[0], price[0]);
        showInformationTaxi2(link[1], carName[1], companyName[1], passengerAmount[1], baggageAmount[1], price[1]);
        showInformationTaxi3(link[2], carName[2], companyName[2], passengerAmount[2], baggageAmount[2], price[2]);
        showInformationTaxi4(link[3], carName[3], companyName[3], passengerAmount[3], baggageAmount[3], price[3]);
        showInformationTaxi5(link[4], carName[4], companyName[4], passengerAmount[4], baggageAmount[4], price[4]);
        showInformationTaxi6(link[5], carName[5], companyName[5], passengerAmount[5], baggageAmount[5], price[5]);
        showInformationTaxi7(link[6], carName[6], companyName[6], passengerAmount[6], baggageAmount[6], price[6]);
        showInformationTaxi8(link[7], carName[7], companyName[7], passengerAmount[7], baggageAmount[7], price[7]);
    }

    public void choose1ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[0]);
        callInterface(interfaceTaxiButtonChoose1, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose2ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[1]);
        callInterface(interfaceTaxiButtonChoose2, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose3ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[2]);
        callInterface(interfaceTaxiButtonChoose3, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose4ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[3]);
        callInterface(interfaceTaxiButtonChoose4, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose5ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[4]);
        callInterface(interfaceTaxiButtonChoose5, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose6ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[5]);
        callInterface(interfaceTaxiButtonChoose6, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose7ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[6]);
        callInterface(interfaceTaxiButtonChoose7, PATH_NAME_INTERFACETAXIPAYMENT);
    }
    public void choose8ButtonOnAction(ActionEvent event){
        addIdentificationIdTaxi(idTaxi[7]);
        callInterface(interfaceTaxiButtonChoose8, PATH_NAME_INTERFACETAXIPAYMENT);
    }

    public void backButtonOnAction(ActionEvent event){
        callInterface(interfaceTaxi2ButtonBack, PATH_NAME_INTERFACETAXI1);
        InformationTaxiList informationTaxiList = new InformationTaxiList();
        informationTaxiList.deleteSearchTaxi();
        informationTaxiList.deleteTaxi();
    }


    public void showInformationTaxi1(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar1.setImage(imageCar(link));
        interfaceTaxi2NameCar1.setText(carName);
        interfaceTaxi2NameCompany1.setText(companyname);
        interfaceTaxi2Passenger1.setText(passenger + " Passengers");
        interfaceTaxi2Baggage1.setText(baggage + " Baggage");
        interfaceTaxi2Price1.setText("$"+price+"/Car");
    }

    public void showInformationTaxi2(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar2.setImage(imageCar(link));
        interfaceTaxi2NameCar2.setText(carName);
        interfaceTaxi2NameCompany2.setText(companyname);
        interfaceTaxi2Passenger2.setText(passenger + " Passengers");
        interfaceTaxi2Baggage2.setText(baggage + " Baggage");
        interfaceTaxi2Price2.setText("$"+price+"/Car");
    }

    public void showInformationTaxi3(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar3.setImage(imageCar(link));
        interfaceTaxi2NameCar3.setText(carName);
        interfaceTaxi2NameCompany3.setText(companyname);
        interfaceTaxi2Passenger3.setText(passenger + " Passengers");
        interfaceTaxi2Baggage3.setText(baggage + " Baggage");
        interfaceTaxi2Price3.setText("$"+price+"/Car");
    }

    public void showInformationTaxi4(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar4.setImage(imageCar(link));
        interfaceTaxi2NameCar4.setText(carName);
        interfaceTaxi2NameCompany4.setText(companyname);
        interfaceTaxi2Passenger4.setText(passenger + " Passengers");
        interfaceTaxi2Baggage4.setText(baggage + " Baggage");
        interfaceTaxi2Price4.setText("$"+price+"/Car");
    }

    public void showInformationTaxi5(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar5.setImage(imageCar(link));
        interfaceTaxi2NameCar5.setText(carName);
        interfaceTaxi2NameCompany5.setText(companyname);
        interfaceTaxi2Passenger5.setText(passenger + " Passengers");
        interfaceTaxi2Baggage5.setText(baggage + " Baggage");
        interfaceTaxi2Price5.setText("$"+price+"/Car");
    }

    public void showInformationTaxi6(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar6.setImage(imageCar(link));
        interfaceTaxi2NameCar6.setText(carName);
        interfaceTaxi2NameCompany6.setText(companyname);
        interfaceTaxi2Passenger6.setText(passenger + " Passengers");
        interfaceTaxi2Baggage6.setText(baggage + " Baggage");
        interfaceTaxi2Price6.setText("$"+price+"/Car");
    }

    public void showInformationTaxi7(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar7.setImage(imageCar(link));
        interfaceTaxi2NameCar7.setText(carName);
        interfaceTaxi2NameCompany7.setText(companyname);
        interfaceTaxi2Passenger7.setText(passenger + " Passengers");
        interfaceTaxi2Baggage7.setText(baggage + " Baggage");
        interfaceTaxi2Price7.setText("$"+price+"/Car");
    }

    public void showInformationTaxi8(String link, String carName, String companyname, int passenger, int baggage, int price){
        InterfaceTaxi2ImageCar8.setImage(imageCar(link));
        interfaceTaxi2NameCar8.setText(carName);
        interfaceTaxi2NameCompany8.setText(companyname);
        interfaceTaxi2Passenger8.setText(passenger + " Passengers");
        interfaceTaxi2Baggage8.setText(baggage + " Baggage");
        interfaceTaxi2Price8.setText("$"+price+"/Car");
    }


    public Image imageCar(String link) {
        File imageFile = new File(link);
        Image image = new Image(imageFile.toURI().toString());
        return image;
    }

    public void addIdentificationIdTaxi(String idTaxi){
        InformationTaxiList informationTaxiList = new InformationTaxiList();
        informationTaxiList.addIdentificationIdTaxi(new IdentificationIdTaxi(idTaxi));
    }

    public void callInterface(Button button, String pathName){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceTaxi2AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceTaxi2ScrollPane.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
