package withBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateProject {
	
	@Test
	public void createProject() {
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("createdBy", "Saran");
		jsonObj.put("createdOn", "11-02-2022");
		jsonObj.put("projectName", "Maczaki");
		jsonObj.put("status", "Done");
		jsonObj.put("teamSize", 5);
		
		given()
		.body(jsonObj)
		.contentType(ContentType.JSON)
		.when()
		.post("http://localhost:8084/addProject")
		.then()
		.assertThat().statusCode(201)
		.assertThat().contentType("application/json")
		.log().all();
		
	}

}
