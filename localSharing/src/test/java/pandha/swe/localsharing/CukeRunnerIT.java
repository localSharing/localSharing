package pandha.swe.localsharing;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

features = { "src/test/java/pandha/swe/localsharing/cucumber/feature" },

format = { "pretty",

"html:target/cucumber-html-report",

"junit:target/cucumber-junit-report/allcukes.xml", "json:target/cucumber.json"

}

)
public class CukeRunnerIT {

}