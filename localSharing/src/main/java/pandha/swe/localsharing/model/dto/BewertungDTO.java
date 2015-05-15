package pandha.swe.localsharing.model.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;

public class BewertungDTO {

	private Long id;

	private Angebot angebot;

	private Benutzer bewerter;
	
	@Size(min = 1, max = 1)
	private Integer bewertungSterne;
	
	@Size(max = 1000)
	private String kommentar;
	
	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}")
	private String datum;

	public BewertungDTO(Long id, Angebot angebot, Benutzer bewerter,
			Integer bewertungSterne, String kommentar, String datum) {
		super();
		this.id = id;
		this.angebot = angebot;
		this.bewerter = bewerter;
		this.bewertungSterne = bewertungSterne;
		this.kommentar = kommentar;
		this.datum = datum;
	}
	
	public BewertungDTO(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Angebot getAngebot() {
		return angebot;
	}

	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}

	public Benutzer getBewerter() {
		return bewerter;
	}

	public void setBewerter(Benutzer bewerter) {
		this.bewerter = bewerter;
	}

	public Integer getBewertungSterne() {
		return bewertungSterne;
	}

	public void setBewertungSterne(Integer bewertungSterne) {
		this.bewertungSterne = bewertungSterne;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "BewertungDTO [id=" + id + ", angebot=" + angebot
				+ ", bewerter=" + bewerter + ", bewertungSterne="
				+ bewertungSterne + ", kommentar=" + kommentar + ", datum="
				+ datum + "]";
	}

}
