package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.enums.Geschlecht;

public class BenutzerRegisterDTO extends BenutzerDTO {

	@Size(min = 8, max = 20, message = "Mindestens 8 Zeichen, maximal 20")
	private String password1;

	@Size(min = 8, max = 20, message = "Mindestens 8 Zeichen, maximal 20")
	private String password2;

	public BenutzerRegisterDTO(Geschlecht geschlecht, String password1,
			String password2, String email, String vorname, String nachname,
			String strasse, String hausnummer, String plz, String stadt,
			String telefonNummer) {
		super(Boolean.TRUE, geschlecht, email, vorname, nachname, strasse,
				hausnummer, plz, stadt, telefonNummer);
		this.password1 = password1;
		this.password2 = password2;
	}

	public BenutzerRegisterDTO() {

	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@Override
	public String toString() {
		return "BenutzerRegisterDTO [password1=" + password1 + ", password2="
				+ password2 + "]";
	}

}
