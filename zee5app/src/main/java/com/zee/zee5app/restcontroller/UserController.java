package com.zee.zee5app.restcontroller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;
import com.zee.zee5app.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/add") // post method + request mapping. Since version 4.3
	// @RequestBody is used to transform json object to java object in the controller.
	public ResponseEntity<?> createUser(@RequestBody User user) throws UnableToGenerateIdException, EmailIdExistException, UsernameExistException
	{
//		User u = new User("0","rajnish sho","pass","raj","sho",
//				"rajnish123@gmail.com",LocalDate.now(),LocalDate.now(),true);
		
	
			User u2 = userService.insertUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(u2);
//		} catch (UnableToGenerateIdException e) {
//			
//			e.printStackTrace();
//			// response with json based message stating unable to create the user
//			HashMap<String, String> resData = new HashMap<>();
//			resData.put("status", "internal id creation problem");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resData);
//			
//		} catch (EmailIdExistException e) {
//			e.printStackTrace();
//			HashMap<String, String> resData = new HashMap<>();
//			resData.put("status", "record already exixts!");
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(resData);
//		} catch (UsernameExistException e) {
//			e.printStackTrace();
//			HashMap<String, String> resData = new HashMap<>();
//			resData.put("status", "username already exixts!");
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(resData);
//		}
//		User user1 = null;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) throws NoDataFoundException
	{
			User u = userService.getUserById(id).get();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllUsers() throws NoDataFoundException
	{
			List<User> data =  userService.getAllUsers().get();
			return ResponseEntity.accepted().body(data);
	}
	
	@DeleteMapping("/{id}")
	// @PathVariable is used to internally convert data type.
	public String deleteUser(@PathVariable("id") String id) throws NoDataFoundException
	{
			userService.deleteUserById(id);
			return "success";
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") String id)
	{
		User u = new User("0","rajnishshonkhia","pass","raj","sho",
				"rajnish123@gmail.com",LocalDate.now(),LocalDate.now(),true);
		userService.updateUserById("rs00000007",u);
		return u;
	}
}
