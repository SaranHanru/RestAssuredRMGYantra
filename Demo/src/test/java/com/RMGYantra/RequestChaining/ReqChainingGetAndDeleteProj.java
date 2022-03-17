package com.RMGYantra.RequestChaining;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqChainingGetAndDeleteProj {
	
	@Test
	public void getAndDeleteReqChain() {
		
		Response response = when()
		.get("http://localhost:8084/projects");
		response.then()
		.assertThat().statusCode(200)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(300l),TimeUnit.SECONDS);
		String responseProjId = response.jsonPath().get("[9].projectId");
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
		/*response.then()
		.assertThat().statusCode(200)
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(500l),TimeUnit.SECONDS)
		.log().all();*/
		/*delete("http://localhost:8084/projects/projectId")
		.then()
        .assertThat().contentType("application/json")
        .and()
        .log().all();*/
	}

}
