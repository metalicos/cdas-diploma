package ua.com.cyberdone.accountmicroservice.entity.superclass;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BasicSecurity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_non_expired")
    private Boolean isNonExpired = true;

    @Column(name = "is_non_locked")
    private Boolean isNonLocked = true;

    @Column(name = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired = true;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;
}
