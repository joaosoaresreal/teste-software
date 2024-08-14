package br.edu.ifms.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.ifms.services.exceptions.DataBaseException;
import br.edu.ifms.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> entityNotFound(
			ResourceNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandartError error = new StandartError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Recurso não encontrado");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandartError> database(
			DataBaseException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandartError error = new StandartError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Database exception");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
}
