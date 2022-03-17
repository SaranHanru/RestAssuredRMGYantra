package withBDD;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteProject {
	
	@Test
	public void deleteProject() {
		
		when()
		       .delete("http://localhost:8084/projects/TY_PROJ_607")
		.then()
		        .assertThat().contentType("application/json")
		        .and()
		        .log().all();
		
	}

}
