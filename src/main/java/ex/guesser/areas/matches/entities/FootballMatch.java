package ex.guesser.areas.matches.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

import static ex.guesser.areas.common.commonFunctions.Constants.PLANNED_STATUS;

@Entity
@Table(name = "matches")
public class FootballMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int round;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teamHome")
    private Team home;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teamAway")
    private Team away;
    @Column
    private String status;
    @Column
    private LocalDateTime kickOff;
    @Column
    private String stadium;
    @Column
    private Integer goalsHome;
    @Column
    private Integer goalsAway;
    @Column
    private Integer FullTimeGoalsHome;
    @Column
    private Integer FullTimeGoalsAway;


    public FootballMatch() {
        this.status = PLANNED_STATUS;
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
