package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import java.io.Serializable;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto implements Serializable {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Boolean isEnabled;
    @JsonIgnore
    private Byte[] photo;
    private Set<Role> roles;
}
