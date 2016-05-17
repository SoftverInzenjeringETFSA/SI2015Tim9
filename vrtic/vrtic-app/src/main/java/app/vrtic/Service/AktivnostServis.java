package app.vrtic.Service;

import app.vrtic.Model.*;

import java.io.Console;
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
	private AktivnostDjecaServis ads;

	// konstruktor klase AktivnostServis
	public AktivnostServis(Session s) {
		this.s = s;
		this.ads = new AktivnostDjecaServis(s);
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
	for(Aktivnost val : niz){
		model.addElement(val);
	}
		return model;
	}
	
	public boolean provjeriDaLiPostojiAktivnost(String naz){
		ArrayList<Aktivnost> ak=SveAktivnosti();
		for(int i=0;i<ak.size();i++)
		{
			if(ak.get(i).getNaziv().equals(naz)) {
				return true;
			}
		}
		return false;
	}
	
	public DefaultListModel<Aktivnost> vratiAktivnostiDjetetaZaListu(Dijete d){
		DefaultListModel<Aktivnost> model = new DefaultListModel<Aktivnost>();
		ArrayList<Aktivnost> niz = vratiAktivnostiDjeteta(d);
		if(niz!=null){
		for(Aktivnost val : niz){
			model.addElement(val);
		}
		}
			return model;
			
	}
	public DefaultListModel<Aktivnost> vratiAktivnostiNaKojeNeIdeLista(Dijete d){
		DefaultListModel<Aktivnost> model = new DefaultListModel<Aktivnost>();
		ArrayList<Aktivnost> niz = vratiDostupneAktivnostiZaDijete(d);
		for(Aktivnost val : niz){
			model.addElement(val);
		}
			return model;
	}
	
	public ArrayList<Aktivnost> vratiDostupneAktivnostiZaDijete(Dijete d){
		ArrayList<Aktivnost> pohadja = vratiAktivnostiDjeteta(d);
		ArrayList<Aktivnost> sveAktivnosti = SveAktivnosti();
		ArrayList<Aktivnost> dostupne = new ArrayList<Aktivnost>();
		if(!pohadja.isEmpty()){ 
		sveAktivnosti.removeAll(pohadja);}
		 return sveAktivnosti;
	
	}
	public ArrayList<Aktivnost> vratiAktivnostiDjeteta(Dijete d){
		ArrayList<Aktivnost> spisakAktivnosti = new ArrayList<Aktivnost>();
		Set<Aktivnostidjeca>skupMedjutabela = d.getAktivnostidjecas();
		if(skupMedjutabela!=null){
		Aktivnostidjeca[] medju = skupMedjutabela.toArray(new Aktivnostidjeca[skupMedjutabela.size()]);
	    for(int i=0; i<medju.length;i++){
	    	spisakAktivnosti.add(medju[i].getAktivnost());
	    	}
		}
	    return new ArrayList<Aktivnost>(spisakAktivnosti);
	    
	}
	
	public boolean obrisiAktivnostiDjeteta(Dijete d){
		Criteria c = s.createCriteria(Aktivnostidjeca.class);
		c.add(Restrictions.eq("dijete",d));
		ArrayList<Aktivnostidjeca> listaAkt = new ArrayList<Aktivnostidjeca>(c.list());
				if(listaAkt == null || listaAkt.size()==0) return false;
				ads.obrisiListu(listaAkt);
				return true;
				
		
	}
	
}
