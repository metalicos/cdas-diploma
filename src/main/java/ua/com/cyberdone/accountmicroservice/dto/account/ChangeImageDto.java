package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeImageDto {
    ;

    @Pattern(regexp = Regex.FIRST_NAME,
            message = Regex.FIRST_NAME_FAIL_MESSAGE)
    private String firstName;
}
