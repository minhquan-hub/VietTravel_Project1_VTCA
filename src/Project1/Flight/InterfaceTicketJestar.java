package Project1.Flight;

import Project1.Information.FlightPackage.Flight;
import Project1.Information.FlightPackage.InformationFlightList;
import Project1.Information.FlightPackage.SearchFlight;
import Project1.Information.FlightPackage.UserNameAndSeatTicket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.PortUnreachableException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InterfaceTicketJestar implements Initializable {
    @FXML
    private Label interfaceTicketJestarDate1;
    @FXML
    private Label interfaceTicketJestarName1;
    @FXML
    private Label interfaceTicketJestarFrom1;
    @FXML
    private Label interfaceTicketJestarTo1;
    @FXML
    private Label interfaceTicketJestarTime1;
    @FXML
    private Label interfaceTicketJestarSeat1;
    @FXML
    private Label interfaceTicketJestarFlight1;
    @FXML
    private Label interfaceTicketJestarDate2;
    @FXML
    private Label interfaceTicketJestarName2;
    @FXML
    private Label interfaceTicketJestarSeat2;
    @FXML
    private Label interfaceTicketJestarFrom2;
    @FXML
    private Label interfaceTicketJestarTo2;
    @FXML
    private Label interfaceTicketJestarFlight2;
    @FXML
    private Label interfaceTicketJestarTypeTicket;

    public InterfaceTicketJestar() {
    }

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

    public void showNameFromLocationAndNameToLocation(String nameFromLocation, String nameToLocation) {
        // set name place departure and arrival
        String showNameFromLocation = "";
        if (nameFromLocation.equalsIgnoreCase("TP HCM")) {
            showNameFromLocation = "HOCHIMINHCITY";
        } else if (nameFromLocation.equalsIgnoreCase("Nha Trang")) {
            showNameFromLocation = "CAMRANH";
        } else if (nameFromLocation.equalsIgnoreCase("Hue")) {
            showNameFromLocation = "HUE";
        }

        String showNameToLocation = "";
        if (nameToLocation.equalsIgnoreCase("TP HCM")) {
            showNameToLocation = "HOCHIMINHCITY";
        } else if (nameToLocation.equalsIgnoreCase("Nha Trang")) {
            showNameToLocation = "CAMRANH";
        } else if (nameToLocation.equalsIgnoreCase("Hue")) {
            showNameToLocation = "HUE";
        }

        // show information up interface
        interfaceTicketJestarFrom1.setText(showNameFromLocation);
        interfaceTicketJestarFrom2.setText(showNameFromLocation);
        interfaceTicketJestarTo1.setText(showNameToLocation);
        interfaceTicketJestarTo2.setText(showNameToLocation);
    }

    // show date and flight up interface
    public void showDateFlightNumberAndSeatClass(String flightNumber){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (SearchFlight searchFlight : InformationFlightList.getSearchFlightList()){
            LocalDate departureDate = searchFlight.getDepartureDate();
            interfaceTicketJestarDate1.setText(departureDate.format(dateTimeFormatter));
            interfaceTicketJestarDate2.setText(departureDate.format(dateTimeFormatter));
            interfaceTicketJestarTypeTicket.setText(searchFlight.getSeatClass());
        }
        interfaceTicketJestarFlight1.setText(flightNumber);
        interfaceTicketJestarFlight2.setText(flightNumber);
    }

    public void showNameTimeAndSeat(String time){
        String fullname = "";
        String positionSeat = "";

        for(UserNameAndSeatTicket userNameAndSeatTicket : InformationFlightList.getUserNameTicketList()){
            fullname = userNameAndSeatTicket.getUsername();
            positionSeat = userNameAndSeatTicket.getPositionSeat();
        }

        // User name own ticket
        interfaceTicketJestarName1.setText(fullname.toUpperCase());
        interfaceTicketJestarName2.setText(fullname.toUpperCase());
        // time take off
        interfaceTicketJestarTime1.setText(time);
        // seat
        String firstSeat = positionSeat.substring(0,2);
        interfaceTicketJestarSeat1.setText(firstSeat);
        interfaceTicketJestarSeat2.setText(firstSeat);
    }


}
