package Project1.Information.TaxiPackage;

import java.time.LocalDate;

public class SearchTaxi {
    private String addressDeparture, addressArrival, time, airline, fightNumber;
    private LocalDate pickUpDate;

    public SearchTaxi(String addressDeparture, String addressArrival, LocalDate pickUpDate, String time, String airline, String fightNumber) {
        this.addressDeparture = addressDeparture;
        this.addressArrival = addressArrival;
        this.time = time;
        this.airline = airline;
        this.fightNumber = fightNumber;
        this.pickUpDate = pickUpDate;
    }

    public String getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(String addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public String getAddressArrival() {
        return addressArrival;
    }

    public void setAddressArrival(String addressArrival) {
        this.addressArrival = addressArrival;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFightNumber() {
        return fightNumber;
    }

    public void setFightNumber(String fightNumber) {
        this.fightNumber = fightNumber;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
}
