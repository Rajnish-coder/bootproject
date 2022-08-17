package com.zee.zee5app.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	//Boolean existsByEmailA(String email,BigInteger contactnumber);
		//Boolean existsByContactnumber(BigDecimal contactnumber);
		Optional<User> findByUsername(String username);
		Boolean existsByUsername(String username );

		Boolean existsByEmail(String email);
}
