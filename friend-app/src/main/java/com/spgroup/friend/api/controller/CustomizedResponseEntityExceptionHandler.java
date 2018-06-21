package com.spgroup.friend.api.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.spgroup.friend.api.dto.response.ErrorrResponseDto;
import com.spgroup.friend.exception.InvalidDataException;
import com.spgroup.friend.exception.ResourceAlreadyExistException;
import com.spgroup.friend.exception.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidDataException.class)
	public final ResponseEntity<ErrorrResponseDto> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
		ErrorrResponseDto error = new ErrorrResponseDto(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorrResponseDto> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ErrorrResponseDto error = new ErrorrResponseDto(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public final ResponseEntity<ErrorrResponseDto> handleResourceAlreadyExistException(ResourceAlreadyExistException ex, WebRequest request) {
		ErrorrResponseDto error = new ErrorrResponseDto(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorrResponseDto> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorrResponseDto error = new ErrorrResponseDto(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
