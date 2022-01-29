package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangePasswordDto {
    @Pattern(regexp = Regex.EMAIL,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = Regex.PASSWORD,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String newPassword;

    @Pattern(regexp = Regex.PASSWORD,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String checkNewPassword;
}
