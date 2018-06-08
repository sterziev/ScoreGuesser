package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You are not authorized to create mini leagues!")
public class NotAuthorizedToCreateLeagueException extends RuntimeException {
    public NotAuthorizedToCreateLeagueException() {
        super("You are not authorized to create mini leagues!");
    }
}
