package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.santiagomarin.dto.role.CreateRoleRequest;
import com.santiagomarin.dto.role.RoleResponse;
import com.santiagomarin.dto.role.UpdateRoleRequest;
import com.santiagomarin.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    Role toEntity(CreateRoleRequest request);

    RoleResponse toResponse(Role role);

    List<RoleResponse> toResponseList(List<Role> roles);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)  // el nombre del rol nunca cambia
    void updateEntity(@MappingTarget Role role, UpdateRoleRequest request);
}
