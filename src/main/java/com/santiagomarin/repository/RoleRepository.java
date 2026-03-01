package com.santiagomarin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagomarin.entities.Role;
import com.santiagomarin.entities.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(RoleName name);
    boolean existsByName(RoleName name);
}
