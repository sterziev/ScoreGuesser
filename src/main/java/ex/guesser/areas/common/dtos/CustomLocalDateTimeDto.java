package ex.guesser.areas.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class CustomLocalDateTimeDto {
    private List<String> days;
    private List<String> years;
    private List<String> months;
    private List<String> hour;
    private List<String> min;

    public CustomLocalDateTimeDto() {
        this.days = new ArrayList<>();
        this.days.add("Day");
        for (int i = 1; i <= 31; i++) {
            this.days.add(i+"");
        }

        this.years = new ArrayList<>();
        this.years.add("Year");
        for (int i = 2017; i >1905 ; i--) {
            this.years.add(i+"");
        }

        this.months = new ArrayList<>();
        this.months.add("Month");
        this.months.add("January");
        this.months.add("February");
        this.months.add("March");
        this.months.add("April");
        this.months.add("May");
        this.months.add("June");
        this.months.add("July");
        this.months.add("August");
        this.months.add("September");
        this.months.add("October");
        this.months.add("November");
        this.months.add("December");

        this.hour = new ArrayList<>();
        this.hour.add("hour");
        for (int i = 0; i <24 ; i++) {
            this.hour.add(i+"");
        }

        this.min = new ArrayList<>();
        this.min.add("hour");
        for (int i = 0; i <60 ; i++) {
            this.min.add(i+"");
        }
    }

    public List<String> getDays() {
        return days;
    }

    public List<String> getYears() {
        return years;
    }

    public List<String> getMonths() {
        return months;
    }

    public List<String> getHour() {
        return hour;
    }

    public List<String> getMin() {
        return min;
    }
}
