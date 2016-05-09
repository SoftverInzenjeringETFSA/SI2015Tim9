package app.vrtic.modeli;

// default package
// Generated 09-May-2016 12:32:34 by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Zaduzenja generated by hbm2java
 */
@Entity
@Table(name = "zaduzenja", catalog = "tim9")
public class Zaduzenja implements java.io.Serializable {

	private Integer idZaduzenja;
	private Dijete dijete;
	private String mjesec;
	private Integer godina;

	public Zaduzenja() {
	}

	public Zaduzenja(Dijete dijete, String mjesec, Integer godina) {
		this.dijete = dijete;
		this.mjesec = mjesec;
		this.godina = godina;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idZaduzenja", unique = true, nullable = false)
	public Integer getIdZaduzenja() {
		return this.idZaduzenja;
	}

	public void setIdZaduzenja(Integer idZaduzenja) {
		this.idZaduzenja = idZaduzenja;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDijete")
	public Dijete getDijete() {
		return this.dijete;
	}

	public void setDijete(Dijete dijete) {
		this.dijete = dijete;
	}

	@Column(name = "mjesec", length = 45)
	public String getMjesec() {
		return this.mjesec;
	}

	public void setMjesec(String mjesec) {
		this.mjesec = mjesec;
	}

	@Column(name = "godina")
	public Integer getGodina() {
		return this.godina;
	}

	public void setGodina(Integer godina) {
		this.godina = godina;
	}

}
