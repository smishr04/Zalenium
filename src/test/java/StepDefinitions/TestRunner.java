package StepDefinitions;

import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//@RunWith(CucumberWithSerenity.class)
@RunWith(Cucumber.class)
@CucumberOptions(strict = false, 
features = "Features",
glue = "StepDefinitions",
plugin = {"json:target/cucumber.json","html:target/cucumber","rerun:target/rerun.txt"}, 
tags = {"@Regression1"})

public class TestRunner {

}
