package pandha.swe.localsharing.model.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class TauschartikelDTO extends AngebotDTO {

	@Size(min = 2, max = 20)
	private String kategorie;

	public TauschartikelDTO(Long id, Benutzer benutzer, String titel,
			String beschreibung, Date startDatum, String kategorie) {
		super(id, benutzer, titel, beschreibung, startDatum);
		this.kategorie = kategorie;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	@Override
	public String toString() {
		return "TauschartikelDTO [kategorie=" + kategorie + "]";
	}
	
	

	

}
