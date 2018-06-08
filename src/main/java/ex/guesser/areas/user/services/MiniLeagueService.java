package ex.guesser.areas.user.services;

import ex.guesser.areas.user.entities.MiniLeague;
import ex.guesser.areas.user.models.binding.JoinMiniLeagueBM;
import ex.guesser.areas.user.models.binding.MiniLeagueBM;
import ex.guesser.areas.user.models.dtos.MiniLeagueDto;
import org.springframework.security.core.Authentication;

public interface MiniLeagueService {
    void create(MiniLeagueBM miniLeagueBM, Authentication authentication);

    MiniLeague join(JoinMiniLeagueBM miniLeagueBM, Authentication authentication);

    MiniLeagueDto findById(String id);
}
