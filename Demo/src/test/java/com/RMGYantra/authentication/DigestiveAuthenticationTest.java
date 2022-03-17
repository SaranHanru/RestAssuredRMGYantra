package com.RMGYantra.authentication;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class DigestiveAuthenticationTest {
	
	@Test
	public void directAuth() {
		given()
		.auth().digest("rmgyantra", "rmgy@9999")
		.when()
		.get("http://localhost:8084/login")
		.then()
		.assertThat().statusCode(202)
		.assertThat().contentType("application/json")
		.assertThat().time(Matchers.lessThan(2l),TimeUnit.SECONDS)
		.log().all();
	}

}
