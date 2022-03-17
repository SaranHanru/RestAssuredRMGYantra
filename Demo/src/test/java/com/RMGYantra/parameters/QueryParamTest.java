package com.RMGYantra.parameters;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class QueryParamTest {

	@Test
	public void queryParamTest() {
		given()
		.queryParam("status", "pending")//available
		.when()
		.get("https://petstore.swagger.io/v2/pet/findByStatus")
		.then()
		.assertThat().time(Matchers.lessThan(300l),TimeUnit.SECONDS)
		.assertThat().statusCode(200)
		.assertThat().log().all();
		}
}
