package com.target.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException {
	
	
	public NotificationNotFoundException() {
	    super();
	  }
	
	
	public NotificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotificationNotFoundException(String message) {
        super(message);
    }
    public NotificationNotFoundException(Throwable cause) {
        super(cause);
    }
}
