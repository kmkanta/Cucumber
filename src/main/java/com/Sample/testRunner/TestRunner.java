package com.Sample.testRunner;
import cucumber.api.CucumberOptions;


@CucumberOptions(features = { "classpath:com.Sample.feature.sample.feature" }, glue = {
		"classpath:com.Sample.stepdefs"},
		//"classpath:com.cucumber.framework.helper" }, 
		plugin = {"html:target/cucumber-html-report"})
public class TestRunner {
	

}
