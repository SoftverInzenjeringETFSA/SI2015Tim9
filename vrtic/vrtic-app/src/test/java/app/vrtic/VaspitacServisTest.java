package app.vrtic;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Korisnik;
import app.vrtic.Model.Termin;
import app.vrtic.Model.Uplata;
import app.vrtic.Model.Vaspitac;
import app.vrtic.Model.Zaduzenja;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.KorisnikServis;
import app.vrtic.Service.TerminServis;
import app.vrtic.Service.UplataServis;
import app.vrtic.Service.VaspitacServis;
import app.vrtic.Service.ZaduzenjeServis;
import app.vrtic.Util.HibernateUtil;
import app.vrtic.View.login;

public class VaspitacServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    VaspitacServis vs = new VaspitacServis(sesija);
    
    @BeforeClass
  	public static void setUpBeforeTest() throws Exception {
  		
  	    	AktivnostServis as = new AktivnostServis(sesija);
  	    	DijeteServis ds = new DijeteServis(sesija);
  	    	GrupaServis gs = new GrupaServis(sesija);
  	    	KorisnikServis ks = new KorisnikServis(sesija);
  	    	TerminServis ts = new TerminServis(sesija);
  	    	UplataServis us = new UplataServis(sesija);
  	    	VaspitacServis vs = new VaspitacServis(sesija);
  	    	ZaduzenjeServis zs = new ZaduzenjeServis(sesija);
  	    	Aktivnost a = new Aktivnost();
  	    	Grupa g = new Grupa();
  	    	Termin t = new Termin();
  	    	Korisnik k = new Korisnik();
  	    	Vaspitac v = new Vaspitac();
  	    	Dijete d = new Dijete();
  	    	Calendar c = Calendar.getInstance();
  			Date datum = c.getTime();
  	    	if(as.pretragaPoIDu(1) == null){
  	    		
  	    		a.setBrojDjece(10);
  	    		a.setCijena(10);
  	    		a.setIdAktivnosti(1);
  	    		a.setNaziv("Aktivnost");
  	    		
  	    		as.dodajAktivnost(a);
  	    	}
  	    	if(gs.PretragaPoIDu(1) == null){
  	    		
  	    		g.setIdGrupe(1);
  	    		g.setKapacitet(10);
  	    		g.setNaziv("Grupa");
  	    		g.setRedniBroj(2);
  	    		
  		    	gs.dodajGrupu(g);
  		    	}
  	    	if(ts.vratiTerminPoId(1) == null){
  	    		t.setIdTermin(1);
  	    		t.setAktivnost(a);
  	    		t.setDan("Ponedjeljak");
  	    		t.setGrupa(g);
  		       	ts.dodajTermin(t);
  		   
  	    	}
  	    	if(ks.dajKorisnika(1) == null){
  	    		k.setBrojTelefona("033225883");
  	    		k.setIdKorisnika(1);
  	    		k.setIme("Korisnik");
  	    		k.setKorisnickoIme("username");
  	    		k.setPrezime("Prezime");
  	    		k.setPrivilegije("direktor");
  	    		k.setSifra("sifra");
  	    	ks.kreirajKorisnika(k);
  	    	}
  	    	
  	    	/*if(== null){
  	    	us.evidentirajUplatu(u);
  	    	}*/
  	    	
  	    	if(vs.nadji(1) == null){
  	    		v.setAdresaPrebivalista("adresa");
  	    		v.setBrojTelefona("033225883");
  	    		v.setGrupa(g);
  	    		v.setIdVaspitac(1);
  	    		v.setIme("Ime");
  	    		v.setPrezime("Prezime");
  		    	vs.evidentiraj(v);
  		    	}  
  	    	if(ds.nadji(1) == null){
  	    		d.setAdresaPrebivalista("Adresa");
  	    		d.setBrojTelefona("033225883");
  	    		d.setDatumIsteka(datum);
  	    		d.setDatumRodjenja("2000-12-12");
  	    		d.setDatumUpisa(datum);
  	    		d.setGrupa(g);
  	    		d.setIdDijete(1);
  	    		d.setIme("Dijet");
  	    		d.setImeRoditelja("roditelj");
  	    		d.setPrezime("Prezime");
  	    		d.setPrezimeRoditelja("Prezime");
  	    		
  		    	ds.evidentiraj(d);
  		    	}
  		    	
  	  
  	}
	@Test
	public void EvidentirajTest() throws Exception {
		
			GrupaServis gs = new GrupaServis(sesija);
			Vaspitac v = new Vaspitac(gs.PretragaPoIDu(1), "testVaspitac",
					"testPrezime", "033225883", "adresa");
			vs.evidentiraj(v);
			assertTrue(vs.sviVaspitaci().contains(v));
		
	}
	
	@Test
	public void ObrisiVaspitacaTest() throws Exception{
		
			Vaspitac brisemoGa = vs.nadji(1);
			vs.obrisi(1);
			Vaspitac novi = vs.nadji(1);
			assertFalse(novi==brisemoGa);
		
	}
	
	@Test
	public void NadjiTest() throws Exception{
		
			assertEquals(vs.nadji(1).getIdVaspitac(), Integer.valueOf(1));
		
	}
	
	@Test
	public void IzmijeniVaspitacaTest() throws Exception{

			Vaspitac v = vs.nadji(1);
			v.setIme("NovoNekoIme");
			vs.izmijeni(v);
			assertEquals(vs.nadji(1).getIme(), "NovoNekoIme");
		
	}

}
