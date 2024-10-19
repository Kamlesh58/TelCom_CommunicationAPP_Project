package com.telcom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcom.dto.PlanDTO;
import com.telcom.entity.Customer;
import com.telcom.entity.Plan;
import com.telcom.repository.CustomerRepository;
import com.telcom.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService{
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PlanRepository planrepo;
	
	@Autowired
	CustomerRepository customerrepo;

	@Override
	public List<PlanDTO> getAllPlans() {
		
		logger.info("Plan Details{} ");
		List<Plan>plans = planrepo.findAll();
		List<PlanDTO> plandto = new ArrayList<>();
		
		for(Plan plan : plans) {
			plandto.add(PlanDTO.prepareDTO(plan));
		}
		logger.info("Plan Details{} ", plandto);
		return plandto;
	}

	@Override
	public void choosePlan(int planId, Long phoneNo) {
		Optional<Customer>custdata = customerrepo.findById(phoneNo);
		if(!custdata.isPresent()) {
			System.out.println("Customer Not Present");
		}
		Customer cust = custdata.get();
		
		Optional<Plan>plans = planrepo.findById(planId);

		if(plans.isPresent()) {
			Plan plan = new Plan();
			plan.setPlainId(plans.get().getPlainId());
			cust.setPlan(plan);
			customerrepo.save(cust);
		}
		
		
		
	}

	

}
