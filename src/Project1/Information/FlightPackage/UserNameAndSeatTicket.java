package Project1.Information.FlightPackage;

public class UserNameAndSeatTicket {
    private String userName;
    private String positionSeat;

    public UserNameAndSeatTicket(String userName, String positionSeat) {
        this.userName = userName;
        this.positionSeat = positionSeat;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPositionSeat() {
        return positionSeat;
    }

    public void setPositionSeat(String positionSeat) {
        this.positionSeat = positionSeat;
    }
}
