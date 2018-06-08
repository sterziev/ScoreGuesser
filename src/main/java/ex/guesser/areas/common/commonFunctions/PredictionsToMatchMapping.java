package ex.guesser.areas.common.commonFunctions;

import ex.guesser.areas.matches.models.binding.MatchDisplayBindingModel;
import ex.guesser.areas.points.entities.Prediction;

import java.util.List;

import static ex.guesser.areas.common.commonFunctions.Constants.*;

public final class PredictionsToMatchMapping {



    public static void mapAllMatches(List<Prediction> predictions, List<MatchDisplayBindingModel> matches){
        mappingPredictionsToMatchDisplay(predictions,matches,ALL_MATCHES);
    }

    public static void mapGuestMatches(List<Prediction> predictions, List<MatchDisplayBindingModel> matches){
        mappingPredictionsToMatchDisplay(predictions,matches,GUEST_MATCHES);

    }

    private static void mappingPredictionsToMatchDisplay(List<Prediction> predictions, List<MatchDisplayBindingModel> matches, String key) {
        if (key.equalsIgnoreCase(ALL_MATCHES)){
            for (Prediction prediction : predictions) {
                for (MatchDisplayBindingModel match : matches) {
                    if (prediction.getFootballMatch().getId()==match.getId()){
                        match.setPoint(prediction.getPoints());
                        match.setPredictedGalsHome(prediction.getGoalsHome());
                        match.setPredictedGoalsAway(prediction.getGoalsAway());
                    }
                }
            }
        }
        else if (key.equalsIgnoreCase(GUEST_MATCHES)){
            for (Prediction prediction : predictions) {
                for (MatchDisplayBindingModel match : matches) {
                    if (match.getStatus().equalsIgnoreCase(CURRENT_STATUS) || match.getStatus().equalsIgnoreCase(PLANNED_STATUS)) {
                        continue;
                    }
                    if (prediction.getFootballMatch().getId()==match.getId()){
                        match.setPoint(prediction.getPoints());
                        match.setPredictedGalsHome(prediction.getGoalsHome());
                        match.setPredictedGoalsAway(prediction.getGoalsAway());
                    }
                }
            }
        }

    }
}
