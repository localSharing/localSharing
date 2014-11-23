package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import pandha.swe.localsharing.model.enums.Geschlecht;

public class BenutzerRegisterDTO {

	@NotNull
	private Geschlecht geschlecht;

	@Size(min = 8, max = 20)
	private String password1;

	@Size(min = 8, max = 20)
	private String password2;

	@Email
	@NotEmpty
	private String email;

	@Size(min = 2, max = 20)
	private String vorname;

	@Size(min = 2, max = 20)
	private String nachname;

	@Size(min = 2, max = 20)
	private String strasse;

	@Size(min = 1, max = 5)
	private String hausnummer;

	@NotNull
	// @Size(min = 5, max = 5)
	@Min(0)
	private Integer plz;

	@Size(min = 2, max = 20)
	private String stadt;

	@Size(min = 3, max = 20)
	private String telefonNummer;

	public BenutzerRegisterDTO(Geschlecht geschlecht, String password1,
			String password2, String email, String vorname, String nachname,
			String strasse, String hausnummer, Integer plz, String stadt,
			String telefonNummer) {
		this.geschlecht = geschlecht;
		this.password1 = password1;
		this.password2 = password2;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.stadt = stadt;
		this.telefonNummer = telefonNummer;
	}

	public BenutzerRegisterDTO() {

	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
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

	public Integer getPlz() {
		return plz;
	}

	public void setPlz(Integer plz) {
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
		return "BenutzerRegisterDto [geschlecht=" + geschlecht + ", password1="
				+ password1 + ", password2=" + password2 + ", email=" + email
				+ ", vorname=" + vorname + ", nachname=" + nachname
				+ ", strasse=" + strasse + ", hausnummer=" + hausnummer
				+ ", plz=" + plz + ", stadt=" + stadt + ", telefonNummer="
				+ telefonNummer + "]";
	}

}
