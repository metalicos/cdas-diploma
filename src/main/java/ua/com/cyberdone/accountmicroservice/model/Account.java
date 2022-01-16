package ua.com.cyberdone.accountmicroservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Account {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
