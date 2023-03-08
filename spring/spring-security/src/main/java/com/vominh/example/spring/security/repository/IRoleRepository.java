package com.vominh.example.spring.security.repository;

import com.vominh.example.spring.security.entity.RoleEntity;
import com.vominh.example.spring.security.enums.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<RoleEntity, Role> {

}
