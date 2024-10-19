package com.telcom.dto;



import com.telcom.entity.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
	
	
    Integer plainId;
	
	String planName;
	
	Integer nationalRate;
	
	Integer localRate;

	public static PlanDTO prepareDTO(Plan plan) {
		
		PlanDTO plandto = new PlanDTO();
		plandto.setPlainId(plan.getPlainId());
		plandto.setPlanName(plan.getPlanName());
		plandto.setLocalRate(plan.getLocalRate());
		plandto.setNationalRate(plan.getNationalRate());
		
		return plandto;
	}

}
