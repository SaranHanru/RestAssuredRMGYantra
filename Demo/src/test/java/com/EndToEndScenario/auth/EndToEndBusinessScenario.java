package com.EndToEndScenario.auth;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoLibraries.Libraries;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EndToEndBusinessScenario {
	
	@Test
	public void endToEndScenario() throws SQLException {
		//Creating a body using pojo class
		Random random=new Random();
		int randomNum = random.nextInt();
		String expectedProjName="Infinite Void"+randomNum;
		String expectedStatus="Completed";
		//String expectedId="TY_PROJ_"+" ";
		Libraries lib=new Libraries("Gojo_Satoru", expectedProjName, expectedStatus, 3);
		        
                //Giving preconditions for the post request
				Response response = given()
				.body(lib)
				.contentType(ContentType.JSON)
				.when()
				.post("http://localhost:8084/addProject");
				//Capturing the project name and ID from the response received
				String responseProjName = response.jsonPath().get("projectName");
				String responseId = response.jsonPath().get("projectId");
				
				System.out.println(responseProjName);
				System.out.println(responseId);
				//Rest Assured Assertions waiting for validating responses
				response.then()
				.assertThat().statusCode(201)
				.assertThat().contentType(ContentType.JSON)
				.assertThat().time(Matchers.lessThan(4l),TimeUnit.SECONDS)
				.log().all();
				
				//JDBC : Register the JDBC Driver
				Driver driver = new Driver();
				DriverManager.registerDriver(driver);
				
				//connect to mySql database
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root", "root");
				
				//create a Statement
				Statement statement = connection.createStatement();
				
				//Execute select Query and get all the projects from the DB
				ResultSet result = statement.executeQuery("select * from project");
				String dataBaseProjName=null;
				String dataBaseProjStatus =null;
				String dataBaseId=null;
				while(result.next()) {
					if(result.getString(4).equals(expectedProjName)) {
						dataBaseProjName=result.getString("project_name");
						dataBaseId=result.getString("project_id");
						dataBaseProjStatus=result.getString("status");
						break;
					}
				}
				System.out.println(dataBaseId);
				//Close the connection
				connection.close();
				
				Assert.assertEquals(responseProjName, expectedProjName);
				Assert.assertEquals(dataBaseProjName,expectedProjName );
				Assert.assertEquals(dataBaseProjStatus,expectedStatus );
				//Assert.assertEquals(responseId, expectedId);
				//Assert.assertEquals(dataBaseId, expectedId);				
	}

}
