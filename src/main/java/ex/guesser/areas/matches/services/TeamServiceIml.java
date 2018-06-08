package ex.guesser.areas.matches.services;

import ex.guesser.areas.matches.entities.Team;
import ex.guesser.areas.matches.models.dtos.TeamNamesDto;
import ex.guesser.areas.matches.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceIml implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper mapper;

    public TeamServiceIml(TeamRepository teamRepository, ModelMapper mapper) {
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TeamNamesDto> getTeamNames() {
        List<Team> allTeams = teamRepository.findAll();
        List<TeamNamesDto> teamNames = new ArrayList<>();
        Type teamsListType = new TypeToken<List<TeamNamesDto>>() {}.getType();
        return mapper.map(allTeams, teamsListType);
    }
}
