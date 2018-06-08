package ex.guesser.areas.matches.models.binding;

import ex.guesser.areas.matches.entities.Team;

import java.time.LocalDateTime;

public class MatchStatusChangeBinding {

    private long id;
    private int round;
    private Team home;
    private Team away;
    private String status;
    private LocalDateTime kickOff;
    private Integer goalsHome;
    private Integer goalsAway;
    private boolean change;

    public MatchStatusChangeBinding() {
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
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

    public void setStatus(String String) {
        this.status = status;
    }

    public LocalDateTime getKickOff() {
        return kickOff;
    }

    public void setKickOff(LocalDateTime kickOff) {
        this.kickOff = kickOff;
    }
}
