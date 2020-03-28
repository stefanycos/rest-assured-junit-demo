package br.com.rest.assured.demo.report;

import com.aventstack.extentreports.ExtentReports;

public class ExtendReportInstance {

	private static final ExtendReportInstance INSTANCE = new ExtendReportInstance();
	private ExtentReports extend = new ExtentReports();

	private ExtendReportInstance() {
	}

	public static ExtentReports getInstance() {
		return INSTANCE.extend;
	}
}
