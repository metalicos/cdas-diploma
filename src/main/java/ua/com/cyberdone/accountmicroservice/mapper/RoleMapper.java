package ua.com.cyberdone.accountmicroservice.mapper;

import org.modelmapper.ModelMapper;
import ua.com.cyberdone.accountmicroservice.entity.Role;

public class RoleMapper<Dto> extends AbstractMapper<Role, Dto> {

    public RoleMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
