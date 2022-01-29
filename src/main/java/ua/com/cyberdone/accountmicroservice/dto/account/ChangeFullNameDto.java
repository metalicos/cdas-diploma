package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeFullNameDto {
    @Pattern(regexp = Regex.EMAIL,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = Regex.FIRST_NAME,
            message = Regex.FIRST_NAME_FAIL_MESSAGE)
    private String firstName;

    @Pattern(regexp = Regex.LAST_NAME,
            message = Regex.LAST_NAME_FAIL_MESSAGE)
    private String lastName;

    @Pattern(regexp = Regex.PATRONYMIC,
            message = Regex.PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
}
