package com.vominh.example.spring.security;

import com.vominh.example.spring.security.entity.RoleEntity;
import com.vominh.example.spring.security.entity.UserEntity;
import com.vominh.example.spring.security.repository.IRoleRepository;
import com.vominh.example.spring.security.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Profile("dev")
class CreateUserTests {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Test
    void createUser() {
        Set<RoleEntity> roles = new HashSet<>((List<RoleEntity>) roleRepository.findAll());
        var user = UserEntity.builder()
                .userName("daicaminh")
                .password(new BCryptPasswordEncoder().encode("Hello12#"))
                .fullName("Độc Vô Minh")
                .roles(roles)
                .build();

        userRepository.save(user);

    }

}
