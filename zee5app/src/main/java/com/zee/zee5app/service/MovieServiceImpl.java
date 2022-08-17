package com.zee.zee5app.service;
import java.util.List;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repos.MoviesRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MoviesRepository movieRepository;

	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException, FileNotFoundException {

		return movieRepository.save(movie);
	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		if(movieRepository.existsById(movieId) == true)
		{
			Movie m = movieRepository.findById(movieId).get();
			m.setActors(movie.getActors());
			m.setDirector(movie.getDirector());
			m.setGenre(movie.getGenre());
			m.setLanguages(movie.getLanguages());
			m.setMovieLength(movie.getMovieLength());
			m.setMovieName(movie.getMovieName());
			m.setProduction(movie.getProduction());
			m.setTrailer1(movie.getTrailer1());
			
			movieRepository.save(m);
			return m;
		}
		else {
			return null;
		}
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		if (movieRepository.existsById(movieId) == true) {

			movieRepository.deleteById(movieId);
			return "success";

		} else {
			throw new NoDataFoundException("No record exists");
		}

	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(movieRepository.findAll());
	}

	@Override
	public List<Movie> getAllMoviesByGenre(Genres genre) {
		// TODO Auto-generated method stub
		return movieRepository.findAllByGenre(genre);
	}

	@Override
	public List<Movie> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.findAllByMovieName(movieName);
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		return movieRepository.findById(movieId);
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub

		return null;
	}

}
