module Project1 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;

    opens Project1;
    opens Project1.Flight;
    opens Project1.UserInterfaceMain;
    opens Project1.Hotel;
    opens Project1.Common;
    opens Project1.Taxi;

}