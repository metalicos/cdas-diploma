package ua.com.cyberdone.accountmicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import ua.com.cyberdone.accountmicroservice.common.constant.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.controller.docs.RoleControllerApi;
import ua.com.cyberdone.accountmicroservice.dto.role.CreateRoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RolesDto;
import ua.com.cyberdone.accountmicroservice.service.RoleService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController implements RoleControllerApi {
    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts')")
    public ResponseEntity<RolesDto> readRoles(@RequestHeader("Authorization") String token,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size,
                                              @RequestParam(defaultValue = "NONE") String direction,
                                              @RequestParam(defaultValue = "NONE") String sortBy) {
        if ("NONE".equals(sortBy) && sortBy.equals(direction)) {
            return ResponseEntity.ok(roleService.getAllRoles(page, size));
        }
        return ResponseEntity.ok(roleService.getAllRoles(page, size, direction, sortBy));
    }

    @GetMapping("/{role-name}")
    @PreAuthorize("hasAnyAuthority('r_all','r_account','r_self')")
    public ResponseEntity<RoleDto> readRole(@RequestHeader("Authorization") String token,
                                            @PathVariable(value = "role-name") String roleName)
            throws NotFoundException {
        return ResponseEntity.ok(roleService.getRole(roleName));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts')")
    public ResponseEntity<String> deleteRoles(@RequestHeader("Authorization") String token) {
        roleService.deleteAllRoles();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{role-name}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    public ResponseEntity<String> deleteRole(@RequestHeader("Authorization") String token,
                                             @PathVariable(value = "role-name") String roleName) {
        roleService.deleteRole(roleName);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestHeader("Authorization") String token,
                                              @RequestBody CreateRoleDto createRoleDto)
            throws AlreadyExistException, NotFoundException {
        return ResponseEntity.ok(roleService.createRole(createRoleDto));
    }
}
