package com.cwcdev.dio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwcdev.dio.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	boolean existsByNumber(String number);

}
