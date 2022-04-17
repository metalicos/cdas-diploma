package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PASSWORD_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PASSWORD_RGX;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex2022@gmail.com\",\n" +
        "    \"password\": \"2022h@tD@g\"\n" +
        "}")
public class LoginDto {
    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = PASSWORD_RGX,
            message = PASSWORD_FAIL_MESSAGE)
    private String password;
}
