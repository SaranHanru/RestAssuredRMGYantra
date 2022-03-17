package withBDD;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UpdateProjectwithBDD {
	
	@Test
	public void updateProjWithBDD() {
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("createdBy", "Saran");
		
		given()
		.body(jsonObj)
		.contentType(ContentType.JSON)
		.when()
		.put("http://localhost:8084/projects/TY_PROJ_603")
		.then()
		.assertThat().statusCode(200)
		.and()
		.assertThat().contentType("application/json")
		.and()
		.log().all();
	}

}
