package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.errorHandling.validation.annotaitons.CustomMinForStr;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomMinForStrValidator implements ConstraintValidator<CustomMinForStr, String> {
    private String minThrValue;

    @Override
    public void initialize(CustomMinForStr constraintAnnotation) {
        this.minThrValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        int minThreshold;
        int inputValue;
        try{
             minThreshold = Integer.valueOf(this.minThrValue);
            inputValue = Integer.valueOf(input);
        }
        catch (Exception e){
            return false;
        }
        if (minThreshold>inputValue){
            return false;
        }
        return true;
    }
}
