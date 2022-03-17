package com.RMGYantra.responseValidation;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StaticValidateResponse {
	
	@Test
	public void staticValidateResponse() {
		String exceptedName="CSk";
		String baseUrl="http://localhost:8084";
		 Response response = when()
		.get(baseUrl+"/projects");
		response.then().assertThat().statusCode(200).log().all();
        String actualName = response.jsonPath().get("[5].projectName");
        System.out.println(actualName);
        Assert.assertEquals(actualName, exceptedName);
		}

}
