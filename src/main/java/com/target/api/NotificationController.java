package com.target.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

import com.google.gson.Gson;
import com.target.exception.NotificationNotFoundException;
import com.target.service.NotificationService;

@RestController
@ComponentScan("com.target")
public class NotificationController {
	
	Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	NotificationService service;
	
	
    @RequestMapping("/test")
    public String test() {
        return "Target !!!";
    }
    
    @Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/message")
	@SendToUser("/queue/reply")
	public String processMessageFromClient(@Payload String message, Principal principal) throws Exception {
		String name = new Gson().fromJson(message, Map.class).get("name").toString();
		messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/reply","hello " +  name);
		return name;
	}
	
	@MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
		
        return exception.getMessage();
    }
    
    
	@RequestMapping(value = "/processemail", method = RequestMethod.POST, consumes = "application/json")
	public void processIncomingRequest(@RequestBody String listMessageBean) {
      logger.info("Entering  processIncomingRequest method" );
		try {
			if (null != listMessageBean) {

				service.processInputRequest(listMessageBean);
				logger.info("Exiting  processIncomingRequest method" );
			}
		} catch (NotificationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification Not Found", e);
		}

	}
	
	
	@RequestMapping(value = "/processjms", method = RequestMethod.POST)
	public void processIncomingJmsRequest(@RequestBody String listMessageBean) {
      logger.info("Entering  processIncomingJmsRequest method" );
		try {
			 
			if (null != listMessageBean) {

				service.processInputJmsRequest(listMessageBean);
	       
	        logger.info("Exiting  processIncomingJmsRequest method" );
			}
			
		} catch (NotificationNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification Not Found", e);
		}

	}


}
