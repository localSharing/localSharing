package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import pandha.swe.localsharing.model.enums.Geschlecht;

public class BenutzerDTO {

	private Long id;

	@NotNull
	private Geschlecht geschlecht;

	@Email
	private String email;

	@Size(min = 2, max = 20, message = "Mindestens 2 Zeichen, maximal 20")
	private String vorname;

	@Size(min = 2, max = 20, message = "Mindestens 2 Zeichen, maximal 20")
	private String nachname;

	@Size(min = 2, max = 20, message = "Mindestens 2 Zeichen, maximal 20")
	private String strasse;

	@Pattern(regexp = "[0-9]{1,4}[a-z]?", message = "Mindestens 1 Zeichen, maximal 5")
	private String hausnummer;

	@Pattern(regexp = "[0-9]{5}", message = "PLZ muss 5 Ziffern lang sein")
	private String plz;

	@Size(min = 2, max = 20, message = "Mindestens 2 Zeichen, maximal 20")
	private String stadt;

	@Size(min = 3, max = 20, message = "Mindestens 3 Zeichen, maximal 20")
	private String telefonNummer;

	public BenutzerDTO(Geschlecht geschlecht, String email, String vorname,
			String nachname, String strasse, String hausnummer, String plz,
			String stadt, String telefonNummer) {
		this.geschlecht = geschlecht;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.stadt = stadt;
		this.telefonNummer = telefonNummer;
	}

	public BenutzerDTO() {

	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getTelefonNummer() {
		return telefonNummer;
	}

	public void setTelefonNummer(String telefonNummer) {
		this.telefonNummer = telefonNummer;
	}

	@Override
	public String toString() {
		return "BenutzerRegisterDto [geschlecht=" + geschlecht + ", email="
				+ email + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", strasse=" + strasse + ", hausnummer=" + hausnummer
				+ ", plz=" + plz + ", stadt=" + stadt + ", telefonNummer="
				+ telefonNummer + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
