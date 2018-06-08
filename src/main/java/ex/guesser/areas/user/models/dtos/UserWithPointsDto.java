package ex.guesser.areas.user.models.dtos;

import ex.guesser.areas.common.enums.Gender;

public class UserWithPointsDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String favTeam;
    private String country;
    private Gender sex;
    private int pointsNumber;
    private Integer lasRoundPoints;

    public Integer getLasRoundPoints() {
        return lasRoundPoints;
    }

    public void setLasRoundPoints(Integer lasRoundPoints) {
        this.lasRoundPoints = lasRoundPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getPointsNumber() {
        return pointsNumber;
    }

    public void setPointsNumber(Integer pointsNumber) {
        this.pointsNumber = pointsNumber;
    }
}
