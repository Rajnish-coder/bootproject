package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

@Service
public interface WebSeriesService {

	public WebSeries insertWebSeries(WebSeries webSeries) throws UnableToGenerateIdException;
	public Optional<WebSeries> updateWebSeries(String webSeriesId,WebSeries webSeries) throws NoDataFoundException;
	public String deleteWebSeriesById(String webSeriesId) throws NoDataFoundException;
	public Optional<List<WebSeries>> getAllWebSeries() throws NoDataFoundException;
	public List<WebSeries> getAllWebSeriesByGenre(Genres genre) throws NoDataFoundException;
	public List<WebSeries> getAllWebSeriesByName(String webSeriesName) throws NoDataFoundException;
	public Optional<WebSeries> getWebSeriesById(String webSeriesId) throws NoDataFoundException;
	public List<WebSeries> findByOrderByWebSeriesNameDsc() throws NoDataFoundException;
}
