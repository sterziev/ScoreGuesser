package ex.guesser.areas.common.dtos;

import ex.guesser.areas.common.enums.Gender;
import ex.guesser.areas.user.entities.MiniLeague;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InfoContainer {

    private String username;
    private String firstName;
    private String lastName;
    private String favTeam;
    private String country;
    private Gender sex;
    private Map<Integer,Integer> pointsPerRound;
    private Set<MiniLeague> leagues;
    private Set<MiniLeague> adminsOf;
    private Set<MiniLeague> creatorOf;
    private double averagePoints;
    private int maxPoints;
    private int minPoints;
    private int points;

    public InfoContainer() {
        this.leagues = new HashSet<>();
        this.pointsPerRound = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavTeam() {
        return favTeam;
    }

    public void setFavTeam(String favTeam) {
        this.favTeam = favTeam;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Map<Integer, Integer> getPointsPerRound() {
        return pointsPerRound;
    }

    public void setPointsPerRound(Map<Integer, Integer> pointsPerRound) {
        this.pointsPerRound = pointsPerRound;
    }

    public Set<MiniLeague> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<MiniLeague> leagues) {
        this.leagues = leagues;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(double averagePoints) {
        this.averagePoints = averagePoints;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public Set<MiniLeague> getAdminsOf() {
        return adminsOf;
    }

    public void setAdminsOf(Set<MiniLeague> adminsOf) {
        this.adminsOf = adminsOf;
    }

    public Set<MiniLeague> getCreatorOf() {
        return creatorOf;
    }

    public void setCreatorOf(Set<MiniLeague> creatorOf) {
        this.creatorOf = creatorOf;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
