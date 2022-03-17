package withoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UpdateAProject {
	
	@Test
	public void updateProject() {
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("createdBy", "Saravanan");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonObj);
		Response response = reqSpec.put("http://localhost:8084/projects/TY_PROJ_001");
		ValidatableResponse res = response.then();
		res.assertThat().statusCode(200);
		res.assertThat().contentType("application/json");
		res.log().all();
	}

}
