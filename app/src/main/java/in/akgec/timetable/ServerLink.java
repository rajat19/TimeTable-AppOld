package in.akgec.timetable;

/**
 * Created by Rajat on 01-Nov-16.
 */
public class ServerLink {

    public String SERVER_ADDRESS;
    public String VALIDATE_LOGIN;
    public String VALIDATE_REGISTER;
    public String VIEW_CLASS;

    public ServerLink() {
        SERVER_ADDRESS = "http://192.168.126.1/TimeTable/api/";
        VALIDATE_LOGIN = "validate_login.php";
        VALIDATE_REGISTER = "validate_register.php";
        VIEW_CLASS = "view_class.php";
    }
}
