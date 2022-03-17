package com.RMGYantra.RequestChaining;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoLibraries.Libraries;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class ReqChainingPostandGetAndDeleteReqTest {
	
	@Test
	public void reqChainingPostandDelete() {
		

		Libraries lib=new Libraries("Sathish", "MHA", "Processing", 10);
		Response response =given() 
		.body(lib)
		.contentType(ContentType.JSON)
		. when()
		.post("http://localhost:8084/addProject");
		response.then()
		.assertThat().time(Matchers.lessThan(300l),TimeUnit.SECONDS)
		.assertThat().statusCode(201)
		.assertThat().contentType("application/json")
		.log().all();
		String responseProjId = response.jsonPath().get("projectId");
		System.out.println("Response name for project"+responseProjId);
				given()
				.pathParam("projectId", responseProjId)
				.when()
				.delete("http://localhost:8084/projects/{projectId}")
				.then()
				.assertThat().statusCode(204)
		        .assertThat().contentType("application/json")
		        .and()
		        .log().all();
		
		
	}

}
