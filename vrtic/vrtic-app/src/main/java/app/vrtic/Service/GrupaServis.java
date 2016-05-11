package app.vrtic.Service;

import app.vrtic.Model.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class GrupaServis {
	private Session s;
	
	//konstruktor klase GrupaServis
	public GrupaServis(Session s){
		this.s=s;
	}
	
	//pretraga po ID-u
	public Grupa PretragaPoIDu(int id){
		Transaction transakcija = s.beginTransaction();
		Grupa gr = s.get(Grupa.class, id);
		transakcija.commit();
		return gr;
	}
	
	
	
	//brisanje grupe
	public boolean ObrisiGrupu(int id){
		Transaction transakcija = s.beginTransaction();
		Grupa gr = s.get(Grupa.class, id);
		if(gr != null)
			s.delete(gr);
		transakcija.commit();
		return true;
	}
	
	//dodavanje grupe
	public boolean dodajGrupu(Grupa g){
		Transaction transakcija = s.beginTransaction();
		Grupa gr = new Grupa();
		gr.setDijetes(g.getDijetes());
		gr.setKapacitet(g.getKapacitet());
		gr.setNaziv(g.getNaziv());
		gr.setRedniBroj(g.getRedniBroj());
		gr.setTermins(g.getTermins());
		gr.setVaspitacs(g.getVaspitacs());
		s.save(gr);
		transakcija.commit();
		return true;
	}
	
	// vraca sve grupe
	public ArrayList<Grupa> sveGrupe(){
		List<Grupa> t = s.createCriteria(Grupa.class).list();
		return new ArrayList<Grupa>(t);
	}
	//pretraga grupe po imenu
		
	
	public Grupa PretragaPoImenu(String imeGrupe)
	{
		
		Transaction transakcija = s.beginTransaction();
		Grupa akt = (Grupa) s.get(Grupa.class, imeGrupe);
		transakcija.commit();
		return akt;
	}
}
