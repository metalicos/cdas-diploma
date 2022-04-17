package ua.com.cyberdone.accountmicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ua.com.cyberdone.accountmicroservice.common.util.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.role.CreateRoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RoleDto;
import ua.com.cyberdone.accountmicroservice.dto.role.RolesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import static ua.com.cyberdone.accountmicroservice.common.util.Regex.NOT_POSITIVE_OR_ZERO_MSG;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.SORT_DIRECTION_FAILED_MSG;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.SORT_DIRECTION_PATTERN;
import static ua.com.cyberdone.accountmicroservice.common.util.Regex.VALUE_IS_NULL_MSG;

@Tag(name = "Roles", description = "Endpoints for managing roles")
public interface RoleApi {

    @Operation(summary = "Read roles", description = "Return all roles with pagination")
    @ApiResponse(responseCode = "200", description = "Return roles with pagination (page, size) / order " +
            "'direction' (ASC / DESC) / filter by word 'sortBy'",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RolesDto.class)))
    ResponseEntity<RolesDto> readRoles(
            String token,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @PositiveOrZero(message = NOT_POSITIVE_OR_ZERO_MSG)
                    Integer page,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @PositiveOrZero(message = NOT_POSITIVE_OR_ZERO_MSG)
                    Integer size,
            @Valid
            @NotNull(message = VALUE_IS_NULL_MSG)
            @Pattern(regexp = SORT_DIRECTION_PATTERN, message = SORT_DIRECTION_FAILED_MSG)
                    String direction,
            String sortBy);

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
