package com.votingsystem.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.votingsystem.entity.User;
import com.votingsystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	    public User registerUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }

	 
	   @Override
		public java.util.Optional<User> loginUser(String email, String password) {
			Optional<User> user = userRepository.findByEmail(email);
	        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
	            return user;
	        }
			return Optional.empty();
		}

}
