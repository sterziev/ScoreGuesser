package ex.guesser.areas.user.services;

import ex.guesser.areas.user.entities.MiniLeague;
import ex.guesser.areas.user.models.binding.JoinMiniLeagueBM;
import ex.guesser.areas.user.models.binding.MiniLeagueBM;
import ex.guesser.areas.user.models.dtos.MiniLeagueDto;
import ex.guesser.areas.user.models.dtos.UserWithPointsDto;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;

public interface MiniLeagueService {
    void create(MiniLeagueBM miniLeagueBM, Authentication authentication);

    MiniLeague join(JoinMiniLeagueBM miniLeagueBM, Authentication authentication);

    MiniLeagueDto findById(String id, List<UserWithPointsDto> usersPoints, Principal principal);
}
