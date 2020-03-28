package br.com.rest.assured.demo.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Rule;
import org.junit.Test;

import br.com.rest.assured.demo.helpers.JsonBuilder;
import br.com.rest.assured.demo.report.ExtendReportInstance;
import br.com.rest.assured.demo.watcher.ExecutionWatcher;
import io.restassured.http.ContentType;

public class UserTests {

	private static final String PATH = "users";
	private static final String PATH_VARIABLE = "users/2";
	private static final String PATH_VARIABLE_NOT_FOUND = "users/23";

	@Rule
	public ExecutionWatcher executionWatcher = new ExecutionWatcher(ExtendReportInstance.getInstance());

	@Test
	public void createUser_shouldMatchSchema() throws Exception { // @formatter:off
		
		given()
			.contentType(ContentType.JSON)
			.body(JsonBuilder.readJsonFile("create-user-success.json"))
		.when()
			.post(PATH)
		.then()
			.assertThat()
				.body(matchesJsonSchemaInClasspath("json-files/create-user-schema.json"));
	}
	
	@Test
	public void searchUser_shouldReturn_EmptyBody() throws Exception {
		given()	.when()
			.get(PATH_VARIABLE_NOT_FOUND)
		.then()
			.assertThat()
				.statusCode(404)
				.body(equalTo("{}"));
	}
	
	@Test
	public void updateUser_shouldMatchValue() throws Exception {
		given()
			.contentType(ContentType.JSON)
			.body(JsonBuilder.readJsonFile("update-user-success.json"))
		.when()
			.put(PATH_VARIABLE)
		.then()
			.assertThat()
				.body("name", equalTo("Rachel Green Geller"));
	}
	
	@Test
	public void getUsers_shouldMatchValue() {
		given()
			.param("page", 2)
		.when()
			.get(PATH)
		.then()
			.assertThat()
				.body(containsString("total_pages"));
				
	}
	
}
