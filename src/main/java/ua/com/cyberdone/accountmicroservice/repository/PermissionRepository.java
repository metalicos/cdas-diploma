package ua.com.cyberdone.accountmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.cyberdone.accountmicroservice.entity.Permission;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);
}
