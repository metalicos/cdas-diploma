package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Regex {
    /* Regex Patterns */
    public static final String PASSWORD_RGX =
            "(?!.*\\s)^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9$&+,:;=?@#|'<>.^*()%!\\-]{8,}$";
    public static final String TOKEN_WITH_TYPE_RGX =
            "^Bearer [A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_.+=]{1,}$";
    public static final String NAME_RGX = "(?i)(^[\\p{L}])((?![ .,'-]$)[\\p{L}]){0,24}$";
    public static final String PERMISSION_VALUE_RGX = "^((r_|u_|w_|d_)[a-z_]+)$";
    public static final String PERMISSION_NAME_RGX = "^[a-zA-Z ]+$";
    public static final String ROLE_NAME_RGX = "^[A-Z_]+$";
    public static final String SORT_BY_RGX = "^(NONE|(?i)[\\p{L}.,'-]+)$";
    public static final String EMAIL_RGX = "^[A-z0-9.-]+[A-z0-9]@[A-z0-9][A-z0-9.-]+\\.[A-z]{2,}$";
    /* Constraints Fail Messages*/
    public static final String EMAIL_FAIL_MESSAGE = "Email is invalid";
    public static final String PASSWORD_FAIL_MESSAGE = "Password is invalid";
    public static final String TOKEN_FAIL_MESSAGE = "Token is invalid";
    public static final String FIRST_NAME_FAIL_MESSAGE = "First name is not valid";
    public static final String LAST_NAME_FAIL_MESSAGE = "Last name is not valid";
    public static final String PATRONYMIC_FAIL_MESSAGE = "Patronymic is not valid";
    public static final String SORT_DIRECTION_PATTERN = "(ASC)|(DESC)";
    public static final String PERMISSION_VALUE_FAIL_MESSAGE = """
            Permission value is of invalid format. Must:
            1. Contain at start r_<permission> / w_<permission> / d_<permission> / u_<permission> prefixes.
            2. Contain only lowercase letters of english alphabet and '_' symbols if needed.
            Example: r_image_description, u_image_description, w_docs
            """;
    public static final String PERMISSION_NAME_FAIL_MESSAGE =
            "Permission name is of invalid format. Must contain only letters of english alphabet and ' ' symbols.";
    public static final String ROLE_NAME_FAIL_MESSAGE =
            "Role name is of invalid format. Must contain only uppercase letters of english alphabet and '_' symbols if needed.";
    public static final String VALUE_IS_BLANK_MSG = "Value is not valid. Cause: blank";
    public static final String VALUE_IS_NULL_MSG = "Value is not valid. Cause: null";
    public static final String VALUE_NOT_NUMBER_MSG = "Value is not valid. Cause: not number";
    public static final String NOT_POSITIVE_MSG = "Value is not valid. Cause: not positive";
    public static final String SORT_DIRECTION_FAILED_MSG = "Direction is invalid. Should be ASC or DESC.";
    public static final String NOT_POSITIVE_OR_ZERO_MSG = "Value is not valid. Cause: not zero or not positive number";
}
