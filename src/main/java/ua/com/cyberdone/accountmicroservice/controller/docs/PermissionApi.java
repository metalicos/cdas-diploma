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
import ua.com.cyberdone.accountmicroservice.dto.permission.CreatePermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionDto;
import ua.com.cyberdone.accountmicroservice.dto.permission.PermissionsDto;

@Tag(name = "Permissions", description = "Endpoints for managing account permissions")
public interface PermissionApi {

    @Operation(summary = "Read permissions", description = "Return all permissions with pagination")
    @ApiResponse(responseCode = "200", description = "Return permissions with pagination (page, size) / " +
            "order 'direction' (ASC / DESC) / filter by word 'sortBy'",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PermissionsDto.class)))
    ResponseEntity<PermissionsDto> readPermissions(String token, int page, int size, String direction, String sortBy);

    @Operation(summary = "Read permission", description = "Return single permission by name")
    @ApiResponse(responseCode = "200", description = "Return single permission by name",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PermissionDto.class)))
    ResponseEntity<PermissionDto> readPermission(String token, String name) throws NotFoundException;

    @Operation(summary = "Delete permissions", description = "Delete all permission")
    @ApiResponse(responseCode = "200", description = "Delete all permission",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deletePermissions(String token);

    @Operation(summary = "Delete permission", description = "Delete permission by name")
    @ApiResponse(responseCode = "200", description = "Delete account by username",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deletePermission(String token, String name);

    @Operation(summary = "Create permission", description = "Create the new permission")
    @ApiResponse(responseCode = "200", description = "Create the new permission",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PermissionDto.class)))
    ResponseEntity<PermissionDto> createPermission(String token, CreatePermissionDto dto) throws AlreadyExistException;
}
