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
		Movie m = movieService.insertMovie(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(m);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") String id) throws NoDataFoundException
	{
		Movie m = movieService.getMovieByMovieId(id).get();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(m);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllMovies()
	{
		List<Movie> l = movieService.getAllMovies().get();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") String id) throws NoDataFoundException
	{
			movieService.deleteMovieByMovieId(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
	}
	
//	@GetMapping("/{movieName}")
//	public ResponseEntity<?> getMoviesByMovieName(@PathVariable("movieName") String movieName)
//	{
//		List<Movie> l = movieService.getAllMoviesByName(movieName);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
//	}
//	
//	@GetMapping("/{genre}")
//	public ResponseEntity<?> getMoviesByMovieName(@PathVariable("genre") Genres genre)
//	{
//		List<Movie> l = movieService.getAllMoviesByGenre(genre);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(l);
//	}
	
	
}
