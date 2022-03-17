package withoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateProject {
	
	@Test
	public void createoroject() {
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("projectId", "TYSS");
		jsonObj.put("projectName", "RMGYantra");
		jsonObj.put("teamSize", 3);
		jsonObj.put("createdBy", "Hanru");
		jsonObj.put("createdOn", "10-02-2022");
		jsonObj.put("status", "Well Done");
		
		
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.contentType(ContentType.JSON);
		reqSpec.body(jsonObj);
		Response response=reqSpec.post("http://localhost:8084/addProject");
		ValidatableResponse vRes=response.then();
		vRes.assertThat().statusCode(201);
		vRes.assertThat().contentType("application/json");
		vRes.log().all();		
	}

}
