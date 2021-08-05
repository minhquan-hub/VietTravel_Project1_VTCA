package Project1.Flight;

import Project1.Information.FlightPackage.Flight;
import Project1.Information.FlightPackage.InformationFlightList;
import Project1.Information.FlightPackage.SearchFlight;
import Project1.Information.FlightPackage.UserNameAndSeatTicket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InterfaceTicketVietjet implements Initializable {
    @FXML
    private Label interfaceTicketVietjetTypeTicket;
    @FXML
    private Label interfaceTicketVietjetName1;
    @FXML
    private Label interfaceTicketVietjetFrom1;
    @FXML
    private Label interfaceTicketVietjetTo1;
    @FXML
    private Label interfaceTicketvietjetFlight1;
    @FXML
    private Label interfaceTicketVietjetSeat1;
    @FXML
    private Label interfaceVietjetDate1;
    @FXML
    private Label interfaceTicketVietjetTime;
    @FXML
    private Label interfaceTicketVietjetFrom2;
    @FXML
    private Label interfaceTicketVietjetTo2;
    @FXML
    private Label interfaceTicketVietjetName2;
    @FXML
    private Label interfaceTicketvietjetFlight2;
    @FXML
    private Label interfaceVietjetDate2;
    @FXML
    private Label interfaceTicketVietjetSeat2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Flight flight : InformationFlightList.getFlightList()){
            if(flight.getIdTickets().equals(InformationFlightList.getIdentificationIdFlightList().get(0).getIdentificationId())){
                showNameFromLocationAndNameToLocation(flight.getNameFromLocation(),flight.getNameToLocation());
                showNameTimeAndSeat(flight.getTimeFromLocation());
                showDateFlightNumberAndSeatClass(flight.getIdTickets());
            }
        }
    }

    public void showNameFromLocationAndNameToLocation(String nameFromLocation, String nameToLocation){
        // set name place departure and arrival
        String showNameFromLocation = "";
        if(nameFromLocation.equalsIgnoreCase("TP HCM")){
            showNameFromLocation = "HOCHIMINHCITY";
        }else if(nameFromLocation.equalsIgnoreCase("Nha Trang")){
            showNameFromLocation = "CAMRANH";
        }else if(nameFromLocation.equalsIgnoreCase("Hue")){
            showNameFromLocation = "HUE";
        }

        String showNameToLocation = "";
        if(nameToLocation.equalsIgnoreCase("TP HCM")){
            showNameToLocation = "HOCHIMINHCITY";
        }else if(nameToLocation.equalsIgnoreCase("Nha Trang")){
            showNameToLocation = "CAMRANH";
        }else if(nameToLocation.equalsIgnoreCase("Hue")){
            showNameToLocation = "HUE";
        }

        // show information up interface
        interfaceTicketVietjetFrom1.setText(showNameFromLocation);
        interfaceTicketVietjetFrom2.setText(showNameFromLocation);
        interfaceTicketVietjetTo1.setText(showNameToLocation);
        interfaceTicketVietjetTo2.setText(showNameToLocation);
    }

    // show date and flight up interface
    public void showDateFlightNumberAndSeatClass(String flightNumber){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (SearchFlight searchFlight : InformationFlightList.getSearchFlightList()){
            LocalDate departureDate = searchFlight.getDepartureDate();
            interfaceVietjetDate1.setText(departureDate.format(dateTimeFormatter));
            interfaceVietjetDate2.setText(departureDate.format(dateTimeFormatter));
            interfaceTicketVietjetTypeTicket.setText(searchFlight.getSeatClass());
        }
        interfaceTicketvietjetFlight1.setText(flightNumber);
        interfaceTicketvietjetFlight2.setText(flightNumber);
    }

    public void showNameTimeAndSeat(String time){
        String fullname = "";
        String positionSeat = "";

        for(UserNameAndSeatTicket userNameAndSeatTicket : InformationFlightList.getUserNameTicketList()){
            fullname = userNameAndSeatTicket.getUsername();
            positionSeat = userNameAndSeatTicket.getPositionSeat();
        }
        // User name own ticket
        interfaceTicketVietjetName1.setText(fullname.toUpperCase());
        interfaceTicketVietjetName2.setText(fullname.toUpperCase());
        // time take off
        interfaceTicketVietjetTime.setText(time);
        // seat
        String firstSeat = positionSeat.substring(0,3);
        interfaceTicketVietjetSeat1.setText(firstSeat);
        interfaceTicketVietjetSeat2.setText(firstSeat);
    }
}
