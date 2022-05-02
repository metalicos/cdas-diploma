package ua.com.cyberdone.accountmicroservice.dto.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ua.com.cyberdone.accountmicroservice.dto.PageableDto;

import java.io.Serial;
import java.io.Serializable;

@Schema(example = "{\n" +
        "    \"page\": 0,\n" +
        "    \"elementsOnThePage\": 3,\n" +
        "    \"totallyPages\": 14,\n" +
        "    \"foundElements\": 3,\n" +
        "    \"totallyElements\": 41,\n" +
        "    \"sortedBy\": \"NONE\",\n" +
        "    \"sortDirection\": \"NONE\",\n" +
        "    \"permissions\": [\n" +
        "        {\n" +
        "            \"id\": 3,\n" +
        "            \"name\": \"Update All\",\n" +
        "            \"value\": \"u_all\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 1,\n" +
        "            \"name\": \"Read All\",\n" +
        "            \"value\": \"r_all\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 2,\n" +
        "            \"name\": \"Write All\",\n" +
        "            \"value\": \"w_all\"\n" +
        "        }\n" +
        "    ]\n" +
        "}")
@Getter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionsDto extends PageableDto<PermissionDto> implements Serializable {
    @Serial
    private static final long serialVersionUID = 77454333L;
}
