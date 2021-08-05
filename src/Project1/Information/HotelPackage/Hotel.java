package Project1.Information.HotelPackage;

public class Hotel {
    private String idHotel,nameHotel,place;
    private int price;
    private HotelLink urlLink;

    public Hotel(String idHotel, String nameHotel, String place, HotelLink urlLink, int price) {
        this.idHotel = idHotel;
        this.nameHotel = nameHotel;
        this.place = place;
        this.urlLink = urlLink;
        this.price = price;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public HotelLink getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(HotelLink urlLink) {
        this.urlLink = urlLink;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
