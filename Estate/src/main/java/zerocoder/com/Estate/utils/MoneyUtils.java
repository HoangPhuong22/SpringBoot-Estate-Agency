package zerocoder.com.Estate.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtils {
    public static String formatMoney(Long money) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(money) + " VNĐ";
    }
}
