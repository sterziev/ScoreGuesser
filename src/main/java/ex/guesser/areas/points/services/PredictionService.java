package ex.guesser.areas.points.services;

import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.points.entities.Prediction;

import java.security.Principal;
import java.util.List;

public interface PredictionService {

    int addPrediction(MatchDisplayContainer bindingModel, Principal principal);

//    MatchDisplayContainer getCurrentPredictions(Principal principal);

    MatchDisplayContainer getAllPredictions();

    MatchDisplayContainer getFinishedAndClosedPredictions(String userId);

    List<Prediction> getPredictionsForMatchId(long id);

    List<Integer> calculatePoints(FootballMatch matchResult);
}
