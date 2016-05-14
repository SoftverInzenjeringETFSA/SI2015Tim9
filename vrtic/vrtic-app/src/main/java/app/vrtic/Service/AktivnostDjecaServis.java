package app.vrtic.Service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.AktivnostidjecaId;
import app.vrtic.Model.Dijete;


public class AktivnostDjecaServis {
	private Session s;
	
	public AktivnostDjecaServis(Session s) {
		this.s = s;
	}
	
	public ArrayList<Aktivnostidjeca> sveAktivnostiDjeca(){
		List<Aktivnostidjeca> aktivnosti = s.createCriteria(Aktivnostidjeca.class).list();
		return new ArrayList<Aktivnostidjeca>(aktivnosti);
	
	}
	public boolean obrisi(Aktivnostidjeca aktivnostDjeca) {
		
		
		if(aktivnostDjeca!=null) { 
			Transaction t = s.beginTransaction();
			s.delete(aktivnostDjeca);
			t.commit();
			return true;
		}
		return false;
	}
	
	

	// probao isto raditi sa AktivnostidjecaID ovdje i ne radi opet, ista gre�ka
	public boolean dodajAktivnostDijete(Aktivnostidjeca t) {
		Transaction transakcija = s.beginTransaction(); //pocetak transakcije

		Aktivnostidjeca akt = new Aktivnostidjeca();
		
		akt.setId(t.getId());
		akt.setDijete(t.getDijete());
		akt.setAktivnost(t.getAktivnost());

		s.save(akt); //NE PADAAAAA VISEEEEEEEEEEEE

		transakcija.commit(); //komitanje transakcije
		System.out.println("Dodana aktivnost za dijete!");
		return true;
	}
}
