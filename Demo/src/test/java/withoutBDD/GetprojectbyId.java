package withoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GetprojectbyId {
	
	@Test
	public void getAllProjects() {
		Response response = RestAssured.get("http://localhost:8084/projects/TY_PROJ_603");
		System.out.println("AsString" +response.asString());
		System.out.println("PrettyString" +response.asPrettyString());
		System.out.println("ContentType" +response.getContentType());
		System.out.println("StatusCode" +response.getStatusCode());
		System.out.println("Time" +response.getTime());
		System.out.println("Body" +response.getBody());
		System.out.println("SessionId" +response.getSessionId());
		
		ValidatableResponse vRes=response.then();
		vRes.assertThat().statusCode(200);
		vRes.assertThat().contentType("application/json");
		vRes.log().all();
		}

}
