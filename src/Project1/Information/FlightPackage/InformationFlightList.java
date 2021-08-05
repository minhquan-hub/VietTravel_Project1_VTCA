package Project1.Information.FlightPackage;

import java.util.ArrayList;

public class InformationFlightList {
    private static ArrayList<Flight> flightList = new ArrayList<>();
    private static ArrayList<SearchFlight> searchFlightList = new ArrayList<>();
    private static ArrayList<IdentificationIdFlight> identificationIdFlightList = new ArrayList<IdentificationIdFlight>();
    private static ArrayList<UserNameAndSeatTicket> userNameAndSeatTicketList = new ArrayList<>();

    public InformationFlightList() {
    }

    public void addFlight(Flight flight){
        flightList.add(flight);
    }

    public void addSearchFlight(SearchFlight searchFlight){
        searchFlightList.add(searchFlight);
    }

    public void addIdentificationIdFlight(IdentificationIdFlight identificationId){
        identificationIdFlightList.add(identificationId);
    }

    public void addUserNameAndSeatTicket(UserNameAndSeatTicket userNameAndSeatTicket){
        userNameAndSeatTicketList.add(userNameAndSeatTicket);
    }

    public void deleteFlight(){
        flightList.clear();
    }

    public void deleteSearchFlight(){
        searchFlightList.clear();
    }

    public void deleteIdentificationIdFlight(){
        identificationIdFlightList.clear();
    }

    public void deleteUserNameTicket(){
        userNameAndSeatTicketList.clear();
    }

    public static ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public static void setFlightList(ArrayList<Flight> flightList) {
        InformationFlightList.flightList = flightList;
    }

    public static ArrayList<SearchFlight> getSearchFlightList() {
        return searchFlightList;
    }

    public static void setSearchFlightList(ArrayList<SearchFlight> searchFlightList) {
        InformationFlightList.searchFlightList = searchFlightList;
    }

    public static ArrayList<IdentificationIdFlight> getIdentificationIdFlightList() {
        return identificationIdFlightList;
    }

    public static void setIdentificationIdFlightList(ArrayList<IdentificationIdFlight> identificationIdFlightList) {
        InformationFlightList.identificationIdFlightList = identificationIdFlightList;
    }

    public static ArrayList<UserNameAndSeatTicket> getUserNameTicketList() {
        return userNameAndSeatTicketList;
    }

    public static void setUserNameTicketList(ArrayList<UserNameAndSeatTicket> userNameAndSeatTicketList) {
        InformationFlightList.userNameAndSeatTicketList = userNameAndSeatTicketList;
    }
}
