package com.employeeApi_TestCase;

import org.json.simple.JSONObject;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employeeApi_base.TestBaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class T005_DataDrivenTest_hardCodedData extends TestBaseClass {
	
	@Test(dataProvider= "empDataProvider")
	public void getAllEmployee(String eName, String eAge) throws InterruptedException {
		
		
		RestAssured.baseURI="https://reqres.in/api";
		 httpRequest =RestAssured.given();
		 
		 
 //Step:2 make object for JSONOBject and use put command for entry. then add header and jsonBody with that. 
		JSONObject js =new JSONObject();
		
		js.put("name", eName);
		js.put("job", eAge);
		
		//now adding header to the message
		httpRequest.header("Content-Type", "application/json");
		
		// now adding json Body with that request and convert into json string
		httpRequest.body(js.toJSONString());
		
		
		 resp=  httpRequest.request(Method.POST,"/users");
		 Thread.sleep(3000); 
		 
		    String responseBody = resp.getBody().asString();
			System.out.println("Response is ="+responseBody);
			Assert.assertEquals(responseBody.contains(eName), true);
			Assert.assertEquals(responseBody.contains(eAge), true);	
			
			System.out.println("Status code for creating new employee is "+resp.getStatusCode());
		    Assert.assertEquals(resp.getStatusCode(), 201);
		 
		}
	
	
	@DataProvider(name ="empDataProvider")
	String [][] getEmpData()
	{
		String empData[][]= { {"abc","clerck"},{"def","LEAD"},{"xyz","supervisor"} };
		return(empData);
	
	}	
	
	
	
	



	

}
