package com.thbs.gb.GlobalExceptionHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String customExceptionHandler(Exception e) {

		final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
		logger.error("Yikes! some Exception occured!!");
		e.printStackTrace();
		return "customError";
	}
}
