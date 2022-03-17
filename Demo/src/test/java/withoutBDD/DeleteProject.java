package withoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DeleteProject {
	
	@Test
	public void deleteProject() {
		Response response = RestAssured.delete("http://localhost:8084/projects/TY_PROJ_602");
		
		
		 ValidatableResponse res = response.then(); 
		 res.assertThat().statusCode(204);
		 res.assertThat().contentType("application/json");
		 res.log().all();
	
		
		
	}

}
