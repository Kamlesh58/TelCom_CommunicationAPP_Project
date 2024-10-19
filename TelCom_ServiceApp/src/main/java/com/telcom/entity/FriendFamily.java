package com.telcom.entity;

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
public class FriendFamily {
	
	@Id
	@GeneratedValue
	int id;
	
	long phoneNo;
	
	long friendAndFamily;

}
