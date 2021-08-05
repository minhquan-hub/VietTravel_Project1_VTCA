package Project1.Information.FlightPackage;

import java.sql.Time;

public class Flight {
    private String idTickets, idFromLocation, idToLocation, nameFromLocation, nameToLocation, duration, brand;
    private String timeFromLocation, timeToLocation;
    private int price;

    public Flight(String idTickets, String idFromLocation, String idToLocation, String nameFromLocation, String nameToLocation, String timeFromLocation, String timeToLocation, String duration, int price, String brand) {
        this.idTickets = idTickets;
        this.idFromLocation = idFromLocation;
        this.idToLocation = idToLocation;
        this.nameFromLocation = nameFromLocation;
        this.nameToLocation = nameToLocation;
        this.timeFromLocation = timeFromLocation;
        this.timeToLocation = timeToLocation;
        this.duration = duration;
        this.price = price;
        this.brand = brand;
    }

    public String getIdTickets() {
        return idTickets;
    }

    public void setIdTickets(String idTickets) {
        this.idTickets = idTickets;
    }

    public String getIdFromLocation() {
        return idFromLocation;
    }

    public void setIdFromLocation(String idFromLocation) {
        this.idFromLocation = idFromLocation;
    }

    public String getIdToLocation() {
        return idToLocation;
    }

    public void setIdToLocation(String idToLocation) {
        this.idToLocation = idToLocation;
    }

    public String getNameFromLocation() {
        return nameFromLocation;
    }

    public void setNameFromLocation(String nameFromLocation) {
        this.nameFromLocation = nameFromLocation;
    }

    public String getNameToLocation() {
        return nameToLocation;
    }

    public void setNameToLocation(String nameToLocation) {
        this.nameToLocation = nameToLocation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTimeFromLocation() {
        return timeFromLocation;
    }

    public void setTimeFromLocation(String timeFromLocation) {
        this.timeFromLocation = timeFromLocation;
    }

    public String getTimeToLocation() {
        return timeToLocation;
    }

    public void setTimeToLocation(String timeToLocation) {
        this.timeToLocation = timeToLocation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
