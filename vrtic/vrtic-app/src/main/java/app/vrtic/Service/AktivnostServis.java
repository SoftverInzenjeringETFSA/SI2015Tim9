package app.vrtic.Service;

import app.vrtic.Model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AktivnostServis {
	private Session s;

	// konstruktor klase AktivnostServis
	public AktivnostServis(Session s) {
		this.s = s;
	}
	
	
	//pretraga po ID-u
	public Aktivnost pretragaPoIDu(int id)
	{
		Transaction transakcija = s.beginTransaction();
		Aktivnost akt = (Aktivnost) s.get(Aktivnost.class, id);
		transakcija.commit();
		return akt;
	}
	
	
	//brisanje aktivnosti
	public boolean ObrisiAktivnost(int id) {
		Transaction trans = s.beginTransaction();
		
		Aktivnost akt = (Aktivnost) s.get(Aktivnost.class, id);
		if (akt != null)
			s.delete(akt);
		trans.commit();
		return true;
	}
	
	//dodavanje aktivnosti
	public boolean dodajTermin(Aktivnost t) {
		Transaction transakcija = s.beginTransaction(); //pocetak transakcije
		Aktivnost akt = new Aktivnost();
		akt.setBrojDjece(t.getBrojDjece());
		akt.setCijena(t.getCijena());
		akt.setNaziv(t.getNaziv());
		akt.setTermins(null);
		akt.setAktivnostidjecas(null);
		
		
		s.save(akt); //spasavanje u sesiju
		transakcija.commit(); //komitanje transakcije
		return true;
	}
}
