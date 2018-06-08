package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.errorHandling.validation.annotaitons.CustomMaxForStr;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomMaxForStrValidator implements ConstraintValidator<CustomMaxForStr, String> {
    private String maxThrValue;

    @Override
    public void initialize(CustomMaxForStr constraintAnnotation) {
        this.maxThrValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        int maxThreshold;
        int inputValue;
        try{
             maxThreshold = Integer.valueOf(this.maxThrValue);
            inputValue = Integer.valueOf(input);
        }
        catch (Exception e){
            return false;
        }
        if (maxThreshold<inputValue){
            return false;
        }
        return true;
    }
}
