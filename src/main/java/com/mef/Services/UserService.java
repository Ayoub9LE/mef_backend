package com.mef.Services;

import com.mef.Repositories.RoleRepository;
import com.mef.Repositories.*;
import com.mef.entities.Role;
import com.mef.entities.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User registerNewUserAccount(User user) {

        // Check if a user with the same login already exists
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("There is an account with that login: " + user.getLogin());
        }

        // Fetch the "NORMAL_USER" role from the repository
        Optional<Role> optionalRole = roleRepository.findByName("NORMAL_USER");

        // Check if the role was found
        if (!optionalRole.isPresent()) {
            throw new IllegalArgumentException("Cannot find NORMAL_USER role");
        }

        // Get the role from the optional
        Role userRole = optionalRole.get();

        // Set the role for the user
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        // Save and return the new user
        return userRepository.save(user);
    }
    public boolean validateUser(String login, String password) {
        // Fetch the user from the database
        Optional<User> optionalUser = userRepository.findByLogin(login);

        // Check if the user is present and password matches
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }




    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with ID: " + id));
    }

    public User updateUser(User user, Long id) {
        User existingUser = getUserById(id);
        existingUser.setFirstname(user.getFirstname());
        existingUser.setSecondname(user.getSecondname());
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}