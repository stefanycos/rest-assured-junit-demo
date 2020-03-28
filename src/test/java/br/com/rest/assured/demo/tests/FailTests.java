package br.com.rest.assured.demo.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Rule;
import org.junit.Test;

import br.com.rest.assured.demo.helpers.JsonBuilder;
import br.com.rest.assured.demo.report.ExtendReportInstance;
import br.com.rest.assured.demo.watcher.ExecutionWatcher;
import io.restassured.http.ContentType;

public class FailTests {
	
private static final String PATH = "register";
	
	@Rule
	public ExecutionWatcher executionWatcher = new ExecutionWatcher(ExtendReportInstance.getInstance());

	@Test
	public void systemRegistration_shouldMatchSchema() throws Exception { // @formatter:off
		
		given()
			.contentType(ContentType.JSON)
			.body(JsonBuilder.readJsonFile("system-register-success.json"))
		.when()
			.post(PATH)
		.then()
			.assertThat()
				.body(matchesJsonSchemaInClasspath("json-files/fail.system-register-schema.json"));
	}
	
	@Test
	public void systemRegistration_shouldReturnError() throws Exception {
		
		given()
			.contentType(ContentType.JSON)
			.body(JsonBuilder.readJsonFile("system-register-error.json"))
		.when()
			.post(PATH)
		.then()
			.assertThat()
				.statusCode(400)
				.body("error", equalTo("Wrong password"));
	}


}
