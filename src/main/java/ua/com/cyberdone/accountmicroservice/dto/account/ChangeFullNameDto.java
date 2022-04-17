package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.FIRST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.LAST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.NAME_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PATRONYMIC_FAIL_MESSAGE;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"username\": \"alex@gmail.com\",\n" +
        "    \"firstName\": \"Alex\",\n" +
        "    \"lastName\": \"Breyks\",\n" +
        "    \"patronymic\": \"D'Amanti\"\n" +
        "}")
public class ChangeFullNameDto {
    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String username;

    @Pattern(regexp = NAME_RGX,
            message = FIRST_NAME_FAIL_MESSAGE)
    private String firstName;

    @Pattern(regexp = NAME_RGX,
            message = LAST_NAME_FAIL_MESSAGE)
    private String lastName;

    @Pattern(regexp = NAME_RGX,
            message = PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
}
