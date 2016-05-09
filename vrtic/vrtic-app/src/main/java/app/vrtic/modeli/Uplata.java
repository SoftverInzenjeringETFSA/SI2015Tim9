package app.vrtic.modeli;

// default package
// Generated 09-May-2016 02:07:59 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Uplata generated by hbm2java
 */
@Entity
@Table(name = "uplata", catalog = "tim9")
public class Uplata implements java.io.Serializable {

	private int idUplate;
	private Dijete dijete;
	private Date datumUplate;
	private Double visinaUplate;
	private Integer zaMjesec;
	private Integer zaGodinu;

	public Uplata() {
	}

	public Uplata(int idUplate) {
		this.idUplate = idUplate;
	}

	public Uplata(int idUplate, Dijete dijete, Date datumUplate, Double visinaUplate, Integer zaMjesec,
			Integer zaGodinu) {
		this.idUplate = idUplate;
		this.dijete = dijete;
		this.datumUplate = datumUplate;
		this.visinaUplate = visinaUplate;
		this.zaMjesec = zaMjesec;
		this.zaGodinu = zaGodinu;
	}

	@Id

	@Column(name = "idUplate", unique = true, nullable = false)
	public int getIdUplate() {
		return this.idUplate;
	}

	public void setIdUplate(int idUplate) {
		this.idUplate = idUplate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDijete")
	public Dijete getDijete() {
		return this.dijete;
	}

	public void setDijete(Dijete dijete) {
		this.dijete = dijete;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DatumUplate", length = 10)
	public Date getDatumUplate() {
		return this.datumUplate;
	}

	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}

	@Column(name = "VisinaUplate", precision = 22, scale = 0)
	public Double getVisinaUplate() {
		return this.visinaUplate;
	}

	public void setVisinaUplate(Double visinaUplate) {
		this.visinaUplate = visinaUplate;
	}

	@Column(name = "zaMjesec")
	public Integer getZaMjesec() {
		return this.zaMjesec;
	}

	public void setZaMjesec(Integer zaMjesec) {
		this.zaMjesec = zaMjesec;
	}

	@Column(name = "zaGodinu")
	public Integer getZaGodinu() {
		return this.zaGodinu;
	}

	public void setZaGodinu(Integer zaGodinu) {
		this.zaGodinu = zaGodinu;
	}

}
