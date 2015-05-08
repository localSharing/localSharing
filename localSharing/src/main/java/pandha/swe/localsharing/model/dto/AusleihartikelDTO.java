package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class AusleihartikelDTO extends AngebotDTO {

	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}", message = "Bitte das Datum richtig angeben (dd.mm.yyyy)")
	private String endDatum;

	@NotNull
	@Min(value = 1)
	@Max(value = 365)
	private int dauer;

	@Size(min = 2, max = 20)
	private String kategorie;

	public AusleihartikelDTO(Long id, Boolean enabled, Benutzer benutzer, String titel,
			String beschreibung, String startDatum, String endDatum, int dauer,
			String kategorie) {
		super(id, enabled, benutzer, titel, beschreibung, startDatum);
		this.endDatum = endDatum;
		this.dauer = dauer;
		this.kategorie = kategorie;
	}

	public AusleihartikelDTO() {
	}

	public String getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(String endDatum) {
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
		return "AusleihartikelDTO[id =" + super.getId() + ", enabled = " + super.getEnabled() + "benutzer = " + super.getBenutzer()
				+ "titel = " + super.getTitel() + ", beschreibung = "
				+ super.getBeschreibung() + ", startDatum = "
				+ super.getStartDatum() + ", endDatum = " + endDatum
				+ ", dauer = " + dauer + ", kategorie = " + kategorie + "]";
	}
	

}
