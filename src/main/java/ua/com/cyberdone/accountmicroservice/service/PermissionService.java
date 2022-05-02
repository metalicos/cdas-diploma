package ua.com.cyberdone.accountmicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionsDto;
import ua.com.cyberdone.accountmicroservice.entity.Permission;
import ua.com.cyberdone.accountmicroservice.mapper.PermissionMapper;
import ua.com.cyberdone.accountmicroservice.repository.PermissionRepository;

import static ua.com.cyberdone.accountmicroservice.config.CyberdoneCachingConfig.PERMISSIONS_CACHE_NAME;
import static ua.com.cyberdone.accountmicroservice.config.CyberdoneCachingConfig.PERMISSION_CACHE_NAME;


@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;

    public PermissionsDto getAllPermissions(int page, int size) {
        var permissionPage = permissionRepository.findAll(PageRequest.of(page, size));
        log.info("Permissions {} are successfully read", permissionPage.getContent());
        return getPageableDto(permissionPage);
    }

    public PermissionsDto getAllPermissions(int page, int size, String direction, String sortBy) {
        var sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        var permissionPage = permissionRepository.findAll(PageRequest.of(page, size, sort));
        log.info("Permissions {} are successfully read", permissionPage.getContent());
        return getPageableDto(permissionPage);
    }

    @Cacheable(value = PERMISSION_CACHE_NAME, key = "#name")
    public PermissionDto getPermission(String name) throws NotFoundException {
        var permission = permissionRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Permission not found"));
        log.info("Permission {} is successfully read", permission);
        return new PermissionMapper<PermissionDto>(modelMapper).toDto(permission, PermissionDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = PERMISSION_CACHE_NAME, allEntries = true),
            @CacheEvict(value = PERMISSIONS_CACHE_NAME, allEntries = true)})
    public PermissionDto createPermission(String name, String value) throws AlreadyExistException {
        if (permissionRepository.existsByName(name)) {
            throw new AlreadyExistException("Permission already exists");
        }
        var saved = permissionRepository.save(new Permission(name, value));
        log.info("Permission {} is successfully created", saved);
        return new PermissionMapper<PermissionDto>(modelMapper).toDto(saved, PermissionDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = PERMISSION_CACHE_NAME, allEntries = true),
            @CacheEvict(value = PERMISSIONS_CACHE_NAME, allEntries = true)})
    public void deletePermission(String name) {
        permissionRepository.deleteByName(name);
        log.info("Permission {} is deleted", name);
    }

    @Caching(evict = {
            @CacheEvict(value = PERMISSION_CACHE_NAME, allEntries = true),
            @CacheEvict(value = PERMISSIONS_CACHE_NAME, allEntries = true)})
    @CacheEvict(value = PERMISSIONS_CACHE_NAME, allEntries = true)
    public void deleteAllPermission() {
        permissionRepository.deleteAll();
        log.info("All Permissions are deleted");
    }

    private PermissionsDto getPageableDto(Page<Permission> pageable) {
        return PermissionsDto.builder()
                .page(pageable.getNumber())
                .elementsOnThePage(pageable.getNumberOfElements())
                .totallyElements(pageable.getTotalElements())
                .totallyPages(pageable.getTotalPages())
                .content(new PermissionMapper<PermissionDto>(modelMapper).toDtoList(pageable.getContent(), PermissionDto.class))
                .build();
    }
}
