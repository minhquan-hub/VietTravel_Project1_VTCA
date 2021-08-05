package Project1.Information.HotelPackage;

import Project1.Information.User;


import java.util.ArrayList;

public class InformationHotelList {
    private static ArrayList<User> userList = new ArrayList<User>();
    private static ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
    private static ArrayList<SearchHotels> searchHotelsList = new ArrayList<SearchHotels>();
    private static ArrayList<IdentificationIdHotel> identificationIdHotelList = new ArrayList<>();

    public InformationHotelList() {
    }

    // method add into list
    public void addUser(User user){
        userList.add(user);
    }

    public void addHotel(Hotel hotel){
        hotelList.add(hotel);
    }

    public void addSearchHotels(SearchHotels searchHotels){
        searchHotelsList.add(searchHotels);
    }
    public void addIdentificationIdHotel(IdentificationIdHotel identificationId){
        identificationIdHotelList.add(identificationId);
    }

    // method delete in the list
    public void deleteUser(){
        userList.clear();
    }
    public void deleteHotel(){
        hotelList.clear();
    }
    public void deleteSearchHotels(){
        searchHotelsList.clear();
    }
    public void deleteIdentificationIdHotel(){
        identificationIdHotelList.clear();
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<User> userList) {
        InformationHotelList.userList = userList;
    }

    public static ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

    public static void setHotelList(ArrayList<Hotel> hotelList) {
        InformationHotelList.hotelList = hotelList;
    }

    public static ArrayList<SearchHotels> getSearchHotelsList() {
        return searchHotelsList;
    }

    public static void setSearchHotelsList(ArrayList<SearchHotels> searchHotelsList) {
        InformationHotelList.searchHotelsList = searchHotelsList;
    }

    public static ArrayList<IdentificationIdHotel> getIdentificationIdHotelList() {
        return identificationIdHotelList;
    }

    public static void setIdentificationIdHotelList(ArrayList<IdentificationIdHotel> identificationIdHotelList) {
        InformationHotelList.identificationIdHotelList = identificationIdHotelList;
    }
}
