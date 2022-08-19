package com.zee.zee5app.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

@Service
public interface MovieService {
  
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException;
	public Movie updateMovie(String movieId,Movie movie) throws NoDataFoundException;
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException;
	public Optional<List<Movie>> getAllMovies() throws NoDataFoundException;
	public List<Movie> getAllMoviesByGenre(Genres genre) throws NoDataFoundException;
	public List<Movie> getAllMoviesByName(String movieName) throws NoDataFoundException;
	public Optional<Movie> getMovieByMovieId(String movieId) throws NoDataFoundException;
	public List<Movie> findByOrderByMovieNameDsc() throws NoDataFoundException;
	
}
