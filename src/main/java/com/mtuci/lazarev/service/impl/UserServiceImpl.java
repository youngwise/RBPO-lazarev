package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.ApplicationRole;
import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.repositories.RoleRepository;
import com.mtuci.lazarev.repositories.UserRepository;
import com.mtuci.lazarev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Optional<ApplicationUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<ApplicationUser> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean saveUser(ApplicationUser user, String password) {
        Optional<ApplicationUser> userFromDB = userRepository.findByLogin(user.getLogin());

        if (userFromDB.isPresent()) return false;

        user.setApplicationRole(ApplicationRole.USER);
        user.setRole(roleRepository.findByName(ApplicationRole.USER.name()));


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword_hash(encoder.encode(password));

        user.setEmail(user.getEmail());
        user.setLogin(user.getLogin());

        userRepository.save(user);
        return true;
    }
}
