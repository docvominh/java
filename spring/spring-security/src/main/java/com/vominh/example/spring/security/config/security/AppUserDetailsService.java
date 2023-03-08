package com.vominh.example.spring.security.config.security;

import com.vominh.example.spring.security.entity.UserEntity;
import com.vominh.example.spring.security.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AppUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public AppUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(userName).orElse(null);
        return new AppUserDetails(userEntity);
    }
}
