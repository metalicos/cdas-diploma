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
import ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.controller.docs.RoleApi;
import ua.com.cyberdone.accountmicroservice.dto.role.CreateRoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RolesDto;
import ua.com.cyberdone.accountmicroservice.service.RoleService;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_DIRECTION;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_ELEMENTS_AMOUNT;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_PAGE;
import static ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils.DEFAULT_SEARCH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController implements RoleApi {
    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_all_roles')")
    public ResponseEntity<RolesDto> readRoles(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
                                              @RequestParam(defaultValue = DEFAULT_ELEMENTS_AMOUNT, required = false) Integer size,
                                              @RequestParam(defaultValue = DEFAULT_DIRECTION, required = false) String direction,
                                              @RequestParam(defaultValue = DEFAULT_SEARCH, required = false) String sortBy) {
        if ("NONE".equals(sortBy) && sortBy.equals(direction)) {
            return ResponseEntity.ok(roleService.getAllRoles(page, size));
        }
        return ResponseEntity.ok(roleService.getAllRoles(page, size, direction, sortBy));
    }

    @GetMapping("/{role-name}")
    @PreAuthorize("hasAnyAuthority('r_all','r_role')")
    public ResponseEntity<RoleDto> readRole(@RequestHeader(AUTHORIZATION) String token,
                                            @PathVariable(value = "role-name") String roleName)
            throws NotFoundException {
        return ResponseEntity.ok(roleService.getRole(roleName));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_all_roles')")
    public ResponseEntity<String> deleteRoles(@RequestHeader(AUTHORIZATION) String token) {
        roleService.deleteAllRoles();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{role-name}")
    @PreAuthorize("hasAnyAuthority('d_all','d_role')")
    public ResponseEntity<String> deleteRole(@RequestHeader(AUTHORIZATION) String token,
                                             @PathVariable(value = "role-name") String roleName) {
        roleService.deleteRole(roleName);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('w_all','w_role')")
    public ResponseEntity<RoleDto> createRole(@RequestHeader(AUTHORIZATION) String token,
                                              @RequestBody @Valid CreateRoleDto createRoleDto)
            throws AlreadyExistException, NotFoundException {
        return ResponseEntity.ok(roleService.createRole(createRoleDto));
    }
}
