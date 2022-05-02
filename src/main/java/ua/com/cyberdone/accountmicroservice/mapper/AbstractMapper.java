package ua.com.cyberdone.accountmicroservice.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public abstract class AbstractMapper<Entity, Dto> {

    protected final ModelMapper modelMapper;

    public Entity toEntity(Dto dto, Class<Entity> clazz) {
        return modelMapper.map(dto, clazz);
    }

    public Dto toDto(Entity account, Class<Dto> clazz) {
        return modelMapper.map(account, clazz);
    }

    public List<Dto> toDtoList(List<Entity> accounts, Class<Dto> clazz) {
        if (isNull(accounts) || accounts.isEmpty()) {
            return Collections.emptyList();
        }
        return accounts.stream().map(entity -> toDto(entity, clazz))
                .collect(toList());
    }

    public List<Entity> toEntityList(List<Dto> dtos, Class<Entity> clazz) {
        if (isNull(dtos) || dtos.isEmpty()) {
            return Collections.emptyList();
        }
        return dtos.stream().map(dto -> toEntity(dto, clazz)).collect(toList());
    }

    public Set<Dto> toDtoSet(Set<Entity> accounts, Class<Dto> clazz) {
        if (isNull(accounts) || accounts.isEmpty()) {
            return Collections.emptySet();
        }
        return accounts.stream().map(entity -> toDto(entity, clazz))
                .collect(Collectors.toSet());
    }

    public Set<Entity> toEntitySet(Set<Dto> dtos, Class<Entity> clazz) {
        if (isNull(dtos) || dtos.isEmpty()) {
            return Collections.emptySet();
        }
        return dtos.stream().map(dto -> toEntity(dto, clazz)).collect(Collectors.toSet());
    }
}
