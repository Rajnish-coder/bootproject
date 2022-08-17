package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;
import com.zee.zee5app.repos.UserRepository;
//import com.zee.zee5app.repos.UserRepoImpl;
import com.zee.zee5app.repos.UserRepository;
//import com.zee.zee5app.repos.UserRepoImpl;

@Service
public class UserServiceImpl implements UserService {

//	private UserServiceImpl()
//	{
//		
//	}
//	
//	private static UserServiceImpl userService;
//	
//	public static UserServiceImpl getInstance()
//	{
//		if(userService == null)
//		{
//			userService = new UserServiceImpl();
//		}
//		
//		return userService;
//	}

	@Autowired
	private UserRepository repo;

	public User insertUser(User user) throws UnableToGenerateIdException, EmailIdExistException, UsernameExistException {
		
		if(repo.existsByEmail(user.getEmail())== true)
		{
			throw new EmailIdExistException("email id exist!");
		}
		
		if(repo.existsByUsername(user.getUsername()) == true)
		{
			throw new UsernameExistException("username exists!");
		}
		return repo.save(user);
	}

	public Optional<List<User>> getAllUsers() {
		return Optional.ofNullable(repo.findAll());
	}

	public Optional<User> getUserById(String userId) {
		return repo.findById(userId);
	}

	public String deleteUserById(String userId) throws NoDataFoundException {
		if (repo.existsById(userId) == true) {
			
			repo.deleteById(userId);
			return "success";

		} else {
			throw new NoDataFoundException("No record exists");
		}

	}

	public User updateUserById(String userId, User user) {
		
		if(repo.existsById(userId) == true)
		{
			User u = repo.findById(userId).get();
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setEmail(user.getEmail());
			u.setDob(user.getDob());
			u.setDoj(user.getDoj());
			u.setActive(true);
			repo.save(u);
            return user;
		}
		else
		{
			return null;
		}
	}

}
