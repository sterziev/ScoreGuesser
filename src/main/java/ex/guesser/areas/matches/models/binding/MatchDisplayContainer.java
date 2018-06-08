package ex.guesser.areas.matches.models.binding;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

public class MatchDisplayContainer {
    @Valid
    private List<MatchDisplayBindingModel> model;
    private int currentRound;
    private Set<Integer> rounds;
    private Map<Integer,Integer> pointsPerRound;

    public MatchDisplayContainer(List<MatchDisplayBindingModel> matchDisplayBindingModel) {
        this.model = matchDisplayBindingModel;
        this.setCurrentRound();
        this.setRounds();
        this.setPointsPerRound();
    }

    public MatchDisplayContainer() {
    }

    public List<MatchDisplayBindingModel> getModel() {
        return model;
    }

    public void setModel(List<MatchDisplayBindingModel> model) {
        this.model = model;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    private void setCurrentRound() {
        for (MatchDisplayBindingModel displayBindingModel : this.model) {
            if (displayBindingModel.getStatus().equalsIgnoreCase("current")){
                this.currentRound = displayBindingModel.getRound();
            }
        }
    }

    public Set<Integer> getRounds() {
        return rounds;
    }

    public void setRounds() {
        this.rounds = this.model.stream().map(MatchDisplayBindingModel::getRound).collect(Collectors.toSet());
    }
    public List<MatchDisplayBindingModel> getMatchesByRound(int r){
        List result = new ArrayList();
        for (MatchDisplayBindingModel matchDisplayBindingModel : this.model) {
            if (matchDisplayBindingModel.getRound()==r){
                result.add(matchDisplayBindingModel);
            }
        }
        return result;
    }

    public Map<Integer, Integer> getPointsPerRound() {
        return pointsPerRound;
    }

    public void setPointsPerRound() {
        this.pointsPerRound = new HashMap<>();
        for (MatchDisplayBindingModel match : model) {
            this.pointsPerRound.putIfAbsent(match.getRound(),0);
            if (match.getPoint()==null){
                continue;
            }
            this.pointsPerRound.put(match.getRound(), this.pointsPerRound.get(match.getRound())+match.getPoint());
        }
    }

    public MatchDisplayBindingModel getMatchById(Long id){

        for (MatchDisplayBindingModel matchDisplayBindingModel : model) {
            if (matchDisplayBindingModel.getId()==id){
                return matchDisplayBindingModel;
            }
        }
        return null;
    }

    public Integer getMatchIdInList(Long id){
        for (int i = 0; i < this.model.size(); i++) {
            if (this.model.get(i).getId()==id){
                return i;
            }
        }
        return 0;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void setRounds(Set<Integer> rounds) {
        this.rounds = rounds;
    }

    public void setPointsPerRound(Map<Integer, Integer> pointsPerRound) {
        this.pointsPerRound = pointsPerRound;
    }
}
