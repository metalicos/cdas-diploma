package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogoutDto {
    @Pattern(regexp = Regex.TOKEN_WITH_TYPE,
            message = Regex.TOKEN_FAIL_MESSAGE)
    private String token;
}
