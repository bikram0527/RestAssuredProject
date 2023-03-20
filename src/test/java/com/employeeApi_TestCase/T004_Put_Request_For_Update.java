package com.employeeApi_TestCase;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi_base.TestBaseClass;
import com.employeeApi_utilities.Utility_Class_For_RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class T004_Put_Request_For_Update extends TestBaseClass{

	String eName= Utility_Class_For_RestAssured.empName();
	String eJob=Utility_Class_For_RestAssured.empjob();


	@BeforeClass
	public void getAllEmployee() throws InterruptedException {
		RestAssured.baseURI="https://reqres.in";
		 httpRequest =RestAssured.given();
		
 //Step:2 make object for JSONOBject and use put command for entry. then add header and jsonBody with that. 
		JSONObject js =new JSONObject();
		
		js.put("name", eName);
		js.put("job", eJob);
		
		//now adding header to the message
		httpRequest.header("Content-Type", "application/json");
		
		// now adding json Body with that request and convert into json string
		httpRequest.body(js.toJSONString());
		
		
		
		 resp=  httpRequest.request(Method.PUT,"/api/users/"+empID);
		 Thread.sleep(3000); 
		}
	
	@Test
	public void checkStatusCode() {
		System.out.println("Status code for creating new employee is "+resp.getStatusCode());
	    Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test
	public void verifyResponseBody() {
		String responseBody = resp.getBody().asString();
		System.out.println("Response is ="+responseBody);
		Assert.assertEquals(responseBody.contains(eName), true);
		Assert.assertEquals(responseBody.contains(eJob), true);		 
	}

}
