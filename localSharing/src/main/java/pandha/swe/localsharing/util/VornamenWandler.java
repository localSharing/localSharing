package pandha.swe.localsharing.util;

public class VornamenWandler {
	
	public static String erzeugeVornameFuerAngebotsseite(String vorname) {
		if (!(vorname.endsWith("s") || vorname.endsWith("x"))) {
			vorname = vorname + "s";
		}
		return vorname;
	}

}
