package app.vrtic.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Korisnik;
import app.vrtic.Model.Zaduzenja;



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
		novo.setAktivnostidjecas(a.getAktivnostidjecas());
		
		
		s.save(novo);
		t.commit();
		System.out.println("Dijete evidentirano!");
		return true;
	}
	public int evidentirajSaId(Dijete a)
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
		//novo.setAktivnostidjecas(a.getAktivnostidjecas());
		
		
		int id = (Integer)s.save(novo);
		t.commit();
		System.out.println("Dijete evidentirano!");
		return id;
	}

	public boolean obrisi(int id) {
		Transaction t = s.beginTransaction();
		Dijete k=(Dijete) s.get(Dijete.class,id);
		if(k!=null ) { 
			s.delete(k);
			t.commit();
			return true;
		}
		return false;
	}
	
	public Dijete nadji(int id)
	{
		Transaction t = s.beginTransaction();
		Dijete akt = (Dijete) s.get(Dijete.class, id);
		t.commit();
		return akt;
	}
	
	public boolean izmijeni(Dijete d){
		Transaction t = s.beginTransaction();
		if(d!=null)
			s.update(d);
		t.commit();
		s.flush();
		s.clear();
		return true;
		
	}
	
	
	public ArrayList<Dijete> svaDjeca(){
		List<Dijete> djeca = s.createCriteria(Dijete.class).list();
		return new ArrayList<Dijete>(djeca);
	
	}
	
	public List<Dijete> svaDjecaLista(){
		List<Dijete> djeca = s.createCriteria(Dijete.class).list();
		return djeca;
	
	}
	
	
	public double vratiCijenuSkolarine(int idDjeteta){
		double suma=0;
		Dijete d=nadji(idDjeteta);
		Set<Aktivnostidjeca>skupMedjutabela = d.getAktivnostidjecas();	
		Aktivnostidjeca[]medju = skupMedjutabela.toArray(new Aktivnostidjeca[skupMedjutabela.size()]);
	    for(int i=0; i<medju.length;i++)
		suma+=medju[i].getAktivnost().getCijena();
	    return suma;
	}
	
	public boolean mozeSeIzbrisati(Dijete d){
		Criteria c = s.createCriteria(Zaduzenja.class);
		c.add(Restrictions.eq("dijete", d));
		int broj=c.list().size();
		return broj==0;
	}
	

}
