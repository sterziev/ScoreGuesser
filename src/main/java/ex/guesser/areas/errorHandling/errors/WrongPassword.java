package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong Password!")
public class WrongPassword extends RuntimeException {
    public WrongPassword() {
        super("Wrong Password!");
    }
}
