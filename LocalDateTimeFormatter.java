import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Before formating: "+now);
        System.out.println(now.plusDays(100));
        System.out.println(now.minusDays(10));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
    }

}
