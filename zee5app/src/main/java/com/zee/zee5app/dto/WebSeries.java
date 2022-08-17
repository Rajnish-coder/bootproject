package com.zee.zee5app.dto;

import com.zee.zee5app.enums.Genres;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class WebSeries {

	private String webSeriesId;
    private String actors[];
    private String webSeriesName;
    private String director;
    private Genres genre;
    private String production;
    private String languages[];
    private float webSeriesLength;
    private String trailer1;
    //private byte[] trailer2;
	public WebSeries(String[] actors, String webSeriesName, String director, Genres genre,
			String production, String[] languages, float webSeriesLength, String trailer1) {
		super();
		this.actors = actors;
		this.webSeriesName = webSeriesName;
		this.director = director;
		this.genre = genre;
		this.production = production;
		this.languages = languages;
		this.webSeriesLength = webSeriesLength;
		this.trailer1 = trailer1;
	}
	
	
    
    public WebSeries() {
		// TODO Auto-generated constructor stub
	}
    
    
	public void setWebSeriesId(String webSeriesId) {
		this.webSeriesId = webSeriesId;
	}
//	
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	
	public void setWebSeriesName(String webSeriesName) {
		this.webSeriesName = webSeriesName;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setGenre(Genres genre) {
		this.genre = genre;
	}
	
	public void setProduction(String production) {
		this.production = production;
	}
	
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	
	public void setWebSeriesLength(float webSeriesLength) {
		this.webSeriesLength = webSeriesLength;
	}
	
	public void setTrailer1(String trailer1) {
		this.trailer1 = trailer1;
	}
    
    
}
