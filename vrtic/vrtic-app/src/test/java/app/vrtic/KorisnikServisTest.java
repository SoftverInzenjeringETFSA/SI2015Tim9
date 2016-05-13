package app.vrtic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import app.vrtic.Model.Korisnik;
import app.vrtic.Service.KorisnikServis;
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
   
    @Test
    public void KreirajKorisnikaTest(){
    	try{
    		
    		
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex", "hamid123",
    				"direktor", "225883" );
    		brisati.add(k);
    		ks.kreirajKorisnika(k);
    		assertTrue(ks.provjeriDaLiPostojiIstiKorisnik("Hamdex"));
    		
    	}
    	catch(Exception e){
    		
    	}
    }
    
    @Test 
    public void IzmjenaKorisnikaTest(){
    	try{

    		Korisnik k = ks.dajKorisnika(1);
    		k.setKorisnickoIme("HamdoTest");
    		ks.izmjeniKorisnika(k);
    		Korisnik korisnikOpet = ks.dajKorisnika(1);
    		assertEquals(korisnikOpet.getKorisnickoIme(), "HamdoTest");
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    @Test 
    public void IzbrisiKorisnika(){
    	try{

    		Korisnik brisemo = ks.dajKorisnika(1);
    		ks.izbrisiKorisnika(1);
    		boolean different = brisemo.getKorisnickoIme() != ks.dajKorisnika(1).getKorisnickoIme();
    		assertTrue(different);
    	}
    	catch(Exception e){
    		
    	}
    }
    
    @Test
    public void DajKorisnikaTest(){
    	try{
   
    		
    		assertEquals(ks.dajKorisnika(1).getIdKorisnika(), Integer.valueOf(1));
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    
   
    
    @Test 
    public void ProvjeriIstiImaTest(){
    	try{
    		
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", ks.dajKorisnika(1).getKorisnickoIme() , "hamid123",
    				"direktor", "225883" );
    		brisati.add(k);
    		ks.kreirajKorisnika(k);
    		assertFalse(ks.provjeriDaLiPostojiIstiKorisnik(ks.dajKorisnika(1).getKorisnickoIme()));
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    
  
    @Test
    public void ProvjeriIstiNemaTest(){
    	try{

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
    		assertTrue(ks.provjeriDaLiPostojiIstiKorisnik(kor.getKorisnickoIme()));
    		}
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    
    @Test
    public void ProvjeriSifruTestIsta(){
    	try{
    		
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex1", "hamid123",
    				"direktor", "225883" );
    		ks.kreirajKorisnika(k);
    		brisati.add(k);
    		assertTrue(ks.provjeriSifruKorisnika(k.getKorisnickoIme(), k.getSifra()));
    		
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    
    @Test
    public void ProvjeriSifruTestNijeIsta(){
    	try{
    		
    		Korisnik k = new Korisnik("Hamdo", "Hamdic", "Hamdex", "hamid123",
    				"direktor", "225883" );
    		ks.kreirajKorisnika(k);
    		brisati.add(k);
    		assertTrue(ks.provjeriSifruKorisnika(k.getKorisnickoIme(), k.getSifra()+"nije"));
    		
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    
    @Test
    public void PromjenaSifreTest(){
    	try{
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
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    @AfterClass
    public static void tearDown(){
    	for(Korisnik k:brisati){
    		ks.izbrisiKorisnika(k.getIdKorisnika());
    	}
    }
    
}
