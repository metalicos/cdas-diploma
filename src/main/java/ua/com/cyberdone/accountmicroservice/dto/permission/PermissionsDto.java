package ua.com.cyberdone.accountmicroservice.dto.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.SORTING_DIRECTION_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.SORTING_DIRECTION_RGX;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.SORT_BY_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.SORT_BY_RGX;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
public class PermissionsDto {
    private Integer page;
    private Integer elementsOnThePage;
    private Integer totallyPages;
    private Integer foundElements;
    private Long totallyElements;
    @Pattern(regexp = SORT_BY_RGX, message = SORT_BY_FAIL_MESSAGE)
    private String sortedBy;
    @Pattern(regexp = SORTING_DIRECTION_RGX, message = SORTING_DIRECTION_FAIL_MESSAGE)
    private String sortDirection;
    private Set<@Valid PermissionDto> permissions;
}
