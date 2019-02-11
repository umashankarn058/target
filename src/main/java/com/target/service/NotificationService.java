package com.target.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.target.bean.MessageBean;

@Service
public class NotificationService {
	
	Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * This function is used to send mail without attachment.
	 * @param MessageBean
	 * @throws MailException
	 */

	public void sendEmail(List<MessageBean> listBean) throws MailException {
		logger.info("Entering sendEmail method");

		SimpleMailMessage mail = new SimpleMailMessage();
		for (MessageBean objBean : listBean) {

			mail.setTo(objBean.getTo());
			mail.setFrom(objBean.getFrom());
			mail.setSubject(objBean.getSubject());
			mail.setText(objBean.getText());
			javaMailSender.send(mail);
			logger.info("Exiting sendEmail method");
		}

	}

	public void processInputRequest(String request) {
		logger.info("Entering processInputRequest method");
		List<MessageBean> msgBean = null;
		try {
			Gson gson = new Gson();
			msgBean = gson.fromJson(request.toString(), new TypeToken<List<MessageBean>>() {
			}.getType());
			this.sendEmail(msgBean);
			logger.info("Exiting processInputRequest method");

		} catch (MailException e) {
			logger.error("Exception occured in processInputRequest method : " + e.getMessage());
		}

	}

	public void processInputJmsRequest(String request) {
		// TODO Auto-generated method stub
		logger.info("Entering processInputJmsRequest method");
		
		try {
			logger.info("Sending a JMS message.");
	        jmsTemplate.convertAndSend("sampleQueue", request);
			logger.info("Exiting processInputJmsRequest method");

		} catch (MailException e) {
			logger.error("Exception occured in processInputRequest method : " + e.getMessage());
		}

	}

}
