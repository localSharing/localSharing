package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TAUSCHARTIKEL")
public class Tauschartikel extends Angebot {

	@Column(name = "KATEGORIE", nullable = false)
	private String kategorie;

	public Tauschartikel(Long id, Boolean enabled, Benutzer benutzer,
			String titel, String beschreibung, Date startDatum, String kategorie) {
		super(id, enabled, benutzer, titel, beschreibung, startDatum);
		this.kategorie = kategorie;
	}

	public Tauschartikel() {
		super();
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	@Override
	public String toString() {
		return "Hilfeleistung [angebotsid = " + super.getAngebotsid()
				+ ", enabled = " + super.getEnabled() + ", benutzer = "
				+ super.getBenutzer() + ", titel = " + super.getTitel()
				+ ", beschreibung = " + super.getBeschreibung()
				+ ", startDatum = " + super.getStartDatum() + ", kategorie = "
				+ kategorie + "]";
	}

}
