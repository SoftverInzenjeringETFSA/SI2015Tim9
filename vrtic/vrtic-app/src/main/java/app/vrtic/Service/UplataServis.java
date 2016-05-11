package app.vrtic.Service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import app.vrtic.Model.Dijete;
import app.vrtic.Model.Uplata;

public class UplataServis {
	
	private Session s;


	public UplataServis(Session s){
	    this.s=s;
	    }
//ovdje idu CRUD operacije
	
	public boolean evidentirajUplatu(Uplata u){
		Transaction t = s.beginTransaction();
		Uplata up = new Uplata();
		up.setDatumUplate(u.getDatumUplate());
		up.setDijete(u.getDijete());
		up.setVisinaUplate(u.getVisinaUplate());
		up.setZaGodinu(u.getZaGodinu());
		up.setZaMjesec(u.getZaMjesec());
		s.save(up);
		t.commit();
		System.out.println("Uplata evidentirana!");
		return true;
	}
}
