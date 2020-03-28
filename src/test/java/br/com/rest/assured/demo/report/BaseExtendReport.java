package br.com.rest.assured.demo.report;

import java.time.LocalDate;
import java.time.ZoneOffset;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import br.com.rest.assured.demo.config.ApplicationProperties;

public class BaseExtendReport {

	private ApplicationProperties properties;

	private ExtentHtmlReporter htmlReporter;
	private ExtentReports extent;

	public BaseExtendReport(ApplicationProperties properties, ExtentReports extent) {
		this.properties = properties;
		this.extent = extent;
		initializeReport();
	}

	public void initializeReport() {
		String reportPath = properties.getProperty("test.report.path") + getReportName();
		htmlReporter = new ExtentHtmlReporter(reportPath);

		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report Results");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	private String getReportName() {
		LocalDate current = LocalDate.now();
		long millis = current.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();

		return "tests-results-report " + millis + ".html";
	}

	public ExtentReports getExtent() {
		return extent;
	}

}
