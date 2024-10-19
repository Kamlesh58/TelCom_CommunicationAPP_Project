package com.telcom.security;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class TokenVerification {
	
	public boolean verifyToken(String token, Long phoneNo, Map<String,String>url) throws Exception {
		
		if(StringUtils.isBlank(token)) {
			
			throw new IllegalArgumentException("The Token should not be null or empty ");
			
		}
		
		try {
			
			token = null;
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		
		return false;
		}
		return false;
		
	}

}
