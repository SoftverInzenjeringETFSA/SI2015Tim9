package app.vrtic.Model;

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
 * Vaspitac generated by hbm2java
 */
@Entity
@Table(name = "vaspitac", catalog = "tim9")
public class Vaspitac implements java.io.Serializable {

	private Integer idVaspitac;
	private Grupa grupa;
	private String ime;
	private String prezime;
	private String brojTelefona;
	private String adresaPrebivalista;

	public Vaspitac() {
	}

	public Vaspitac(Grupa grupa, String ime, String prezime, String brojTelefona, String adresaPrebivalista) {
		this.grupa = grupa;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.adresaPrebivalista = adresaPrebivalista;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idVaspitac", unique = true, nullable = false)
	public Integer getIdVaspitac() {
		return this.idVaspitac;
	}

	public void setIdVaspitac(Integer idVaspitac) {
		this.idVaspitac = idVaspitac;
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

	@Column(name = "BrojTelefona")
	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Column(name = "AdresaPrebivalista")
	public String getAdresaPrebivalista() {
		return this.adresaPrebivalista;
	}

	public void setAdresaPrebivalista(String adresaPrebivalista) {
		this.adresaPrebivalista = adresaPrebivalista;
	}

}
