package com.RMGYantra.authentication;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PrimitiveValueAuthentication {
	@Test
	public void primitiveAuth()
	{
		given()
		.auth().preemptive().basic("rmgyantra", "rmgy@9999")
		.when()
		.get("http://localhost:8084/login")
		.then()
		.assertThat().statusCode(202)
		.assertThat().contentType("application/json")
		.assertThat().time(Matchers.lessThan(2l),TimeUnit.SECONDS)
        .and()
        .log().all();
	}
}
