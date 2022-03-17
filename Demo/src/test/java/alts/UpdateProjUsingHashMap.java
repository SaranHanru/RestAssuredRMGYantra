package alts;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Random;

public class UpdateProjUsingHashMap {
	
	@Test
	public void updateProjUsingHashMap() {
		
		Random r=new Random();
		int n = r.nextInt();
		HashMap map=new HashMap();
		map.put("createdBy", "Hanru");
		map.put("createdOn", "11-02-2022");
		map.put("projectName", "MissionJavid" +n);
		map.put("status", "Started");
		map.put("teamSize", 3);
		given()
		  .body(map)
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
