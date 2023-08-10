package com.student.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.service.entity.Login;

public interface UserRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);

}
