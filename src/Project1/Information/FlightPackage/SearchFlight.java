package Project1.Information.FlightPackage;

import java.time.LocalDate;

public class SearchFlight {
    private String fromLocation,toLocation,adultAmount,childAmount,ifnantAmount,seatClass;
    private LocalDate departureDate;

    public SearchFlight(String fromLocation, String toLocation, String adultAmount, String childAmount, String ifnantAmount,LocalDate departureDate,  String seatClass) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.adultAmount = adultAmount;
        this.childAmount = childAmount;
        this.ifnantAmount = ifnantAmount;
        this.seatClass = seatClass;
        this.departureDate = departureDate;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(String adultAmount) {
        this.adultAmount = adultAmount;
    }

    public String getChildAmount() {
        return childAmount;
    }

    public void setChildAmount(String childAmount) {
        this.childAmount = childAmount;
    }

    public String getIfnantAmount() {
        return ifnantAmount;
    }

    public void setIfnantAmount(String ifnantAmount) {
        this.ifnantAmount = ifnantAmount;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
