package com.student.service.service;

import com.student.service.entity.Login;

public interface UserService {
	
	Login findByUsername(String username);
    Login registerNewUser(Login user);

}
