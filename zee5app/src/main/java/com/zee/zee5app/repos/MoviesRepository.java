package com.zee.zee5app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.enums.Genres;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, String>
{
	// we will create our own method here
	// find -->  select * from movie_table
	public List<Movie> findAllByMovieName(String movieName);
    public List<Movie> findAllByGenre(Genres genre);
}
