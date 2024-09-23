package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndUserType(String username, String userType);

}
