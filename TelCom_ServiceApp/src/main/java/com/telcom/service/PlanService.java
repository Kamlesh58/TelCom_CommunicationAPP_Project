package com.telcom.service;

import java.util.List;

import com.telcom.dto.PlanDTO;

public interface PlanService {
	
	List<PlanDTO> getAllPlans();
	
	public void choosePlan(int planId,Long phoneNo);

}
