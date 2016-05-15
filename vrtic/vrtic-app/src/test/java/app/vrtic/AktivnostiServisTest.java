package app.vrtic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.Termin;
import app.vrtic.Model.Uplata;
import app.vrtic.Model.Vaspitac;
import app.vrtic.Model.Zaduzenja;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Korisnik;
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

public class AktivnostiServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    AktivnostServis as = new AktivnostServis(sesija);
/*	@Test
	public void testAktivnostKonstr() {


		try{
	        AktivnostServis as = new AktivnostServis(sesija);
			assertEquals(as.getS(), sesija);
		}
		catch(Exception e){
			logger.info(e);
		}
	}
	*/
    
    @Before
    public void setUp() throws Exception{
    	DefaultListModel<Aktivnost> akt_sve =  as.sveAktivnostiLista();
    	boolean imaLiIndex = false;
    	for(int i=0; i<akt_sve.size(); i++){
    		if(akt_sve.elementAt(i).getIdAktivnosti()==1){
    			imaLiIndex=true;
    			break;
    		}
    		
    	}
    	if(!imaLiIndex){
    	Aktivnost akt = new Aktivnost();
		akt.setBrojDjece(10);
		akt.setCijena(10);
		akt.setNaziv("aktivnost");
		akt.setIdAktivnosti(1);
		as.dodajAktivnost(akt);
    	}
		
    }
    
	@Test
	public void testAktivnostPretragaID() throws Exception{
		
			
			DefaultListModel<Aktivnost> akt_sve =  as.sveAktivnostiLista();
			boolean nasli =false;
			Aktivnost akt_nadjena = new Aktivnost();
			for(int i=0; i < akt_sve.size(); i++){
				if(akt_sve.elementAt(i).getIdAktivnosti()==1 )
				{
					nasli = true;
					akt_nadjena=akt_sve.elementAt(i);
					break;
					
				}
			
			}
			assertTrue(nasli);

			assertEquals(akt_nadjena.getIdAktivnosti(), Integer.valueOf(1));
			
			
		
	}
	
	@Test
	public void testAktivnostObrisi() throws Exception{
		
			AktivnostServis as = new AktivnostServis(sesija);
			as.ObrisiAktivnost(1);
			
			
	
	}

	@Test
	public void testDodajAktivnost() throws Exception{
		
			AktivnostServis as = new AktivnostServis(sesija);
			Aktivnost a = new Aktivnost();
			
			
			a.setNaziv("TestImeAktivnosti");
			
			a.setBrojDjece(12);
			a.setCijena(12);
	
			as.dodajAktivnost(a);
			ArrayList<Aktivnost> sveaktivnosti = as.SveAktivnosti();
			Aktivnost nadjena = new Aktivnost();
			boolean nasli = false;
			for(Aktivnost akt:sveaktivnosti){
				if(akt.getNaziv().equals("TestImeAktivnosti")){
					nadjena = akt;
					nasli= true && (akt.getBrojDjece()==12) && (akt.getCijena()==12);
				}
			}
			assertTrue(nasli);
			
	}
	
	@After
	public void tearDown() throws Exception{
		as.ObrisiAktivnost(1);
	}
	

}
