package ua.com.cyberdone.accountmicroservice.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Regex {

    public static final String EMAIL =
            "^[A-z0-9.-]+[A-z0-9]@[A-z0-9][A-z0-9.-]+\\.[A-z]{2,}$";
    public static final String PASSWORD =
            "(?!.*\\s)^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9$&+,:;=?@#|'<>.^*()%!\\-]{8,}$";
    public static final String TOKEN =
            "^[A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_.+=]{1,}$";
    public static final String TOKEN_TYPE =
            "^Bearer $";
    public static final String TOKEN_WITH_TYPE =
            "^Bearer [A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_=]{1,}\\.[A-Za-z0-9-_.+=]{1,}$";
    public static final String FIRST_NAME =
            "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
    public static final String LAST_NAME =
            "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
    public static final String PATRONYMIC =
            "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";


    public static final String EMAIL_FAIL_MESSAGE = "Email is invalid";
    public static final String PASSWORD_FAIL_MESSAGE = "Password is invalid";
    public static final String TOKEN_FAIL_MESSAGE = "Token is invalid";
    public static final String TOKEN_TYPE_FAIL_MESSAGE = "Token type is invalid";
    public static final String FIRST_NAME_FAIL_MESSAGE = "First name is not valid";
    public static final String LAST_NAME_FAIL_MESSAGE = "Last name is not valid";
    public static final String PATRONYMIC_FAIL_MESSAGE = "Patronymic is not valid";
}
