package Project1.Flight;

import Project1.Common.DataConnection;
import Project1.Information.FlightPackage.*;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class InterfaceFlightPayment implements Initializable {
    @FXML
    private AnchorPane interfaceFlightPaymentAnchorPane;
    @FXML
    private AnchorPane interfaceFlightPaymentAnchorPaneSmall;
    @FXML
    private TextField interfaceFlightPaymentTextField;
    @FXML
    private Label interfaceFlightPaymentFrom;
    @FXML
    private Label interfaceFlightPaymentTo;
    @FXML
    private Label interfaceFlightPaymentCarrer;
    @FXML
    private Label interfaceFlightPaymentFlight;
    @FXML
    private Label interfaceFlightPaymentSeat;
    @FXML
    private Label interfaceFlightPaymentDate;
    @FXML
    private Label interfaceFlightPaymentPrice;
    @FXML
    private Label interfaceFlightPaymentMessageFullName;
    @FXML
    private Label interfaceFlightPaymentMessage;
    @FXML
    private RadioButton interfaceFlightPaymentRadioButtonPaymentUpon;
    @FXML
    private RadioButton interfaceFlightPaymentRadioButtonMOMO;
    @FXML
    private RadioButton interfaceFlightPaymentRadioButtonBank;
    @FXML
    private Button interfaceFlightPaymentButtonPayment;
    @FXML
    private Button interfaceFlightPaymentButtonBack;

    private int adultAmountInt = 0;
    private int childAmountInt = 0;
    private int infantAmountInt = 0;
    private String idUser = "";
    private String idTickets = "";
    private String positionSeat = "";
    private String carrer = "";
    private int priceTotal = 0;
    private static final String PATH_NAME_INTERFACELOGIN = "/Project1/UserInterfaceMain/Login.fxml";
    private static final String PATH_NAME_INTERFACEFLIGHT2 = "InterfaceFlight2.fxml";
    private static final String PATH_NAME_INTERFACETICKETVIETNAMAIRLINES = "InterfaceTicketVietnamAirlines.fxml";
    private static final String PATH_NAME_INTERFACETICKETVIETJET = "InterfaceTicketVietjet.fxml";
    private static final String PATH_NAME_INTERFACETICKETJETSTAR = "InterfaceTicketJestar.fxml";




    public InterfaceFlightPayment() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Flight flight : InformationFlightList.getFlightList()){
            if(flight.getIdTickets().equals(InformationFlightList.getIdentificationIdFlightList().get(0).getIdentificationId())){
                showNameFromLocationAndNameToLocation(flight.getNameFromLocation(),flight.getNameToLocation());
                showCarrerAndFlight(flight.getBrand(),flight.getIdTickets());
                showSeatDateAndPrice(flight.getPrice());
            }
        }
        // add id User into variable idUser
        for (User user : InformationHotelList.getUserList()){
            idUser = user.getIdUser();
        }
        // inform user not login
        if(User.getFlag() == 0){
            interfaceFlightPaymentMessage.setText("You haven't login");
        }

    }

    public void paymentButtonOnAction(ActionEvent event){
        if(User.getFlag() == 0){
            // load logininterface if user haven't login
           callInterface(interfaceFlightPaymentButtonPayment, PATH_NAME_INTERFACELOGIN);
        }else if(User.getFlag() == 1) {
            String fullName = interfaceFlightPaymentTextField.getText();
            String paymentMethods = "";
            if (interfaceFlightPaymentRadioButtonPaymentUpon.isSelected()) {
                paymentMethods = "Payment upon check in";
            } else if (interfaceFlightPaymentRadioButtonMOMO.isSelected()) {
                paymentMethods = "MOMO";
            } else if (interfaceFlightPaymentRadioButtonBank.isSelected()) {
                paymentMethods = "Bank";
            }
            if (!checkFullName(fullName)) {
                interfaceFlightPaymentMessageFullName.setText("The FullName is only unsigned character");
            } else {
                if(checkFullName(fullName)){
                    interfaceFlightPaymentMessageFullName.setVisible(false);
                }
                // add data into list
                UserNameAndSeatTicket userNameAndSeatTicket = new UserNameAndSeatTicket(fullName,positionSeat);
                InformationFlightList informationFlightList = new InformationFlightList();
                informationFlightList.addUserNameAndSeatTicket(userNameAndSeatTicket);

                // insert data into database if user was login
                Connection connection = null;
                String createNewIdOrderFlight = "";
                try {
                    connection = new DataConnection().getConnection();
                    connection.setAutoCommit(false);

                    createNewIdOrderFlight = insertBookFlight(connection);
                    insertPayment(connection,createNewIdOrderFlight,paymentMethods);

                    connection.commit();
                    interfaceFlightPaymentMessage.setText("Thank you! Payment successful");
                    // move interface flight ticket
                    if(carrer.equalsIgnoreCase("Vietnam Airlines")){
                        callInterface(interfaceFlightPaymentButtonPayment, PATH_NAME_INTERFACETICKETVIETNAMAIRLINES);
                    }else if(carrer.equalsIgnoreCase("VietjetAir")){
                        callInterface(interfaceFlightPaymentButtonPayment, PATH_NAME_INTERFACETICKETVIETJET);
                    }else if(carrer.equalsIgnoreCase("Jetstar")){
                       callInterface(interfaceFlightPaymentButtonPayment, PATH_NAME_INTERFACETICKETJETSTAR);
                    }
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
    }

    public void backButtonOnAction(ActionEvent event){
      callInterface(interfaceFlightPaymentButtonBack, PATH_NAME_INTERFACEFLIGHT2);
        InformationFlightList informationFlightList = new InformationFlightList();
        informationFlightList.deleteIdentificationIdFlight();
    }

    public void showNameFromLocationAndNameToLocation(String nameFromLocation, String nameToLocation){
        // set name place departure and arrival
        String showNameFromLocation = "";
        if(nameFromLocation.equalsIgnoreCase("TP HCM")){
            showNameFromLocation = "HO CHI MINH CITY";
        }else if(nameFromLocation.equalsIgnoreCase("Nha Trang")){
            showNameFromLocation = "CAM RANH";
        }else if(nameFromLocation.equalsIgnoreCase("Hue")){
            showNameFromLocation = "HUE";
        }

        String showNameToLocation = "";
        if(nameToLocation.equalsIgnoreCase("TP HCM")){
            showNameToLocation = "HO CHI MINH CITY";
        }else if(nameToLocation.equalsIgnoreCase("Nha Trang")){
            showNameToLocation = "CAM RANH";
        }else if(nameToLocation.equalsIgnoreCase("Hue")){
            showNameToLocation = "HUE";
        }

        // show depature and arrival up interface
        interfaceFlightPaymentFrom.setText(showNameFromLocation);
        interfaceFlightPaymentTo.setText(showNameToLocation);
    }


    public void showCarrerAndFlight(String carrer, String flightNumber){
        idTickets = flightNumber;
        this.carrer = carrer;
        interfaceFlightPaymentCarrer.setText(carrer.toUpperCase());
        interfaceFlightPaymentFlight.setText(flightNumber);
    }

    public void showSeatDateAndPrice(int price){
        String adultAmountString = "";
        String childAmountString = "";
        String infantAmountString = "";
        String seatClass = "";
        LocalDate departureDate = null;
        int ticketTotal = 0;

        // take data from list
        for (SearchFlight searchFlight : InformationFlightList.getSearchFlightList()){
            adultAmountString = searchFlight.getAdultAmount();
            childAmountString = searchFlight.getChildAmount();
            infantAmountString = searchFlight.getIfnantAmount();
            seatClass = searchFlight.getSeatClass();
            departureDate = searchFlight.getDepartureDate();
        }

        // separate type string become int
        adultAmountInt = separateNumber(adultAmountString);
        childAmountInt = separateNumber(childAmountString);
        infantAmountInt = separateNumber(infantAmountString);

        // calculator total seat and show information seat up interface
        ticketTotal = adultAmountInt+childAmountInt;
        positionSeat = setUpSeat(ticketTotal,seatClass);
        interfaceFlightPaymentSeat.setText(positionSeat);

        // show dateTicket up interface
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        interfaceFlightPaymentDate.setText(departureDate.format(dateTimeFormatter));

        // show price up interface
        priceTotal = price*ticketTotal;
        priceTotal = distinguishPriceEconomyAndBusinessClass(priceTotal,seatClass);
        interfaceFlightPaymentPrice.setText("$"+priceTotal);
    }

    // method separate number in String
    public int separateNumber(String guestAmount){
        char guestAmountChar = guestAmount.charAt(0);
        int guestAmountInt = Integer.parseInt(String.valueOf(guestAmountChar));
        return guestAmountInt;
    }

    // set up quantity seat
    public String setUpSeat(int ticketTotal, String seatClass){
        String[] rowSeatInPlane = new String[0];
        int resetCount = 0;
        int randomSeatNumber = 0;
        int lengthString = 0;
        String seatAfterSetUp = "";
        String seatAfterSetUpTrim = "";
        StringBuffer seatSetUpStringBuffer = new StringBuffer();

        if(seatClass.equalsIgnoreCase("Economy Class")){
            rowSeatInPlane = new String[]{"A","B","C","D","E","F"};
            resetCount = 6;
            randomSeatNumber = (int)(Math.random()*(33-9+1)+9);
        }else if(seatClass.equalsIgnoreCase("Business Class")){
            rowSeatInPlane = new String[]{"A","C","D","F"};
            resetCount = 4;
            randomSeatNumber = (int)(Math.random()*(6-1+1)+1);
        }

        int count = 0;
        for (int i = 0; i < ticketTotal; i++) {
            seatSetUpStringBuffer.append(""+randomSeatNumber+rowSeatInPlane[count]+",");
            count++;
            if(count == resetCount){
                count = 0;
                randomSeatNumber++;
            }
        }
        seatAfterSetUp = String.valueOf(seatSetUpStringBuffer);
        // trim last comma in string seatAfterSetUp
        lengthString = seatAfterSetUp.length();
        seatAfterSetUpTrim = seatAfterSetUp.substring(0,lengthString-1);

        return seatAfterSetUpTrim;
    }

    // cretae id payment new
    public String createIdPayment(String id){
        String createIdString1 = id.substring(1);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "P"+createIdInt;
        return createIdString2;
    }

    // cretae id order new
    public String createIdOrderFlight(String id){
        String createIdString1 = id.substring(2);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "OF"+createIdInt;
        return createIdString2;
    }

    public Boolean checkFullName(String fullName){
        boolean checkCharacter = true;
        String regexp = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(regexp);
        if(!pattern.matcher(fullName).find()){
            return checkCharacter = false;
        }
        return checkCharacter;
    }

    public int distinguishPriceEconomyAndBusinessClass(int price, String seatClass){
        int setUpPrice = 0;
        if(seatClass.equalsIgnoreCase("Economy Class")){
            setUpPrice = price;
        }else if(seatClass.equalsIgnoreCase("Business Class")){
            setUpPrice = price*2;
        }
        return setUpPrice;
    }

    public String insertBookFlight(Connection connection){
        PreparedStatement preparedStatement1  = null;
        PreparedStatement preparedStatement2  = null;
        ResultSet resultSet = null;
        String createNewIdOrderFlight = "";
        try {

        String sqlSelectOldIdOrder = "{CALL pro_select_id_old_order_flight()}";
        preparedStatement1  = connection.prepareStatement(sqlSelectOldIdOrder);
        preparedStatement1 .executeQuery();
        resultSet = preparedStatement1 .getResultSet();
        while (resultSet.next()) {
            createNewIdOrderFlight = createIdOrderFlight(resultSet.getString(1));
        }
        // insert Book_Flight
        String sqlInsertBookRoom = "INSERT INTO Book_Flight(Id_Order_Flight,Id_Tickets,Adult_Amount,Child_Amount,infant_Amount,Position_Seat) VALUE (?,?,?,?,?,?)";
            preparedStatement2 = connection.prepareStatement(sqlInsertBookRoom);
            preparedStatement2.setString(1, createNewIdOrderFlight);
            preparedStatement2.setString(2, idTickets);
            preparedStatement2.setInt(3, adultAmountInt);
            preparedStatement2.setInt(4, childAmountInt);
            preparedStatement2.setInt(5, infantAmountInt);
            preparedStatement2.setString(6, positionSeat);
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
        return createNewIdOrderFlight;
    }

    public void insertPayment(Connection connection,String idOrderFlight, String paymentMethods){
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
        String sqlInsertPayment = "INSERT INTO Payment(Id_Payment,Id_User,Id_Order_Flight,Payment_Method,Price_Total) VALUE(?,?,?,?,?)";
        preparedStatement2= connection.prepareStatement(sqlInsertPayment);
        preparedStatement2.setString(1, createNewIdPayment);
        preparedStatement2.setString(2, idUser);
        preparedStatement2.setString(3, idOrderFlight);
        preparedStatement2.setString(4, paymentMethods);
        preparedStatement2.setInt(5, priceTotal);
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

    public void callInterface(Button button, String pathName){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            interfaceFlightPaymentAnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            interfaceFlightPaymentAnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
