package ua.com.cyberdone.accountmicroservice.dto.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.SORT_BY_RGX;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.SORT_DIRECTION_FAILED_MSG;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.SORT_DIRECTION_PATTERN;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"page\": 0,\n" +
        "    \"elementsOnThePage\": 1,\n" +
        "    \"totallyPages\": 6,\n" +
        "    \"foundElements\": 1,\n" +
        "    \"totallyElements\": 6,\n" +
        "    \"sortedBy\": \"NONE\",\n" +
        "    \"sortDirection\": \"NONE\",\n" +
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
public class RolesDto {
    private Integer page;
    private Integer elementsOnThePage;
    private Integer totallyPages;
    private Integer foundElements;
    private Long totallyElements;
    private String sortedBy;
    @Pattern(regexp = SORT_DIRECTION_PATTERN, message = SORT_DIRECTION_FAILED_MSG)
    private String sortDirection;
    private Set<@Valid RoleDto> roles;
}
