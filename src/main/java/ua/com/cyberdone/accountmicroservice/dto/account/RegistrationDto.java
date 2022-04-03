package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Set;

import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.EMAIL_RGX;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.FIRST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.LAST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.NAME_RGX;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PASSWORD_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PASSWORD_RGX;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PATRONYMIC_FAIL_MESSAGE;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"firstName\": \"Bearny\",\n" +
        "    \"lastName\": \"Pritrix\",\n" +
        "    \"patronymic\": \"Adams son\",\n" +
        "    \"username\": \"b.pritrix@gmail.com\",\n" +
        "    \"password\": \"22Rtytr#$\",\n" +
        "    \"roles\": [\n" +
        "        \"ADMIN\",\n" +
        "        \"MANAGER\"\n" +
        "    ]\n" +
        "}")
public class RegistrationDto {
    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String username;
    @Pattern(regexp = PASSWORD_RGX,
            message = PASSWORD_FAIL_MESSAGE)
    private String password;
    @Pattern(regexp = PASSWORD_RGX,
            message = PASSWORD_FAIL_MESSAGE)
    private String passwordCheck;
    @Pattern(regexp = NAME_RGX,
            message = FIRST_NAME_FAIL_MESSAGE)
    private String firstName;
    @Pattern(regexp = NAME_RGX,
            message = LAST_NAME_FAIL_MESSAGE)
    private String lastName;
    @Pattern(regexp = NAME_RGX,
            message = PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
    private byte[] photo;
    private Set<String> roles;
}
