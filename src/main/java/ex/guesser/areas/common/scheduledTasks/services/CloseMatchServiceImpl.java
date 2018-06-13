package ex.guesser.areas.common.scheduledTasks.services;

import ex.guesser.areas.matches.repositories.MatchRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CloseMatchServiceImpl implements CloseMatchService {
    private final MatchRepository matchRepository;

    public CloseMatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public void closeMatch() {
        this.matchRepository.checkMatchesForClosing();
    }
}
