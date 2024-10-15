package com.mtuci.lazarev.service.impl;

import com.mtuci.lazarev.models.ApplicationUser;
import com.mtuci.lazarev.models.UserDetailsImpl;
import com.mtuci.lazarev.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws  UsernameNotFoundException{
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return UserDetailsImpl.fromApplicationUser(user);
    }
}
