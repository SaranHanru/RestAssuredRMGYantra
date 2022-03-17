package com.RMGYantra.parameters;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class Parameters {
	@Test
	public void parameters() {
		String ProjId="TY_PROJ_808";
		given()
		.pathParam("{ProjId}", ProjId)
		.when()
		.get("http://localhost:8084/projects/{ProjId}")
		.then()
		.assertThat().statusCode(200)
		.assertThat().time(Matchers.lessThan(500l),TimeUnit.SECONDS)
		.log().all();
		}

}
