package ex.guesser.areas.errorHandling.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Not Found!")
public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User Not Found!");
    }
}
