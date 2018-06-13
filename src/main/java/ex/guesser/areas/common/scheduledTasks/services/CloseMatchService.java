package ex.guesser.areas.common.scheduledTasks.services;

import ex.guesser.areas.user.models.binding.MiniLeagueBM;
import org.springframework.security.core.Authentication;

public interface CloseMatchService {
    void closeMatch();
}
