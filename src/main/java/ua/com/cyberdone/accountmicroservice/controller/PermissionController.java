package ua.com.cyberdone.accountmicroservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.controller.docs.PermissionApi;
import ua.com.cyberdone.accountmicroservice.dto.permission.CreatePermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionsDto;
import ua.com.cyberdone.accountmicroservice.service.PermissionService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_DIRECTION;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_ELEMENTS_AMOUNT;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_PAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_SEARCH;

@RestController
@AllArgsConstructor
@RequestMapping("/permissions")
public class PermissionController implements PermissionApi {
    private final PermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_all_permissions')")
    public ResponseEntity<PermissionsDto> readPermissions(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
                                                          @RequestParam(defaultValue = DEFAULT_ELEMENTS_AMOUNT, required = false) Integer size,
                                                          @RequestParam(defaultValue = DEFAULT_DIRECTION, required = false) String direction,
                                                          @RequestParam(defaultValue = DEFAULT_SEARCH, required = false) String sortBy) {
        if (DEFAULT_SEARCH.equals(sortBy)) {
            return ResponseEntity.ok(permissionService.getAllPermissions(page, size));
        }
        return ResponseEntity.ok(permissionService.getAllPermissions(page, size, direction, sortBy));
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyAuthority('r_all','r_permission')")
    public ResponseEntity<PermissionDto> readPermission(@RequestHeader(AUTHORIZATION) String token,
                                                        @PathVariable String name) throws NotFoundException {
        return ResponseEntity.ok(permissionService.getPermission(name));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_all_permissions')")
    public ResponseEntity<String> deletePermissions(@RequestHeader(AUTHORIZATION) String token) {
        permissionService.deleteAllPermission();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAnyAuthority('d_all','d_permission')")
    public ResponseEntity<String> deletePermission(@RequestHeader(AUTHORIZATION) String token,
                                                   @PathVariable String name) {
        permissionService.deletePermission(name);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_permission')")
    public ResponseEntity<PermissionDto> createPermission(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestBody CreatePermissionDto dto)
            throws AlreadyExistException {
        return ResponseEntity.ok(permissionService.createPermission(dto.getName(), dto.getValue()));
    }
}
