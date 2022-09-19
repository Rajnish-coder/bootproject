package com.zee.zee5app.service;
import java.util.Collections;
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
import com.zee.zee5app.utils.MovieComparator;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MoviesRepository movieRepository;

	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException, NoDataFoundException {

		
			List<Movie> m = getAllMoviesByName(movie.getMovieName());
			if(m.size()>=1)
			{
				throw new NoDataFoundException("movie already exists!"); 
			}
		
		return movieRepository.save(movie);
	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) throws NoDataFoundException {
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
			throw new NoDataFoundException("movie does not exist by this id");
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
	public Optional<List<Movie>> getAllMovies() throws NoDataFoundException {
		
		List<Movie> l = movieRepository.findAll();
		if(l.size() ==0)
		{
			throw new NoDataFoundException("no records exits!");
		}
		else
			return Optional.of(l);
	}

	@Override
	public List<Movie> getAllMoviesByGenre(Genres genre) throws NoDataFoundException {
		
		List<Movie> l = movieRepository.findAllByGenre(genre);
		if(l.size() ==0)
		{
			throw new NoDataFoundException("no records exits by this genre!");
		}
		else
			return l;
	}

	@Override
	public List<Movie> getAllMoviesByName(String movieName) {
		
		List<Movie> l = movieRepository.findAllByMovieName(movieName);
		if(l.size() ==0)
		{
			//throw new NoDataFoundException("no records exits!");
			return l;
		}
		else
			return l;
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
	
		if(movieRepository.existsById(movieId)==false)
		{
			throw new NoDataFoundException("movie does not exists by this id");
		}
		else
		   return movieRepository.findById(movieId);
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() throws NoDataFoundException {
		
		List<Movie> l = movieRepository.findAll();
		if(l.size() == 0)
		{
			throw new NoDataFoundException("no records exits!");
		}
		else
		{
			Collections.sort(l,new MovieComparator());
			return l;
		}
	}

}
