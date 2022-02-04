package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;

import javax.validation.constraints.Pattern;
import java.util.Set;

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
    @Pattern(regexp = Regex.EMAIL,
            message = Regex.EMAIL_FAIL_MESSAGE)
    private String username;
    @Pattern(regexp = Regex.PASSWORD,
            message = Regex.PASSWORD_FAIL_MESSAGE)
    private String password;
    @Pattern(regexp = Regex.FIRST_NAME,
            message = Regex.FIRST_NAME_FAIL_MESSAGE)
    private String firstName;
    @Pattern(regexp = Regex.LAST_NAME,
            message = Regex.LAST_NAME_FAIL_MESSAGE)
    private String lastName;
    @Pattern(regexp = Regex.PATRONYMIC,
            message = Regex.PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
    private Set<String> roles;
}
