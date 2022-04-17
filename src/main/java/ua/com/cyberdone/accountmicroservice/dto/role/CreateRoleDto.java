package ua.com.cyberdone.accountmicroservice.dto.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Set;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PERMISSION_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.PERMISSION_NAME_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.ROLE_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.ROLE_NAME_RGX;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"role\": \"DRIVER\",\n" +
        "    \"permissionNames\": [\n" +
        "        \"Read Directions\"\n" +
        "    ]\n" +
        "}")
public class CreateRoleDto {
    @Pattern(regexp = ROLE_NAME_RGX, message = ROLE_NAME_FAIL_MESSAGE)
    private String role;
    private Set<@Pattern(regexp = PERMISSION_NAME_RGX, message = PERMISSION_NAME_FAIL_MESSAGE) String> permissionNames;
}
