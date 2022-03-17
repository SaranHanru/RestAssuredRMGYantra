package alts;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;

public class UpdateProjUsingJsonFile {
	
	@Test
	public void updateProjUsingJsonFile() {
		
		File file=new File("./Put.json");
		given()
		  .body(file)
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
