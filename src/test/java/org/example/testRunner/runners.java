package org.example.testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions
        (
                features ="src/main/resources/features" ,
                glue = "org.example.stepDefs" ,
                plugin = {      "pretty",
                        "html:target/cucumber.html",
                        "json:target/cucumber.json",
                      //  "junit:target/cukes.xml" ,
                     //   "rerun:target/rerun.txt"
                } ,
                tags = "@test",
                monochrome = true

        )
public class runners extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
