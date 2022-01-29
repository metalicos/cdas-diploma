package ua.com.cyberdone.accountmicroservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.accountmicroservice.common.constant.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.permission.CreatePermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionsDto;
import ua.com.cyberdone.accountmicroservice.service.PermissionService;

import static java.util.Objects.nonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_permissions')")
    public ResponseEntity<Object> readPermissions(@RequestParam(value = "name", required = false) String name)
            throws NotFoundException {
        if (nonNull(name)) {
            return ResponseEntity.ok(permissionService.getPermission(name));
        }
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_permissions')")
    public ResponseEntity<String> deletePermissions(@RequestParam(value = "name", required = false) String name) {
        if (nonNull(name)) {
            permissionService.deletePermission(name);
            return ResponseEntity.ok(ControllerConstantUtils.OK);
        }
        permissionService.deleteAllPermission();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_permissions')")
    public ResponseEntity<PermissionDto> createPermission(@RequestBody CreatePermissionDto dto)
            throws AlreadyExistException {
        return ResponseEntity.ok(permissionService.createPermission(dto.getName(), dto.getValue()));
    }

    @GetMapping("/page/{page}/size/{size}/sort-by/{sort-by}/direction/{direction}")
    @PreAuthorize("hasAnyAuthority('r_all','r_permissions')")
    public ResponseEntity<PermissionsDto> readPermissions(@PathVariable("page") Integer page,
                                                          @PathVariable("size") Integer size,
                                                          @PathVariable("sort-by") String sortBy,
                                                          @PathVariable("direction") String direction) {
        return ResponseEntity.ok(permissionService.getAllPermissions(page, size, direction, sortBy));
    }
}
