package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.errorHandling.validation.annotaitons.BeforeToday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BeforeTodayValidator implements ConstraintValidator<BeforeToday, String> {

    private static final String EMPTY_STRING = "";

    @Override
    public void initialize(BeforeToday constraintAnnotation) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        return !date.equals(EMPTY_STRING)
                && LocalDate.now().isAfter(LocalDate.parse(date));
    }
}