package ex.guesser.areas.errorHandling.validation.annotaitons;


import ex.guesser.areas.errorHandling.validation.validators.CheckForDuplicatedMiniLeagueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CheckForDuplicatedMiniLeagueValidator.class)
public @interface CheckForDuplicatedMiniLeague {

    String message() default "The name of mini league is taken, choose another";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
