package ua.com.cyberdone.accountmicroservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.cyberdone.accountmicroservice.entity.Account;
import ua.com.cyberdone.accountmicroservice.entity.Permission;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CyberdoneUserDetails implements UserDetails {

    private final Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var permissions = new HashSet<Permission>();
        for (Role role : account.getRoles()) {
            permissions.addAll(role.getPermissions());
        }
        return toSetAuthorities(permissions);
    }

    private Set<SimpleGrantedAuthority> toSetAuthorities(Set<Permission> permissions) {
        return permissions.stream().map(p -> new SimpleGrantedAuthority(p.getValue())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.getIsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getIsNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return account.getIsEnabled();
    }
}
