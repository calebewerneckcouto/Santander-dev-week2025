package com.cwcdev.dio.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwcdev.dio.model.Account;
import com.cwcdev.dio.model.User;
import com.cwcdev.dio.repository.AccountRepository;
import com.cwcdev.dio.repository.UserRepository;
import com.cwcdev.dio.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }

    @Override
    @Transactional
    public User create(User userToCreate) {
        if (userToCreate == null || userToCreate.getAccount() == null || userToCreate.getAccount().getNumber() == null) {
            throw new IllegalArgumentException("User or Account cannot be null.");
        }

        Account account = userToCreate.getAccount();

        if (account.getId() != null) {
            System.out.println("Account ID: " + account.getId());
            if (accountRepository.existsById(account.getId())) {
                System.out.println("Account with ID " + account.getId() + " exists in the database.");
                throw new IllegalArgumentException("Account with this ID already exists.");
            } else {
                System.out.println("Account with ID " + account.getId() + " does not exist in the database.");
            }
        }

        if (accountRepository.existsByNumber(account.getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }

        // Salvar a Account primeiro para evitar erro de entidade destacada
        account = accountRepository.save(account);
        userToCreate.setAccount(account);

        return userRepository.save(userToCreate);
    }

    @Override
    @Transactional
    public User update(Long id, User userToUpdate) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));

        if (userToUpdate.getName() != null) {
            existingUser.setName(userToUpdate.getName());
        }
        if (userToUpdate.getCard() != null) {
            existingUser.setCard(userToUpdate.getCard());
        }
        if (userToUpdate.getFeatures() != null) {
            existingUser.setFeatures(userToUpdate.getFeatures());
        }
        if (userToUpdate.getAccount() != null) {
            Account updatedAccount = userToUpdate.getAccount();
            if (updatedAccount.getId() == null || !accountRepository.existsById(updatedAccount.getId())) {
                updatedAccount = accountRepository.save(updatedAccount);
            }
            existingUser.setAccount(updatedAccount);
        }
        if (userToUpdate.getNews() != null) {
            existingUser.setNews(userToUpdate.getNews());
        }

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));

        userRepository.delete(user);
    }
}