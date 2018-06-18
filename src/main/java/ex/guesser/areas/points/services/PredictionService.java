package ex.guesser.areas.points.services;

import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.points.entities.Prediction;
import ex.guesser.areas.user.entities.User;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface PredictionService {

    int addPrediction(MatchDisplayContainer bindingModel, Principal principal);

//    MatchDisplayContainer getCurrentPredictions(Principal principal);

    MatchDisplayContainer getAllPredictions();

    MatchDisplayContainer getFinishedAndClosedPredictions(String userId);

    List<Prediction> getPredictionsForMatchId(long id);

    List<Integer> calculatePoints(FootballMatch matchResult);

    MatchDisplayContainer getAllPredictionsForCloseAndFinishedMatches(Set<User> participants);
}
