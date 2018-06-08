package ex.guesser.areas.points.services;

import ex.guesser.areas.common.commonFunctions.PredictionsToMatchMapping;
import ex.guesser.areas.errorHandling.errors.UserNotFound;
import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.matches.models.binding.MatchDisplayBindingModel;
import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.matches.repositories.MatchRepository;
import ex.guesser.areas.matches.services.MatchService;
import ex.guesser.areas.points.entities.Prediction;
import ex.guesser.areas.points.repositories.PredictionRepository;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static ex.guesser.areas.common.commonFunctions.Constants.*;


@Service
public class PredictionServiceIml implements PredictionService {



    private final ModelMapper mapper;
    private final PredictionRepository predictionRepository;
    private final MatchRepository matchRepository;
    private final MatchService matchService;
    private final UserRepository userRepository;

    public PredictionServiceIml(ModelMapper mapper, PredictionRepository predictionRepository, MatchRepository matchRepository, @Lazy MatchService matchService, UserRepository userRepository) {
        this.mapper = mapper;
        this.predictionRepository = predictionRepository;
        this.matchRepository = matchRepository;
        this.matchService = matchService;
        this.userRepository = userRepository;
    }

    @Override
    public int addPrediction(MatchDisplayContainer bindingModel, Principal principal) {
        List<MatchDisplayBindingModel> matches = new ArrayList<>();
        List<MatchDisplayBindingModel> temp = new ArrayList<>();
        List<Long> matchIds = new ArrayList<>();
        bindingModel.getModel().stream()
                .filter(m -> m.getPredictedGalsHome() != null)
                .filter(m -> m.getPredictedGoalsAway() != null)
                .forEach(x->{
                    temp.add(x);
                    matchIds.add(x.getId());
                });
        List<FootballMatch> allMatches = this.matchRepository.findAllById(matchIds);

        for (FootballMatch match : allMatches) {
            if (match.getStatus().equalsIgnoreCase(CURRENT_STATUS)){
                for (MatchDisplayBindingModel matchDisplayBindingModel : temp) {
                    if (matchDisplayBindingModel.getId()==match.getId()){
                        matches.add(matchDisplayBindingModel);
                    }
                }
            }
        }

        List<Prediction> predictions = new ArrayList<>();


        for (MatchDisplayBindingModel match : matches) {
            Prediction prediction = this.predictionRepository.
                    findFirstByUser_UsernameAndFootballMatch_Id(principal.getName(), match.getId());
            if (prediction == null) {
                prediction = new Prediction();
            }

            prediction.setUser(this.userRepository.findByUsername(principal.getName()));
            prediction.setFootballMatch(this.matchRepository.findById(match.getId()).get());
            prediction.setGoalsHome(match.getPredictedGalsHome());
            prediction.setGoalsAway(match.getPredictedGoalsAway());
            predictions.add(prediction);
        }

        this.predictionRepository.saveAll(predictions);

        return bindingModel.getModel().size() - matches.size();
    }

    @Override
    public MatchDisplayContainer getAllPredictions() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = null;
        if (principal instanceof UserDetails) {
            userId = ((User)principal).getId();
        }

        List<MatchDisplayBindingModel> currentMatches = this.takePredictions(userId, ALL_MATCHES);
        return new MatchDisplayContainer(currentMatches);
    }

    @Override
    public MatchDisplayContainer getFinishedAndClosedPredictions(String userId) {
        List<MatchDisplayBindingModel> currentMatches = this.takePredictions(userId, GUEST_MATCHES);
        return new MatchDisplayContainer(currentMatches);
    }

    @Override
    public List<Prediction> getPredictionsForMatchId(long footballMatchId) {
         return this.predictionRepository.findAllByFootballMatch_Id(footballMatchId);

    }

    @Override
    public List<Integer> calculatePoints(FootballMatch matchResult) {
        List<Prediction> predictionsForMatch = this.getPredictionsForMatchId(matchResult.getId());
        List<Integer> points = new ArrayList<>();
        for (Prediction predictionForMatch : predictionsForMatch) {
            int pointsToAdd = calculate(predictionForMatch, matchResult);
            predictionForMatch.setPoints(pointsToAdd);
            points.add(pointsToAdd);
        }
        this.predictionRepository.saveAll(predictionsForMatch);
        return points;
    }


    private List<MatchDisplayBindingModel> takePredictions(String userId, String key) {
        User user = null;
        List<Prediction> predictions = new ArrayList<>();

        if (userId!=null){
            user = this.userRepository.findById(userId).orElse(null);
            if (user==null){
                throw new UserNotFound();
            }
            predictions = this.predictionRepository
                    .findAllByUserId(userId);
        }

        List<MatchDisplayBindingModel> matches = this.matchService.getAllMatches();


        if (key.equalsIgnoreCase(ALL_MATCHES)){
            PredictionsToMatchMapping.mapAllMatches(predictions,matches);
        }
        else if (key.equalsIgnoreCase(GUEST_MATCHES)){
            PredictionsToMatchMapping.mapGuestMatches(predictions,matches);
        }

        return matches;
    }
    public int calculate(Prediction predictionDto, FootballMatch matchResult) {
        int predictionDelta = predictionDto.getGoalsHome() - predictionDto.getGoalsAway();
        int matchResultDelta = matchResult.getGoalsHome()-matchResult.getGoalsAway();
        int points =0;
        if (predictionDelta==matchResultDelta){
            if (predictionDto.getGoalsAway()==matchResult.getGoalsAway()){
                points = POINTS_FOR_GUESSED_SCORE;
            }
            else {
                points = POINTS_FOR_GUESSED_DELTA;
            }
        }
        else if (matchResultDelta*predictionDelta>0){
            points = POINTS_FOR_GUESSED_WINNER;
        }
        else if (matchResultDelta*predictionDelta<=0){
            points = POINTS_FOR_WRONG_PREDICTION;
        }
        return points;
    }
}
