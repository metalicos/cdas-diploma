package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.TOKEN_FAIL_MESSAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.TOKEN_WITH_TYPE_RGX;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"token\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzd.WIiOiJvc3RhcC5qYUBnbWFpbC5jb20\"\n" +
        "}")
public class LogoutDto {
    @Pattern(regexp = TOKEN_WITH_TYPE_RGX,
            message = TOKEN_FAIL_MESSAGE)
    private String token;
}
