package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You are already in league!")
public class AlreadyInLeagueException extends RuntimeException {
    public AlreadyInLeagueException() {
        super("You are already in league!");
    }
}
