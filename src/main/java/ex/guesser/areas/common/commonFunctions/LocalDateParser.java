package ex.guesser.areas.common.commonFunctions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class LocalDateParser {
    public static LocalDateTime localDateTimeParser(String year, String month, String day, String hour, String min){
        return LocalDateTime.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day)
                , Integer.parseInt(hour),
                Integer.parseInt(min));
    }

    public static LocalDate localDateParser(String year, String month, String day){
        int y=0,m=0,d=0;
        try {
            y=Integer.parseInt(year);
            m=Integer.parseInt(month);
            d=Integer.parseInt(day);
        }
        catch (NumberFormatException nfe){
            return null;
        }
        return LocalDate.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day));
    }
}
