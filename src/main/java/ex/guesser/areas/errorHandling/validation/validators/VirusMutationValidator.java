package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.common.enums.VirusMutation;
import ex.guesser.areas.errorHandling.validation.annotaitons.Mutation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VirusMutationValidator implements ConstraintValidator<Mutation, String> {

    @Override
    public void initialize(Mutation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String mutation, ConstraintValidatorContext constraintValidatorContext) {
        for (VirusMutation virusMutation : VirusMutation.values()) {
            if (virusMutation.name().equals(mutation)) return true;
        }

        return false;
    }
}
