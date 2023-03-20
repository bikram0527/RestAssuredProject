package com.employeeApi_TestCase;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi_base.TestBaseClass;
import com.employeeApi_utilities.Utility_Class_For_RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class T003_Post_CresteNewRecordInDatabase extends TestBaseClass {
	// this is not working because of web site
	
	
//Note1://Utility_Class_For_RestAssured utl =new Utility_Class_For_RestAssured();// we donot need this bcoz we make the functions static
	 
	
 //step 1 :create three variables for name age and salary. 
	String eName= Utility_Class_For_RestAssured.empName();
	String eJob=Utility_Class_For_RestAssured.empjob();


	@BeforeClass
	public void getAllEmployee() throws InterruptedException {
		
		
		RestAssured.baseURI="https://reqres.in/api";
		 httpRequest =RestAssured.given();
		 
		 
 //Step:2 make object for JSONOBject and use put command for entry. then add header and jsonBody with that. 
		JSONObject js =new JSONObject();
		
		js.put("name", eName);
		js.put("job", eJob);
		
		//now adding header to the message
		httpRequest.header("Content-Type", "application/json");
		
		// now adding json Body with that request and convert into json string
		httpRequest.body(js.toJSONString());
		
		
		 resp=  httpRequest.request(Method.POST,"/users");
		 Thread.sleep(3000); 
		}
	
	@Test
	public void verifyResponseBody() {
		String responseBody = resp.getBody().asString();
		System.out.println("Response is ="+responseBody);
		Assert.assertEquals(responseBody.contains(eName), true);
		Assert.assertEquals(responseBody.contains(eJob), true);		 
	}
	
	@Test
	public void checkStatusCode() {
		System.out.println("Status code for creating new employee is "+resp.getStatusCode());
	    Assert.assertEquals(resp.getStatusCode(), 201);
	}


}
