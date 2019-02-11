package com.target;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
 

@RunWith(MockitoJUnitRunner.class)
public class NotificationControllerTest  {
	
	
	
	

	
	
	@Test
	public void processIncomingForValidResource() throws Exception {
		String input = "[{\"from\":\"umesha058@gmail.com\",\"to\":\"umesha058@gmail.com\",\"subject\":\"test\",\"text\":\"1 st consumer\"},{\"from\":\"umesha058@gmail.com\",\"to\":\"bhavanabapu@gmail.com\",\"subject\":\"test\",\"text\":\"2nd consumer\"}]";
		 RequestSpecification request = RestAssured.given();
		 
		 request.header("content-type", "application/json");
		 request.body(input);
		 com.jayway.restassured.response.Response response = request.post("http://localhost:8081/processemail");
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("stats : " +statusCode );
	
		 Assert.assertEquals(statusCode, 200);
		 
		}
	
	@SuppressWarnings("deprecation")
	@Test
	public void processIncomingForInValidResource() throws Exception {
		String input = "[{\"from\":\"umesha058@gmail.com\",\"to\":\"umesha058@gmail.com\",\"subject\":\"test\",\"text\":\"1 st consumer\"},{\"from\":\"umesha058@gmail.com\",\"to\":\"bhavanabapu@gmail.com\",\"subject\":\"test\",\"text\":\"2nd consumer\"}]";
		 RequestSpecification request = RestAssured.given();
		 
		 //request.header("content-type", "application/json");
		 request.body(input);
		 com.jayway.restassured.response.Response response = request.post("http://localhost:8081/processemail");
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("stats : " +statusCode );
	
		 Assert.assertEquals(statusCode, 415);
		 
		}

	@Test
	public void processIncomingForValidJms() throws Exception {
		String input = "[{\"from\":\"umesha058@gmail.com\",\"to\":\"umesha058@gmail.com\",\"subject\":\"test\",\"text\":\"1 st consumer\"},{\"from\":\"umesha058@gmail.com\",\"to\":\"bhavanabapu@gmail.com\",\"subject\":\"test\",\"text\":\"2nd consumer\"}]";
		 RequestSpecification request = RestAssured.given();
		 
		 request.header("content-type", "application/json");
		 request.body(input);
		 com.jayway.restassured.response.Response response = request.post("http://localhost:8081/processjms");
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("stats : " +statusCode );
	
		 Assert.assertEquals(statusCode, 200);
		 
		}
	
	@Test
	public void processIncomingForInValidJms() throws Exception {
		String input = "[{\"from\":\"umesha058@gmail.com\",\"to\":\"umesha058@gmail.com\",\"subject\":\"test\",\"text\":\"1 st consumer\"},{\"from\":\"umesha058@gmail.com\",\"to\":\"bhavanabapu@gmail.com\",\"subject\":\"test\",\"text\":\"2nd consumer\"}]";
		 RequestSpecification request = RestAssured.given();
		 
		 
		 request.body(input);
		 com.jayway.restassured.response.Response response = request.post("http://localhost:8081/processj");
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("stats : " +statusCode );
	
		 Assert.assertEquals(statusCode, 404);
		 
		}


	
	
    
    
    
    
       
       

}
