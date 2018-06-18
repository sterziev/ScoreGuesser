package ex.guesser.areas.user.models.dtos;

import ex.guesser.areas.user.entities.User;

import java.util.Set;

public class MiniLeagueDto {
    private String leagueName;
    private String keyCode;
    private String id;
    private Set<User> participants;

    public MiniLeagueDto() {
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
