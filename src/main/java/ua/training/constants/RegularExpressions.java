package ua.training.constants;

public class RegularExpressions {
    /**
     * Exhibition Regex
     */
    public static final String EN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{2,}$";
    public static final String UA_REGEX = "[А-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9\\']{2,}";
    public static final String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
    public static final String DATE_REGEX = "^(19|20)\\d\\d[- .](0[1-9]|1[012])[- .](0[1-9]|[12][0-9]|3[01])$";
    public static final String PRICE_REGEX = "^[0-9]+$";

    /**
     * User Regex
     */
    public static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{1,19}$";
    public static final String PASSWORD_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії0-9]+";
    public static final String EMAIL_REG = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$";
    public static final String FIRST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
    public static final String LAST_NAME_REG = "[A-Za-zА-ЩЬЮЯЫҐЄІЇа-щьюяыґєії\\']{2,}";
}
