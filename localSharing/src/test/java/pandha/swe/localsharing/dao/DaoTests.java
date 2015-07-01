package pandha.swe.localsharing.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAusleihartikelDAOImpl.class, TestBenutzerDAOImpl.class,
		TestFileUploadDAOImpl.class, TestHilfeleistungDAOImpl.class,
		TestTauschartikelDAOImpl.class })
public class DaoTests {

}
