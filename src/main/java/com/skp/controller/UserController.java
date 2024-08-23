package com.skp.controller;

import com.skp.dao.UserService;
import com.skp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;


    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Users createUser(@RequestBody Users users) {
        return userService.updateUser(users);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users users) {
        users.setId(id);
        return userService.updateUser(users);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/cache/stats")
    public Map<String, Object> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        cacheManager.getCacheNames().forEach(cacheName -> {
            var caffeineCache = (com.github.benmanes.caffeine.cache.Cache<?, ?>) cacheManager.getCache(cacheName).getNativeCache();
            stats.put(cacheName, caffeineCache.stats().toString());
        });
        return stats;
    }
}
