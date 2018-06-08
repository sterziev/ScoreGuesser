package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.common.enums.Gender;
import ex.guesser.areas.errorHandling.validation.annotaitons.Sex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexValidator implements ConstraintValidator<Sex, String> {

    @Override
    public void initialize(Sex constraintAnnotation) {
    }

    @Override
    public boolean isValid(String sex, ConstraintValidatorContext constraintValidatorContext) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equals(sex)) return true;
        }

        return false;
    }
}
