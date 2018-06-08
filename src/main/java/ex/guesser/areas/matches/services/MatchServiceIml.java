package ex.guesser.areas.matches.services;

import ex.guesser.areas.common.commonFunctions.LocalDateParser;
import ex.guesser.areas.errorHandling.errors.MatchNotFound;
import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.matches.entities.Team;
import ex.guesser.areas.matches.models.binding.AddMatchBinningModel;
import ex.guesser.areas.matches.models.binding.MatchDisplayBindingModel;
import ex.guesser.areas.matches.models.binding.MatchStatusChangeBinding;
import ex.guesser.areas.matches.models.binding.MatchStatusChangeBindingContainer;
import ex.guesser.areas.matches.models.dtos.MatchCloseDto;
import ex.guesser.areas.matches.models.dtos.MatchCloseDtoContainer;
import ex.guesser.areas.matches.models.dtos.MatchResultDto;
import ex.guesser.areas.matches.repositories.MatchRepository;
import ex.guesser.areas.matches.repositories.TeamRepository;
import ex.guesser.areas.points.services.PredictionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ex.guesser.areas.common.commonFunctions.Constants.*;

@Service
public class MatchServiceIml implements MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final PredictionService predictionService;
    private final ModelMapper mapper;

    public MatchServiceIml(MatchRepository matchRepository, TeamRepository teamRepository, PredictionService predictionService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.predictionService = predictionService;
        this.mapper = new ModelMapper();
    }

    @Override
    public FootballMatch addMatch(AddMatchBinningModel bindingModel) {
        FootballMatch match = this.mapper.map(bindingModel, FootballMatch.class);
        Team home = this.teamRepository.findFirstByName(bindingModel.getHome());
        Team away = this.teamRepository.findFirstByName(bindingModel.getAway());

        match.setAway(away);
        match.setHome(home);
        match.setStatus(PLANNED_STATUS);

        LocalDateTime localDateTime = LocalDateParser.localDateTimeParser(bindingModel.getYear(),bindingModel.getMonth(),
                bindingModel.getDay(),bindingModel.getMin(),bindingModel.getMin());
        match.setKickOff(localDateTime);

        return this.matchRepository.save(match);

    }

    @Override
    public boolean validateLocalDate(AddMatchBinningModel bindingModel) {
        try {
            LocalDateTime localDateTime = LocalDateTime.of(
                    Integer.parseInt(bindingModel.getYear()),
                    Integer.parseInt(bindingModel.getMonth()),
                    Integer.parseInt(bindingModel.getDay()),
                    Integer.parseInt(bindingModel.getHour()),
                    Integer.parseInt(bindingModel.getMin()));
        }
        catch (DateTimeException ex){
            return false;
        }
        return true;
    }

    @Override
    public List<MatchDisplayBindingModel> getAllMatches() {
        List<FootballMatch> matches= this.matchRepository.findAllByOrderByKickOffAsc();
        Type matchListType = new TypeToken<List<MatchDisplayBindingModel>>() {}.getType();
        return this.mapper.map(matches, matchListType);

    }

    @Override
    public List<MatchDisplayBindingModel> getAllMatchesByStatus(String status) {
        List<FootballMatch> matches= this.matchRepository.findAllByStatusOrderByKickOffAsc(status);
        Type matchListType = new TypeToken<List<MatchDisplayBindingModel>>() {}.getType();
        return this.mapper.map(matches, matchListType);
    }

    @Override
    public MatchCloseDtoContainer getMatchesForChangesByStatus(String status) {
        List<FootballMatch> matches= this.matchRepository.findAllByStatusOrderByKickOffAsc(status);
        Type matchListType = new TypeToken<List<MatchCloseDto>>() {}.getType();

        return new MatchCloseDtoContainer(this.mapper.map(matches, matchListType));
    }

    @Override
    public List<FootballMatch> changeStatus(MatchStatusChangeBindingContainer bindingModel) {
        String change = PLANNED_STATUS;
        List<Long> ids = bindingModel.getModel()
                .stream()
                .filter(MatchStatusChangeBinding::isChange)
                .map(MatchStatusChangeBinding::getId)
                .collect(Collectors.toList());

        List<FootballMatch> matcheEntities = this.matchRepository.findAllById(ids);

        for (MatchStatusChangeBinding matches : bindingModel.getModel()) {
            for (FootballMatch matcheEntity : matcheEntities) {
                if (matches.isChange() && matcheEntity.getId() == matches.getId()) {
                    switch (matcheEntity.getStatus()) {
                        case CURRENT_STATUS:
                            change = CLOSED_STATUS;

                            break;
                        case CLOSED_STATUS:
                            change = FINISHED_STATUS;
                            matcheEntity.setGoalsHome(matches.getGoalsHome());
                            matcheEntity.setGoalsAway(matches.getGoalsAway());
                            this.predictionService.calculatePoints(matcheEntity);
                            break;
                        case PLANNED_STATUS:
                            change = CURRENT_STATUS;
                            break;
                    }

                    matcheEntity.setStatus(change);
                }
            }
        }
        return this.matchRepository.saveAll(matcheEntities);
    }

    @Override
    public MatchResultDto getMatchById(long id) {
        Optional<FootballMatch> matchEntity = this.matchRepository.findById(id);
        if (matchEntity.isPresent()){
            return this.mapper.map(matchEntity.get(), MatchResultDto.class);
        }
        throw new MatchNotFound();
    }

    @Override
    public Optional<FootballMatch> getAllFootballMatchesEntById(long matchId) {
        return this.matchRepository.findById(matchId);
    }
}
