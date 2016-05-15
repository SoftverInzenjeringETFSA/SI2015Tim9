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
import org.junit.After;
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
    @Before
    public void setUp() throws Exception{
    	ArrayList<Korisnik> svi_korisnici = ks.dajKorisnike();
    	boolean ima=false;
    	for(Korisnik kor: svi_korisnici){
    		if(kor.getIdKorisnika()==123){
    			ima=true;
    			break;
    		}
    			
    	}
    	if(ima==false){
    		Korisnik k = new Korisnik();
    		k.setBrojTelefona("033225883");
    		k.setIdKorisnika(123);
    		k.setIme("NekoIme");
    		k.setKorisnickoIme("username_unique");
    		k.setPrezime("Prezime");
    		k.setPrivilegije("direktor");
    		k.setSifra("Sifra");
    		ks.kreirajKorisnika(k);
    	}
    }
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
    	
    		Korisnik k = ks.dajKorisnika(123);
    		k.setKorisnickoIme("HamdoTest");
    		ks.izmjeniKorisnika(k);
    		Korisnik korisnikOpet = ks.dajKorisnika(123);
    		assertEquals(korisnikOpet.getKorisnickoIme(), "HamdoTest");
    	
    }
    /*@Test 
    public void IzbrisiKorisnika() throws Exception{
    	

    		Korisnik brisemo = ks.dajKorisnika(1);
    		ks.izbrisiKorisnika(1);
    		boolean different = brisemo.getKorisnickoIme() != ks.dajKorisnika(1).getKorisnickoIme();
    		assertTrue(different);
    	
    }*/
    
    @Test
    public void DajKorisnikaTest() throws Exception{
    	
    		
    		assertEquals(ks.dajKorisnika(123).getIdKorisnika(), Integer.valueOf(123));

    }
    
   
    
    @Test 
    public void ProvjeriIstiImaTest() throws Exception{
    
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", ks.dajKorisnika(123).getKorisnickoIme() , "hamid123",
    				"direktor", "225883" );
    		brisati.add(k);
    		ks.kreirajKorisnika(k);
    		assertTrue(ks.provjeriDaLiPostojiIstiKorisnik(ks.dajKorisnika(123).getKorisnickoIme()));
    	
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
    @After
    public void tearDown() throws Exception{
    	ks.izbrisiKorisnika(123);
    }
    
}
