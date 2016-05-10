package app.vrtic.Service;

import app.vrtic.Model.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TerminServis {
	// sesija
	private Session s;

	// konstruktor klase TerminServis
	public TerminServis(Session s) {
		this.s = s;
	}

	// Dodavanje termina
	public boolean dodajTermin(Termin t) {
		Transaction transakcija = s.beginTransaction(); //pocetak transakcije
		Termin NoviTermin = new Termin();
		NoviTermin.setDan(t.getDan());
		NoviTermin.setGrupa(t.getGrupa());
		NoviTermin.setVrijemePocetka(t.getVrijemePocetka());
		NoviTermin.setVrijemeZavrsetka(t.getVrijemeZavrsetka());
		NoviTermin.setAktivnost(t.getAktivnost());
		
		
		s.save(NoviTermin); //spasavanje u sesiju
		transakcija.commit(); //komitanje transakcije
		return true;
	}

	// Pretraga termina po Id-u
	public Termin vratiTerminPoId(int id) {
		Transaction transakcija = s.beginTransaction();
		Termin t = (Termin) s.get(Termin.class, id);
		transakcija.commit();
		return t;
	}

	// brisanje termina
	public boolean ObrisiTermin(int id) {
		Transaction trans = s.beginTransaction();
		
		Termin t = (Termin) s.get(Termin.class, id);
		if (t != null)
			s.delete(t);
		trans.commit();
		return true;
	}
	
	public ArrayList<Termin> SviTermini(){

		List<Termin> t = s.createCriteria(Termin.class).list();
		return new ArrayList<Termin>(t);
	}
	
}