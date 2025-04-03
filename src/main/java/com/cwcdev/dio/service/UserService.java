package com.cwcdev.dio.service;

import com.cwcdev.dio.model.User;

public interface UserService {
    
    User findById(Long id);
    
    User create(User user);
    
    User update(Long id, User user);
    
    void delete(Long id);
}