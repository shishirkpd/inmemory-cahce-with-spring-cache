package com.skp.dao;

import com.skp.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "usersCache", key = "#userId")
    public Users getUserById(Long userId) {
        System.out.println("Fetching user with id: " + userId);
        // Simulate a delay for demonstration purposes
        simulateSlowService();
        return userRepository.findById(userId).orElse(null);
    }

    @CachePut(value = "usersCache", key = "#users.id")
    public Users updateUser(Users users) {
        return userRepository.save(users);
    }

    @CacheEvict(value = "usersCache", key = "#userId")
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

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
