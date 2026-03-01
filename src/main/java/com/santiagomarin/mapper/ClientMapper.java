package com.santiagomarin.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.santiagomarin.dto.client.ClientResponse;
import com.santiagomarin.dto.client.CreateClientRequest;
import com.santiagomarin.dto.client.UpdateClientRequest;
import com.santiagomarin.entities.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)  // se asigna en el service como ACTIVE
    Client toEntity(CreateClientRequest request);

	ClientResponse toResponse(Client client);

    List<ClientResponse> toResponseList(List<Client> clients);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntity(@MappingTarget Client client, UpdateClientRequest request);

}
