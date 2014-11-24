package pandha.swe.localsharing.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class AusleihartikelDTO extends AngebotDTO {

	// TODO
	@NotNull
	private Date endDatum;

	@NotNull
	private int dauer;

	@Size(min = 2, max = 20)
	private String kategorie;

	public AusleihartikelDTO(Benutzer benutzer, String titel,
			String beschreibung, Date startDatum, Date endDatum, int dauer,
			String kategorie) {
		super(benutzer, titel, beschreibung, startDatum);
		this.endDatum = endDatum;
		this.dauer = dauer;
		this.kategorie = kategorie;
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
		return "AusleihartikelDTO[benutzer = " + super.getBenutzer() + "titel = "
				+ super.getTitel() + ", beschreibung = "
				+ super.getBeschreibung() + ", startDatum = "
				+ super.getStartDatum() + ", endDatum = " + endDatum
				+ ", dauer = " + dauer + ", kategorie = " + kategorie + "]";
	}

}
