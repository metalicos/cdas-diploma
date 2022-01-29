package ua.com.cyberdone.accountmicroservice.dto.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDto {
    private String authToken;
    private Long tokenLiveTimeInSeconds;
}
