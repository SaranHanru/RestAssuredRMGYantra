package com.RMGYantra.authentication;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class BearerTokenToListAllReposInGitHub {
	
	@Test
	public void bearerTokenToListAllRepo() {
		JSONObject jObj=new JSONObject();
		jObj.put("name", "BearertokenauthenticationRepo");
		jObj.put("description", "Test repo");
		
		given()
		.body(jObj)
		.contentType(ContentType.JSON)
		.auth().oauth2("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")
		.when()
		.post("https://api.github.com/user/repos")
		.then()
		.assertThat().statusCode(201)
		.time(Matchers.lessThan(3l),TimeUnit.SECONDS)
		.log().all();
	}

}
