package application.untity;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatCurrency {
    public String vietNam(Double number){
        NumberFormat nf = NumberFormat.getInstance();
        Locale locale = new Locale("vi", "VI");
        NumberFormat vi = NumberFormat.getInstance(locale);
        String result = vi.format(number);

        return result;
    }
}
