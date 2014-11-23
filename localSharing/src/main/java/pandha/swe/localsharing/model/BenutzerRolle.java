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

import pandha.swe.localsharing.model.enums.Rollen;

@Entity
@Table(name = "ROLLEN", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ROLLE", "ID" }))
public class BenutzerRolle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long beutzerRollenId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	private Benutzer benutzer;

	@Column(name = "ROLLE")
	private Rollen rolle;

	public BenutzerRolle(Long benutzerRollenId, Benutzer benutzer, Rollen rolle) {
		this.beutzerRollenId = benutzerRollenId;
		this.benutzer = benutzer;
		this.rolle = rolle;
	}

	public BenutzerRolle() {

	}

	public long getBeutzerRollenId() {
		return beutzerRollenId;
	}

	public void setBeutzerRollenId(Long beutzerRollenId) {
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

	@Override
	public String toString() {
		return "BenutzerRolle [beutzerRollenId=" + beutzerRollenId
				+ ", benutzer=" + benutzer + ", rolle=" + rolle + "]";
	}

}
