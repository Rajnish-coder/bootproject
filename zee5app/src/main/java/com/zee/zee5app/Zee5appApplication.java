package com.zee.zee5app;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Employee;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;
import com.zee.zee5app.repos.EmployeeRepository;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.UserService;

@SpringBootApplication
public class Zee5appApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = null;
		context = SpringApplication.run(Zee5appApplication.class, args);
		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println(dataSource!=null);
		String[] beans = context.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String l : beans) {
			System.out.println(l);
		}
		
		UserService userService = context.getBean(UserService.class);
	    MovieService movieService = context.getBean(MovieService.class);
	    
	    EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
//	    Employee e = new Employee(2l,"abhi","engg",30000);
//	    employeeRepository.save(e);
	    
//	    List<Employee> depts = employeeRepository.findByDept("engg");
//	    for (Employee employee : depts) {
//			System.out.println(employee);
//		}
	    
	  //  depts = employeeRepository.findBySalaryGreaterThan(20000);
	    
	 //   depts = employeeRepository.findByDeptAndSalaryLessThan("engg", 30000);
	    
//	    depts = employeeRepository.findBySalaryGreaterThanEqual(20000);
//	    
//	    System.out.println(depts);
		
		try {
			User user = userService.insertUser(
					new User("0","rajnish","pass","raj","sho","rajnish12@gmail.com",LocalDate.now(),LocalDate.now(),true));
			System.out.println(user);
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailIdExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsernameExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		String[] actors = {"a","b","c"};
		
		Genres g = Genres.DRAMA;
		String[] languages = {"a","b","c"};
//		
//		try {
//			Movie m = movieService.insertMovie(
//					new Movie("0",actors,"under the shadows","a",g,"a",languages,3.02f,"abc"));
//			System.out.println(m);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnableToGenerateIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		List<Movie> l = movieService.getAllMoviesByName("under the shadows");
//		for (Movie movie : l) {
//			System.out.println(movie);
//		}
//		
//	    l = movieService.getAllMoviesByGenre(g);
//	    
//	    for (Movie movie : l) {
//			System.out.println(movie);
//		}
		
		
//		List<User> u = userService.getAllUsers().get();
//		
//		for (User user : u) {
//			System.out.println(user);
//		}
	    
//	    User u = userService.updateUserById("rs00000005", 
//	    		new User("0","raj","update","rajnish1@gmail.com",LocalDate.now(),LocalDate.now(),true));
//	    System.out.println(u);
	    
//		
//		String result = null;
//		
//		try {
//			result = userService.deleteUserById("rs00000005");
//			System.out.println(result);
//		} catch (NoDataFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
//	    List<Movie> m = movieService.getAllMovies().get();
//	    
//	    for (Movie movie : m) {
//			System.out.println(movie);
//		}
		
//	    String result = null;
//	    
//	    try {
//			result = movieService.deleteMovieByMovieId("un00000002");
//			System.out.println(result);
//		} catch (NoDataFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
//	    Movie m = movieService.getMovieByMovieId("un00000002").get();
//	    System.out.println(m);
		
		context.close();
	}

}
