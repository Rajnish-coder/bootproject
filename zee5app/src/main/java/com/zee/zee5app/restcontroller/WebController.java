package com.zee.zee5app.restcontroller;

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

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.WebSeriesService;

@RestController
@RequestMapping("/api/web")
public class WebController {

	@Autowired
	WebSeriesService webSeriesService;

	@PostMapping("/add") // post method + request mapping. Since version 4.3
	// @RequestBody is used to transform json object to java object in the controller.
	public ResponseEntity<?> createWebSeries(@RequestBody WebSeries web) throws UnableToGenerateIdException, EmailIdExistException, UsernameExistException
	{
//		User u = new User("0","rajnish sho","pass","raj","sho",
//				"rajnish123@gmail.com",LocalDate.now(),LocalDate.now(),true);
		
	
			WebSeries w2 = webSeriesService.insertWebSeries(web);
			return ResponseEntity.status(HttpStatus.CREATED).body(w2);
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
	public ResponseEntity<?> getWebById(@PathVariable("id") String id) throws NoDataFoundException
	{
			WebSeries w = webSeriesService.getWebSeriesById(id).get();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(w);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllWebSeries() throws NoDataFoundException
	{
			List<WebSeries> data = webSeriesService.getAllWebSeries().get();
			return ResponseEntity.accepted().body(data);
	}
	
	@DeleteMapping("/{id}")
	// @PathVariable is used to internally convert data type.
	public ResponseEntity<?> deleteWebSeries(@PathVariable("id") String id) throws NoDataFoundException
	{
			webSeriesService.deleteWebSeriesById(id);
			return ResponseEntity.ok().body("success");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateWeb(@PathVariable("id") String id,@RequestBody WebSeries ws) throws NoDataFoundException
	{
	       WebSeries u = webSeriesService.updateWebSeries(id, ws).get();
	       return ResponseEntity.ok().body(u);
	}
	
	@GetMapping("byWebName/{webName}")
	public ResponseEntity<?> getWebByWebName(@PathVariable("webName") String webName) throws NoDataFoundException
	{
		List<WebSeries> l = webSeriesService.getAllWebSeriesByName(webName);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
	}
	
	@GetMapping("byGenre/{genre}")
	public ResponseEntity<?> getWebByWebGenre(@PathVariable("genre") Genres genre) throws NoDataFoundException
	{
		List<WebSeries> l = webSeriesService.getAllWebSeriesByGenre(genre);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
	}
	
	@GetMapping("byWebNameDesc")
	public ResponseEntity<?> getWebByDesc() throws NoDataFoundException
	{
		List<WebSeries> l = webSeriesService.findByOrderByWebSeriesNameDsc();
		return ResponseEntity.ok().body(l);
	}
}
