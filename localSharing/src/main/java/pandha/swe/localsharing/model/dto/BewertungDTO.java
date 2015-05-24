package pandha.swe.localsharing.model.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;

public class BewertungDTO {

	private Long id;

	private Angebot angebot;

	private Benutzer benutzer;

	@Min(value = 1)
	@Max(value = 3)
	private Integer bewertungSterne;

	@Size(max = 1000)
	private String kommentar;

	private Date datum;

	public BewertungDTO(Long id, Angebot angebot, Benutzer bewerter,
			Integer bewertungSterne, String kommentar, Date datum) {
		this.id = id;
		this.angebot = angebot;
		this.benutzer = bewerter;
		this.bewertungSterne = bewertungSterne;
		this.kommentar = kommentar;
		this.datum = datum;
	}

	public BewertungDTO() {

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
		return benutzer;
	}

	public void setBewerter(Benutzer bewerter) {
		this.benutzer = bewerter;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "BewertungDTO [id=" + id + ", angebot=" + angebot
				+ ", bewerter=" + benutzer + ", bewertungSterne="
				+ bewertungSterne + ", kommentar=" + kommentar + ", datum="
				+ datum + "]";
	}

}
