package com.telcom.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Customer {

	@Id
	Long phoneNo;
	String name;
	Integer age;
	String address;
	String password;
	char gender;
	
	@ManyToOne
	@JoinColumn(name="plainId")
	Plan plan;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="FriendPhoneDetails")
	List<FriendFamily> friends = new ArrayList<>();
}
