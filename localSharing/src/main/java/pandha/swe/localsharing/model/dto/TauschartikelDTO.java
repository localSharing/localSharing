package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class TauschartikelDTO extends AngebotDTO {

	@Size(min = 2, max = 20)
	private String kategorie;

	public TauschartikelDTO(Long id, Boolean enabled, Benutzer benutzer, String titel,
			String beschreibung, String startDatum, String kategorie) {
		super(id, enabled, benutzer, titel, beschreibung, startDatum);
		this.kategorie = kategorie;
	}

	public TauschartikelDTO() {

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
