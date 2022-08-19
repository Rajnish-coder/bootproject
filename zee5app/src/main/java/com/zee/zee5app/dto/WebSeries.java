package com.zee.zee5app.dto;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zee.zee5app.enums.EROLE;
import com.zee.zee5app.enums.Genres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WebSeries {

	@Id
	@GenericGenerator(name = "webSeriesIdGenerator",
	strategy = "com.zee.zee5app.utils.WebSeriesIdGenerator")
	@GeneratedValue(generator = "webSeriesIdGenerator")
	private String webSeriesId;
	@NotNull
    private String actors[];
	@NotNull
    private String webSeriesName;
	@NotNull
    private String director;
	@NotNull
    private Genres genre;
	@NotNull
    private String production;
	@NotNull
    private String languages[];
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime webSeriesLength;
	@NotNull
    private String trailer1;
    //private byte[] trailer2;
	
}
