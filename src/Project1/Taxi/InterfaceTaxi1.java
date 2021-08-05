package Project1.Taxi;

import Project1.Common.DataConnection;
import Project1.Information.TaxiPackage.InformationTaxiList;
import Project1.Information.TaxiPackage.SearchTaxi;
import Project1.Information.TaxiPackage.Taxi;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InterfaceTaxi1 implements Initializable {
    @FXML
    private AnchorPane interfaceTaxi1AnchorPane;

    @FXML
    private AnchorPane interfaceTaxi1AnchorPaneSmall;

    @FXML
    private ComboBox<String> interfaceTaxi1ComboxFrom;

    @FXML
    private TextField intertfaceTaxi1TextFieldTo;

    @FXML
    private DatePicker interfaceTaxi1DatePicker;

    @FXML
    private ComboBox<String> interfaceTaxi1ComboxHour;

    @FXML
    private ComboBox<String> interfaceTaxi1ComboxMinutes;

    @FXML
    private Button interfaceTaxi1ButonSearchTaxi;

    @FXML
    private ComboBox<String> interfaceTaxi1ComboxAirline;

    @FXML
    private TextField intertfaceTaxi1FightNumber;

    @FXML
    private Label interfaceTaxi1LabelMessage;

    public InterfaceTaxi1() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> fromLocationList = FXCollections.observableArrayList("Ho Chi Minh (SGN)", "Hue (HUI)", "Cam Ranh (CXR)");
        interfaceTaxi1ComboxFrom.setItems(fromLocationList);

        ObservableList<String> hourList = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        interfaceTaxi1ComboxHour.setItems(hourList);

        ObservableList<String> minutesList = FXCollections.observableArrayList("0","5","10","15","20","25","30","35","40","45","50","55");
        interfaceTaxi1ComboxMinutes.setItems(minutesList);

        ObservableList<String> airlineList = FXCollections.observableArrayList("Vietnam Airlines","Vietjet Air","Jetstar");
        interfaceTaxi1ComboxAirline.setItems(airlineList);
    }

    public void searchTaxiButtonOnAction(ActionEvent event){
        if(interfaceTaxi1ComboxFrom.getValue() != null && intertfaceTaxi1TextFieldTo.getText() != null && interfaceTaxi1DatePicker.getValue() != null && interfaceTaxi1ComboxHour.getValue() != null && interfaceTaxi1ComboxMinutes.getValue() != null && interfaceTaxi1ComboxAirline.getValue() != null && intertfaceTaxi1FightNumber.getText() != null) {
            String addressdeparture = interfaceTaxi1ComboxFrom.getSelectionModel().getSelectedItem().toString();
            String addressarrival = intertfaceTaxi1TextFieldTo.getText();
            LocalDate date = interfaceTaxi1DatePicker.getValue();
            String hour = interfaceTaxi1ComboxHour.getSelectionModel().getSelectedItem().toString();
            String minutes = interfaceTaxi1ComboxMinutes.getSelectionModel().getSelectedItem().toString();
            String airline = interfaceTaxi1ComboxAirline.getSelectionModel().getSelectedItem().toString();
            String flightNumber = intertfaceTaxi1FightNumber.getText();

            // group hour and minutes from enter from interface
            String time = hour +":"+ minutes;

            SearchTaxi searchTaxi = new SearchTaxi(addressdeparture, addressarrival, date, time, airline, flightNumber);
            InformationTaxiList informationTaxiList = new InformationTaxiList();
            informationTaxiList.addSearchTaxi(searchTaxi);
            // method take data from database
            takeTaxiData();
            //method load next interface
            callInterface(interfaceTaxi1ButonSearchTaxi,"InterfaceTaxi2.fxml");
        }else {
                interfaceTaxi1LabelMessage.setText("Please! You should fill all the fields");
        }
    }

    public void takeTaxiData(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = new DataConnection().getConnection();
            statement = connection.createStatement();
            String sqlSelectTaxi = "SELECT * FROM stravel.taxi";
            resultSet = statement.executeQuery(sqlSelectTaxi);
            while (resultSet.next()){
                String idTaxi = resultSet.getString(1);
                String carName = resultSet.getString(2);
                String companyName = resultSet.getString(3);
                int passengerAmount = resultSet.getInt(4);
                int baggage = resultSet.getInt(5);
                int price = resultSet.getInt(6);
                String imageLink = resultSet.getString(7);

                Taxi taxi = new Taxi(idTaxi, carName, companyName, passengerAmount, baggage, price, imageLink);
                InformationTaxiList informationTaxiList = new InformationTaxiList();
                informationTaxiList.addTaxi(taxi);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void callInterface(Button button, String pathName){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceTaxi1AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceTaxi1AnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
