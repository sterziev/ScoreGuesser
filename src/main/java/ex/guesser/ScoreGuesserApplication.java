package ex.guesser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScoreGuesserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScoreGuesserApplication.class, args);
    }
}
