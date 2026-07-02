package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import com.app.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {

	Optional<Users> findByUsername(String username);

}
