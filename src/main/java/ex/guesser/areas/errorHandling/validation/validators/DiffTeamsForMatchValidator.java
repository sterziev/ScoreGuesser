package ex.guesser.areas.errorHandling.validation.validators;
import ex.guesser.areas.errorHandling.validation.annotaitons.DiffTeamsForMatch;
import ex.guesser.areas.matches.models.binding.AddMatchBinningModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DiffTeamsForMatchValidator implements ConstraintValidator<DiffTeamsForMatch, Object> {

    @Override
    public void initialize(DiffTeamsForMatch constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        AddMatchBinningModel match = (AddMatchBinningModel) obj;
        return !match.getHome().equalsIgnoreCase(match.getAway());
    }
}