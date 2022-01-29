package ua.com.cyberdone.accountmicroservice.mapper;

import org.modelmapper.ModelMapper;
import ua.com.cyberdone.accountmicroservice.entity.Account;

public class AccountMapper<Dto> extends AbstractMapper<Account, Dto> {
    public AccountMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
