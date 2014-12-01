package pandha.swe.localsharing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datumsumwandler {
	
	public static Date stringToDate(String dateAsString) {
		Date dateAsDate = new Date();
		try {
			dateAsDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateAsDate;
	}

	public static String dateToString(Date dateAsDate) {
		String dateAsString = new SimpleDateFormat("dd.MM.yyyy")
				.format(dateAsDate);
		return dateAsString;
	}

}
