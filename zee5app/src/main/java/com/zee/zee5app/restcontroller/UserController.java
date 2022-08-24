package com.zee.zee5app.restcontroller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Role;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.EROLE;
import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;
import com.zee.zee5app.payload.request.LoginRequest;
import com.zee.zee5app.payload.request.SignupRequest;
import com.zee.zee5app.payload.response.JwtResponse;
import com.zee.zee5app.repos.RoleRepository;
import com.zee.zee5app.repos.UserRepository;
import com.zee.zee5app.security.jwt.JwtUtils;
import com.zee.zee5app.security.services.UserDetailsImpl;
import com.zee.zee5app.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	@PostMapping("/signup") // post method + request mapping. Since version 4.3
	// @RequestBody is used to transform json object to java object in the controller.
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) throws UnableToGenerateIdException, EmailIdExistException, UsernameExistException
	{
//		User u = new User("0","rajnish sho","pass","raj","sho",
//				"rajnish123@gmail.com",LocalDate.now(),LocalDate.now(),true);
		
		     User u2 = new User();
		     u2.setFirstName(signUpRequest.getFirstName());
		     u2.setLastName(signUpRequest.getLastName());
		     u2.setUsername(signUpRequest.getUsername());
		     u2.setEmail(signUpRequest.getEmail());
		     u2.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		     u2.setDob(signUpRequest.getDob());
		     u2.setDoj(LocalDate.now());
		     
		     Set<String> strroles = signUpRequest.getRole();
		     Set<Role> roles = new HashSet<>();
		     if(strroles == null)
		     {
		    	 
		    	 Role userRole = roleRepo.findByRoleName(EROLE.ROLE_USER).orElseThrow(
		    			 ()->new RuntimeException("error: role not found")
		    			 );
		    	 roles.add(userRole);
		     }
		     else
		     {
		    	 strroles.forEach(e->{
					  switch (e) {
					case "admin":
						Role roleAdmin	= roleRepo.findByRoleName(EROLE.ROLE_ADMIN)
								  .orElseThrow(
										  ()->new RuntimeException("Error:role not found")
										  );
						roles.add(roleAdmin);
						break;
						
					case "mod":
					Role roleMod	= roleRepo.findByRoleName(EROLE.ROLE_MODERATOR)
						  .orElseThrow(
								  ()->new RuntimeException("Error:role not found")
								  );
				roles.add(roleMod);
				break;
						
						
							

					default:
						 Role userRole = roleRepo.findByRoleName(EROLE.ROLE_USER)
						  .orElseThrow(
								  ()->new RuntimeException("Error:role not found")
								  );
						 roles.add(userRole);
					}
				  });
				  
				  
				  
			  }
			  u2.setRoles(roles);
			  userService.insertUser(u2);
			  
			  
			return ResponseEntity.status(201).body("user created successfully");
	
//			 u2 = userService.insertUser(u2);
//		    System.out.println(signUpRequest);
//			return ResponseEntity.status(HttpStatus.CREATED).body(signUpRequest);
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
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
	{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
						loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authentication.getPrincipal();
		
		List<String> roles = userDetailsImpl.getAuthorities()
				           .stream()
				           .map(i->i.getAuthority())
                           .collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,userDetailsImpl.getId(),userDetailsImpl.getUsername(),
				                 userDetailsImpl.getEmail(),roles));

				     
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
	public ResponseEntity<?> updateUser(@PathVariable("id") String id,@RequestBody User user) throws NoDataFoundException
	{
	       User u = userService.updateUserById(id, user);
	       return ResponseEntity.ok().body(u);
	}
}
