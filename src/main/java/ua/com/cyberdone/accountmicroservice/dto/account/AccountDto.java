package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto implements Serializable {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Boolean isEnabled;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Byte[] photo;
    private Set<RoleDto> roles;
}
