package pandha.swe.localsharing.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import pandha.swe.localsharing.model.Benutzer;

public class HilfeleistungDTO extends AngebotDTO {

	// TODO
	@NotNull
	private Date endDatum;

	public HilfeleistungDTO(Long id, Benutzer benutzer, String titel,
			String beschreibung, Date startDatum, Date endDatum) {
		super(id, benutzer, titel, beschreibung, startDatum);
		this.endDatum = endDatum;
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
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
