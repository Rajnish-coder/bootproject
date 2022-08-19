package com.zee.zee5app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repos.WebRepository;
import com.zee.zee5app.utils.MovieComparator;
import com.zee.zee5app.utils.WebComparator;

@Service
public class WebSeriesServiceImpl implements WebSeriesService {

	@Autowired
	private WebRepository repo;
	@Override
	public WebSeries insertWebSeries(WebSeries webSeries) throws UnableToGenerateIdException {
		
		return repo.save(webSeries);
	}

	@Override
	public Optional<WebSeries> updateWebSeries(String webSeriesId, WebSeries webSeries) throws NoDataFoundException {
		
		if(repo.existsById(webSeriesId) == true)
		{
			WebSeries ws = repo.findById(webSeriesId).get();
			ws.setActors(webSeries.getActors());
			ws.setDirector(webSeries.getDirector());
			ws.setGenre(webSeries.getGenre());
			ws.setLanguages(webSeries.getLanguages());
			ws.setProduction(webSeries.getProduction());
			ws.setTrailer1(webSeries.getTrailer1());
			ws.setWebSeriesLength(webSeries.getWebSeriesLength());
			ws.setWebSeriesName(webSeries.getWebSeriesName());
			repo.save(ws);
			return Optional.of(ws);
		}
		else
		{
			throw new NoDataFoundException("webseries not exists with this id");
		}
	}

	@Override
	public String deleteWebSeriesById(String webSeriesId) throws NoDataFoundException {
		
		if (repo.existsById(webSeriesId) == true) {

			repo.deleteById(webSeriesId);
			return "success";

		} else {
			throw new NoDataFoundException("No record exists");
		}
	}

	@Override
	public Optional<List<WebSeries>> getAllWebSeries() throws NoDataFoundException {
		
		List<WebSeries> l = repo.findAll();
		if(l.size() ==0)
		{
			throw new NoDataFoundException("no records exits!");
		}
		else
			return Optional.of(l);
	}

	@Override
	public List<WebSeries> getAllWebSeriesByGenre(Genres genre) throws NoDataFoundException {
		
		List<WebSeries> l = repo.findAllByGenre(genre);
		if(l.size() ==0)
		{
			throw new NoDataFoundException("no records exits by this genre!");
		}
		else
			return l;
	}

	@Override
	public List<WebSeries> getAllWebSeriesByName(String webSeriesName) throws NoDataFoundException {
		
		List<WebSeries> l = repo.findAllByWebSeriesName(webSeriesName);
		if(l.size() ==0)
		{
			throw new NoDataFoundException("no records exits by this webseries name!");
		}
		else
			return l;
	}

	@Override
	public Optional<WebSeries> getWebSeriesById(String webSeriesId) throws NoDataFoundException {
		
		if(repo.existsById(webSeriesId) == true)
		{
			return Optional.of(repo.findById(webSeriesId).get());
		}
		else
		{
		    throw new NoDataFoundException("no record exists by this id!");
		}
	}

	@Override
	public List<WebSeries> findByOrderByWebSeriesNameDsc() throws NoDataFoundException {
		
		List<WebSeries> l = repo.findAll();
		if(l.size() == 0)
		{
			throw new NoDataFoundException("no records exits!");
		}
		else
		{
			Collections.sort(l,new WebComparator());
			return l;
		}
	}

}
