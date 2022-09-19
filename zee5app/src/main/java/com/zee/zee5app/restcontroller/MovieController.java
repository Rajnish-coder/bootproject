package com.zee.zee5app.restcontroller;

import java.io.FileNotFoundException;
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
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@PostMapping("/add")
	public ResponseEntity<?> createMovie(@RequestBody Movie movie) throws UnableToGenerateIdException
	{
		Movie m;
		try {
			m = movieService.insertMovie(movie);
			return ResponseEntity.status(HttpStatus.CREATED).body(m);
		} catch (UnableToGenerateIdException | NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("movie already exists!");
		}
			
	}
	
	@GetMapping("/getid/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") String id) throws NoDataFoundException
	{
		Movie m = movieService.getMovieByMovieId(id).get();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(m);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllMovies() throws NoDataFoundException
	{
		List<Movie> l = movieService.getAllMovies().get();
		if(l.size() == 0)
		{
			throw new NoDataFoundException("no records exits!");
		}
		else
		    return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") String id) throws NoDataFoundException
	{
			movieService.deleteMovieByMovieId(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
	}
	
	@GetMapping("byMovieName/{movieName}")
	public ResponseEntity<?> getMoviesByMovieName(@PathVariable("movieName") String movieName) throws NoDataFoundException
	{
		List<Movie> l = movieService.getAllMoviesByName(movieName);
		if(l.size() == 0)
		{
			throw new NoDataFoundException("no movie exits by this name!");
		}
		else
		    return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
	}
	
	@GetMapping("byGenre/{genre}")
	public ResponseEntity<?> getMoviesByMovieGenre(@PathVariable("genre") Genres genre) throws NoDataFoundException
	{
		List<Movie> l = movieService.getAllMoviesByGenre(genre);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
	}
	
	@GetMapping("byMovieNameDesc")
	public ResponseEntity<?> getMoviesByDesc() throws NoDataFoundException
	{
		List<Movie> l = movieService.findByOrderByMovieNameDsc();
		return ResponseEntity.ok().body(l);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") String id,@RequestBody Movie movie) throws NoDataFoundException
	{
		Movie m = movieService.updateMovie(id, movie);
		return ResponseEntity.ok().body(m);
	}
	
	
}
