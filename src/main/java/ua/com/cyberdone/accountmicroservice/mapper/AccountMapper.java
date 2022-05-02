package ua.com.cyberdone.accountmicroservice.mapper;

import org.modelmapper.ModelMapper;
import ua.com.cyberdone.accountmicroservice.dto.account.AccountDto;
import ua.com.cyberdone.accountmicroservice.entity.Account;
import ua.com.cyberdone.accountmicroservice.entity.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class AccountMapper<Dto> extends AbstractMapper<Account, Dto> {
    public AccountMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Dto toDto(Account account, Class<Dto> clazz) {
        var dto = modelMapper.map(account, clazz);
        if (dto instanceof AccountDto) {
            ((AccountDto) dto).setPermissions(extractAllUniquePermissions(account.getRoles()));
        }
        return dto;
    }

    @Override
    public List<Dto> toDtoList(List<Account> list, Class<Dto> clazz) {
        if (isNull(list) || list.isEmpty()) {
            return Collections.emptyList();
        }
        var dtoList = new ArrayList<Dto>();
        for (var element : list) {
            var dto = toDto(element, clazz);
            if (dto instanceof AccountDto) {
                ((AccountDto) dto).setPermissions(extractAllUniquePermissions(element.getRoles()));
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Set<Dto> toDtoSet(Set<Account> set, Class<Dto> clazz) {
        if (isNull(set) || set.isEmpty()) {
            return Collections.emptySet();
        }
        var dtoSet = new HashSet<Dto>();
        for (var element : set) {
            var dto = toDto(element, clazz);
            if (dto instanceof AccountDto) {
                ((AccountDto) dto).setPermissions(extractAllUniquePermissions(element.getRoles()));
            }
            dtoSet.add(dto);
        }
        return dtoSet;
    }

    private Set<String> extractAllUniquePermissions(Collection<Role> roles) {
        var permissions = new HashSet<String>();
        roles.forEach(r ->
                r.getPermissions().forEach(p ->
                        permissions.add(p.getValue())));
        return permissions.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
