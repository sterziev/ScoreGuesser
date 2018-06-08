package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Match Not Found!")
public class MatchNotFound extends RuntimeException {
    public MatchNotFound() {
        super("Match Not Found!");
    }
}
