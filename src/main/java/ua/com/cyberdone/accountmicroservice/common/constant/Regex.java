package ua.com.cyberdone.accountmicroservice.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Regex {
    public static final String EMAIL_RGX =
            "^[A-z0-9.-]+[A-z0-9]@[A-z0-9][A-z0-9.-]+\\.[A-z]{2,}$";
    public static final String EMAIL_FAIL_MESSAGE = "Email is invalid";

    public static final String PASSWORD_RGX =
            "(?!.*\\s)^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9$&+,:;=?@#|'<>.^*()%!\\-]{8,}$";
    public static final String PASSWORD_FAIL_MESSAGE = "Password is invalid";

    public static final String TOKEN_WITH_TYPE_RGX =
            "^Bearer [A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_.+=]{1,}$";
    public static final String TOKEN_FAIL_MESSAGE = "Token is invalid";

    public static final String NAME_RGX = "(?i)(^[\\p{L}])((?![ .,'-]$)[\\p{L}]){0,24}$";
    public static final String FIRST_NAME_FAIL_MESSAGE = "First name is not valid";
    public static final String LAST_NAME_FAIL_MESSAGE = "Last name is not valid";
    public static final String PATRONYMIC_FAIL_MESSAGE = "Patronymic is not valid";

    public static final String PERMISSION_VALUE_RGX = "^((r_|u_|w_|d_)[a-z_]+)$";
    public static final String PERMISSION_VALUE_FAIL_MESSAGE = """
            Permission value is of invalid format. Must:
            1. Contain at start r_<permission> / w_<permission> / d_<permission> / u_<permission> prefixes.
            2. Contain only lowercase letters of english alphabet and '_' symbols if needed.
            Example: r_image_description, u_image_description, w_docs
            """;

    public static final String PERMISSION_NAME_RGX = "^[a-zA-Z ]+$";
    public static final String PERMISSION_NAME_FAIL_MESSAGE =
            "Permission name is of invalid format. Must contain only letters of english alphabet and ' ' symbols.";

    public static final String ROLE_NAME_RGX = "^[A-Z_]+$";
    public static final String ROLE_NAME_FAIL_MESSAGE =
            "Role name is of invalid format. Must contain only uppercase letters of english alphabet and '_' symbols if needed.";

    public static final String SORT_BY_RGX = "^(NONE|(?i)[\\p{L}.,'-]+)$";
    public static final String SORT_BY_FAIL_MESSAGE =
            "Sort by can be only NONE - not to sort, or single word with allowed ''', '-' symbols if needed.";

    public static final String SORTING_DIRECTION_RGX = "^(ASC|DESC|NONE)$";
    public static final String SORTING_DIRECTION_FAIL_MESSAGE =
            "Sorting direction can be only ASC, DESC or NONE - will take a default one.";
}
