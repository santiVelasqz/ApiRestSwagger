package com.santiagomarin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
@Transactional(readOnly = true) 
public class ClientService {
	
	private static final Logger log = LoggerFactory.getLogger(ClientService.class); 
	private ClientRepository clientRepository;
	private ClientMapper clientMapper;
	
	public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}
	@Transactional
	public ClientResponse createClient(CreateClientRequest request) {
		
		log.debug("Creating new Client ");
		String normalizedEmail = request.email().trim().toLowerCase();
		
		if(clientRepository.existsByEmail(normalizedEmail)) {
			throw new BusinessException("Client already exist by email: " + request.email());
		}
		Client client = clientMapper.toEntity(request);
		client.setEmail(normalizedEmail);
		
		if(request.taxId() != null) {
			String normalizeTaxId = request.taxId().trim().toUpperCase();
			if(clientRepository.existsByTaxId(normalizeTaxId)) {
				throw new BusinessException("Client already exist by tax ID: " + request.taxId());
			}
			client.setTaxId(normalizeTaxId);
		}
		
		client.setStatus(ClientStatus.ACTIVE);
		clientRepository.save(client);
		log.info("Client created successfully with ID: {}",  client.getId());
		return clientMapper.toResponse(client);
	}
	
	public ClientResponse findById(Long id) {
		log.debug("Fetching client from database");
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
		log.info("Client found: {}", client.getId());
		return clientMapper.toResponse(client);
	}
	
	public List<ClientResponse> findAll(){
		log.debug("Fetching all clients from database");
		return clientMapper.toResponseList(clientRepository.findAll());
	}
	
	@Transactional
	public ClientResponse updateClient(Long id,UpdateClientRequest request) {
		
		log.debug("Updating client with ID: {}", id);
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Client", "id", request));
		
		if(request.email() != null) {
			String normalizeEmail = request.email().trim().toLowerCase();
			if(!normalizeEmail.equals(client.getEmail()) && clientRepository.existsByEmail(normalizeEmail)) {
				throw new BusinessException("Client already exists with email: " + request.email());
			}
			client.setEmail(normalizeEmail);
		}
		
		clientMapper.updateEntity(client, request);
		clientRepository.save(client);
		log.info("Client updated successfully with id: {}", client.getId());
		return clientMapper.toResponse(client);
	}
	
	@Transactional
	public void deleteClient(Long id) {
		log.debug("Deleting category with ID: {}", id);
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Client", "id", id));
		clientRepository.delete(client);
		log.info("Client deleted successfully with ID: {}", id);
	}

}
