package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex2022@gmail.com\",\n" +
        "    \"password\": \"2022h@tD@g\"\n" +
        "}")
public class LoginDto {
    @Pattern(regexp = Regex.EMAIL,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = Regex.PASSWORD,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String password;
}
