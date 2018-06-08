package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.common.enums.VirusMagnitude;
import ex.guesser.areas.errorHandling.validation.annotaitons.Magnitude;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VirusMagnitudeValidator implements ConstraintValidator<Magnitude, String> {

    @Override
    public void initialize(Magnitude constraintAnnotation) {
    }

    @Override
    public boolean isValid(String magnitude, ConstraintValidatorContext constraintValidatorContext) {
        for (VirusMagnitude virusMagnitude : VirusMagnitude.values()) {
            if (virusMagnitude.name().equals(magnitude)) return true;
        }

        return false;
    }
}
