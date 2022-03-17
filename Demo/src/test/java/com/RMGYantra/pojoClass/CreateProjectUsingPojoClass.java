package com.RMGYantra.pojoClass;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import pojoLibraries.Libraries;

public class CreateProjectUsingPojoClass {
	
	@Test
	public void createUsingPojoClass() {
		Libraries lib=new Libraries("Luffy", "One-Piece", "Completed", 5);
		
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
		System.out.println(lib.createdBy+"--------------------");
	}

}
