package Project1.Hotel;

import Project1.Common.DataConnection;
import Project1.Information.HotelPackage.Hotel;
import Project1.Information.HotelPackage.HotelLink;
import Project1.Information.HotelPackage.SearchHotels;
import Project1.Information.HotelPackage.InformationHotelList;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class InterfaceHotel1 implements Initializable {
    @FXML
    private AnchorPane interfaceHotel1AnchorPane;
    @FXML
    private AnchorPane interfaceHotel1AnchorPaneSmall;
    @FXML
    private ComboBox interfaceHotel1ComboBoxDestination;
    @FXML
    private ComboBox interfaceHotel1ComboBoxDuration;
    @FXML
    private ComboBox interfaceHotel1ComboBoxGuest;
    @FXML
    private ComboBox interfaceHotel1ComboBoxRoom;
    @FXML
    private Button interfaceHotel3ButtonSearchHotels;
    @FXML
    private DatePicker interfaceHotel1DatePickerCheckIn;
    @FXML
    private Label interfaceHotel3Message;
    @FXML
    private Label interfaceHotel3LabelCheckOut;

    private LocalDate dateCheckIn ;
    private LocalDate dateCheckOut;

    public InterfaceHotel1() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // insert information into user interface
        ObservableList<String> destinationList = FXCollections.observableArrayList("Nha Trang","Hue");
        interfaceHotel1ComboBoxDestination.setItems(destinationList);

        ObservableList<String> durationList = FXCollections.observableArrayList("1 Night","2 Nights","3 Nights","4 Nights","5 Nights","6 Nights","7 Nights");
        interfaceHotel1ComboBoxDuration.setItems(durationList);

        ObservableList<String> guestList = FXCollections.observableArrayList("1 Person","2 Persons","3 Persons","4 Persons","5 Persons","6 Persons","7 Persons");
        interfaceHotel1ComboBoxGuest.setItems(guestList);

        ObservableList<String> roomList = FXCollections.observableArrayList("1 Room","2 Rooms","3 Rooms","4 Rooms","5 Rooms","6 Rooms","7 Rooms");
        interfaceHotel1ComboBoxRoom.setItems(roomList);
    }

    // method event when press button datapicker
    public void datePickerCheckInOnAction(ActionEvent event){
        dateCheckIn = interfaceHotel1DatePickerCheckIn.getValue();
        if(interfaceHotel1ComboBoxDuration.getValue() != null){
           showCheckOut();
        }
    }

    // method event when press button duration to addition amount date
    public void durationComboxOnAction(ActionEvent event){
        if(interfaceHotel1DatePickerCheckIn.getValue() != null) {
            showCheckOut();
        }

    }

    // method event when press button Search Hotels
    public void searchHotelsButtonOnAction(ActionEvent event){
        if( interfaceHotel1ComboBoxDestination.getValue() != null && interfaceHotel1ComboBoxDuration.getValue() != null && interfaceHotel1ComboBoxGuest.getValue() != null && interfaceHotel1ComboBoxDuration.getValue() != null && interfaceHotel1ComboBoxRoom.getValue() != null) {
        // data user enter into UI
        String destination = interfaceHotel1ComboBoxDestination.getSelectionModel().getSelectedItem().toString();
        String duration = interfaceHotel1ComboBoxDuration.getSelectionModel().getSelectedItem().toString();
        String guest = interfaceHotel1ComboBoxGuest.getSelectionModel().getSelectedItem().toString();
        String room = interfaceHotel1ComboBoxRoom.getSelectionModel().getSelectedItem().toString();

            // create class and save data into searchHotelsList
            SearchHotels searchHotels = new SearchHotels(destination, duration, guest, room, dateCheckIn, dateCheckOut);
            InformationHotelList informationHotelList = new InformationHotelList();
            informationHotelList.addSearchHotels(searchHotels);

            // select database of Hotel
            String idPlace = "";
            if (destination.equals("Nha Trang")) {
                idPlace = "HNT";
            } else if (destination.equals("Hue")) {
                idPlace = "HH";
            }
            hotelData(idPlace);

            // load interfaceHotel2
            callInterfaceHotel2();
        }else {
            interfaceHotel3Message.setText("Please! You should fill all the fields");
        }

    }

    // Query data from the database
    public void hotelData(String idPlace){
        Connection conn = null;
        PreparedStatement prepar = null;
        ResultSet resultSet = null;
        InterfaceHotel2 interfaceHotel2 = new InterfaceHotel2();
        try {
            conn = new DataConnection().getConnection();
            String sqlSelectInformationHotel = "{CALL  pro_select_hotel(?)}";
            prepar = conn.prepareStatement(sqlSelectInformationHotel);
            prepar.setString(1,idPlace);
            prepar.executeQuery();
            resultSet = prepar.getResultSet();
            while (resultSet.next()){
                String idHotel = resultSet.getString(1);
                String nameHotel = resultSet.getString(2);
                String place = resultSet.getString(3);
                String urlLink = resultSet.getString(4);
                int  price = resultSet.getInt(5);

                // separate urlLink when take from the database
                int count = 0;
                int i = 0;
                String image[] = new String[5];
                do {
                    String temp = Separate(urlLink);
                    image[i] = temp;
                    temp = temp + ',';
                    urlLink = urlLink.replace(temp, "");
                    count++;
                    i++;
                } while (count != 5);

                // create Object HotelLink
                HotelLink link = new HotelLink(image[0],image[1],image[2],image[3],image[4]);
                // create object Hotel and add into list
                Hotel hotel = new Hotel(idHotel,nameHotel,place,link,price);
                InformationHotelList informationHotelList = new InformationHotelList();
                informationHotelList.addHotel(hotel);
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // method separate urlLink
    public static String Separate(String Link){
        int i = 0;
        String GetLink = "";
        while (Link.charAt(i) != ','){
            GetLink = GetLink.concat(String.valueOf(Link.charAt(i)));
            i++;
            if (i == Link.length()) break;
        }
        return GetLink;
    }

    public void showCheckOut(){
        String duration = interfaceHotel1ComboBoxDuration.getSelectionModel().getSelectedItem().toString();
        char number = duration.charAt(0);
        int numberdate = Integer.parseInt(String.valueOf(number));
        // convert form date
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dateCheckOut = dateCheckIn.plusDays(numberdate);
        String dateCheckOut1 = dateCheckOut.format(dateTimeFormatter);
        // insert user interface
        interfaceHotel3LabelCheckOut.setText(dateCheckOut1.toString());
    }

    public void callInterfaceHotel2(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("interfaceHotel2.fxml"));
            Scene scene = interfaceHotel3ButtonSearchHotels.getScene();
            root.translateYProperty().set(scene.getWidth());
            interfaceHotel1AnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.15), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceHotel1AnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
