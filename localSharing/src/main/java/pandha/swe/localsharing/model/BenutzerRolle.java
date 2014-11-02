package pandha.swe.localsharing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLLEN", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ROLLE", "ID" }))
public class BenutzerRolle {

	public enum Rollen {
		USER, ADMIN
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long beutzerRollenId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID", nullable = false)
	private Benutzer benutzer;

	@Column(name = "ROLLE", nullable = false)
	private Rollen rolle;

	public BenutzerRolle(long beutzerRollenId, Benutzer benutzer, Rollen rolle) {
		this.beutzerRollenId = beutzerRollenId;
		this.benutzer = benutzer;
		this.rolle = rolle;
	}

	public BenutzerRolle() {

	}

	public long getBeutzerRollenId() {
		return beutzerRollenId;
	}

	public void setBeutzerRollenId(long beutzerRollenId) {
		this.beutzerRollenId = beutzerRollenId;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public Rollen getRolle() {
		return rolle;
	}

	public void setRolle(Rollen rolle) {
		this.rolle = rolle;
	}

}
