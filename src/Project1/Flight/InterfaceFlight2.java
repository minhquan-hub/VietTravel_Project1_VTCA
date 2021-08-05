package Project1.Flight;

import Project1.Information.FlightPackage.Flight;
import Project1.Information.FlightPackage.IdentificationIdFlight;
import Project1.Information.FlightPackage.InformationFlightList;
import Project1.Information.FlightPackage.SearchFlight;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InterfaceFlight2 implements Initializable {
    @FXML
    private AnchorPane interfaceFlight2AnchorPane;
    @FXML
    private ScrollPane interfaceFlight2ScrollPane;
    @FXML
    private Button interfaceFlight2ButtonBack;
    @FXML
    private ImageView interfaceFlight2Logo1;
    @FXML
    private Label interfaceFlight2DepartureTime1;
    @FXML
    private Label interfaceFlight2LabelFrom1;
    @FXML
    private Label interfaceFlight2FromSign1;
    @FXML
    private Label interfaceFlight2ArrivalTime1;
    @FXML
    private Label interfaceFlight2To1;
    @FXML
    private Label interfaceFlight2ToSign1;
    @FXML
    private Label interfaceFlight2TimeFly1;
    @FXML
    private Label interfaceFlight2TypeTicket1;
    @FXML
    private Label interfaceFlight2Flight1;
    @FXML
    private Label interfaceFlight2Date1;
    @FXML
    private Button interfaceFlight2Button1;
    @FXML
    private Label interfaceFlight2Price1;

    @FXML
    private ImageView interfaceFlight2Logo2;
    @FXML
    private Label interfaceFlight2DepartureTime2;
    @FXML
    private Label interfaceFlight2LabelFrom2;
    @FXML
    private Label interfaceFlight2FromSign2;
    @FXML
    private Label interfaceFlight2ArrivalTime2;
    @FXML
    private Label interfaceFlight2To2;
    @FXML
    private Label interfaceFlight2ToSign2;
    @FXML
    private Label interfaceFlight2TimeFly2;
    @FXML
    private Label interfaceFlight2TypeTicket2;
    @FXML
    private Label interfaceFlight2Flight2;
    @FXML
    private Label interfaceFlight2Date2;
    @FXML
    private Button interfaceFlight2Button2;
    @FXML
    private Label interfaceFlight2Price2;

    @FXML
    private ImageView interfaceFlight2Logo3;
    @FXML
    private Label interfaceFlight2DepartureTime3;
    @FXML
    private Label interfaceFlight2LabelFrom3;
    @FXML
    private Label interfaceFlight2FromSign3;
    @FXML
    private Label interfaceFlight2ArrivalTime3;
    @FXML
    private Label interfaceFlight2To3;
    @FXML
    private Label interfaceFlight2ToSign3;
    @FXML
    private Label interfaceFlight2TimeFly3;
    @FXML
    private Label interfaceFlight2TypeTicket3;
    @FXML
    private Label interfaceFlight2Flight3;
    @FXML
    private Label interfaceFlight2Date3;
    @FXML
    private Button interfaceFlight2Button3;
    @FXML
    private Label interfaceFlight2Price3;

    @FXML
    private ImageView interfaceFlight2Logo4;
    @FXML
    private Label interfaceFlight2DepartureTime4;
    @FXML
    private Label interfaceFlight2LabelFrom4;
    @FXML
    private Label interfaceFlight2FromSign4;
    @FXML
    private Label interfaceFlight2ArrivalTime4;
    @FXML
    private Label interfaceFlight2To4;
    @FXML
    private Label interfaceFlight2ToSign4;
    @FXML
    private Label interfaceFlight2TimeFly4;
    @FXML
    private Label interfaceFlight2TypeTicket4;
    @FXML
    private Label interfaceFlight2Flight4;
    @FXML
    private Label interfaceFlight2Date4;
    @FXML
    private Button interfaceFlight2Button4;
    @FXML
    private Label interfaceFlight2Price4;

    @FXML
    private ImageView interfaceFlight2Logo5;
    @FXML
    private Label interfaceFlight2DepartureTime5;
    @FXML
    private Label interfaceFlight2LabelFrom5;
    @FXML
    private Label interfaceFlight2FromSign5;
    @FXML
    private Label interfaceFlight2ArrivalTime5;
    @FXML
    private Label interfaceFlight2To5;
    @FXML
    private Label interfaceFlight2ToSign5;
    @FXML
    private Label interfaceFlight2TimeFly5;
    @FXML
    private Label interfaceFlight2TypeTicket5;
    @FXML
    private Label interfaceFlight2Flight5;
    @FXML
    private Label interfaceFlight2Date5;
    @FXML
    private Button interfaceFlight2Button5;
    @FXML
    private Label interfaceFlight2Price5;

    @FXML
    private ImageView interfaceFlight2Logo6;
    @FXML
    private Label interfaceFlight2DepartureTime6;
    @FXML
    private Label interfaceFlight2LabelFrom6;
    @FXML
    private Label interfaceFlight2FromSign6;
    @FXML
    private Label interfaceFlight2ArrivalTime6;
    @FXML
    private Label interfaceFlight2To6;
    @FXML
    private Label interfaceFlight2ToSign6;
    @FXML
    private Label interfaceFlight2TimeFly6;
    @FXML
    private Label interfaceFlight2TypeTicket6;
    @FXML
    private Label interfaceFlight2Flight6;
    @FXML
    private Label interfaceFlight2Date6;
    @FXML
    private Button interfaceFlight2Button6;
    @FXML
    private Label interfaceFlight2Price6;


    private String idTickets[] = null;
    private String idFromLocation[] = null;
    private String idToLocation[] = null;
    private String nameFromLocation[] = null;
    private String nameToLocation[] = null;
    private String timeFromLocation[] = null;
    private String timeToLocation[] = null;
    private String durationFlight[] = null;
    private int priceTicket[] = null;
    private String brandFlight[] = null;
    private String logoLink[] = null;

    public InterfaceFlight2() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idTickets = new String[6];
        idFromLocation = new String[6];
        idToLocation = new String[6];
        nameFromLocation = new String[6];
        nameToLocation = new String[6];
        timeFromLocation = new String[6];
        timeToLocation = new String[6];
        durationFlight = new String[6];
        priceTicket = new int[6];
        brandFlight = new String[6];
        String seatClass = "";
        LocalDate departureDate = null;
        logoLink = new String[]{"src/ProjectFrame1/interfaceFlight/logo_jestar.PNG", "src/ProjectFrame1/interfaceFlight/logo_vietJet.PNG", "src/ProjectFrame1/interfaceFlight/logo_vietnam_airline.PNG"};

        for (int i = 0; i < InformationFlightList.getFlightList().size(); i++) {
            idTickets[i] = InformationFlightList.getFlightList().get(i).getIdTickets();
            idFromLocation[i] = InformationFlightList.getFlightList().get(i).getIdFromLocation();
            idToLocation[i] = InformationFlightList.getFlightList().get(i).getIdToLocation();
            nameFromLocation[i] = InformationFlightList.getFlightList().get(i).getNameFromLocation();
            nameToLocation[i] = InformationFlightList.getFlightList().get(i).getNameToLocation();
            timeFromLocation[i] = InformationFlightList.getFlightList().get(i).getTimeFromLocation();
            timeToLocation[i] = InformationFlightList.getFlightList().get(i).getTimeToLocation();
            durationFlight[i] = InformationFlightList.getFlightList().get(i).getDuration();
            priceTicket[i] = InformationFlightList.getFlightList().get(i).getPrice();
            brandFlight[i] = InformationFlightList.getFlightList().get(i).getBrand();
        }
        for (SearchFlight searchFlight : InformationFlightList.getSearchFlightList()) {
            seatClass = searchFlight.getSeatClass();
            departureDate = searchFlight.getDepartureDate();
        }

        ticket1Information(idTickets[0], idFromLocation[0], idToLocation[0], nameFromLocation[0], nameToLocation[0], timeFromLocation[0], timeToLocation[0], durationFlight[0], priceTicket[0], brandFlight[0], seatClass, departureDate);
        ticket2Information(idTickets[1], idFromLocation[1], idToLocation[1], nameFromLocation[1], nameToLocation[1], timeFromLocation[1], timeToLocation[1], durationFlight[1], priceTicket[1], brandFlight[1], seatClass, departureDate);
        ticket3Information(idTickets[2], idFromLocation[2], idToLocation[2], nameFromLocation[2], nameToLocation[2], timeFromLocation[2], timeToLocation[2], durationFlight[2], priceTicket[2], brandFlight[2], seatClass, departureDate);
        ticket4Information(idTickets[3], idFromLocation[3], idToLocation[3], nameFromLocation[3], nameToLocation[3], timeFromLocation[3], timeToLocation[3], durationFlight[3], priceTicket[3], brandFlight[3], seatClass, departureDate);
        ticket5Information(idTickets[4], idFromLocation[4], idToLocation[4], nameFromLocation[4], nameToLocation[4], timeFromLocation[4], timeToLocation[4], durationFlight[4], priceTicket[4], brandFlight[4], seatClass, departureDate);
        ticket6Information(idTickets[5], idFromLocation[5], idToLocation[5], nameFromLocation[5], nameToLocation[5], timeFromLocation[5], timeToLocation[5], durationFlight[5], priceTicket[5], brandFlight[5], seatClass, departureDate);
    }

    public void choose1ButtonOnAction(ActionEvent event) {
        addIdentificationIdFlight(idTickets[0]);
        callInterfaceFlightPayment(interfaceFlight2Button1);

    }

    public void choose2ButtonOnAction(ActionEvent event) {
        addIdentificationIdFlight(idTickets[1]);
        callInterfaceFlightPayment(interfaceFlight2Button2);
    }

    public void choose3ButtonOnAction(ActionEvent event) {
        addIdentificationIdFlight(idTickets[2]);
        callInterfaceFlightPayment(interfaceFlight2Button3);
    }

    public void choose4ButtonOnAction(ActionEvent event) {
        addIdentificationIdFlight(idTickets[3]);
        callInterfaceFlightPayment(interfaceFlight2Button4);
    }

    public void choose5ButtonOnAction(ActionEvent event) {
        addIdentificationIdFlight(idTickets[4]);
        callInterfaceFlightPayment(interfaceFlight2Button5);
    }

    public void choose6ButtonOnAction(ActionEvent event) {
        addIdentificationIdFlight(idTickets[5]);
        callInterfaceFlightPayment(interfaceFlight2Button6);
    }

    public void backButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("InterfaceFlight1.fxml"));
            Scene scene = interfaceFlight2ButtonBack.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceFlight2AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceFlight2ScrollPane.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InformationFlightList informationFlightList = new InformationFlightList();
        informationFlightList.deleteFlight();

    }

    // show information ticket in user interface
    public void ticket1Information(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand, String seatClass, LocalDate departureDate) {
        interfaceFlight2Flight1.setText(idTickets);
        interfaceFlight2DepartureTime1.setText(timeFromLocation);
        interfaceFlight2LabelFrom1.setText(nameFromLocation);
        interfaceFlight2FromSign1.setText(idFromLocation);
        interfaceFlight2ArrivalTime1.setText(timeToLocation);
        interfaceFlight2To1.setText(nameToLocation);
        interfaceFlight2ToSign1.setText(idToLocation);
        interfaceFlight2TimeFly1.setText(duration);
        interfaceFlight2Price1.setText("$" + distinguishPriceEconomyAndBusinessClass(price, seatClass) + "/Pax");
        interfaceFlight2TypeTicket1.setText(seatClass);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlight2Date1.setText(departureDate.format(dateTimeFormatter));
        interfaceFlight2Logo1.setImage(imageLogo(brand));
    }

    public void ticket2Information(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand, String seatClass, LocalDate departureDate) {
        interfaceFlight2Flight2.setText(idTickets);
        interfaceFlight2DepartureTime2.setText(timeFromLocation);
        interfaceFlight2LabelFrom2.setText(nameFromLocation);
        interfaceFlight2FromSign2.setText(idFromLocation);
        interfaceFlight2ArrivalTime2.setText(timeToLocation);
        interfaceFlight2To2.setText(nameToLocation);
        interfaceFlight2ToSign2.setText(idToLocation);
        interfaceFlight2TimeFly2.setText(duration);
        interfaceFlight2Price2.setText("$" + distinguishPriceEconomyAndBusinessClass(price, seatClass) + "/Pax");
        interfaceFlight2TypeTicket2.setText(seatClass);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlight2Date2.setText(departureDate.format(dateTimeFormatter));
        interfaceFlight2Logo2.setImage(imageLogo(brand));
    }

    public void ticket3Information(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand, String seatClass, LocalDate departureDate) {
        interfaceFlight2Flight3.setText(idTickets);
        interfaceFlight2DepartureTime3.setText(timeFromLocation);
        interfaceFlight2LabelFrom3.setText(nameFromLocation);
        interfaceFlight2FromSign3.setText(idFromLocation);
        interfaceFlight2ArrivalTime3.setText(timeToLocation);
        interfaceFlight2To3.setText(nameToLocation);
        interfaceFlight2ToSign3.setText(idToLocation);
        interfaceFlight2TimeFly3.setText(duration);
        interfaceFlight2Price3.setText("$" + distinguishPriceEconomyAndBusinessClass(price, seatClass) + "/Pax");
        interfaceFlight2TypeTicket3.setText(seatClass);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlight2Date3.setText(departureDate.format(dateTimeFormatter));
        interfaceFlight2Logo3.setImage(imageLogo(brand));
    }

    public void ticket4Information(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand, String seatClass, LocalDate departureDate) {
        interfaceFlight2Flight4.setText(idTickets);
        interfaceFlight2DepartureTime4.setText(timeFromLocation);
        interfaceFlight2LabelFrom4.setText(nameFromLocation);
        interfaceFlight2FromSign4.setText(idFromLocation);
        interfaceFlight2ArrivalTime4.setText(timeToLocation);
        interfaceFlight2To4.setText(nameToLocation);
        interfaceFlight2ToSign4.setText(idToLocation);
        interfaceFlight2TimeFly4.setText(duration);
        interfaceFlight2Price4.setText("$" + distinguishPriceEconomyAndBusinessClass(price, seatClass) + "/Pax");
        interfaceFlight2TypeTicket4.setText(seatClass);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlight2Date4.setText(departureDate.format(dateTimeFormatter));
        interfaceFlight2Logo4.setImage(imageLogo(brand));
    }

    public void ticket5Information(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand, String seatClass, LocalDate departureDate) {
        interfaceFlight2Flight5.setText(idTickets);
        interfaceFlight2DepartureTime5.setText(timeFromLocation);
        interfaceFlight2LabelFrom5.setText(nameFromLocation);
        interfaceFlight2FromSign5.setText(idFromLocation);
        interfaceFlight2ArrivalTime5.setText(timeToLocation);
        interfaceFlight2To5.setText(nameToLocation);
        interfaceFlight2ToSign5.setText(idToLocation);
        interfaceFlight2TimeFly5.setText(duration);
        interfaceFlight2Price5.setText("$" + distinguishPriceEconomyAndBusinessClass(price, seatClass) + "/Pax");
        interfaceFlight2TypeTicket5.setText(seatClass);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlight2Date5.setText(departureDate.format(dateTimeFormatter));
        interfaceFlight2Logo5.setImage(imageLogo(brand));
    }

    public void ticket6Information(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand, String seatClass, LocalDate departureDate) {
        interfaceFlight2Flight6.setText(idTickets);
        interfaceFlight2DepartureTime6.setText(timeFromLocation);
        interfaceFlight2LabelFrom6.setText(nameFromLocation);
        interfaceFlight2FromSign6.setText(idFromLocation);
        interfaceFlight2ArrivalTime6.setText(timeToLocation);
        interfaceFlight2To6.setText(nameToLocation);
        interfaceFlight2ToSign6.setText(idToLocation);
        interfaceFlight2TimeFly6.setText(duration);
        interfaceFlight2Price6.setText("$" + distinguishPriceEconomyAndBusinessClass(price, seatClass) + "/Pax");
        interfaceFlight2TypeTicket6.setText(seatClass);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlight2Date6.setText(departureDate.format(dateTimeFormatter));
        interfaceFlight2Logo6.setImage(imageLogo(brand));
    }

    public Image imageLogo(String brandFlight) {
        String linkLogoFlight = "";
        if (brandFlight.equalsIgnoreCase("Vietnam Airlines")) {
            linkLogoFlight = logoLink[2];
        } else if (brandFlight.equalsIgnoreCase("VietjetAir")) {
            linkLogoFlight = logoLink[1];
        } else if (brandFlight.equalsIgnoreCase("Jetstar")) {
            linkLogoFlight = logoLink[0];
        }
        File imageFile = new File(linkLogoFlight);
        Image image = new Image(imageFile.toURI().toString());
        return image;
    }

    public void addIdentificationIdFlight(String flight){
        IdentificationIdFlight identificationIdFlight = new IdentificationIdFlight(flight);
        InformationFlightList informationFlightList = new InformationFlightList();
        informationFlightList.addIdentificationIdFlight(identificationIdFlight);
    }

    public int distinguishPriceEconomyAndBusinessClass(int price, String seatClass){
        int setUpPrice = 0;
        if(seatClass.equalsIgnoreCase("Economy Class")){
            setUpPrice = price;
        }else if(seatClass.equalsIgnoreCase("Business Class")){
            setUpPrice = price*2;
        }
        return setUpPrice;
    }


    public void callInterfaceFlightPayment(Button Choose){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("InterfaceFlightPayment.fxml"));
            Scene scene = Choose.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceFlight2AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceFlight2ScrollPane.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
