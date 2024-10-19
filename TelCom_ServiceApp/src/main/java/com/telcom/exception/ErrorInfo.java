package com.telcom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
	
	
	private int errorCode;
	private String errorMessage;
	
	

}
