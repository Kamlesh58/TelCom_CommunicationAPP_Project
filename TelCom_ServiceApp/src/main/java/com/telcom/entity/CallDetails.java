package com.telcom.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CallDetails {
	
	@Id
	@GeneratedValue
	long id;
	
	long calledBy;
	
	long calledTo;
	
	Date calledOn;
	
	int duration;

}
