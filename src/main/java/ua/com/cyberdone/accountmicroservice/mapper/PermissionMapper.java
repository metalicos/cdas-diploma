package ua.com.cyberdone.accountmicroservice.mapper;

import org.modelmapper.ModelMapper;
import ua.com.cyberdone.accountmicroservice.entity.Permission;

public class PermissionMapper<Dto> extends AbstractMapper<Permission, Dto> {

    public PermissionMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
