package app.vrtic.Model;

// default package
// Generated 09-May-2016 12:32:34 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Dijete generated by hbm2java
 */
@Entity
@Table(name = "dijete", catalog = "tim9")
public class Dijete implements java.io.Serializable {

	private Integer idDijete;
	private Grupa grupa;
	private String ime;
	private String prezime;
	private String datumRodjenja;
	private String adresaPrebivalista;
	private String imeRoditelja;
	private String brojTelefona;
	private String prezimeRoditelja;
	private Date datumUpisa;
	private Date datumIsteka;
	private Set<Uplata> uplatas = new HashSet<Uplata>(0);
	private Set<Aktivnostidjeca> aktivnostidjecas = new HashSet<Aktivnostidjeca>(0);
	private Set<Zaduzenja> zaduzenjas = new HashSet<Zaduzenja>(0);

	public Dijete() {
	}

	public Dijete(Grupa grupa, String ime, String prezime, String datumRodjenja, String adresaPrebivalista,
			String imeRoditelja, String brojTelefona, String prezimeRoditelja, Date datumUpisa, Date datumIsteka,
			Set<Uplata> uplatas, Set<Aktivnostidjeca> aktivnostidjecas, Set<Zaduzenja> zaduzenjas) {
		this.grupa = grupa;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresaPrebivalista = adresaPrebivalista;
		this.imeRoditelja = imeRoditelja;
		this.brojTelefona = brojTelefona;
		this.prezimeRoditelja = prezimeRoditelja;
		this.datumUpisa = datumUpisa;
		this.datumIsteka = datumIsteka;
		this.uplatas = uplatas;
		this.aktivnostidjecas = aktivnostidjecas;
		this.zaduzenjas = zaduzenjas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idDijete", unique = true, nullable = false)
	public Integer getIdDijete() {
		return this.idDijete;
	}

	public void setIdDijete(Integer idDijete) {
		this.idDijete = idDijete;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idGrupe")
	public Grupa getGrupa() {
		return this.grupa;
	}

	public void setGrupa(Grupa grupa) {
		this.grupa = grupa;
	}

	@Column(name = "Ime")
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Column(name = "Prezime")
	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Column(name = "DatumRodjenja")
	public String getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Column(name = "AdresaPrebivalista")
	public String getAdresaPrebivalista() {
		return this.adresaPrebivalista;
	}

	public void setAdresaPrebivalista(String adresaPrebivalista) {
		this.adresaPrebivalista = adresaPrebivalista;
	}

	@Column(name = "ImeRoditelja")
	public String getImeRoditelja() {
		return this.imeRoditelja;
	}

	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}

	@Column(name = "BrojTelefona")
	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Column(name = "PrezimeRoditelja")
	public String getPrezimeRoditelja() {
		return this.prezimeRoditelja;
	}

	public void setPrezimeRoditelja(String prezimeRoditelja) {
		this.prezimeRoditelja = prezimeRoditelja;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DatumUpisa", length = 10)
	public Date getDatumUpisa() {
		return this.datumUpisa;
	}

	public void setDatumUpisa(Date datumUpisa) {
		this.datumUpisa = datumUpisa;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DatumIsteka", length = 10)
	public Date getDatumIsteka() {
		return this.datumIsteka;
	}

	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dijete")
	public Set<Uplata> getUplatas() {
		return this.uplatas;
	}

	public void setUplatas(Set<Uplata> uplatas) {
		this.uplatas = uplatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dijete")
	public Set<Aktivnostidjeca> getAktivnostidjecas() {
		return this.aktivnostidjecas;
	}

	public void setAktivnostidjecas(Set<Aktivnostidjeca> aktivnostidjecas) {
		this.aktivnostidjecas = aktivnostidjecas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dijete")
	public Set<Zaduzenja> getZaduzenjas() {
		return this.zaduzenjas;
	}

	public void setZaduzenjas(Set<Zaduzenja> zaduzenjas) {
		this.zaduzenjas = zaduzenjas;
	}
	
	@Override
	public String toString() {
		
		return this.ime+" "+this.prezime+","+this.imeRoditelja+" "+this.prezimeRoditelja;
	}

}