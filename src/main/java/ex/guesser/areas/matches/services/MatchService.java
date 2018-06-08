package ex.guesser.areas.matches.services;


import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.matches.models.binding.AddMatchBinningModel;
import ex.guesser.areas.matches.models.binding.MatchDisplayBindingModel;
import ex.guesser.areas.matches.models.binding.MatchStatusChangeBindingContainer;
import ex.guesser.areas.matches.models.dtos.MatchCloseDtoContainer;
import ex.guesser.areas.matches.models.dtos.MatchResultDto;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    FootballMatch addMatch(AddMatchBinningModel bindingModel);

    boolean validateLocalDate(AddMatchBinningModel bindingModel);

    public List<MatchDisplayBindingModel> getAllMatches();

    List<MatchDisplayBindingModel> getAllMatchesByStatus(String status);

    MatchCloseDtoContainer getMatchesForChangesByStatus(String status);

    List<FootballMatch> changeStatus(MatchStatusChangeBindingContainer bindingModel);

    MatchResultDto getMatchById(long id);

    Optional<FootballMatch> getAllFootballMatchesEntById(long matchId);
}
