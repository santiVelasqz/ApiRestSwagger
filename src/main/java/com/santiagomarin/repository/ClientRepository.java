package com.santiagomarin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	Optional<Client> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByTaxId(String taxId);

}
