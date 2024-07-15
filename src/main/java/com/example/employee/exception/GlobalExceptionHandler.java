package com.example.employee.exception;

	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.ControllerAdvice;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;

	import java.time.LocalDateTime;
	import java.util.HashMap;
	import java.util.Map;

	@ControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(EmployeeNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<Map<String, Object>> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("message", ex.getMessage());
	        response.put("status", HttpStatus.NOT_FOUND.value());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(RoleInvalidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<Map<String, Object>> handleRoleInvalidException(RoleInvalidException ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("message", ex.getMessage());
	        response.put("status", HttpStatus.BAD_REQUEST.value());
	      

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    
	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("message", "An unexpected error occurred");
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	

}
