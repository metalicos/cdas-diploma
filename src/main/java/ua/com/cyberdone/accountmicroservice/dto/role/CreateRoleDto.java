package ua.com.cyberdone.accountmicroservice.dto.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"role\": \"DRIVER\",\n" +
        "    \"permissionNames\": [\n" +
        "        \"Read Directions\"\n" +
        "    ]\n" +
        "}")
public class CreateRoleDto {
    private String role;
    private Set<String> permissionNames;
}
