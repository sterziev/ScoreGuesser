package ex.guesser.areas.user.models.dtos;

public class MiniLeagueDto {
    private String leagueName;
    private String keyCode;

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
}
