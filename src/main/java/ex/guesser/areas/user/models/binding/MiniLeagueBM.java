package ex.guesser.areas.user.models.binding;

import ex.guesser.areas.errorHandling.validation.annotaitons.CheckForDuplicatedMiniLeague;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MiniLeagueBM {

    private static final String LEAGUE_NAME_NOT_EMPTY_MSG = "The Mini League name cannot be empty";
    private static final String LENGHT_LEAGUE_MSG = "The length of the mini league name must be more than 4 symbols and less than 40";
    private static final String DUPLICATED_MINILEAGUE = "This name is taken, please choose another";

    @NotEmpty(message = LEAGUE_NAME_NOT_EMPTY_MSG)
    @Size(min = 4, max = 40, message = LENGHT_LEAGUE_MSG)
    @CheckForDuplicatedMiniLeague(message = DUPLICATED_MINILEAGUE)
    private String leagueName;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}
