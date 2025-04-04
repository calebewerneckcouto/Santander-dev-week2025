package com.cwcdev.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwcdev.dio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Verifica se existe um usuário associado a um número de conta específico
    boolean existsByAccount_Number(String accountNumber);
}
