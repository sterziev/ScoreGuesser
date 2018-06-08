package ex.guesser.areas.user.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class JoinMiniLeagueBM {

    private static final String LEAGUE_NAME_NOT_EMPTY_MSG = "The Mini League name cannot be empty";
    private static final String LENGHT_LEAGUE_MSG = "The length of the mini league name must be more than 4 symbols and less than 50";
    private static final String DUPLICATED_MINILEAGUE = "This name is taken, please choose another";

    @NotEmpty(message = LEAGUE_NAME_NOT_EMPTY_MSG)
    @Size(min = 4, max = 55, message = LENGHT_LEAGUE_MSG)
    private String leagueName;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}
