package pandha.swe.localsharing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BEWERTUNG")
public class Bewertung {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BEWERTUNGSID")
	private Long bewertungsid;
	
	@OneToMany
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

	@Override
	public String toString() {
		return "Bewertung [bewertungsid=" + bewertungsid + ", angebot="
				+ angebot + ", bewerter=" + bewerter + ", bewertungSterne="
				+ bewertungSterne + ", kommentar=" + kommentar + ", datum="
				+ datum + "]";
	}
	
	
	
	
}
