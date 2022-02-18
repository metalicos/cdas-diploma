package ua.com.cyberdone.accountmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.cyberdone.accountmicroservice.entity.Account;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account a WHERE a.username = :username", nativeQuery = true)
    Optional<Account> findByUsername(@QueryParam("username") String username);

    @Query(value = "SELECT a.photo FROM account a WHERE a.username = :username", nativeQuery = true)
    Optional<byte[]> getPhotoByAccountUsername(@QueryParam("username") String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM account a WHERE a.username = :username", nativeQuery = true)
    void deleteByUsername(@QueryParam("username") String username);

    boolean existsByUsername(String username);
}
