package ex.guesser.areas.points.models.dtos;


import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.user.entities.User;

public class PointsDto {

    private long id;
    private FootballMatch footballMatch;
    private User user;
    private int points;
    private int currentPoints;

    public PointsDto() {
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FootballMatch getFootballMatch() {
        return footballMatch;
    }

    public void setFootballMatch(FootballMatch footballMatch) {
        this.footballMatch = footballMatch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
