package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(example = "{\n" +
        "    \"page\": 0,\n" +
        "    \"elementsOnThePage\": 1,\n" +
        "    \"totallyPages\": 2,\n" +
        "    \"foundElements\": 1,\n" +
        "    \"totallyElements\": 2,\n" +
        "    \"sortedBy\": \"NONE\",\n" +
        "    \"sortDirection\": \"NONE\",\n" +
        "    \"accounts\": [\n" +
        "        {\n" +
        "            \"id\": 1,\n" +
        "            \"username\": \"ostap.ja@gmail.com\",\n" +
        "            \"firstName\": \"Ostap\",\n" +
        "            \"lastName\": \"Komplikevych\",\n" +
        "            \"patronymic\": \"Yaroslavovych\",\n" +
        "            \"isEnabled\": true,\n" +
        "            \"roles\": [\n" +
        "                {\n" +
        "                    \"id\": 1,\n" +
        "                    \"role\": \"OWNER\",\n" +
        "                    \"permissions\": [\n" +
        "                        {\n" +
        "                            \"id\": 3,\n" +
        "                            \"name\": \"Update All\",\n" +
        "                            \"value\": \"u_all\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"id\": 4,\n" +
        "                            \"name\": \"Delete All\",\n" +
        "                            \"value\": \"d_all\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"id\": 1,\n" +
        "                            \"name\": \"Read All\",\n" +
        "                            \"value\": \"r_all\"\n" +
        "                        },\n" +
        "                        {\n" +
        "                            \"id\": 2,\n" +
        "                            \"name\": \"Write All\",\n" +
        "                            \"value\": \"w_all\"\n" +
        "                        }\n" +
        "                    ]\n" +
        "                }\n" +
        "            ]\n" +
        "        }\n" +
        "    ]\n" +
        "}")
public class AccountsDto {
    private Integer page;
    private Integer elementsOnThePage;
    private Integer totallyPages;
    private Integer foundElements;
    private Long totallyElements;
    private String sortedBy;
    private String sortDirection;
    private List<AccountDto> accounts;
}
