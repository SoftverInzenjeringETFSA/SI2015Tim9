package app.vrtic.Service;
import java.util.ArrayList;
import java.util.List;

import app.vrtic.Model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class KorisnikServis {
  private Session s;

	// konstruktor klase AktivnostServis
	public KorisnikServis(Session s) {
		this.s = s;
	}


	public boolean kreirajKorisnika(Korisnik k)	{
		Transaction t = s.beginTransaction();
		Korisnik kor=new Korisnik();

		kor.setIme(k.getIme());
		kor.setPrezime(k.getPrezime());
		kor.setKorisnickoIme(k.getKorisnickoIme());
		kor.setSifra(k.getSifra());
		kor.setPrivilegije(k.getPrivilegije());
		kor.setBrojTelefona(k.getBrojTelefona());

		s.save(kor);
		t.commit();
		return true;
	}

	public boolean izmjeniKorisnika(Korisnik k){
		s.flush();
		s.clear();
		Transaction t = s.beginTransaction();
		s.update(k);
		t.commit();
		s.flush();
		return true;
	}

	public boolean izbrisiKorisnika(int id)	{
		Transaction t = s.beginTransaction();
		Korisnik k=(Korisnik) s.get(Korisnik.class,id);
		if(k!=null)s.delete(k);
		t.commit();
		return true;
	}

	public Korisnik dajKorisnika(int id){
		Transaction t = s.beginTransaction();
		Korisnik k=(Korisnik) s.get(Korisnik.class, id);
		t.commit();
		return k;
	}

	public ArrayList<Korisnik> dajKorisnike(){
		List<Korisnik> k=s.createCriteria(Korisnik.class).list();
		return new ArrayList<Korisnik>(k);
	}

	public boolean provjeriDaLiPostojiIstiKorisnik(String username){
		List<Korisnik> k=dajKorisnike();
		for(int i=0;i<k.size();i++)
		{
			if(k.get(i).getKorisnickoIme().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean provjeriDaLiPostojiIstiOsimUlogovanogKorisnika(String username,Integer idUlogovanog){
		List<Korisnik> k=dajKorisnike();
		for(int i=0;i<k.size();i++)
		{
			if(k.get(i).getKorisnickoIme().equals(username) && k.get(i).getIdKorisnika()!=idUlogovanog) {
				return true;
			}
		}
		return false;
	}
	

	public boolean provjeriSifruKorisnika(String username,String sifra){
		List<Korisnik> k=dajKorisnike();
		for(int i=0;i<k.size();i++)
		{
			if(k.get(i).getKorisnickoIme().equals(username) && k.get(i).getSifra().equals(sifra)) {
				return true;
			}
		}
		return false;
	}

	public boolean promjeniSifru(Korisnik k,String novaSifra){
			k.setSifra(novaSifra);
			izmjeniKorisnika(k);		
		return true;
	}

}
