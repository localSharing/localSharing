package pandha.swe.localsharing.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AusleiharikelServiceImplTest.class,
		TestTauschartikelServiceImpl.class, HilfeleistungServiceImpl.class })
public class ServiceUnitTest {

}
