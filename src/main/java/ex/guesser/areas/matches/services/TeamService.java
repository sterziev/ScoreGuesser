package ex.guesser.areas.matches.services;

import ex.guesser.areas.matches.models.dtos.TeamNamesDto;

import java.util.List;

public interface TeamService {
    List<TeamNamesDto> getTeamNames();
}
