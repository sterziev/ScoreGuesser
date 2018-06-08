package ex.guesser.areas.common.enums;

public enum Gender {
    MALE,
    FEMALE;

    public static String getGender(Gender gender) {
        switch(gender) {
            case MALE:
                return "MALE";
            case FEMALE:
                return "FEMALE";
        }

        return null;
    }


}
