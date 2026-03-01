package com.santiagomarin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santiagomarin.exception.BusinessException;
import com.santiagomarin.exception.ResourceNotFoundException;
import com.santiagomarin.dto.client.ClientResponse;
import com.santiagomarin.dto.client.CreateClientRequest;
import com.santiagomarin.dto.client.UpdateClientRequest;
import com.santiagomarin.entities.Client;
import com.santiagomarin.entities.ClientStatus;
import com.santiagomarin.mapper.ClientMapper;
import com.santiagomarin.repository.ClientRepository;
import com.santiagomarin.utils.StringUtils;

@Service
@Transactional
public class ClientService {
	
	private ClientRepository clientRepository;
	private ClientMapper clientMapper;
	
	public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
		super();
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}
	
	public ClientResponse createClient(CreateClientRequest request) {
		
		String normalizedEmail = StringUtils.normalize(request.email());
		String normalizeTaxId = StringUtils.normalize(request.taxId());
		
		if(clientRepository.existsByEmail(normalizedEmail)) {
			throw new BusinessException("Client already exist by email: " + request.email());
		}
		
		if(request.taxId() != null) {
			if(clientRepository.existsByTaxId(normalizeTaxId)) {
				throw new BusinessException("Client already exist by tax ID: " + request.taxId());
			}
		}
		
		Client client = clientMapper.toEntity(request);
		client.setEmail(normalizedEmail);
		if(request.taxId() != null) {
			client.setTaxId(normalizeTaxId);
		}
		client.setStatus(ClientStatus.ACTIVE);
		clientRepository.save(client);
		
		return clientMapper.toResponse(client);
	}
	
	@Transactional(readOnly = true)
	public ClientResponse findById(Long id) {
		
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
		return clientMapper.toResponse(client);
	}
	
	@Transactional(readOnly = true)
	public List<ClientResponse> findAll(){
		return clientMapper.toResponseList(clientRepository.findAll());
	}
	
	public ClientResponse updateClient(Long id,UpdateClientRequest request) {
		
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Client", "id", request));
		
		if(request.email() != null) {
			String normalizeEmail = StringUtils.normalize(request.email());
			if(!normalizeEmail.equals(client.getEmail()) && clientRepository.existsByEmail(normalizeEmail)) {
				throw new BusinessException("Client already exists with email: " + request.email());
			}
			client.setEmail(normalizeEmail);
		}
		
		clientMapper.updateEntity(client, request);
		clientRepository.save(client);
		return clientMapper.toResponse(client);
	}
	
	public void deleteClient(Long id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
		clientRepository.delete(client);
	}

}
