package com.RMGYantra.responseValidation;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class DynamicValidateResponse {

	@Test
	public void dynamicValidateResponse() {
		String expectedProjName="One-Piece";
		String baseUrl="http://localhost:8084";
		Response response = when()
				.get(baseUrl+"/projects");
		response.then().log().all();
		Boolean flag= false;  
		String actualProjName = null;
		List<String> responseProjName = response.jsonPath().get("projectName");
		for (String singleProjName : responseProjName) {
			if(singleProjName.equals(expectedProjName)) {
				actualProjName=singleProjName;
				flag=true;
				break;
			}
		}
		System.out.println(response.getTime());
		Assert.assertEquals(actualProjName, expectedProjName);
	}
}
