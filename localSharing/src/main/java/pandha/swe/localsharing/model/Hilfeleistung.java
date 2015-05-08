package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HILFELEISTUNG")
public class Hilfeleistung extends Angebot {

	@Column(name = "ENDDATUM", nullable = false)
	private Date endDatum;

	public Hilfeleistung(Long id, Boolean enabled, Benutzer benutzer,
			String titel, String beschreibung, Date startDatum, Date endDatum) {
		super(id, enabled, benutzer, titel, beschreibung, startDatum);
		this.endDatum = endDatum;
	}

	public Hilfeleistung() {
		super();
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	@Override
	public String toString() {
		return "Hilfeleistung [angebotsid = " + super.getAngebotsid()
				+ ", enabled = " + super.getEnabled() + ", benutzer = "
				+ super.getBenutzer() + ", titel = " + super.getTitel()
				+ ", beschreibung = " + super.getBeschreibung()
				+ ", startDatum = " + super.getStartDatum() + ", endDatum = "
				+ endDatum + "]";
	}
}
