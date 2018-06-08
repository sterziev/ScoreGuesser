package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The date you entered is wrong!")
public class LocalTimeException extends RuntimeException {
    public LocalTimeException() {
        super("The date you entered is wrong!");
    }
}
