package ua.com.cyberdone.accountmicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.role.CreateRoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RolesDto;
import ua.com.cyberdone.accountmicroservice.entity.Permission;
import ua.com.cyberdone.accountmicroservice.entity.Role;
import ua.com.cyberdone.accountmicroservice.mapper.RoleMapper;
import ua.com.cyberdone.accountmicroservice.repository.PermissionRepository;
import ua.com.cyberdone.accountmicroservice.repository.RoleRepository;

import java.util.HashSet;

import static ua.com.cyberdone.accountmicroservice.config.CyberdoneCachingConfig.ROLES_CACHE_NAME;
import static ua.com.cyberdone.accountmicroservice.config.CyberdoneCachingConfig.ROLE_CACHE_NAME;


@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;

    public RolesDto getAllRoles(int page, int size) {
        var rolePage = roleRepository.findAll(PageRequest.of(page, size));
        log.info("Roles {} are successfully read", rolePage.getContent());
        return RolesDto.builder()
                .page(page)
                .elementsOnThePage(size)
                .totallyPages(rolePage.getTotalPages())
                .foundElements(rolePage.getNumberOfElements())
                .totallyElements(rolePage.getTotalElements())
                .sortedBy("NONE")
                .sortDirection("NONE")
                .roles(new RoleMapper<RoleDto>(modelMapper).toDtoSet(rolePage.toSet(), RoleDto.class))
                .build();
    }

    public RolesDto getAllRoles(int page, int size, String direction, String sortBy) {
        var sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        var rolePage = roleRepository.findAll(PageRequest.of(page, size, sort));
        log.info("Roles {} are successfully read", rolePage.getContent());
        return RolesDto.builder()
                .page(page)
                .elementsOnThePage(size)
                .totallyPages(rolePage.getTotalPages())
                .foundElements(rolePage.getNumberOfElements())
                .totallyElements(rolePage.getTotalElements())
                .sortedBy(sortBy)
                .sortDirection(direction)
                .roles(new RoleMapper<RoleDto>(modelMapper).toDtoSet(rolePage.toSet(), RoleDto.class))
                .build();
    }

    @Cacheable(value = ROLE_CACHE_NAME, key = "#name")
    public RoleDto getRole(String name) throws NotFoundException {
        var role = roleRepository.findByRole(name)
                .orElseThrow(() -> new NotFoundException("Role is not found"));
        log.info("Role {} is successfully read", role);
        return new RoleMapper<RoleDto>(modelMapper).toDto(role, RoleDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = ROLE_CACHE_NAME, allEntries = true),
            @CacheEvict(value = ROLES_CACHE_NAME, allEntries = true)})
    public RoleDto createRole(CreateRoleDto createDto) throws AlreadyExistException, NotFoundException {
        if (roleRepository.existsByRole(createDto.getRole())) {
            throw new AlreadyExistException("Role already exists");
        }
        var permissionsSet = new HashSet<Permission>();
        for (var permissionName : createDto.getPermissionNames()) {
            permissionsSet.add(permissionRepository.findByName(permissionName)
                    .orElseThrow(() -> new NotFoundException("Permission '" + permissionName + "' is not found.")));
        }

        var saved = roleRepository.save(new Role(createDto.getRole(), permissionsSet));
        log.info("Role {} is successfully created", saved);
        return new RoleMapper<RoleDto>(modelMapper).toDto(saved, RoleDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = ROLE_CACHE_NAME, allEntries = true),
            @CacheEvict(value = ROLES_CACHE_NAME, allEntries = true)})
    public void deleteRole(String roleName) {
        roleRepository.deleteByRole(roleName);
        log.info("Role {} is deleted", roleName);
    }

    @Caching(evict = {
            @CacheEvict(value = ROLE_CACHE_NAME, allEntries = true),
            @CacheEvict(value = ROLES_CACHE_NAME, allEntries = true)})
    @CacheEvict(value = ROLES_CACHE_NAME, allEntries = true)
    public void deleteAllRoles() {
        roleRepository.deleteAll();
        log.info("All roles are deleted");
    }
}
