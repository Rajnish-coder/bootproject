package com.zee.zee5app.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Table(name = "user_table")

public class User {
	
	@Id
	
	// responsible for generating value
	@GenericGenerator(name = "userIdGenerator",
	strategy = "com.zee.zee5app.utils.UserIdGenerator")
	
	// responsible for consuming the value
	@GeneratedValue(generator = "userIdGenerator")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	    private String UserId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate doj;
	private LocalDate dob;
	private boolean isActive;
	
	
	
}
