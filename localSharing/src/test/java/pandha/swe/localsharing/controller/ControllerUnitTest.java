package pandha.swe.localsharing.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FileControllerTest.class, LoginControllerTest.class,
		ProfilControllerTest.class, RegisterControllerTest.class,
		StartPageControllerTest.class, AngebotControllerTest.class })
public class ControllerUnitTest {

}
