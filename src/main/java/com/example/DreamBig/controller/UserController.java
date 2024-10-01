package com.example.DreamBig.controller;

import com.example.DreamBig.dto.UserDTO;
import com.example.DreamBig.exception.InvalidInputException;
import com.example.DreamBig.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<UserDTO> users = new ArrayList<>();

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getFullName() == null || userDTO.getFullName().isEmpty()) {
            throw new InvalidInputException("Full name cannot be empty");
        }
        users.add(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        for (UserDTO user : users) {
            if (user.getId().equals(id)) {
                user.setFullName(updatedUser.getFullName());
                user.setPhoneNumber(updatedUser.getPhoneNumber());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());
                user.setRole(updatedUser.getRole());
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("User not found with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>("User with ID " + id + " deleted.", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
    }
}