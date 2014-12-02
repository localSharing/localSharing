package pandha.swe.localsharing;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

features = { "src/test/java/pandha/swe/localsharing/cucumber/feature" },

format = { "pretty",

"html:target/cucumber-html-report", "json:target/cucumber.json",

"junit:target/cucumber-junit-report/allcukes.xml"

}

)
public class CukeRunnerIT {

}