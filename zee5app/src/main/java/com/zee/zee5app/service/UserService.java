package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;

@Service
public interface UserService {

	public User insertUser(User user) throws UnableToGenerateIdException, EmailIdExistException, UsernameExistException;
	public User updateUserById(String userId,User user);
	public String deleteUserById(String userId) throws NoDataFoundException;
	public Optional<List<User>> getAllUsers();
	public Optional<User> getUserById(String userId);
}
