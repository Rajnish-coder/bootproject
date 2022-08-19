package com.zee.zee5app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;

public interface WebRepository extends JpaRepository<WebSeries, String> {
	
	public List<WebSeries> findAllByWebSeriesName(String webSeriesName);
    public List<WebSeries> findAllByGenre(Genres genre);
    boolean existsById(String webSeriesId);

}
