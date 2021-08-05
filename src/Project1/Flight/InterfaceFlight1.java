package Project1.Flight;

import Project1.Common.DataConnection;
import Project1.Information.FlightPackage.Flight;
import Project1.Information.FlightPackage.InformationFlightList;
import Project1.Information.FlightPackage.SearchFlight;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InterfaceFlight1 implements Initializable {
    @FXML
    private AnchorPane interfaceFlight1AnchorPane;
    @FXML
    private AnchorPane interfaceFlight1AnchorPaneSmall;
    @FXML
    private ComboBox<String> interfaceFlight1ComboBox_fromLocation;
    @FXML
    private ComboBox<String> interfaceFlight1ComboBox_toLocation;
    @FXML
    private ComboBox<String> interfaceFlight1ComboBoxNo_Adult;
    @FXML
    private DatePicker interfaceFlight1DatePickerDepartureDate;
    @FXML
    private ComboBox<String> interfaceFlight1ComboBoxSeatClass;
    @FXML
    private Button interfaceFlight2ButtonSearchTickets;
    @FXML
    private ComboBox<String> interfaceFlight1ComboBoxNo_Child;
    @FXML
    private ComboBox<String> interfaceFlight1ComboBoxNo_Infant;
    @FXML
    private Label interfaceFlight1Message;
    private LocalDate DepartureDate;

    public InterfaceFlight1() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> LocationList = FXCollections.observableArrayList("Nha Trang", "Hue", "Tp HCM");
        interfaceFlight1ComboBox_fromLocation.setItems(LocationList);
        interfaceFlight1ComboBox_toLocation.setItems(LocationList);

        ObservableList<String> No_Adults = FXCollections.observableArrayList("1 Adult", "2 Adults", "3 Adults", "4 Adults", "5 Adults", "6 Adults", "7 Adults", "8 Adults");
        ObservableList<String> No_Children = FXCollections.observableArrayList("0 Child", "1 Child", "2 Children", "3 Children", "4 Children", "5 Children", "6 Children", "7 Children", "8 Children");
        ObservableList<String> No_Infants = FXCollections.observableArrayList("0 Infant", "1 Infant", "2 Infants", "3 Infants", "4 Infants", "5 Infants", "6 Infants", "7 Infants", "8 Infants");
        interfaceFlight1ComboBoxNo_Adult.setItems(No_Adults);
        interfaceFlight1ComboBoxNo_Child.setItems(No_Children);
        interfaceFlight1ComboBoxNo_Infant.setItems(No_Infants);

        ObservableList<String> SeatClass = FXCollections.observableArrayList("Economy Class", "Business Class");
        interfaceFlight1ComboBoxSeatClass.setItems(SeatClass);
    }

    public void searchTicketsButtonOnAction(ActionEvent event){
        if (interfaceFlight1ComboBox_fromLocation.getValue() != null &&
                interfaceFlight1ComboBox_toLocation.getValue() != null &&
                interfaceFlight1ComboBoxNo_Adult.getValue() != null &&
                interfaceFlight1ComboBoxNo_Child.getValue() != null &&
                interfaceFlight1ComboBoxNo_Infant.getValue() != null &&
                interfaceFlight1DatePickerDepartureDate.getValue() != null &&
                interfaceFlight1ComboBoxSeatClass.getValue() != null){
            //check from and to location
            if (interfaceFlight1ComboBox_fromLocation.getValue() == interfaceFlight1ComboBox_toLocation.getValue()){
                interfaceFlight1Message.setText("Departure and destination city must differ!");
            }
            else if (interfaceFlight1ComboBox_fromLocation.getSelectionModel().getSelectedItem().toString().equals("Nha Trang") && interfaceFlight1ComboBox_toLocation.getSelectionModel().getSelectedItem().toString().equals("Hue") ||
                    interfaceFlight1ComboBox_fromLocation.getSelectionModel().getSelectedItem().toString().equals("Hue") && interfaceFlight1ComboBox_toLocation.getSelectionModel().getSelectedItem().toString().equals("Nha Trang")) {
                interfaceFlight1Message.setText("Sorry! Out of Tickets");
            }
            else {
                String nameFromLocation = interfaceFlight1ComboBox_fromLocation.getSelectionModel().getSelectedItem().toString();
                String nameToLocation = interfaceFlight1ComboBox_toLocation.getSelectionModel().getSelectedItem().toString();
                DepartureDate = interfaceFlight1DatePickerDepartureDate.getValue();
                String seatClass = interfaceFlight1ComboBoxSeatClass.getSelectionModel().getSelectedItem().toString();
                String Adult = interfaceFlight1ComboBoxNo_Adult.getSelectionModel().getSelectedItem().toString();
                String Child = interfaceFlight1ComboBoxNo_Child.getSelectionModel().getSelectedItem().toString();
                String Infant = interfaceFlight1ComboBoxNo_Infant.getSelectionModel().getSelectedItem().toString();

                // create class and save data into searchFlightList
                SearchFlight searchFlight = new SearchFlight(nameFromLocation, nameToLocation, Adult, Child, Infant, DepartureDate, seatClass);
                InformationFlightList informationFlightList = new InformationFlightList();
                informationFlightList.addSearchFlight(searchFlight);

                // select database of Tickets
                flightData(nameFromLocation, nameToLocation);

                // load interfaceFlight2
                callInterfaceFlight2();
            }
        } else {
            interfaceFlight1Message.setText("Please! You should fill all the fields");
        }
    }

    public void flightData(String nameFrom, String nameTo){
        Connection conn = null;
        PreparedStatement prepar = null;
        ResultSet resultSet = null;
        InterfaceFlight2 interfaceFlight2 = new InterfaceFlight2();
        try {
            conn = new DataConnection().getConnection();
            String sqlSelectInformationTicket = "{CALL pro_select_tickets(?, ?)}";
            prepar = conn.prepareStatement(sqlSelectInformationTicket);
            prepar.setString(1, nameFrom);
            prepar.setString(2, nameTo);
            prepar.executeQuery();
            resultSet = prepar.getResultSet();
            while (resultSet.next()){
                String idTicket = resultSet.getString(1);
                String idFromLocation = resultSet.getString(2);
                String idToLocation = resultSet.getString(3);
                String nameFromLocation = resultSet.getString(4);
                String nameToLocation = resultSet.getString(5);
                String timeFromLocation = resultSet.getString(6);
                String timeToLocation = resultSet.getString(7);
                String duration = resultSet.getString(8);
                int price = resultSet.getInt(9);
                String brand = resultSet.getString(11);


                Flight Ticket = new Flight(idTicket, idFromLocation, idToLocation, nameFromLocation, nameToLocation, timeTrim(timeFromLocation), timeTrim(timeToLocation), duration, price, brand);
                InformationFlightList informationFlightList = new InformationFlightList();
                informationFlightList.addFlight(Ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (conn != null){
                    conn.close();
                }
                if (prepar != null){
                    prepar.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public String timeTrim(String timeTrim){
        return  timeTrim.substring(0,5);
    }

    public void callInterfaceFlight2(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("interfaceFlight2.fxml"));
            Scene scene = interfaceFlight2ButtonSearchTickets.getScene();
            root.translateYProperty().set(scene.getWidth());
            interfaceFlight1AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceFlight1AnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

