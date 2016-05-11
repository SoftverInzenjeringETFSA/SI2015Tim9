package app.vrtic.Service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.AktivnostidjecaId;


public class AktivnostDjecaServis {
	private Session s;
	
	public AktivnostDjecaServis(Session s) {
		this.s = s;
	}
	// probao isto raditi sa AktivnostidjecaID ovdje i ne radi opet, ista greška
	public boolean dodajAktivnostDijete(Aktivnostidjeca t) {
		Transaction transakcija = s.beginTransaction(); //pocetak transakcije

		Aktivnostidjeca akt = new Aktivnostidjeca();
		
		akt.setDijete(t.getDijete());
		akt.setAktivnost(t.getAktivnost());

		s.save(akt); //spasavanje u sesiju PADA OVDJE

		transakcija.commit(); //komitanje transakcije
		System.out.println("Dodana aktivnost za dijete!");
		return true;
	}
}
