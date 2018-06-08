package ex.guesser.areas.common.enums;

public enum VirusMagnitude {
    LOW,
    MEDIUM,
    HIGH;

    public static int getNumeralValue(VirusMagnitude virusMagnitude) {
        switch(virusMagnitude) {
            case LOW:
                return 3;
            case MEDIUM:
                return 5;
            case HIGH:
                return 7;
        }

        return 0;
    }
}
