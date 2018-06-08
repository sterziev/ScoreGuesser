package ex.guesser.areas.errorHandling.validation.annotaitons;


import ex.guesser.areas.errorHandling.validation.validators.CheckForDuplicatedUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CheckForDuplicatedUsernameValidator.class)
public @interface CheckForDuplicatedUsername {

    String message() default "User name is taken, choose another";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
