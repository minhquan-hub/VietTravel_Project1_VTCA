package Project1.Taxi;

import Project1.Common.DataConnection;
import Project1.Information.HotelPackage.InformationHotelList;
import Project1.Information.TaxiPackage.InformationTaxiList;
import Project1.Information.TaxiPackage.SearchTaxi;
import Project1.Information.TaxiPackage.Taxi;
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
import javafx.scene.control.*;
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

public class InterfaceTaxiPayment implements Initializable {
    @FXML
    private AnchorPane interfaceTaxiPaymentAnchorPane;
    @FXML
    private AnchorPane interfaceTaxiPaymentAnchorPaneSmall;
    @FXML
    private Label interfaceTaxiPaymentUserName;
    @FXML
    private Label interfaceTaxiPaymentDate;
    @FXML
    private Label interfaceTaxiPaymentTime;
    @FXML
    private Label interfaceTaxiPaymentAirline;
    @FXML
    private Label interfaceTaxiPaymentFlightNumber;
    @FXML
    private Label interfaceTaxiPaymentDeparture;
    @FXML
    private Label interfaceTaxiPaymentArrival;
    @FXML
    private Label interfaceTaxiPaymentMessage;
    @FXML
    private TextField interfacTaxiPaymentTextFieldNote;
    @FXML
    private Label interfaceTaxiPaymentNumberCar;
    @FXML
    private Label interfaceTaxiPaymentPrice;
    @FXML
    private RadioButton interfaceTaxiPaymentRadioPaymentUpon;
    @FXML
    private RadioButton interfaceTaxiPaymentRadioMoMo;
    @FXML
    private RadioButton interfaceTaxiPaymentRadioBank;
    @FXML
    private Button interfaceTaxiPaymentButtonPayment;
    @FXML
    private Button interfaceTaxiPaymentButtonBack;

    private int numberCar = 1;
    private int price = 0;
    private int originPrice = 0;
    private String fullName = "";
    private String idUser = "";
    private String idTaxi = "";
    private LocalDate date = null;
    private String time = "";
    private String airline = "";
    private String flightNumber = "";
    private String addressDeparture = "";
    private String addressArrival = "";

    private static final String PATH_NAME_INTERFACETAXI2 = "InterfaceTaxi2.fxml";
    private static final String PATH_NAME_INTERFACELOGIN = "/Project1/UserInterfaceMain/Login.fxml";

    public InterfaceTaxiPayment() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (SearchTaxi searchTaxi : InformationTaxiList.getSearchTaxiList()){
                date = searchTaxi.getPickUpDate();
                time = searchTaxi.getTime();
                airline = searchTaxi.getAirline();
                flightNumber = searchTaxi.getFightNumber();
                addressDeparture = searchTaxi.getAddressDeparture();
                addressArrival = searchTaxi.getAddressArrival();
        }

        for (Taxi taxi : InformationTaxiList.getTaxiList()){
            if(taxi.getIdTaxi().equals(InformationTaxiList.getIdentificationIdTaxiList().get(0).getIdTaxi())){
                price = taxi.getPrice();
                originPrice = taxi.getPrice();
                idTaxi = taxi.getIdTaxi();
            }
        }

        for(User user : InformationHotelList.getUserList()){
            idUser = user.getIdUser();
            fullName = user.getFullName();
        }

        showUserDateAndTime(fullName, date, time);
        showAirlineAndFlightNumber(airline, flightNumber);
        showAdressDepartureAndArrival(addressDeparture, addressArrival);
        showNumberCarAndPrice();

