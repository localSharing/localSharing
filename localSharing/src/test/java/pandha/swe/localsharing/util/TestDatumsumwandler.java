package pandha.swe.localsharing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TestDatumsumwandler {

	@Test
	public void testStringToDate() throws ParseException {

		String dateString = "12.12.2011";
		Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);

		Assert.assertEquals(date.getTime(),
				Datumsumwandler.stringToDate(dateString).getTime());

		String errorDateSting = "123.12.2011";
		Date errorDate = new Date();

		long diff = Datumsumwandler.stringToDate(errorDateSting).getTime()
				- errorDate.getTime();

		boolean passed = false;

		if (diff < 3000) {
			passed = true;
		}

		Assert.assertTrue(passed);
	}

	@Test
	public void testDateToString() throws ParseException {
		String dateString = "12.12.2011";
		Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);

		Assert.assertEquals(dateString, Datumsumwandler.dateToString(date));

	}

}
