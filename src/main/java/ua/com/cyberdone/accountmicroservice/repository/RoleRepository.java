package ua.com.cyberdone.accountmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT DISTINCT * FROM public.account_role r WHERE r.role = :role LIMIT 1", nativeQuery = true)
    Optional<Role> findByRole(@QueryParam("role") String role);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.account_role r WHERE r.role = :role", nativeQuery = true)
    void deleteByRole(@QueryParam("role") String role);

    boolean existsByRole(String role);
}
