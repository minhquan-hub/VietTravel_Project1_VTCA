package Project1.UserInterfaceMain;

import Project1.Common.DataConnection;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;

public class SignUp {
        @FXML
        private AnchorPane signUpAnchorPane;
        @FXML
        private AnchorPane signUpAnchorPaneSmall;
        @FXML
        private TextField signupTextFieldFullName;
        @FXML
        private TextField signupTextFieldPhone;
        @FXML
        private TextField signupTextFieldAddress;
        @FXML
        private TextField signupTextFieldUserName;
        @FXML
        private TextField signupTextFieldEmail;
        @FXML
        private TextField signupTextFieldPassWord;
        @FXML
        private TextField signupTextFieldRePassWord;
        @FXML
        private Button signupButton;
        @FXML
        private Label signUplabelMessage;

    public SignUp() {
    }

    public void signupButtonOnAction(ActionEvent event) {
        // add data from user interface
        String fullName = signupTextFieldFullName.getText();
        String phone = signupTextFieldPhone.getText();
        String address = signupTextFieldAddress.getText();
        String userName = signupTextFieldUserName.getText();
        String email = signupTextFieldEmail.getText();
        String passWord = signupTextFieldPassWord.getText();
        String rePassWord = signupTextFieldRePassWord.getText();
        String createNewId = null;

        // check data and insert into database
        if (fullName.isBlank() == false && phone.isBlank() == false && address.isBlank() == false &&
                userName.isBlank() == false && email.isBlank() == false && passWord.isBlank() == false && rePassWord.isBlank() == false) {
            if (!passWord.equals(rePassWord)) {
                signUplabelMessage.setText("The Passwword is difference Re-password");
            } else if (!checkSignUpPhone(phone)) {
                signUplabelMessage.setText("Please ! Enter again the phone");
            } else {
                Connection conn = null;
                PreparedStatement prepar1 = null;
                PreparedStatement prepar2 = null;
                ResultSet resultSet = null;
                try {
                    conn = new DataConnection().getConnection();
                    String sqlSelectOldId = "{CALL pro_select_id_old_user()}";
                    prepar1 = conn.prepareStatement(sqlSelectOldId);
                    prepar1.executeQuery();
                    resultSet = prepar1.getResultSet();
                    while (resultSet.next()) {
                        createNewId = createIdUser(resultSet.getString(1));
                    }

                    conn.setAutoCommit(false);
                    String sqlSignupInsert = "INSERT INTO signup(Id_User,Full_Name, Phone, Address, User_Name, Email, PassWord) VALUE(?,?,?,?,?,?,?)";
                    prepar2 = conn.prepareStatement(sqlSignupInsert);
                    prepar2.setString(1, createNewId);
                    prepar2.setString(2, fullName);
                    prepar2.setString(3, phone);
                    prepar2.setString(4, address);
                    prepar2.setString(5, userName);
                    prepar2.setString(6, email);
                    prepar2.setString(7, passWord);
                    prepar2.executeUpdate();
                    conn.commit();
                    signUplabelMessage.setText("Sign up successful");
                    callInterfaceLogin();
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
                        if(prepar2 != null){
                            prepar2.close();
                        }
                        if(resultSet != null){
                            resultSet.close();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        } else {
            signUplabelMessage.setText("Please ! You should fill all the fields");
        }
    }

    // method off interface signup
    public void cancelButtonOnAction(ActionEvent event){
        signUpAnchorPane.setVisible(false);
    }

    public Boolean checkSignUpPhone(String sdt){
        if (sdt.length() != 10) return false;
        if (sdt.charAt(0) == 0) return true;
        for (int i = 0; i < sdt.length(); i++)
            if (sdt.charAt(i) < '0' || sdt.charAt(i) > '9') return false;
        return true;
    }

    // method create new id from old id
    public String createIdUser(String id){
        String createIdString1 = id.substring(1);
        int createIdInt = Integer.parseInt(createIdString1)+1;
        String createIdString2 = "U"+createIdInt;
        System.out.println(createIdString2);
        return createIdString2;
    }

    // method load interface login
    public void callInterfaceLogin(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = signupButton.getScene();
            root.translateYProperty().set(scene.getHeight());
            signUpAnchorPane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.05),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            signUpAnchorPaneSmall.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
