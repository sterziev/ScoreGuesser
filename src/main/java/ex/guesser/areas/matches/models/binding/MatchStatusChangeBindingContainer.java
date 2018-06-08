package ex.guesser.areas.matches.models.binding;

import java.util.List;

public class MatchStatusChangeBindingContainer {
    private List<MatchStatusChangeBinding> model;

    public MatchStatusChangeBindingContainer(List<MatchStatusChangeBinding> model) {
        this.model = model;
    }

    public MatchStatusChangeBindingContainer() {
    }

    public List<MatchStatusChangeBinding> getModel() {
        return model;
    }

    public void setModel(List<MatchStatusChangeBinding> model) {
        this.model = model;
    }
}
