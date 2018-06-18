package ex.guesser.areas.common.scheduledTasks.schedule;

import ex.guesser.areas.matches.repositories.RoundsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FinishRound {
    private final RoundsRepository roundsRepository;


    public FinishRound(RoundsRepository roundsRepository) {
        this.roundsRepository = roundsRepository;
    }

    @Scheduled(fixedRate = 21600000)
    public void reportCurrentTime() {
        this.roundsRepository.finishRound();
    }
}
