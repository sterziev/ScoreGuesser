package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.errorHandling.validation.annotaitons.CheckForDuplicatedMiniLeague;
import ex.guesser.areas.user.entities.MiniLeague;
import ex.guesser.areas.user.repositories.MiniLeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckForDuplicatedMiniLeagueValidator implements ConstraintValidator<CheckForDuplicatedMiniLeague, String> {
    @Autowired
    private MiniLeagueRepository miniLeagueRepository;

    @Override
    public void initialize(CheckForDuplicatedMiniLeague constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        MiniLeague league = this.miniLeagueRepository.findByLeagueName(name);
        if (league != null) {
            return false;
        }
        return true;

    }
}
