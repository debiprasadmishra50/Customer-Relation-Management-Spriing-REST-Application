package demo.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRESTExceptionHandler {

	// Add an exception handler for CustomerNotfoundException
	@ExceptionHandler // : states the method is exception handler method
	// ResponseEntity<Response_Body_Type> handleException(Exception_Type Exception_Type_Object_Reference)
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException c) {
		
		// create Customer Error Response
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), c.getMessage(), System.currentTimeMillis());;
		
		// Return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// Add another Exception for any exception ... to catch any exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception e) {
		
		// create Customer Error Response
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());;
		
		// Own Custom Message to be displayed
		error.setMessage("Do Not Enter Anything Un-Necessary, Enter Appropriate Values"); 

		// Return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
