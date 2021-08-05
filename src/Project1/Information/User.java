package Project1.Information;

public class User {
    private String idUser;
    private String fullName;
    private String phone;
    private String address;
    private String userName;
    private String email;
    private String passWord;
    private static int flag = 0;

    public User(String idUser, String fullName, String phone, String address, String userName, String email, String passWord) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        flag = 1;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        User.flag = flag;
    }
}
