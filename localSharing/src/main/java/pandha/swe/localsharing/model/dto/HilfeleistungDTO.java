package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class HilfeleistungDTO extends AngebotDTO {

	@Size(min = 10, max = 10)
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
