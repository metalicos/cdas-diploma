package ua.com.cyberdone.accountmicroservice.dto.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PERMISSION_NAME_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PERMISSION_NAME_RGX;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PERMISSION_VALUE_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.PERMISSION_VALUE_RGX;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"name\": \"Read Notes\",\n" +
        "    \"value\": \"r_notes\"\n" +
        "}")
public class CreatePermissionDto {
    @Pattern(regexp = PERMISSION_NAME_RGX,
            message = PERMISSION_NAME_FAIL_MESSAGE)
    private String name;
    @Pattern(regexp = PERMISSION_VALUE_RGX,
            message = PERMISSION_VALUE_FAIL_MESSAGE)
    private String value;
}
