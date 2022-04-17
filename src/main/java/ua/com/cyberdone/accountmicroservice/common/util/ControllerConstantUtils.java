package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ControllerConstantUtils {
    /* Parameters */
    public static final String DEVICE_UUID_PARAMETER = "device-uuid";
    public static final String DELEGATION_STATUS_PARAMETER = "delegation-status";
    public static final String USERNAME_PARAMETER = "username";
    /* Default values */
    public static final String DEFAULT_COMMENT_FOR_DEVICE_CONTROL_DELEGATION = "Please grant permission to control the device.";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_ELEMENTS_AMOUNT = "20";
    public static final String DEFAULT_DIRECTION = "ASC";
    public static final String DEFAULT_SEARCH = "NONE";
    /* Responses */
    public static final String OK = "OK";
}
