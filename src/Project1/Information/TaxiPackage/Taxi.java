package Project1.Information.TaxiPackage;

public class Taxi {
    private String idTaxi, carName, companyName;
    private int passengerAmount, baggageAmount, price;
    private String link;

    public Taxi(String idTaxi, String carName, String companyName, int passengerAmount, int baggageAmount, int price, String link) {
        this.idTaxi = idTaxi;
        this.carName = carName;
        this.companyName = companyName;
        this.passengerAmount = passengerAmount;
        this.baggageAmount = baggageAmount;
        this.price = price;
        this.link = link;
    }

    public String getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(String idTaxi) {
        this.idTaxi = idTaxi;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(int passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public int getBaggageAmount() {
        return baggageAmount;
    }

    public void setBaggageAmount(int baggageAmount) {
        this.baggageAmount = baggageAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
