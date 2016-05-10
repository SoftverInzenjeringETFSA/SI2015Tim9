package app.vrtic.Model;

// default package
// Generated 09-May-2016 12:32:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grupa generated by hbm2java
 */
@Entity
@Table(name = "grupa", catalog = "tim9")
public class Grupa implements java.io.Serializable {

	private Integer idGrupe;
	private String naziv;
	private Integer redniBroj;
	private Integer kapacitet;
	private Set<Vaspitac> vaspitacs = new HashSet<Vaspitac>(0);
	private Set<Dijete> dijetes = new HashSet<Dijete>(0);
	private Set<Termin> termins = new HashSet<Termin>(0);

	public Grupa() {
	}

	public Grupa(String naziv, Integer redniBroj, Integer kapacitet, Set<Vaspitac> vaspitacs, Set<Dijete> dijetes,
			Set<Termin> termins) {
		this.naziv = naziv;
		this.redniBroj = redniBroj;
		this.kapacitet = kapacitet;
		this.vaspitacs = vaspitacs;
		this.dijetes = dijetes;
		this.termins = termins;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idGrupe", unique = true, nullable = false)
	public Integer getIdGrupe() {
		return this.idGrupe;
	}

	public void setIdGrupe(Integer idGrupe) {
		this.idGrupe = idGrupe;
	}

	@Column(name = "naziv", length = 45)
	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Column(name = "RedniBroj")
	public Integer getRedniBroj() {
		return this.redniBroj;
	}

	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}

	@Column(name = "Kapacitet")
	public Integer getKapacitet() {
		return this.kapacitet;
	}

	public void setKapacitet(Integer kapacitet) {
		this.kapacitet = kapacitet;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupa")
	public Set<Vaspitac> getVaspitacs() {
		return this.vaspitacs;
	}

	public void setVaspitacs(Set<Vaspitac> vaspitacs) {
		this.vaspitacs = vaspitacs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupa")
	public Set<Dijete> getDijetes() {
		return this.dijetes;
	}

	public void setDijetes(Set<Dijete> dijetes) {
		this.dijetes = dijetes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupa")
	public Set<Termin> getTermins() {
		return this.termins;
	}

	public void setTermins(Set<Termin> termins) {
		this.termins = termins;
	}
	
	public String toString() { 
	    return this.idGrupe + ": " + this.naziv;
	} 


}
