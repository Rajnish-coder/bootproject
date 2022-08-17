package com.zee.zee5app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.zee.zee5app.enums.EROLE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
	
	@Id //Id must be auto generated
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;
	
	//it should be the value from available Enums
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private EROLE roleName;
	
	
}
