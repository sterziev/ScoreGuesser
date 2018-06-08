package ex.guesser.areas.user.models.binding;

import ex.guesser.areas.errorHandling.validation.annotaitons.CustomMaxForStr;
import ex.guesser.areas.errorHandling.validation.annotaitons.CustomMinForStr;
import ex.guesser.areas.errorHandling.validation.annotaitons.Sex;

import javax.validation.constraints.*;

public class UpdateUserPersonalBindingModel {

    private static final String USER_NAME_NOT_EMPTY_MSG = "User name cannot be empty";
    private static final String LENGHT_EMAIL_MSG = "Email must be longer then 5 symbols";
    private static final String EMAIL_NOT_VALID_MSG = "Emai is in wrong format";
    private static final String PSW_NOT_EMPTY_MSG = "Password cannot be empty";
    private static final String PSW_LENGHT_MSG = "Password must be at least 5 symbols";
    private static final String FN_NOT_EMMPTY_MSG = "First Name cannot be empty";;
    private static final String LN_NOT_EMMPTY_MSG = "Last Name cannot be empty";;
    private static final String DAY_NOT_EMMPTY_MSG = "Day cannot be empty";;
    private static final String MONTH_NOT_EMMPTY_MSG = "Month cannot be empty";;
    private static final String YEAR_NOT_EMMPTY_MSG = "Year cannot be empty";;
    private static final String SEX_ERROR_MSG = "Sex must be either FEMALE or MALE";
    private static final String FN_LENGHT_MSG = "First Name must be at least 3 symbols";
    private static final String LN_LENGHT_MSG = "Last Name must be at least 3 symbols";
    private static final String DAY_IN_RANGE_MSG = "The Day can be from 1 to 31";
    private static final String MONTH_IN_RANGE_MSG = "The Month can be from January to December";
    private static final String YEAR_IN_RANGE_MSG = "The Year can be from 1905 to 2017";
    private static final String PASSWORD_MUST_CONT_NUMS = "Password must contains at least one number";
    private static final String DUPLICATEDUSERNAME = "This user name is taken, please choose another";

    private String username;

    @NotEmpty(message = FN_NOT_EMMPTY_MSG)
    @Size(min = 3, max = 20, message = FN_LENGHT_MSG)
    private String firstName;

    @NotEmpty(message = LN_NOT_EMMPTY_MSG)
    @Size(min = 3, max = 15, message = LN_LENGHT_MSG)
    private String lastName;

    private String favTeam;

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


    private String country;

    @Sex(message = SEX_ERROR_MSG)
    private String sex;

    public UpdateUserPersonalBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavTeam() {
        return favTeam;
    }

    public void setFavTeam(String favTeam) {
        this.favTeam = favTeam;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}
