package com.RMGYantra.responseValidation;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import pojoLibraries.Libraries;

public class HancrestValidationTest {

	@Test
	public void hanscrestTimevalidation() {
		
	Libraries lib=new Libraries("Rahane", "Rajasthan_Royals", "First_Champions", 11);
	given()
	.body(lib)
	.contentType(ContentType.JSON)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.assertThat().time(Matchers.lessThan(5000l),TimeUnit.MILLISECONDS)//5000l - Passed //300l - passed
		.assertThat().body("projectName", Matchers.equalTo("Rajasthan_Royals"))		
		.log().all();
	}
}
