package com.zee.zee5app.utils;

import java.util.Comparator;

import com.zee.zee5app.dto.Movie;

public class MovieComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
	
		return o2.getMovieName().compareTo(o1.getMovieName());
	}

}
