package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUSLEIHARTIKEL")
public class Ausleihartikel extends Angebot {

	@Column(name = "ENDDATUM", nullable = false)
	private Date endDatum;

	@Column(name = "DAUER", nullable = false)
	private int dauer;

	@Column(name = "KATEGORIE", nullable = false)
	private String kategorie;

	public Ausleihartikel(Long id, Boolean enabled, Benutzer benutzer,
			String titel, String beschreibung, Date startDatum, Date endDatum,
			int dauer, String kategorie) {
		super(id, enabled, benutzer, titel, beschreibung, startDatum);
		this.endDatum = endDatum;
		this.dauer = dauer;
		this.kategorie = kategorie;
	}

	public Ausleihartikel() {
		super();
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	public int getDauer() {
		return dauer;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	@Override
	public String toString() {
		return "Ausleihartikel [angebotsid = " + super.getAngebotsid()
				+ ", enabled = " + super.getEnabled() + ", benutzer = "
				+ super.getBenutzer() + ", titel = " + super.getTitel()
				+ ", beschreibung = " + super.getBeschreibung()
				+ ", startDatum = " + super.getStartDatum() + ", endDatum = "
				+ endDatum + ", dauer = " + dauer + ", kategorie = "
				+ kategorie + "]";
	}

}
