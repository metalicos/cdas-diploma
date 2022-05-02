package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.EMAIL_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.FIRST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.LAST_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.NAME_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PASSWORD_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PASSWORD_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PATRONYMIC_FAIL_MESSAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"id\": 1,\n" +
        "    \"username\": \"ostap.ja@gmail.com\",\n" +
        "    \"firstName\": \"Ostap\",\n" +
        "    \"lastName\": \"Komplikevych\",\n" +
        "    \"patronymic\": \"Yaroslavovych\",\n" +
        "    \"isEnabled\": true,\n" +
        "    \"roles\": [\n" +
        "        {\n" +
        "            \"id\": 1,\n" +
        "            \"role\": \"OWNER\",\n" +
        "            \"permissions\": [\n" +
        "                {\n" +
        "                    \"id\": 3,\n" +
        "                    \"name\": \"Update All\",\n" +
        "                    \"value\": \"u_all\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"id\": 4,\n" +
        "                    \"name\": \"Delete All\",\n" +
        "                    \"value\": \"d_all\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"id\": 1,\n" +
        "                    \"name\": \"Read All\",\n" +
        "                    \"value\": \"r_all\"\n" +
        "                },\n" +
        "                {\n" +
        "                    \"id\": 2,\n" +
        "                    \"name\": \"Write All\",\n" +
        "                    \"value\": \"w_all\"\n" +
        "                }\n" +
        "            ]\n" +
        "        }\n" +
        "    ]\n" +
        "}")
public class AccountDto implements Serializable {
    private Long id;
    @Pattern(regexp = EMAIL_RGX,
            message = EMAIL_FAIL_MESSAGE)
    private String username;
    @Pattern(regexp = PASSWORD_RGX,
            message = PASSWORD_FAIL_MESSAGE)
    @JsonIgnore
    private String password;
    @Pattern(regexp = NAME_RGX,
            message = FIRST_NAME_FAIL_MESSAGE)
    private String firstName;
    @Pattern(regexp = NAME_RGX,
            message = LAST_NAME_FAIL_MESSAGE)
    private String lastName;
    @Pattern(regexp = NAME_RGX,
            message = PATRONYMIC_FAIL_MESSAGE)
    private String patronymic;
    private Boolean isEnabled;
    @JsonIgnore
    private Byte[] photo;
    private Set<@Valid RoleDto> roles;
    private Set<String> permissions;
}
