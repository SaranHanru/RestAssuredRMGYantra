package com.RMGYantra.authentication;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class OauthAuthenticationForCoop {
	
	@Test
	public void oauth2Auth() {
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
		/*
		 * given() .auth().oauth2(accessToken) .pathParam("USER_ID","2819") .when()
		 * .post("http://coop.apps.symfonycasts.com/api/{USER_ID}/chickens-feed")
		 * .then() .assertThat().time(Matchers.lessThan(2l),TimeUnit.SECONDS);
		 */
			/*
			 * given() .auth().oauth2(accessToken) .pathParam("USER_ID", "2819") .when()
			 * .post("http://coop.apps.symfonycasts.com/api/{USER_ID}/toiletseat-down")
			 * .then() .assertThat().time(Matchers.lessThan(2l),TimeUnit.SECONDS)
			 * .log().all();
			 */
		given()
		.auth().oauth2(accessToken)
		.pathParam("USER_ID", "2819")
		.when()
		.post("http://coop.apps.symfonycasts.com//api/{USER_ID}/eggs-collect")
		.then()
		.assertThat().time(Matchers.lessThan(2l),TimeUnit.SECONDS)
		.log().all();
	}

}
