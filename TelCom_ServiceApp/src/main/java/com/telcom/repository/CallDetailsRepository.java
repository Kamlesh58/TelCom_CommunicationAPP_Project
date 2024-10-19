package com.telcom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telcom.entity.CallDetails;

@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetails, Long> {
	
	List<CallDetails>findBycalledBy(long calledBy);

}
