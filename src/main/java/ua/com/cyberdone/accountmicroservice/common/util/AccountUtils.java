package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.cyberdone.accountmicroservice.entity.Account;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import java.util.Arrays;
import java.util.Set;

@UtilityClass
public final class AccountUtils {
    public static final String DEFAULT_ROLE = "USER";
    private static final String SPLIT_CHAR = ";";
    private static final String CREATE_USER_PERMITTED = "OWNER" + SPLIT_CHAR + "ADMIN" + SPLIT_CHAR + "SUPER_ADMIN";

    public static boolean permittedToCreateNewUser(Account creatorAccount) {
        return creatorAccount.getRoles().stream()
                .anyMatch(role -> Arrays.stream(CREATE_USER_PERMITTED.split(SPLIT_CHAR))
                        .anyMatch(p -> p.equals(role.getRole())));
    }

    public static void setupAccount(PasswordEncoder passwordEncoder, Account account, Set<Role> roles, byte[] photo) {
        account.setRoles(roles);
        setupAccount(passwordEncoder, account, photo);
    }

    public static void setupAccount(PasswordEncoder passwordEncoder, Account account, byte[] photo) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setPhoto(photo);
        makeFullyAvailable(account);
    }

    private static void makeFullyAvailable(Account account) {
        setupAvailable(account, true, true, true, true);
    }

    public static void fullyDisableAccount(Account account) {
        setupAvailable(account, false, false, false, false);
    }

    private static void setupAvailable(Account account, boolean enabled, boolean credentialsNonExpired,
                                       boolean nonExpired, boolean nonLocked) {
        account.setIsEnabled(enabled);
        account.setIsCredentialsNonExpired(credentialsNonExpired);
        account.setIsNonExpired(nonExpired);
        account.setIsNonLocked(nonLocked);
    }
}
