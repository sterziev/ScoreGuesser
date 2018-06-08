package ex.guesser.areas.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class BirthdayInputsDTO {
    private List<String> days;
    private List<String> years;
    private List<String> months;

    public BirthdayInputsDTO() {
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

}
