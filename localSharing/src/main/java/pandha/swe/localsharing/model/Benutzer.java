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

	public enum Geschlecht {
		MANN, FRAU
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID")
	private long userid;

	@Column(name = "PASSWORT", nullable = false)
	private String passwort;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "benutzer")
	private Set<BenutzerRolle> benutzerRolle = new HashSet<BenutzerRolle>(0);
	
	

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
	private String plz;
	@Column(name = "STADT")
	private String stadt;

	// Kontakt
	@Column(name = "EMAIL",unique=true)
	private String email;
	@Column(name = "TELNR")
	private String telefonNr;

	

	public Benutzer() {

	}

	public Benutzer(long id, String passwort, boolean enabled,
			Geschlecht geschlecht, String vorname, String nachname,
			String strasse, String hausnummer, String plz, String stadt,
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

	public long getId() {
		return userid;
	}

	public void setId(long id) {
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<BenutzerRolle> getBenutzerRolle() {
		return benutzerRolle;
	}

	public void setBenutzerRolle(Set<BenutzerRolle> benutzerRolle) {
		this.benutzerRolle = benutzerRolle;
	}

}
