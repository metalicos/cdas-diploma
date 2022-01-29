package ua.com.cyberdone.accountmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.cyberdone.accountmicroservice.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}
