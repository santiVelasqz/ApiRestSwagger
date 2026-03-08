package com.santiagomarin.configurations;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.santiagomarin.entities.Role;
import com.santiagomarin.entities.RoleName;
import com.santiagomarin.repository.RoleRepository;

@Component
public class DataSeeder implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public DataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
    	createRoleIfNotExists(RoleName.SUPERADMIN, "Full system access");
        createRoleIfNotExists(RoleName.ADMIN, "System access");
        createRoleIfNotExists(RoleName.MANAGER, "Manage products and invoices");
        createRoleIfNotExists(RoleName.EMPLOYEE, "Read only access");
    }

    private void createRoleIfNotExists(RoleName name, String description) {
        if (!roleRepository.existsByName(name)) {
            roleRepository.save(new Role(null, name, description));
        }
    }
}