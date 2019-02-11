package com.target;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.target.service.NotificationService;
 
@Component
public class Listener {
	
	Logger logger = LoggerFactory.getLogger(Listener.class);
	@JmsListener(destination = "sampleQueue")
    public void receiveMessage(String msg) {
        logger.info("Received :" + msg);
    }

}
