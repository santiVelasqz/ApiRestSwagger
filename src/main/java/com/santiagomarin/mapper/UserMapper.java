package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.santiagomarin.dto.user.CreateUserRequest;
import com.santiagomarin.dto.user.UpdateUserRequest;
import com.santiagomarin.dto.user.UserResponse;
import com.santiagomarin.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)  // se encripta en el service con BCrypt
    @Mapping(target = "status", ignore = true)    // se asigna como ACTIVE en el service
    @Mapping(target = "roles", ignore = true)     // se buscan en BBDD por roleIds
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)  // el username nunca cambia
    @Mapping(target = "password", ignore = true)  // se cambia por endpoint propio
    @Mapping(target = "roles", ignore = true)     // se gestionan por endpoint propio
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget User user, UpdateUserRequest request);
}

