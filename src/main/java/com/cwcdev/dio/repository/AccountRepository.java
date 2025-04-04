package com.cwcdev.dio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwcdev.dio.model.Account;
import com.cwcdev.dio.model.User;

public interface AccountRepository extends JpaRepository<Account, Long>{

	boolean existsByNumber(String number);

	Optional<User> findByNumber(String number);

}
