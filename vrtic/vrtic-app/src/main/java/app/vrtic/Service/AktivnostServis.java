package app.vrtic.Service;

import app.vrtic.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class AktivnostServis {
	private Session s;

	// konstruktor klase AktivnostServis
	public AktivnostServis(Session s) {
		this.s = s;
	}
	public boolean ObrisiAktivnost(int id) {
		Transaction trans = s.beginTransaction();
		
		Aktivnost akt = (Aktivnost) s.get(Aktivnost.class, id);
		if (akt != null) {
			s.delete(akt);
			trans.commit();
			return true;
		}
		return false;
	}
	
	//pretraga po ID-u
	public Aktivnost pretragaPoIDu(int id)
	{
		Transaction transakcija = s.beginTransaction();
		Aktivnost akt = (Aktivnost) s.get(Aktivnost.class, id);
		transakcija.commit();
		return akt;
	}
	
	public Aktivnost pretragaPoImenu(String ime) {
		
		Criteria c = s.createCriteria(Aktivnost.class);
		c.add(Restrictions.eq("Naziv",ime));
		return (Aktivnost) c.uniqueResult();
	}
	
	
	/*
	public Aktivnost pretragaPoNazivu(String naziv)
	{
		Transaction transakcija = s.beginTransaction();
		Aktivnost akt = (Aktivnost) s.get(Aktivnost.class, naziv);
		transakcija.commit();
		return akt;
	}
	
	*/
	//brisanje aktivnosti

	
	//dodavanje aktivnosti
	public boolean dodajAktivnost(Aktivnost t) {
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
	
	public ArrayList<Aktivnost> SveAktivnosti(){

		List<Aktivnost> t = s.createCriteria(Aktivnost.class).list();
		return new ArrayList<Aktivnost>(t);
	}
	public DefaultListModel<Aktivnost> sveAktivnostiLista(){
	DefaultListModel<Aktivnost> model = new DefaultListModel<Aktivnost>();
	ArrayList<Aktivnost> niz = (ArrayList) s.createCriteria(Aktivnost.class).list();
	for(Aktivnost val : niz)  {
		model.addElement(val); 
		}
		return model;
	}
	
	public ArrayList<Aktivnost> vratiAktivnostiDjeteta(Dijete d){
		List<Aktivnost> spisakAktivnosti = new ArrayList<Aktivnost>();;
		Set<Aktivnostidjeca>skupMedjutabela = d.getAktivnostidjecas();
		Aktivnostidjeca[] medju = skupMedjutabela.toArray(new Aktivnostidjeca[skupMedjutabela.size()]);
	    for(int i=0; i<medju.length;i++){
	    	spisakAktivnosti.add(medju[i].getAktivnost());
	    	
	    	}
	    return new ArrayList<Aktivnost>(spisakAktivnosti);
	}
	
}
