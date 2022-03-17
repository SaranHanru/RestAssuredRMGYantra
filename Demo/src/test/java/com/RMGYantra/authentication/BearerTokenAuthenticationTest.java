package com.RMGYantra.authentication;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class BearerTokenAuthenticationTest {

	@Test
	public void bearerTokenAuth() {
		given()
		.auth().oauth2("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")
		.when()
		.get("https://api.github.com/user/repos")
		.then()
        .assertThat().statusCode(200)
        .log().all();
	}
}
//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