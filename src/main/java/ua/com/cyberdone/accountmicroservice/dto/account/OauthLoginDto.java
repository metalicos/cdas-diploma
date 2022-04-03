package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.EMAIL_RGX;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.FIRST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.LAST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.NAME_RGX;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex2022@gmail.com\",\n" +
        "    \"password\": \"2022h@tD@g\"\n" +
        "}")
public class OauthLoginDto {
    private String id;
    private String idToken;
    private String authToken;
    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String email;
    private String photoUrl;
    @Pattern(regexp = NAME_RGX,
            message = FIRST_NAME_FAIL_MESSAGE)
    private String firstName;
    @Pattern(regexp = NAME_RGX,
            message = LAST_NAME_FAIL_MESSAGE)
    private String lastName;
    private String provider;
}
