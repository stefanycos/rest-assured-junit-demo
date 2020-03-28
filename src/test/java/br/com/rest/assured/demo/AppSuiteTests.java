package br.com.rest.assured.demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.rest.assured.demo.config.ApplicationProperties;
import br.com.rest.assured.demo.report.BaseExtendReport;
import br.com.rest.assured.demo.report.ExtendReportInstance;
import br.com.rest.assured.demo.tests.FailTests;
import br.com.rest.assured.demo.tests.RegisterTests;
import br.com.rest.assured.demo.tests.UserTests;
import io.restassured.RestAssured;

@RunWith(Suite.class)
@SuiteClasses({ UserTests.class, RegisterTests.class, FailTests.class })
public class AppSuiteTests {

	private static final ApplicationProperties PROPERTIES = new ApplicationProperties();
	private static BaseExtendReport report = new BaseExtendReport(PROPERTIES, ExtendReportInstance.getInstance());
	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = PROPERTIES.getProperty("test.base.url");
	}

	@AfterClass
	public static void tearDown() {
		report.getExtent().flush();
	}

}
