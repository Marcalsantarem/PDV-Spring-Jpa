package com.marcalsantarem.pdvspringjpa.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.marcalsantarem.pdvspringjpa.entities.dto.ResponseDTO;
import com.marcalsantarem.pdvspringjpa.exceptions.InvalidOperationException;
import com.marcalsantarem.pdvspringjpa.exceptions.NoItemException;

@RestControllerAdvice
public class ApplicationAdviceController {
	
	@ExceptionHandler(NoItemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDTO handleNoItemException(NoItemException e) {
		String messageError = e.getMessage();
		return new ResponseDTO(messageError);		
	}

	@ExceptionHandler(InvalidOperationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDTO handleInvalidOperationException(InvalidOperationException e) {
		String messageError = e.getMessage();
		return new ResponseDTO(messageError);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDTO handleValidationException(MethodArgumentNotValidException ex) {
		List<String> erros = new ArrayList<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			erros.add(errorMessage); 
		});
		return new ResponseDTO(erros);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseDTO handleConstraintViolationException(ConstraintViolationException e) {
		String messageError = e.getMessage();
		return new ResponseDTO(messageError);		
	}
}
