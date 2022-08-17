package com.zee.zee5app.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zee.zee5app.dto.Role;
import com.zee.zee5app.enums.EROLE;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional< Role> findByRoleName(EROLE roleName);
}
