package com.examly.springapp.model;

import com.examly.springapp.entity.User;
import com.examly.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

   
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user != null ? 
                new ResponseEntity<>(user, HttpStatus.OK) : 
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User updated = userService.updateUser(id, updatedUser);
        return updated != null ? 
                new ResponseEntity<>(updated, HttpStatus.OK) : 
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? 
                new ResponseEntity<>("{\"message\": \"User deleted successfully\"}", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @GetMapping("/paginated")
    public ResponseEntity<Page<User>> getPaginatedUsers(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        Page<User> paginatedUsers = userService.getUsersByPage(size, page);
        return new ResponseEntity<>(paginatedUsers, HttpStatus.OK);
    }

   
    @GetMapping("/paginated/sorted")
    public ResponseEntity<Page<User>> getPaginatedAndSortedUsers(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size, 
            @RequestParam String field) {
        Page<User> paginatedAndSortedUsers = userService.getUsersByPageAndSort(size, page, field);
        return new ResponseEntity<>(paginatedAndSortedUsers, HttpStatus.OK);
    }
}
