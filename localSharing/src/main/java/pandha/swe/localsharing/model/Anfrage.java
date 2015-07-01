package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pandha.swe.localsharing.model.enums.AnfrageStatus;
import pandha.swe.localsharing.model.enums.Kontaktart;

@Entity
@Table(name = "ANFRAGE")
public class Anfrage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "anfrageID")
	private Long anfrageid;

	@OneToOne
	private Angebot angebot;

	@OneToOne
	private Benutzer sender;

	@Column(name = "DATUM")
	private Date datum;

	@Column(name = "STARTDATUM")
	private Date startDatum;

	@Column(name = "ENDDATUM")
	private Date endDatum;

	@Column(name = "KOMMENTAR")
	private String kommentar;

	@Enumerated(EnumType.STRING)
	@Column(name = "ANFRAGESTATUS")
	private AnfrageStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "KONTAKT")
	private Kontaktart kontaktArt;
	
	@Column(name = "ANNAHMEKOMMENTAR")
	private String annahmeKommentar;
	
	@Column(name = "ANNAHMEDATUM")
	private Date annahmeDatum;

	public Anfrage(Long anfrageid, Angebot angebot, Benutzer sender,
			Date datum, Date startDatum, Date endDatum, String kommentar,
			AnfrageStatus status, Kontaktart kontaktArt,
			String annahmeKommentar, Date annahmeDatum) {
		this.anfrageid = anfrageid;
		this.angebot = angebot;
		this.sender = sender;
		this.datum = datum;
		this.startDatum = startDatum;
		this.endDatum = endDatum;
		this.kommentar = kommentar;
		this.status = status;
		this.kontaktArt = kontaktArt;
		this.annahmeKommentar = annahmeKommentar;
		this.annahmeDatum = annahmeDatum;
	}


	public Anfrage() {
	}


	public Long getAnfrageid() {
		return anfrageid;
	}

	public void setAnfrageid(Long anfrageid) {
		this.anfrageid = anfrageid;
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

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
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

	public String getAnnahmeKommentar() {
		return annahmeKommentar;
	}


	public void setAnnahmeKommentar(String annahmeKommentar) {
		this.annahmeKommentar = annahmeKommentar;
	}


	public Date getAnnahmeDatum() {
		return annahmeDatum;
	}


	public void setAnnahmeDatum(Date annahmeDatum) {
		this.annahmeDatum = annahmeDatum;
	}


	@Override
	public String toString() {
		return "Anfrage [anfrageid=" + anfrageid + ", angebot=" + angebot
				+ ", sender=" + sender + ", datum=" + datum + ", startDatum="
				+ startDatum + ", endDatum=" + endDatum + ", kommentar="
				+ kommentar + ", status=" + status + ", kontaktArt="
				+ kontaktArt + ", annahmeKommentar=" + annahmeKommentar
				+ ", annahmeDatum=" + annahmeDatum + "]";
	}

}
