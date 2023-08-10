package com.student.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.service.entity.Login;
import com.student.service.repository.UserRepository;
import com.student.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public Login findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public Login registerNewUser(Login username) {
		// TODO Auto-generated method stub
		 return userRepository.save(username);
	}

}