        // inform user not login
        if(User.getFlag() == 0){
            interfaceTaxiPaymentMessage.setText("You haven't login");
        }
    }

    public void paymentButtonOnAction(ActionEvent event){
        if(User.getFlag() == 0){
            // load logininterface if user haven't login
            callInterface(interfaceTaxiPaymentButtonPayment, PATH_NAME_INTERFACELOGIN);
        }else if(User.getFlag() == 1) {
            String paymentMethods = "";
            if (interfaceTaxiPaymentRadioPaymentUpon.isSelected()) {
                paymentMethods = "Payment upon check in";
            } else if (interfaceTaxiPaymentRadioMoMo.isSelected()) {
                paymentMethods = "MOMO";
            } else if (interfaceTaxiPaymentRadioBank.isSelected()) {
                paymentMethods = "Bank";
            }

            String noteToDriver = interfacTaxiPaymentTextFieldNote.getText();

                // insert data into database if user was login
                Connection connection = null;
                String createNewIdOrderTaxi = "";
                try {
                    connection = new DataConnection().getConnection();
                    connection.setAutoCommit(false);

                    createNewIdOrderTaxi = insertBookTaxi(connection, noteToDriver);
                    insertPayment(connection, createNewIdOrderTaxi, paymentMethods);

                    connection.commit();
                    interfaceTaxiPaymentMessage.setText("Thank you! Payment successful");
                } catch (SQLException throwables) {
                    try {
                        connection.rollback();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throwables.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
        }
    }

    public void backButtonOnAction(ActionEvent event){
        callInterface(interfaceTaxiPaymentButtonBack, PATH_NAME_INTERFACETAXI2);
        InformationTaxiList informationTaxiList = new InformationTaxiList();
        informationTaxiList.deleteIdentificationIdTaxi();
    }

    public void minusButtonOnAction(ActionEvent event){
        if(numberCar > 1){
            numberCar--;
            price = price - originPrice;
            interfaceTaxiPaymentNumberCar.setText(numberCar+"");
            interfaceTaxiPaymentPrice.setText("$"+price);
        }
    }

    public void addButtonOnAction(ActionEvent event){
        if(numberCar <= 5){
            numberCar++;
            price = price + originPrice;
            interfaceTaxiPaymentNumberCar.setText(numberCar+"");
            interfaceTaxiPaymentPrice.setText("$"+price);
        }
    }

    public void showUserDateAndTime(String userName, LocalDate date, String time){
        interfaceTaxiPaymentUserName.setText(userName);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceTaxiPaymentDate.setText(date.format(dateTimeFormatter));
        interfaceTaxiPaymentTime.setText(time);
    }

    public void showAirlineAndFlightNumber(String airline, String fightNumber){
        interfaceTaxiPaymentAirline.setText(airline);
        interfaceTaxiPaymentFlightNumber.setText(fightNumber);
    }

    public void showAdressDepartureAndArrival(String departure, String arrival){
        interfaceTaxiPaymentDeparture.setText(departure);
        interfaceTaxiPaymentArrival.setText(arrival);
    }

    public void showNumberCarAndPrice(){
        interfaceTaxiPaymentNumberCar.setText(numberCar+"");
        interfaceTaxiPaymentPrice.setText("$"+price);
    }

    // cretae id payment new
    public String createIdPayment(String id){
        String createIdString1 = id.substring(1);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "P"+createIdInt;
        return createIdString2;
    }

    public void insertPayment(Connection connection, String idOrderTaxi, String paymentMethods){
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        String createNewIdPayment = "";

        try {
            String sqlSelectOldIdPayment = "{CALL pro_select_id_old_payment()}";
            preparedStatement1 = connection.prepareStatement(sqlSelectOldIdPayment);
            preparedStatement1.executeQuery();
            resultSet = preparedStatement1.getResultSet();
            while (resultSet.next()) {
                createNewIdPayment = createIdPayment(resultSet.getString(1));
            }
            // insert table payment
            String sqlInsertPayment = "INSERT INTO Payment(Id_Payment,Id_User,Id_Order_Taxi,Payment_Method,Price_Total) VALUE(?,?,?,?,?)";
            preparedStatement2= connection.prepareStatement(sqlInsertPayment);
            preparedStatement2.setString(1, createNewIdPayment);
            preparedStatement2.setString(2, idUser);
            preparedStatement2.setString(3, idOrderTaxi);
            preparedStatement2.setString(4, paymentMethods);
            preparedStatement2.setInt(5, price);
            preparedStatement2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if(preparedStatement2 != null){
                    preparedStatement2.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // cretae id order taxi new
    public String createIdOrderTaxi(String id){
        String createIdString1 = id.substring(2);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "OT"+createIdInt;
        return createIdString2;
    }

    public String insertBookTaxi(Connection connection, String note){
        PreparedStatement preparedStatement1  = null;
        PreparedStatement preparedStatement2  = null;
        ResultSet resultSet = null;
        String createNewIdOrderTaxi = "";
        try {

            String sqlSelectOldIdOrder = "{CALL pro_select_id_old_order_taxi()}";
            preparedStatement1  = connection.prepareStatement(sqlSelectOldIdOrder);
            preparedStatement1 .executeQuery();
            resultSet = preparedStatement1 .getResultSet();
            while (resultSet.next()) {
                createNewIdOrderTaxi = createIdOrderTaxi(resultSet.getString(1));
            }
            // insert Book_Flight
            String sqlInsertBookRoom = "INSERT INTO Book_Taxi(Id_Order_Taxi, Id_Taxi, Number_Car, Address_Departure, Address_Arrival, Pick_Up_Date, Pick_Up_Time, Airline, Flight_Number, Note_To_Driver) VALUE (?,?,?,?,?,?,?,?,?,?)";
            preparedStatement2 = connection.prepareStatement(sqlInsertBookRoom);
            preparedStatement2.setString(1, createNewIdOrderTaxi);
            preparedStatement2.setString(2, idTaxi);
            preparedStatement2.setInt(3, numberCar);
            preparedStatement2.setString(4, addressDeparture);
            preparedStatement2.setString(5, addressArrival);
            preparedStatement2.setString(6, String.valueOf(date));
            preparedStatement2.setString(7, time);
            preparedStatement2.setString(8, airline);
            preparedStatement2.setString(9, flightNumber);
            preparedStatement2.setString(10, note);
            preparedStatement2.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (preparedStatement1 != null){
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null){
                    preparedStatement2.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return createNewIdOrderTaxi;
    }

    public void callInterface(Button button, String pathName){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceTaxiPaymentAnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceTaxiPaymentAnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
