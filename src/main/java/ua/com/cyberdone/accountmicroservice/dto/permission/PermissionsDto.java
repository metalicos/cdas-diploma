package ua.com.cyberdone.accountmicroservice.dto.permission;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionsDto {
    private Integer page;
    private Integer elementsOnThePage;
    private Integer totallyPages;
    private Integer foundElements;
    private Long totallyElements;
    private String sortedBy;
    private String sortDirection;
    private Set<PermissionDto> permissions;
}
