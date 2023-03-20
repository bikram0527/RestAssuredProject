package com.employeeApi_TestCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi_base.TestBaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class T002_Get_Sigle_Employee_Record extends TestBaseClass{
	@BeforeClass
	public void getAllEmployee() throws InterruptedException {
		
		RestAssured.baseURI="https://reqres.in";
		 httpRequest =RestAssured.given();
		 resp=  httpRequest.request(Method.GET,"/api/users/"+empID);
		 
		 Thread.sleep(3000); 
		}
	
	@Test
	public void checkResponseBody() {
		 String responseBody = resp.getBody().asString();
		 System.out.println("Response is ="+responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);	 
	}
	
	
	@Test
	public void checkStatusCode() {
	int statusCode =resp.getStatusCode();
	System.out.println("Status code is ="+statusCode);
	Assert.assertEquals(statusCode, 200);
	}
	
	@Test//status line verification
	public void CheckStatusLine() {
		String statusLine=resp.getStatusLine();
		System.out.println("Status Line is : "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void verifyElementsOfHeader() {
		String ContentType= resp.header("Content-Type");
 		String Server= resp.header("Server");
 		System.out.println("Content-Type is "+ContentType);
 		System.out.println("Connection is "+Server);
 		// now their verification
 		Assert.assertEquals(ContentType ,"application/json; charset=utf-8");
 		Assert.assertEquals(Server,"cloudflare");
	}
	
	//@Test // very important.....is type casting process // this is not on this website
	public void verifyContactLength() {
		String contactLength =resp.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contactLength)<500);
		
	}
	
	

}
