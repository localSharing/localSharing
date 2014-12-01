package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Angebot {

	// GenerationType.TABLE bewirkt, dass IDs in Tabelle unique sind
	// GenerationType.AUTO bewirkt, dass IDs in allen drei Tabellen unique sind
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ANGEBOTSID")
	private Long angebotsid;

	@OneToOne
	private Benutzer benutzer;

	@Column(name = "TITEL", nullable = false)
	private String titel;

	@Column(name = "BESCHREIBUNG", length = 1000)
	private String beschreibung;

	@Column(name = "STARTDATUM", nullable = false)
	private Date startDatum;

	public Angebot(Long id, Benutzer benutzer, String titel,
			String beschreibung, Date startDatum) {
		this.angebotsid = id;
		this.benutzer = benutzer;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.startDatum = startDatum;
	}
	
	public Angebot() {
		
	}

	public Long getAngebotsid() {
		return angebotsid;
	}

	public void setAngebotsid(Long angebotsid) {
		this.angebotsid = angebotsid;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	@Override
	public String toString() {
		return "Angebot [angebotsid = " + angebotsid + ", benutzer = "
				+ benutzer + ", titel = " + titel + ", beschreibung = "
				+ beschreibung + ", startDatum = " + startDatum + "]";
	}

}
