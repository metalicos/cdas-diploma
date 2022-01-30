package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Set<String> roles;
}
