package zerocoder.com.Estate.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class TimeUtils {
    public static String convertTime(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(time, now);
        Period period = Period.between(time.toLocalDate(), now.toLocalDate());

        if (duration.getSeconds() < 60) {
            return duration.getSeconds() + " giây trước";
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " phút trước";
        } else if (duration.toHours() < 24) {
            return duration.toHours() + " giờ trước";
        } else if (period.getDays() < 7) {
            return period.getDays() + " ngày trước";
        } else if (period.getDays() < 30) {
            return (period.getDays() / 7) + " tuần trước";
        } else if (period.getMonths() < 12) {
            return period.getMonths() + " tháng trước";
        } else {
            return period.getYears() + " năm trước";
        }
    }
    public static boolean isEqual(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.isEqual(endDate);
    }
}