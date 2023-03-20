package com.employeeApi_utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class Utility_Class_For_RestAssured {
	// note  here we need to remember RandomStringUtils.randomAlphabetic(int). just type rand and then press ctrl+space togather
	
	
	public static String empName() {	
		String a=RandomStringUtils.randomAlphabetic(5);
		return(a);		
	 }
	
	public static String empjob() {       
       return(RandomStringUtils.randomAlphabetic(3));       	
     }
	
	
	
	 //public static String empSalary() {      // this is if we want to create integer variable like age , salary, etc etc
	    //return(RandomStringUtils.randomNumeric(3));
	
	
}
