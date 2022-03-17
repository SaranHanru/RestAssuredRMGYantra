package com.RMGYantra.pojoClass;


import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pojoLibraries.Libraries;

public class CreateMultipleProjectsUsingPojoClass {

	@DataProvider()
	public Object[][] provideData() {
		Object[][] objArray=new Object[4][4];
		
		objArray[0][0]="MS-Dhoni";
		objArray[0][1]="CSk";
		objArray[0][2]="Champions";
	    objArray[0][3]=11;
	    
	    objArray[1][0]="Rohit";
		objArray[1][1]="MI";
		objArray[1][2]="Former-Champions";
	    objArray[1][3]=11;
	    
	    objArray[2][0]="Virat Kohli";
		objArray[2][1]="RCB";
		objArray[2][2]="Take my Heart";
	    objArray[2][3]=11;
	    
	    objArray[3][0]="Risabh Pant";
		objArray[3][1]="DCapitals";
		objArray[3][2]="Challengers";
	    objArray[3][3]=11;
	    
	    return objArray;
	}
	
	@Test(dataProvider = "provideData")
	public void createMultipleProjectsByPojo(String createdBy,String projectName,String status,int teamSize) {
    Libraries lib=new Libraries(createdBy,  projectName, status, teamSize);	
		given() 
		.contentType(ContentType.JSON)
		.body(lib)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.assertThat().statusCode(201)
		.and()
		.assertThat().contentType("application/json")
		.and()
		.log().all();
		
	}

}
