package ex.guesser.areas.matches.models.binding;

import ex.guesser.areas.matches.entities.Round;
import ex.guesser.areas.matches.repositories.RoundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static ex.guesser.areas.common.commonFunctions.Constants.*;

public class MatchDisplayContainer {
    @Valid
    private List<MatchDisplayBindingModel> model;
    private int currentRound;
    private Set<Integer> rounds;
    private Map<Integer,Integer> pointsPerRound;

    @Autowired
    private RoundsRepository roundsRepository;

    public MatchDisplayContainer(List<MatchDisplayBindingModel> matchDisplayBindingModel, List<Round> roundsEntities) {
        this.model = matchDisplayBindingModel;
        this.setCurrentRound(roundsEntities);
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

    private void setCurrentRound( List<Round> roundsEntities) {
        if (roundsEntities.size()==0){
            this.currentRound = 0;
        }
        else {
            int minCurrent = Integer.MAX_VALUE;
            int maxFinished = 0;
            int minPlanned = 0;

            for (Round r : roundsEntities) {
                switch (r.getStatus()){
                    case CURRENT_STATUS: minCurrent = r.getRound()<=minCurrent? r.getRound():minCurrent;
                    break;
                    case FINISHED_STATUS: maxFinished = r.getRound()>maxFinished? r.getRound(): maxFinished;
                }
            }
            if (minCurrent==0){
                this.currentRound = maxFinished;
            }
            else {
                this.currentRound=minCurrent;
            }

        }
    }

    public Set<Integer> getRounds() {
        return rounds;
    }

    private void setRounds() {
        this.rounds = this.model.stream().map(MatchDisplayBindingModel::getRound).collect(Collectors.toSet());
    }
    public List<MatchDisplayBindingModel> getMatchesByRound(int r){
        List<MatchDisplayBindingModel> result = new ArrayList();
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

    private void setPointsPerRound() {
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
