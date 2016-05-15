package app.vrtic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.AfterClass;
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

public class KorisnikServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    static ArrayList<Korisnik> brisati = new ArrayList<Korisnik>();
    
    static KorisnikServis ks = new KorisnikServis(sesija);
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
	/*@Test
	public void TestKorisnikKonst() {
		try{
			KorisnikServis ks = new KorisnikServis(sesija);
			assertEquals(ks.getS(), sesija();
		}
		catch(Exception e){
			logger.info(e);
		}
	}
	*/
   
    @Test
    public void KreirajKorisnikaTest() throws Exception{
    	
    		
    		
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex", "hamid123",
    				"direktor", "225883" );
    		brisati.add(k);
    		ks.kreirajKorisnika(k);
    		assertTrue(ks.provjeriDaLiPostojiIstiKorisnik("Hamdex"));
    	
    }
    
    @Test 
    public void IzmjenaKorisnikaTest() throws Exception{
    	
    		Korisnik k = ks.dajKorisnika(1);
    		k.setKorisnickoIme("HamdoTest");
    		ks.izmjeniKorisnika(k);
    		Korisnik korisnikOpet = ks.dajKorisnika(1);
    		assertEquals(korisnikOpet.getKorisnickoIme(), "HamdoTest");
    	
    }
    @Test 
    public void IzbrisiKorisnika() throws Exception{
    	

    		Korisnik brisemo = ks.dajKorisnika(1);
    		ks.izbrisiKorisnika(1);
    		boolean different = brisemo.getKorisnickoIme() != ks.dajKorisnika(1).getKorisnickoIme();
    		assertTrue(different);
    	
    }
    
    @Test
    public void DajKorisnikaTest() throws Exception{
    	
    		
    		assertEquals(ks.dajKorisnika(1).getIdKorisnika(), Integer.valueOf(1));

    }
    
   
    
    @Test 
    public void ProvjeriIstiImaTest() throws Exception{
    
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", ks.dajKorisnika(1).getKorisnickoIme() , "hamid123",
    				"direktor", "225883" );
    		brisati.add(k);
    		ks.kreirajKorisnika(k);
    		assertTrue(ks.provjeriDaLiPostojiIstiKorisnik(ks.dajKorisnika(1).getKorisnickoIme()));
    	
    }
    
  
    @Test
    public void ProvjeriIstiNemaTest() throws Exception{
    	

    		ArrayList<Korisnik> lista_svih = ks.dajKorisnike();
    		Set<Korisnik> skup_svih = new HashSet<Korisnik>(lista_svih);
    		//ovaj test ce skloniti sve duple korisnike iz baze!
    		//ne bi smjelo biti potrebe za ovim
    		if(lista_svih.size()>skup_svih.size()){
    			for(int i=0; i<lista_svih.size(); i++){
    				ks.izbrisiKorisnika(lista_svih.get(i).getIdKorisnika());
    			}
    			for(Korisnik kor : skup_svih){
    				ks.kreirajKorisnika(
    						new Korisnik(kor.getIme(), kor.getPrezime(), 
    								kor.getKorisnickoIme(), kor.getSifra(), 
    								kor.getPrivilegije(), kor.getBrojTelefona())
    						
    						);
    			}
    		}
    		lista_svih.clear();
    		lista_svih=ks.dajKorisnike();
    		for(Korisnik kor : lista_svih){
    		assertFalse(ks.provjeriDaLiPostojiIstiKorisnik(kor.getKorisnickoIme()));
    		}
    	
    }
    
    @Test
    public void ProvjeriSifruTestIsta() throws Exception{
    	
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex1", "hamid123",
    				"direktor", "225883" );
    		ks.kreirajKorisnika(k);
    		brisati.add(k);
    		boolean different =ks.provjeriSifruKorisnika(k.getKorisnickoIme(), k.getSifra());
    		assertTrue(different);
    	
    }
    
    @Test
    public void ProvjeriSifruTestNijeIsta() throws Exception{
    	
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex", "hamid123",
    				"direktor", "225883" );
    		ks.kreirajKorisnika(k);
    		brisati.add(k);
    		boolean different=ks.provjeriSifruKorisnika(k.getKorisnickoIme(), k.getSifra()+"nije");
    		assertFalse(different);
    	
    }
    
    @Test
    public void PromjenaSifreTest() throws Exception{
    
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex", "hamid123",
    				"direktor", "225883" );
    		ks.kreirajKorisnika(k);
    		ks.promjeniSifru(k, "novasifra");
    		//sad nam treba id tog korisnika da ga nadjemo
    		//mozda bi trebala metoda da se trazi po usernameu
    		Korisnik provjera = new Korisnik();
    		List<Korisnik> k_lista = ks.dajKorisnike();
    		for(Korisnik k_var:k_lista){
    			if(k_var.getKorisnickoIme()==k.getKorisnickoIme()){
    				provjera = k_var;
    			}
    		}
    		
    		assertEquals("novasifra", provjera.getSifra());
    		
    		
    }
    @AfterClass
    public static void tearDown() throws Exception{
    	for(Korisnik k:brisati){
    		ks.izbrisiKorisnika(k.getIdKorisnika());
    		System.out.println(k.getIme());
    	}
    }
    
}
