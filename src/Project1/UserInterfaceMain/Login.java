package Project1.UserInterfaceMain;

import Project1.Common.DataConnection;
import Project1.Information.FlightPackage.InformationFlightList;
import Project1.Information.HotelPackage.InformationHotelList;
import Project1.Information.TaxiPackage.InformationTaxiList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private AnchorPane loginAnchorPaneSmall;
    @FXML
    private ImageView userImageView;
    @FXML
    private ImageView passwordImageView;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button signUpButton;
    @FXML
    private Button loginButton;

    private static final String PATHNAME_INTERFACEFLIGHTPAYMENT = "/Project1/Flight/InterfaceFlightPayment.fxml";
    private static final String PATHNAME_INTERFACEHOTELPAYMENT = "/Project1/Hotel/InterfaceHotelPayment.fxml";
    private static final String PATHNAME_INTERFACETAXIPAYMENT = "/Project1/Taxi/InterfaceTaxiPayment.fxml";
    private static final String PATHNAME_INTERFACESIGNUP = "/Project1/UserInterfaceMain/SignUp.fxml";

    public Login() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File userImageFile = new File("src/ProjectFrame1/loginFrame/LoginUser.png");
        Image userImage = new Image(userImageFile.toURI().toString());
        userImageView.setImage(userImage);


        File passwordImageFile = new File("src/ProjectFrame1/LoginFrame/LoginPassword.png");
        Image passwordImage = new Image(passwordImageFile.toURI().toString());
        passwordImageView.setImage(passwordImage);
    }

    public void loginButtonOnAction(ActionEvent event){
        if(userTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            String userName = userTextField.getText();
            String password = passwordTextField.getText();

            Connection conn = null;
            PreparedStatement prepar = null;
            ResultSet resultSet = null;

            try {
                conn = new DataConnection().getConnection();
                String sqlSelectUser = "{CALL pro_select_user(?,?)}";
                prepar = conn.prepareStatement(sqlSelectUser);
                prepar.setString(1,userName);
                prepar.setString(2,password);
                prepar.executeQuery();
                resultSet = prepar.getResultSet();
                if(resultSet.next()){

                    String idUser = resultSet.getString(1);
                    String fullName = resultSet.getString(2);
                    String phone = resultSet.getString(3);
                    String address  = resultSet.getString(4);
                    String userName1 = resultSet.getString(5);
                    String email = resultSet.getString(6);
                    String passWord1 = resultSet.getString(7);
                    User user = new User(idUser,fullName,phone,address,userName1,email,passWord1);
                    InformationHotelList informationHotelList = new InformationHotelList();
                    informationHotelList.addUser(user);
                    messageLabel.setText("you try to login");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    messageLabel.setText("Username or password is wrong");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    if(conn != null) {
                        conn.close();
                    }
                    if(prepar != null){
                        prepar.close();
                    }
                    if(resultSet != null){
                        resultSet.close();
                    }
                    if(User.getFlag() == 1 && !InformationHotelList.getIdentificationIdHotelList().isEmpty()){
                        callInterface(loginButton, PATHNAME_INTERFACEHOTELPAYMENT);
                    }else if(User.getFlag() == 1 && !InformationFlightList.getIdentificationIdFlightList().isEmpty()){
                        callInterface(loginButton, PATHNAME_INTERFACEFLIGHTPAYMENT);
                    }else if(User.getFlag() == 1 && !InformationTaxiList.getIdentificationIdTaxiList().isEmpty()){
                        callInterface(loginButton, PATHNAME_INTERFACETAXIPAYMENT);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }else {
            messageLabel.setText("Please enter Username and Password");
        }
    }

    public void signUpButtonOnAction(ActionEvent event){
        callInterface(signUpButton, PATHNAME_INTERFACESIGNUP);
    }

    public void callInterface(Button button, String pathName){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathName));
            Scene scene = button.getScene();
            root.translateYProperty().set(scene.getHeight());
            loginAnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            loginAnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
