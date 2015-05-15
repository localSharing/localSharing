package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BEWERTUNG")
public class Bewertung {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BEWERTUNGSID")
	private Long bewertungsid;
	
	@ManyToOne
	private Angebot angebot; 
	
	@OneToOne
	private Benutzer bewerter; 
	
	@Column(name = "BEWERTUNGSTERNE")
	private Integer bewertungSterne;
	
	@Column(name = "KOMMENTAR", length = 1000)
	private String kommentar;
	
	@Column(name = "DATUM", nullable = false)
	private Date datum;

	public Bewertung(Long id, Angebot angebot, Benutzer bewerter,
			Integer bewertungSterne, String kommentar, Date datum) {
		this.bewertungsid = id;
		this.angebot = angebot;
		this.bewerter = bewerter;
		this.bewertungSterne = bewertungSterne;
		this.kommentar = kommentar;
		this.datum = datum;
	}
	
	public Bewertung(){
		
	}
	
	public Long getBewertungsid() {
		return bewertungsid;
	}

	public void setBewertungsid(Long bewertungsid) {
		this.bewertungsid = bewertungsid;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "Bewertung [bewertungsid=" + bewertungsid + ", angebot="
				+ angebot + ", bewerter=" + bewerter + ", bewertungSterne="
				+ bewertungSterne + ", kommentar=" + kommentar + ", datum="
				+ datum + "]";
	}
	
}
