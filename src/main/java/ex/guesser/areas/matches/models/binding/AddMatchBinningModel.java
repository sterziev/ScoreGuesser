package ex.guesser.areas.matches.models.binding;

import ex.guesser.areas.errorHandling.validation.annotaitons.CustomMaxForStr;
import ex.guesser.areas.errorHandling.validation.annotaitons.CustomMinForStr;
import ex.guesser.areas.errorHandling.validation.annotaitons.DiffTeamsForMatch;

import javax.validation.constraints.NotNull;

@DiffTeamsForMatch
public class AddMatchBinningModel {
    private static final String DAY_NOT_EMMPTY_MSG = "Day cannot be empty";;
    private static final String MONTH_NOT_EMMPTY_MSG = "Month cannot be empty";;
    private static final String YEAR_NOT_EMMPTY_MSG = "Year cannot be empty";;
    private static final String DAY_IN_RANGE_MSG = "The Day can be from 1 to 31";
    private static final String MONTH_IN_RANGE_MSG = "The Month can be from January to December";
    private static final String YEAR_IN_RANGE_MSG = "The Year can be from 1905 to 2017";
    private static final String NOT_NULL_MSG_ROUND = "Round cannot be empty";
    private static final String HOUR_NOT_EMMPTY_MSG = "Hours cannot be empty";
    private static final String HOUR_IN_RANGE_MSG = "Hours can be from 1 to 24";
    private static final String MIN_NOT_EMMPTY_MSG = "Minutes cannot be empty";
    private static final String MIN_IN_RANGE_MSG = "Minutes can be form 0 to 59";

    @NotNull(message = NOT_NULL_MSG_ROUND)
    private int round;

    @NotNull
    private String home;

    @NotNull
    private String away;

    @NotNull
    private String stadium;

    @NotNull(message = DAY_NOT_EMMPTY_MSG)
    @CustomMinForStr(value = "1", message = DAY_IN_RANGE_MSG)
    @CustomMaxForStr(value = "31", message = DAY_IN_RANGE_MSG)
    private String day;

    @NotNull(message = MONTH_NOT_EMMPTY_MSG)
    @CustomMinForStr(value = "1", message = MONTH_IN_RANGE_MSG)
    @CustomMaxForStr(value = "12", message = MONTH_IN_RANGE_MSG)
    private String month;

    @NotNull(message = YEAR_NOT_EMMPTY_MSG)
    @CustomMinForStr(value = "1905", message = YEAR_IN_RANGE_MSG)
    @CustomMaxForStr(value = "2017", message = YEAR_IN_RANGE_MSG)
    private String year;

    @NotNull(message = HOUR_NOT_EMMPTY_MSG)
    @CustomMinForStr(value = "1", message = HOUR_IN_RANGE_MSG)
    @CustomMaxForStr(value = "24", message = HOUR_IN_RANGE_MSG)
    private String hour;

    @NotNull(message = MIN_NOT_EMMPTY_MSG)
    @CustomMinForStr(value = "0", message = MIN_IN_RANGE_MSG)
    @CustomMaxForStr(value = "24", message = MIN_IN_RANGE_MSG)
    private String min;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }


    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
