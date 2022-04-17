package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_RGX;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"oldEmail\": \"old2022@gmail.com\",\n" +
        "    \"newEmail\": \"newone2022@gmail.com\"\n" +
        "}")
public class ChangeEmailDto {
    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String oldEmail;

    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String newEmail;
}
