//package com.zee.zee5app.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.zee.zee5app.dto.WebSeries;
//import com.zee.zee5app.exceptions.NoDataFoundException;
//import com.zee.zee5app.repos.WebSeriesRepository;
//
//@Service
//public class WebSeriesServiceImpl implements WebSeriesService {
//
//	@Autowired
//	private WebSeriesRepository repo;
//	@Override
//	public WebSeries insertWebSeries(WebSeries webSeries) {
//		// TODO Auto-generated method stub
//		
//		return repo.insertWebSeries(webSeries);
//	}
//
//	@Override
//	public Optional<WebSeries> updateWebSeries(String webSeriesId, WebSeries webSeries) {
//		// TODO Auto-generated method stub
//		return repo.updateWebSeries(webSeriesId, webSeries);
//	}
//
//	@Override
//	public String deleteWebSeriesById(String webSeriesId) throws NoDataFoundException {
//		// TODO Auto-generated method stub
//		return repo.deleteWebSeriesById(webSeriesId);
//	}
//
//	@Override
//	public Optional<List<WebSeries>> getAllWebSeries() {
//		// TODO Auto-generated method stub
//		return repo.getAllWebSeries();
//	}
//
//	@Override
//	public Optional<WebSeries[]> getAllWebSeriesByGenre(String genre) {
//		// TODO Auto-generated method stub
//		return repo.getAllWebSeriesByGenre(genre);
//	}
//
//	@Override
//	public Optional<WebSeries[]> getAllWebSeriesByName(String webSeriesName) {
//		// TODO Auto-generated method stub
//		return repo.getAllWebSeriesByName(webSeriesName);
//	}
//
//	@Override
//	public Optional<WebSeries> getWebSeriesById(String webSeriesId) {
//		// TODO Auto-generated method stub
//		return repo.getWebSeriesById(webSeriesId);
//	}
//
//	@Override
//	public List<WebSeries> findByOrderByWebSeriesNameDsc() {
//		// TODO Auto-generated method stub
//		return repo.findByOrderByWebSeriesNameDsc();
//	}
//
//}
