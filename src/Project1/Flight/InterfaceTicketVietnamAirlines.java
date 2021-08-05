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

public class InterfaceTicketVietnamAirlines implements Initializable {
    @FXML
    private Label interfaceTicketVietnamAirlinesTypeTicket;
    @FXML
    private Label interfaceTicketVietnamAirlinesName1;
    @FXML
    private Label interfaceTicketVietnamAirlinesFrom1;
    @FXML
    private Label interfaceTicketVietnamAirlinesTo1;
    @FXML
    private Label interfaceTicketVietnamAirlinesFlight1;
    @FXML
    private Label interfaceTicketVietnamAirlinesTime;
    @FXML
    private Label interfaceTicketVietnamAirlinesSeat1;
    @FXML
    private Label interfaceTicketVietnamAirlinesDate1;
    @FXML
    private Label interfaceTicketVietnamAirlinesFrom2;
    @FXML
    private Label interfaceTicketVietnamAirlinesTo2;
    @FXML
    private Label interfaceTicketVietnamAirlinesDate2;
    @FXML
    private Label interfaceTicketVietnamAirlinesSeat2;
    @FXML
    private Label interfaceTicketVietnamAirlinesFlight2;
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
            showNameFromLocation = "HO CHI MINH CITY";
        }else if(nameFromLocation.equalsIgnoreCase("Nha Trang")){
            showNameFromLocation = "CAM RANH";
        }else if(nameFromLocation.equalsIgnoreCase("Hue")){
            showNameFromLocation = "HUE";
        }

        String showNameToLocation = "";
        if(nameToLocation.equalsIgnoreCase("TP HCM")){
            showNameToLocation = "HO CHI MINH CITY";
        }else if(nameToLocation.equalsIgnoreCase("Nha Trang")){
            showNameToLocation = "CAM RANH";
        }else if(nameToLocation.equalsIgnoreCase("Hue")){
            showNameToLocation = "HUE";
        }

        // show information up interface
        interfaceTicketVietnamAirlinesFrom1.setText(showNameFromLocation);
        interfaceTicketVietnamAirlinesFrom2.setText(showNameFromLocation);
        interfaceTicketVietnamAirlinesTo1.setText(showNameToLocation);
        interfaceTicketVietnamAirlinesTo2.setText(showNameToLocation);
    }

    // show date and flight up interface
    public void showDateFlightNumberAndSeatClass(String flightNumber){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (SearchFlight searchFlight : InformationFlightList.getSearchFlightList()){
            LocalDate departureDate = searchFlight.getDepartureDate();
            interfaceTicketVietnamAirlinesDate1.setText(departureDate.format(dateTimeFormatter));
            interfaceTicketVietnamAirlinesDate2.setText(departureDate.format(dateTimeFormatter));
            interfaceTicketVietnamAirlinesTypeTicket.setText(searchFlight.getSeatClass());
        }
        interfaceTicketVietnamAirlinesFlight1.setText(flightNumber);
        interfaceTicketVietnamAirlinesFlight2.setText(flightNumber);
    }

    public void showNameTimeAndSeat(String time){
        String fullname = "";
        String positionSeat = "";

        for(UserNameAndSeatTicket userNameAndSeatTicket : InformationFlightList.getUserNameTicketList()){
            fullname = userNameAndSeatTicket.getUsername();
            positionSeat = userNameAndSeatTicket.getPositionSeat();
        }
        // User name own ticket
        interfaceTicketVietnamAirlinesName1.setText(fullname.toUpperCase());
        // time take off
        interfaceTicketVietnamAirlinesTime.setText(time);
        // seat
        String firstSeat = positionSeat.substring(0,3);
        interfaceTicketVietnamAirlinesSeat1.setText(firstSeat);
        interfaceTicketVietnamAirlinesSeat2.setText(firstSeat);
    }
}
