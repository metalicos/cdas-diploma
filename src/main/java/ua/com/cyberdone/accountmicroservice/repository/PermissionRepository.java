package ua.com.cyberdone.accountmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberdone.accountmicroservice.entity.Permission;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(value = "SELECT DISTINCT * FROM public.permission p WHERE p.name = :name LIMIT 1", nativeQuery = true)
    Optional<Permission> findByName(@QueryParam("name") String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.permission p WHERE p.name = :name", nativeQuery = true)
    void deleteByName(@QueryParam("name") String name);

    boolean existsByName(String name);
}