package ex.guesser.areas.errorHandling.validation.validators;

import ex.guesser.areas.errorHandling.validation.annotaitons.CheckForDuplicatedUsername;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckForDuplicatedUsernameValidator implements ConstraintValidator<CheckForDuplicatedUsername, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(CheckForDuplicatedUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            return false;
        }
        return true;

    }
}
