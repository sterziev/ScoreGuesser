package ex.guesser.areas.matches.models.dtos;

import java.util.List;

public class MatchCloseDtoContainer {
    private List<MatchCloseDto> model;

    public MatchCloseDtoContainer(List<MatchCloseDto> model) {
        this.model = model;
    }

    public MatchCloseDtoContainer() {
    }

    public List<MatchCloseDto> getModel() {
        return model;
    }

    public void setModel(List<MatchCloseDto> model) {
        this.model = model;
    }
}
