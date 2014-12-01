package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Pattern;

import pandha.swe.localsharing.model.Benutzer;

public class HilfeleistungDTO extends AngebotDTO {

	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}", message = "Bitte das Datum richtig angeben (dd.mm.yyyy)")
	private String endDatum;

	public HilfeleistungDTO(Long id, Benutzer benutzer, String titel,
			String beschreibung, String startDatum, String endDatum) {
		super(id, benutzer, titel, beschreibung, startDatum);
		this.endDatum = endDatum;
	}

	public HilfeleistungDTO() {

	}

	public String getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(String endDatum) {
		this.endDatum = endDatum;
	}

	@Override
	public String toString() {
		return "HilfeleistungDTO [benutzer = " + super.getBenutzer()
				+ "titel = " + super.getTitel() + ", beschreibung = "
				+ super.getBeschreibung() + ", startDatum = "
				+ super.getStartDatum() + ", endDatum = " + endDatum + "]";
	}

}
