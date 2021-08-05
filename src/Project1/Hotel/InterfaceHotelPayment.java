package Project1.Hotel;

import Project1.Common.DataConnection;
import Project1.Information.HotelPackage.SearchHotels;
import Project1.Information.HotelPackage.InformationHotelList;
import Project1.Information.User;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InterfaceHotelPayment implements Initializable {
    @FXML
    private AnchorPane interfacePaymentAnchorPane;
    @FXML
    private AnchorPane interfacePaymentAnchorPaneSmall;
    @FXML
    private Label interfaceHotelPaymentUserName;
    @FXML
    private Label interfaceHotelPaymentNameHotel;
    @FXML
    private Label interfaceHotelPaymentPlace;
    @FXML
    private Label interfaceHotelPaymentPriceTotal;
    @FXML
    private Label interfaceHotelPaymentLabelMessage;
    @FXML
    private Button interfaceHotelPaymentButtonPayment;
    @FXML
    private Button interfaceHotelPaymentButtonBack;
    @FXML
    private RadioButton interfaceHotelPaymentRadioPaymentUpon;
    @FXML
    private RadioButton interfaceHotelPaymentRadioMOMO;
    @FXML
    private RadioButton interfaceHotelPaymentRadioBank;

    private String idHotel;
    private String idUser;
    private int priceTotal;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private char personAmount;
    private char nightAmount;
    private char roomAmount;

    private static final String PATHNAME_INTERFACEHOTEL3 = "interfaceHotel3.fxml";
    private static final String PATHNAME_INTERFACElOGIN = "/Project1/UserInterfaceMain/Login.fxml";

    public InterfaceHotelPayment() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < InformationHotelList.getHotelList().size(); i++) {
            if (InformationHotelList.getIdentificationIdHotelList().get(0).getIdentificationId().equals(InformationHotelList.getHotelList().get(i).getIdHotel())){
                // insert label name, place, price hotel in userinterface
                idHotel = InformationHotelList.getHotelList().get(i).getIdHotel();
                interfaceHotelPaymentNameHotel.setText(InformationHotelList.getHotelList().get(i).getNameHotel());
                interfaceHotelPaymentPlace.setText(InformationHotelList.getHotelList().get(i).getPlace());
                int priceHotel = InformationHotelList.getHotelList().get(i).getPrice();
                priceTotal = priceTotal(priceHotel);
                interfaceHotelPaymentPriceTotal.setText("$"+priceTotal);
            }
        }
        // add id User into variable idUser
        for (User user : InformationHotelList.getUserList()){
            idUser = user.getIdUser();
            interfaceHotelPaymentUserName.setText(user.getFullName());
        }
        // add date check-in and check-out into variable checkIn, checkOut
        for (SearchHotels searchHotel : InformationHotelList.getSearchHotelsList()){
            checkIn = searchHotel.getCheckIn();
            checkOut = searchHotel.getCheckOut();
            personAmount = (searchHotel.getGuest()).charAt(0);
            nightAmount = (searchHotel.getDuration()).charAt(0);
            roomAmount = (searchHotel.getRoom()).charAt(0);
        }
        // inform user not login
        if(User.getFlag() == 0){
            interfaceHotelPaymentLabelMessage.setText("You haven't login");
        }
    }

    public void paymentButtonOnAction(ActionEvent event){
        if(User.getFlag() == 0){
            // load logininterface if user haven't login
            callInterface(interfaceHotelPaymentButtonPayment, PATHNAME_INTERFACElOGIN);
        }else if(User.getFlag() == 1) {
            // insert data into database if user was login
            String paymentMethods = "";
            if (interfaceHotelPaymentRadioPaymentUpon.isSelected()) {
                paymentMethods = "Payment upon check in";
            } else if (interfaceHotelPaymentRadioMOMO.isSelected()) {
                paymentMethods = "MOMO";
            } else if (interfaceHotelPaymentRadioBank.isSelected()) {
                paymentMethods = "Bank";
            }

            Connection conn = null;
            PreparedStatement prepar1 = null;
            PreparedStatement prepar2 = null;
            PreparedStatement prepar3 = null;
            PreparedStatement prepar4 = null;
            ResultSet resultSet1 = null;
            ResultSet resultSet2 = null;
            String createNewIdPayment = "";
            String createNewIdOrderRoom = "";
            try {
                conn = new DataConnection().getConnection();

                String sqlSelectOldIdOrder = "{CALL pro_select_id_old_order_hotel()}";
                prepar1 = conn.prepareStatement(sqlSelectOldIdOrder);
                prepar1.executeQuery();
                resultSet1 = prepar1.getResultSet();
                while (resultSet1.next()) {
                    createNewIdOrderRoom = createIdOrder(resultSet1.getString(1));
                }

                String sqlSelectOldIdPayment = "{CALL pro_select_id_old_payment()}";
                prepar2 = conn.prepareStatement(sqlSelectOldIdPayment);
                prepar2.executeQuery();
                resultSet2 = prepar2.getResultSet();
                while (resultSet2.next()) {
                    createNewIdPayment = createIdPayment(resultSet2.getString(1));
                }
                conn.setAutoCommit(false);
                // insert Book_Room
                String sqlInsertBookRoom = "INSERT INTO Book_Room(Id_Order_Hotel,Id_Hotel,Check_IN,Check_Out,Person_Amount,Night_Amount,Room_Amount) VALUE(?,?,?,?,?,?,?)";
                prepar3 = conn.prepareStatement(sqlInsertBookRoom);
                prepar3.setString(1,createNewIdOrderRoom);
                prepar3.setString(2,idHotel);
                prepar3.setDate(3, Date.valueOf(checkIn));
                prepar3.setDate(4, Date.valueOf(checkOut));
                // convert from char type to int
                prepar3.setInt(5,Integer.parseInt(String.valueOf(personAmount)));
                prepar3.setInt(6,Integer.parseInt(String.valueOf(nightAmount)));
                prepar3.setInt(7,Integer.parseInt(String.valueOf(roomAmount)));
                prepar3.executeUpdate();
                // insert table payment
                String sqlInsertPayment = "INSERT INTO Payment(Id_Payment,Id_User,Id_Order_Hotel,Payment_Method,Price_Total) VALUE(?,?,?,?,?)";
                prepar4 = conn.prepareStatement(sqlInsertPayment);
                prepar4.setString(1, createNewIdPayment);
                prepar4.setString(2, idUser);
                prepar4.setString(3, createNewIdOrderRoom);
                prepar4.setString(4, paymentMethods);
                prepar4.setInt(5, priceTotal);
                prepar4.executeUpdate();
                conn.commit();
                interfaceHotelPaymentLabelMessage.setText("Thank you! Payment successful");

            } catch (SQLException throwables) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                throwables.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                    if (prepar1 != null) {
                        prepar1.close();
                    }
                    if (prepar2 != null) {
                        prepar2.close();
                    }
                    if (prepar3 != null) {
                        prepar3.close();
                    }
                    if (prepar4 != null) {
                        prepar4.close();
                    }
                    if (resultSet1 != null) {
                        resultSet1.close();
                    }
                    if (resultSet2 != null) {
                        resultSet2.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void backButtonOnAction(ActionEvent event){
        // back and load interfaceHotel3
        callInterface(interfaceHotelPaymentButtonBack, PATHNAME_INTERFACEHOTEL3);
    }

    // Method calculator price
    public int priceTotal(int price){
        String numberNightString = "";
        for (SearchHotels searchInformation : InformationHotelList.getSearchHotelsList()){
            numberNightString = searchInformation.getDuration();
        }
        char numberNightChar = numberNightString.charAt(0);
        int  numberNightInt = Integer.parseInt(String.valueOf(numberNightChar));
        int $TotalNights = price*numberNightInt;
        int $TotalPayment = $TotalNights+4;
        return $TotalPayment;
    }

    // cretae id payment new
    public String createIdPayment(String id){
        String createIdString1 = id.substring(1);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "P"+createIdInt;
        return createIdString2;
    }

    // cretae id order new
    public String createIdOrder(String id){
        String createIdString1 = id.substring(2);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "OH"+createIdInt;
        return createIdString2;
    }

    public void callInterface(Button button, String pathName){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfacePaymentAnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfacePaymentAnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
