package pandha.swe.localsharing.util;

public class VornameAngebotsseiteWandler {
	
	public static String erzeugeVornameFuerAngebotsseite(String vorname) {
		if (!(vorname.endsWith("s") || vorname.endsWith("x"))) {
			vorname = vorname + "s";
		}
		return vorname;
	}

}
