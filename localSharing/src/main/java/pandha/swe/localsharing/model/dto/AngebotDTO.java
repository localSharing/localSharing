package pandha.swe.localsharing.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class AngebotDTO {

	@NotNull
	private Benutzer benutzer;

	@Size(min = 2, max = 50)
	private String titel;

	@Size(max = 500)
	private String beschreibung;

	// TODO
	@NotNull
	private Date startDatum;

	public AngebotDTO(Benutzer benutzer, String titel, String beschreibung,
			Date startDatum) {
		this.benutzer = benutzer;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.startDatum = startDatum;
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
		return "AngebotDTO [benutzer = " + benutzer + "titel = " + titel + ", benutzer = " + benutzer
				+ ", beschreibung = " + beschreibung + ", startDatum = "
				+ startDatum + "]";
	}

}