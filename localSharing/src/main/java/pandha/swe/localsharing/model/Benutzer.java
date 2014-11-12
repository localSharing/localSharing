package pandha.swe.localsharing.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BENUTZER")
public class Benutzer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID")
	private Long userid;

	@Column(name = "PASSWORT", nullable = false)
	private String passwort;

	@Column(name = "ENABLED", nullable = false)
	private Boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "benutzer")
	private Set<BenutzerRolle> benutzerRolle;

	@Column(name = "GESCHLECHT")
	private Geschlecht geschlecht;

	// Adresse
	@Column(name = "VORNAME")
	private String vorname;
	@Column(name = "NACHNAME")
	private String nachname;
	@Column(name = "STRASSE")
	private String strasse;
	@Column(name = "HAUSNR")
	private String hausnummer;
	@Column(name = "PLZ")
	private Integer plz;
	@Column(name = "STADT")
	private String stadt;

	// Kontakt
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	@Column(name = "TELNR")
	private String telefonNr;

	public Benutzer() {
		this.benutzerRolle = new HashSet<BenutzerRolle>(0);

	}

	public Benutzer(Long id, String passwort, Boolean enabled,
			Geschlecht geschlecht, String vorname, String nachname,
			String strasse, String hausnummer, Integer plz, String stadt,
			String email, String telefonNr, Set<BenutzerRolle> benutzerRolle) {
		this.userid = id;
		this.passwort = passwort;
		this.enabled = enabled;
		this.geschlecht = geschlecht;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.stadt = stadt;
		this.email = email;
		this.telefonNr = telefonNr;
		this.benutzerRolle = benutzerRolle;
	}

	public Long getId() {
		return userid;
	}

	public void setId(Long id) {
		this.userid = id;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonNr() {
		return telefonNr;
	}

	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<BenutzerRolle> getBenutzerRolle() {
		return benutzerRolle;
	}

	public void setBenutzerRolle(Set<BenutzerRolle> benutzerRolle) {
		this.benutzerRolle = benutzerRolle;
	}

	@Override
	public String toString() {
		return "Benutzer [userid=" + userid + ", passwort=" + passwort
				+ ", enabled=" + enabled + ", benutzerRolle=" + benutzerRolle
				+ ", geschlecht=" + geschlecht + ", vorname=" + vorname
				+ ", nachname=" + nachname + ", strasse=" + strasse
				+ ", hausnummer=" + hausnummer + ", plz=" + plz + ", stadt="
				+ stadt + ", email=" + email + ", telefonNr=" + telefonNr + "]";
	}

}
