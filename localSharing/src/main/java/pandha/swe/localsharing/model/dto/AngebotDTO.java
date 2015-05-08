package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Benutzer;

public class AngebotDTO {

	private Long id;
	
	private Boolean enabled;

	private Benutzer benutzer;

	@Size(min = 2, max = 50)
	private String titel;

	@Size(max = 1000)
	private String beschreibung;

	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}", message = "Bitte das Datum richtig angeben (dd.mm.yyyy)")
	private String startDatum;

	public AngebotDTO(Long id, Boolean enabled, Benutzer benutzer, String titel,
			String beschreibung, String startDatum) {
		this.id = id;
		this.enabled = enabled;
		this.benutzer = benutzer;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.startDatum = startDatum;
	}

	public AngebotDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public String getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(String startDatum) {
		this.startDatum = startDatum;
	}

	@Override
	public String toString() {
		return "AngebotDTO [id=" + id + ", enabled=" + enabled + ", benutzer="
				+ benutzer + ", titel=" + titel + ", beschreibung="
				+ beschreibung + ", startDatum=" + startDatum + "]";
	}
	

}
