package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "League Not Found!")
public class LeagueNotFound extends RuntimeException {
    public LeagueNotFound() {
        super("League Not Found!");
    }
}
