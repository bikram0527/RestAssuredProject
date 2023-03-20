package com.employeeApi_TestCase;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.employeeApi_base.TestBaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class T006_DataDrivenApproach_DataFromExcelFile extends TestBaseClass {
		
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
		String [][] getEmpData() throws IOException
		{
			//step 1:create an excel file for data and copy it in the project.
			//step 2:copy the XLUtils.java file in your project. Its ready made code from net. 
			         //IMPORTANT NOTE for step 2 is : do not change its name because we need to use methods that are inbuilt under this XLUtils.java file by pressing dot after writing XLUtilsin our code. 
		    //step 3:create a path variable for the file by right click on excel file and properties and copy the path 
			// step 4:write XLUtils and press dot and use row count and column count method.
			
		  // actual path is this-->  "C:\\QA\\Selenium_Workspace\\Project_Rest_Assured\\src\\test\\java\\com\\employeeApi_utilities\\RestAssuredProjectData.xlsx";
		  //but we use this property notation for path before source.and reverse all the slashes in the path. use / slash....instead of \ slash.
		  
			String path=System.getProperty("user.dir")+"/src/test/java/com/employeeApi_utilities/RestAssuredProjectData.xlsx";
			
	      int rowcount = XLUtils.getRowCount(path, "Sheet1");
		  int colcount= XLUtils.getCellCount(path, "Sheet1", 0);
		  
		 String empData[][]=new String [rowcount][colcount];
		    
		    for(int i=1;i<rowcount;i++) {
		 	   for(int j=0;j<colcount;j++) {
		 		  empData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			        }
		        }
		    return(empData);
		
		}	

}
