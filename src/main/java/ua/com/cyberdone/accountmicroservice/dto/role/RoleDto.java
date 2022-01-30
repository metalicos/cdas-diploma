package ua.com.cyberdone.accountmicroservice.dto.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionDto;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"id\": 3,\n" +
        "    \"role\": \"ADMIN\",\n" +
        "    \"permissions\": [\n" +
        "        {\n" +
        "            \"id\": 21,\n" +
        "            \"name\": \"Read Self\",\n" +
        "            \"value\": \"r_self\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 24,\n" +
        "            \"name\": \"Delete Self\",\n" +
        "            \"value\": \"d_self\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 22,\n" +
        "            \"name\": \"Write Self\",\n" +
        "            \"value\": \"w_self\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 23,\n" +
        "            \"name\": \"Update Self\",\n" +
        "            \"value\": \"u_self\"\n" +
        "        }\n" +
        "    ]\n" +
        "}")
public class RoleDto {
    private Long id;
    private String role;
    private Set<PermissionDto> permissions;
}
