package application.untity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public String PlusMinus(String date1){
        String m = "";

//HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

        Date d1 = null;
        Date d2 =java.util.Calendar.getInstance().getTime();

        try {
            d1 = format.parse(String.valueOf(date1));

//in milliseconds
            long diff = d2.getTime() - d1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if(diffDays > 0) {
                m = diffDays + " Ngày, " + diffHours + " giờ";
            } if(diffHours > 0) {
                m = diffHours + " giờ";
            } else {
                m = diffMinutes + " phút";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return m;
    }

}
