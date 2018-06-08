package ex.guesser.areas.matches.models.binding;

import ex.guesser.areas.matches.entities.Team;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public class MatchDisplayBindingModel {

    private static final String INCORRECT_GOALS_VALUE = "Invalid prediction, please use numbers in interval from 0 to 20!";

    private long id;
    private int round;
    private Team home;
    private Team away;
    private String status;
    private LocalDateTime kickOff;
    private String stadium;
    private Integer goalsHome;
    private Integer goalsAway;
    private Integer FullTimeGoalsHome;
    private Integer FullTimeGoalsAway;

    @Min(value = 0, message = INCORRECT_GOALS_VALUE)
    @Max(value = 20, message = INCORRECT_GOALS_VALUE)
    private Integer predictedGalsHome;
    @Min(value = 0, message = INCORRECT_GOALS_VALUE)
    @Max(value = 20, message = INCORRECT_GOALS_VALUE)
    private Integer predictedGoalsAway;

    private Integer point;


    public MatchDisplayBindingModel() {

    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getPredictedGalsHome() {
        return predictedGalsHome;
    }

    public void setPredictedGalsHome(Integer predictedGalsHome) {
        this.predictedGalsHome = predictedGalsHome;
    }

    public Integer getPredictedGoalsAway() {
        return predictedGoalsAway;
    }

    public void setPredictedGoalsAway(Integer predictedGoalsAway) {
        this.predictedGoalsAway = predictedGoalsAway;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getKickOff() {
        return kickOff;
    }

    public void setKickOff(LocalDateTime kickOff) {
        this.kickOff = kickOff;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(Integer goalsHome) {
        this.goalsHome = goalsHome;
    }

    public Integer getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(Integer goalsAway) {
        this.goalsAway = goalsAway;
    }

    public Integer getFullTimeGoalsHome() {
        return FullTimeGoalsHome;
    }

    public void setFullTimeGoalsHome(Integer fullTimeGoalsHome) {
        FullTimeGoalsHome = fullTimeGoalsHome;
    }

    public Integer getFullTimeGoalsAway() {
        return FullTimeGoalsAway;
    }

    public void setFullTimeGoalsAway(Integer fullTimeGoalsAway) {
        FullTimeGoalsAway = fullTimeGoalsAway;
    }
}
