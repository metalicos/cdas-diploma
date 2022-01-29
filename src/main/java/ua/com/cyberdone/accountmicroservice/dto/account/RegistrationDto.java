package ua.com.cyberdone.accountmicroservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Set<Role> roles;
}
