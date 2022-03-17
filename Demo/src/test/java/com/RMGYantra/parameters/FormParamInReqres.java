package com.RMGYantra.parameters;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class FormParamInReqres {
	
	@Test
	public void formParam() {
		given()
		.formParam("name", "George")
		.formParam("job", "leader")
		.when()
		.post("https://reqres.in/api/users")
		.then()
		.assertThat().time(Matchers.lessThan(5l),TimeUnit.SECONDS)
		.assertThat().statusCode(200)
		.log().all();
	}

}
