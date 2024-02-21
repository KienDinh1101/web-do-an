package application.constant;

public class MyConstant {
    // Replace with your email here:
    public static final String MY_EMAIL = "khanhdaix3@gmail.com";

    // Replace password!!
    public static final String MY_PASSWORD = "khanhdai2109";

    public static final String MY_URL = "http://localhost:8080/";

    public static final String REGEX_EMAIL = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";
    public static final String REGEX_PHONE = "((09|03|07|08|05)+([0-9]{8})\\b)/g";
    public static final String REGEX_USERNAME = "/^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{5,50}$/";
    public static final String REGEX_PASSWORD = "/^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$/";
}
