package app.vrtic.Service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import app.vrtic.Model.Dijete;



public class DijeteServis {

	private Session s;

	public DijeteServis(Session s){
	    this.s=s;
    }
	
	public boolean evidentiraj(Dijete a)
	{
		Transaction t = s.beginTransaction();
		Dijete novo = new Dijete();
		
		novo.setGrupa(a.getGrupa());
		novo.setIme(a.getIme());
		novo.setPrezime(a.getPrezime());
		novo.setDatumRodjenja(a.getDatumRodjenja());
		novo.setAdresaPrebivalista(a.getAdresaPrebivalista());
		novo.setImeRoditelja(a.getImeRoditelja());
		novo.setBrojTelefona(a.getBrojTelefona());
		novo.setPrezimeRoditelja(a.getPrezimeRoditelja());
		novo.setDatumUpisa(a.getDatumUpisa());
		novo.setDatumIsteka(a.getDatumIsteka());
		
		
		s.save(novo);
		t.commit();
		System.out.println("Dijete evidentirano!");
		return true;
	}
	
	public boolean obrisi(int id) {
		Transaction t = s.beginTransaction();
		
		Dijete d = (Dijete) s.get(Dijete.class, id);
		if (d != null)
			s.delete(d);
		t.commit();
		return true;
	}
	
	public Dijete nadji(int id)
	{
		Transaction t = s.beginTransaction();
		Dijete akt = (Dijete) s.get(Dijete.class, id);
		t.commit();
		return akt;
	}
	
	public Dijete izmijeni(Dijete d){
		Transaction t = s.beginTransaction();
		s.merge(d);
		t.commit();
		long l=d.getIdDijete();
		int id=(int) l;
		return nadji(id);
	}
	
	
	public ArrayList<Dijete> svaDjeca(){
		List<Dijete> djeca = s.createCriteria(Dijete.class).list();
		return new ArrayList<Dijete>(djeca);
	
	}
	
	

}
