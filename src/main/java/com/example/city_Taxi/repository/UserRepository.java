package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
