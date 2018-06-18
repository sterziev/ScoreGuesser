package ex.guesser.areas.user.services;

import ex.guesser.areas.errorHandling.errors.AlreadyInLeagueException;
import ex.guesser.areas.errorHandling.errors.LeagueNotFound;
import ex.guesser.areas.errorHandling.errors.NotAuthorizedToCreateLeagueException;
import ex.guesser.areas.user.entities.MiniLeague;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.models.binding.JoinMiniLeagueBM;
import ex.guesser.areas.user.models.binding.MiniLeagueBM;
import ex.guesser.areas.user.models.dtos.MiniLeagueDto;
import ex.guesser.areas.user.models.dtos.UserWithPointsDto;
import ex.guesser.areas.user.repositories.MiniLeagueRepository;
import ex.guesser.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class MiniLeagueServiceImpl implements MiniLeagueService {
    private final MiniLeagueRepository miniLeagueRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public MiniLeagueServiceImpl(MiniLeagueRepository miniLeagueRepository, UserRepository userRepository, ModelMapper mapper) {
        this.miniLeagueRepository = miniLeagueRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(MiniLeagueBM miniLeagueBM, Authentication authentication) {
        if (authentication == null){
            throw new NotAuthorizedToCreateLeagueException();
        }

        User user = (User)authentication.getPrincipal();
        MiniLeague miniLeague = this.mapper.map(miniLeagueBM, MiniLeague.class);
        miniLeague.getAdmins().add(user);
        miniLeague.setCreator(user);
        miniLeague.getParticipants().add(user);
        String leagueKey = this.createLegueKey(miniLeague.getLeagueName());
        miniLeague.setKeyCode(leagueKey);

        this.miniLeagueRepository.save(miniLeague);
    }

    @Override
    public MiniLeague join(JoinMiniLeagueBM miniLeagueBM, Authentication authentication) {
        if (authentication == null){
            throw new NotAuthorizedToCreateLeagueException();
        }
        User user = (User)authentication.getPrincipal();

        MiniLeague miniLeague = this.miniLeagueRepository.findByKeyCode(miniLeagueBM.getLeagueName());
        if (miniLeague==null){
            throw new LeagueNotFound();
        }

        Set<String> participants = miniLeague.getParticipants().stream().map(User::getUsername).collect(Collectors.toSet());
        if (participants.contains(user.getUsername())){
            throw new AlreadyInLeagueException();
        }

        miniLeague.addParticipant(user);
        this.miniLeagueRepository.save(miniLeague);

        return miniLeague;

    }

    @Override
    public MiniLeagueDto findById(String id, List<UserWithPointsDto> usersPoints, Principal principal) {
        boolean isParticipant = false;
        MiniLeague miniLeague = this.miniLeagueRepository.findById(id).orElse(null);
        if (miniLeague == null){
            throw new LeagueNotFound();
        }
        MiniLeagueDto miniLeagueDto = this.mapper.map(miniLeague, MiniLeagueDto.class);
        for (UserWithPointsDto usersPoint : usersPoints) {
            if (usersPoint.getUsername().equalsIgnoreCase(principal.getName())){
                isParticipant=true;
            }
        }
        if (!isParticipant){
            miniLeagueDto.setKeyCode(null);
        }
        return miniLeagueDto;

    }

    @Override
    public MiniLeagueDto findById(String id) {
        MiniLeague miniLeague = this.miniLeagueRepository.findById(id).orElse(null);
        if (miniLeague == null){
            throw new LeagueNotFound();
        }
        return this.mapper.map(miniLeague, MiniLeagueDto.class);

    }

    @Override
    public void leaveMiniLeague(String leagueId, String userId) {
        MiniLeague league = this.miniLeagueRepository.findById(leagueId).orElse(null);
        if (league==null){
            throw new LeagueNotFound();
        }
        Set<User> participants = league.getParticipants();
        league.setParticipants(new HashSet<>());
        for (User p : participants) {
            if(!p.getId().equalsIgnoreCase(userId)){
               league.addParticipant(p);
            }
        }
        this.miniLeagueRepository.save(league);
    }


    private String createLegueKey(String leagueName) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);

        return leagueName + randomNum;
    }
}
