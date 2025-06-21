package org.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.report.GenerateReportClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\Arun\\eclipse-workspace\\HRIS_Application_Automation\\src\\test\\resources\\Feature\\FooterNav.feature",
glue = {"org.step","org.hooks"}, 
monochrome = true, 
dryRun = false, 
snippets = SnippetType.CAMELCASE,
//tags="@SignIn07",
plugin = {
		"html:target\\HTMLReport", "junit:target\\xmlReport.xml", "json:target\\JsonReport.json" })

public class RunnerClass {
	
	@AfterClass
	public static void tc01() {
		GenerateReportClass.toGenerate("target\\JsonReport.json");
	}
}
