package ex.guesser.areas.matches.models.enums;

public enum Statuses {
    CURRENT,
    PLANED,
    CLOSED;

    public static String getGender(Statuses gender) {
        switch(gender) {
            case CURRENT:
                return "current";
            case PLANED:
                return "planed";
            case CLOSED:
                return "closed";
        }

        return null;
    }
}
