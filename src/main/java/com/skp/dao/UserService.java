package com.skp.dao;

import com.skp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#userId")
    public Users getUserById(Long userId) {
        // Simulate a delay for demonstration purposes
        simulateSlowService();
        return userRepository.findById(userId).orElse(null);
    }

    @CachePut(value = "users", key = "#user.id")
    public Users updateUser(Users users) {
        return userRepository.save(users);
    }

    @CacheEvict(value = "users", key = "#userId")
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
    @Cacheable(value = "users", key = "#userId")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
