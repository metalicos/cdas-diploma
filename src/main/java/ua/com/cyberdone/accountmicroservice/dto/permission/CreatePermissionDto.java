package ua.com.cyberdone.accountmicroservice.dto.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"name\": \"Read Notes\",\n" +
        "    \"value\": \"r_notes\"\n" +
        "}")
public class CreatePermissionDto {
    private String name;
    private String value;
}
