package com.example.sb.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"com.example.sb.bdd"},
        monochrome = true,
        strict = true,
        plugin = {
                "pretty", "html:build/cucumber-reports"
        }
)
public class TestRunner {
}