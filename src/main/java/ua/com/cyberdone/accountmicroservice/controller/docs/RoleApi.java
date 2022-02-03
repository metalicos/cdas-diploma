package ua.com.cyberdone.accountmicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.accountmicroservice.common.constant.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.role.CreateRoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RolesDto;

@Tag(name = "Roles", description = "Endpoints for managing roles")
public interface RoleApi {

    @Operation(summary = "Read roles", description = "Return all roles with pagination")
    @ApiResponse(responseCode = "200", description = "Return roles with pagination (page, size) / order " +
            "'direction' (ASC / DESC) / filter by word 'sortBy'",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RolesDto.class)))
    ResponseEntity<RolesDto> readRoles(String token, int page, int size, String direction, String sortBy);

    @Operation(summary = "Read role", description = "Return role by role name")
    @ApiResponse(responseCode = "200", description = "Return role by role name",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RoleDto.class)))
    ResponseEntity<RoleDto> readRole(String token, String roleName)
            throws NotFoundException;

    @Operation(summary = "Delete roles", description = "Delete all roles")
    @ApiResponse(responseCode = "200", description = "Delete all roles",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteRoles(String token);

    @Operation(summary = "Delete role by role name", description = "Delete role by role name")
    @ApiResponse(responseCode = "200", description = "Delete role by role name",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteRole(String token, String roleName);

    @Operation(summary = "Create role", description = "Create new role")
    @ApiResponse(responseCode = "200", description = "Create new role",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RoleDto.class)))
    ResponseEntity<RoleDto> createRole(String token, CreateRoleDto createRoleDto)
            throws AlreadyExistException, NotFoundException;
}
