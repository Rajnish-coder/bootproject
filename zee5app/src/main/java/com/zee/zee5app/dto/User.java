package com.zee.zee5app.dto;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username")})

public class User {
	
	@Id
	
	// responsible for generating value
	@GenericGenerator(name = "userIdGenerator",
	strategy = "com.zee.zee5app.utils.UserIdGenerator")
	
	// responsible for consuming the value
	@GeneratedValue(generator = "userIdGenerator")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	    private String UserId;
	@NotNull
	@Size(max = 100)
	private String username;
	@NotNull
//	@Size(min=3, max = 50)
	private String password;
	@NotNull
	@Size(max = 50)
	private String firstName;
	@NotNull
	@Size(max = 50)
	private String lastName;
	@Email
	@NotNull
	private String email;
//	@Temporal(TemporalType.DATE) // should only be set on java.utils.date and not on LocalDate
	@Column(columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@NotNull
	private LocalDate doj;
//	@Temporal(TemporalType.DATE)
//	@Column(columnDefinition = "DATE")
	@Column(columnDefinition = "DATE")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private LocalDate dob;
	private boolean isActive;
	
	@ManyToMany(fetch = FetchType.LAZY) 
	
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="userId"),
	inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles = new HashSet<>();
	
	
}
