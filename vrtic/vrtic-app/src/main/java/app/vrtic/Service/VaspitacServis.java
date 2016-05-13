package app.vrtic.Service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import app.vrtic.Model.Dijete;
import app.vrtic.Model.Vaspitac;

	public class VaspitacServis {
	private Session s;

	public VaspitacServis(Session s){
	    this.s=s;
    }
	
	public boolean evidentiraj(Vaspitac a)
	{
		Transaction t = s.beginTransaction();
		Vaspitac novi = new Vaspitac();
		
		novi.setGrupa(a.getGrupa());
		novi.setIme(a.getIme());
		novi.setPrezime(a.getPrezime());
		novi.setAdresaPrebivalista(a.getAdresaPrebivalista());
		novi.setBrojTelefona(a.getBrojTelefona());
		
		s.save(novi);
		t.commit();
		System.out.println("Vaspitac evidentiran!");
		return true;
	}
	
	public boolean obrisi(int id) {
		Transaction t = s.beginTransaction();
		
		Vaspitac v = (Vaspitac) s.get(Vaspitac.class, id);
		if (v != null)
			s.delete(v);
		t.commit();
		return true;
	}
	
	public Vaspitac nadji(int id)
	{
		Transaction t = s.beginTransaction();
		Vaspitac v = (Vaspitac) s.get(Vaspitac.class, id);
		t.commit();
		return v;
	}
	
	public boolean izmijeni(Vaspitac d){
		//s.flush();
		//s.clear();
		Transaction t = s.beginTransaction();
		s.update(d);
		t.commit();
		s.flush();
		return true;
	}
	
	
	public ArrayList<Vaspitac> sviVaspitaci(){
		List<Vaspitac> v = s.createCriteria(Vaspitac.class).list();
		return new ArrayList<Vaspitac>(v);
	
	}

}
