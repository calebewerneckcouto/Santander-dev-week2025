package com.cwcdev.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwcdev.dio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
