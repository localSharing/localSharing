package pandha.swe.localsharing.model.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import pandha.swe.localsharing.model.Angebot;
import pandha.swe.localsharing.model.Benutzer;
import pandha.swe.localsharing.model.enums.AnfrageStatus;
import pandha.swe.localsharing.model.enums.Kontaktart;

public class AnfrageDTO {

	private Long id;

	private Angebot angebot;

	private Benutzer sender;

	private Date datum;

	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}", message = "Bitte das Datum richtig angeben (dd.mm.yyyy)")
	private String startDatum;

	@Pattern(regexp = "[0-9]{2}.[0-9]{2}.[0-9]{4}", message = "Bitte das Datum richtig angeben (dd.mm.yyyy)")
	private String endDatum;

	@Size(max = 1000)
	private String kommentar;

	private AnfrageStatus status;

	private Kontaktart kontaktArt;

	public AnfrageDTO(Long id, Angebot angebot, Benutzer sender, Date datum,
			String startDatum, String endDatum, String kommentar,
			AnfrageStatus status, Kontaktart kontaktArt) {
		this.id = id;
		this.angebot = angebot;
		this.sender = sender;
		this.datum = datum;
		this.startDatum = startDatum;
		this.endDatum = endDatum;
		this.kommentar = kommentar;
		this.status = status;
		this.kontaktArt = kontaktArt;
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

	public Benutzer getSender() {
		return sender;
	}

	public void setSender(Benutzer sender) {
		this.sender = sender;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(String startDatum) {
		this.startDatum = startDatum;
	}

	public String getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(String endDatum) {
		this.endDatum = endDatum;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public AnfrageStatus getStatus() {
		return status;
	}

	public void setStatus(AnfrageStatus status) {
		this.status = status;
	}

	public Kontaktart getKontaktArt() {
		return kontaktArt;
	}

	public void setKontaktArt(Kontaktart kontaktArt) {
		this.kontaktArt = kontaktArt;
	}

	@Override
	public String toString() {
		return "AnfrageDTO [id=" + id + ", angebot=" + angebot + ", sender="
				+ sender + ", datum=" + datum + ", startDatum=" + startDatum
				+ ", endDatum=" + endDatum + ", kommentar=" + kommentar
				+ ", status=" + status + ", kontaktArt=" + kontaktArt + "]";
	}

}
