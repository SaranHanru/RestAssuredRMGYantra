package com.CoopsAPI.authentication;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CoopsApiUnlockTheBarn {
	@Test
	public void oauth2AuthForUnlockTheBarn() {

	Response response = given()
			.formParam("client_id", "Chennai_SDET")
			.formParam("client_secret", "5b9c96e27a130f0d0ab08241cb3d5a83")
			.formParam("grant_type", "client_credentials")
			.formParam("redirect_uri", "https://example.com")
			.when()
			.post("http://coop.apps.symfonycasts.com/token");
	         response.prettyPrint();
			String accessToken = response.jsonPath().get("access_token");
			System.out.println(accessToken);
	
			given()
			.auth().oauth2(accessToken)
			.pathParam("USER_ID", "2819")
			.when()
			.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/barn-unlock")
			.then()
			.assertThat().time(Matchers.lessThan(2l),TimeUnit.SECONDS)
			.log().all();
	}
}
