package app.vrtic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
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

public class GrupaServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    GrupaServis gs = new GrupaServis(sesija);
	/*
	@Test
	public void testGrupaKonst() {
		try{
		GrupaServis gs = new GrupaServis(sesija);
		assertEquals(gs.getS(), sesija);
		}
		catch(Exception e){
			logger.info(e);
		}
	}
	*/
    
    @Before
    public void SetUp() throws Exception{
    	Grupa g = new Grupa();
    	g.setIdGrupe(123);
    	g.setKapacitet(12);
    	g.setNaziv("Grupica");
    	g.setRedniBroj(2);
    	gs.dodajGrupu(g);
    	
    }
	@Test 
	public void testGrupaPretragaID() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			Grupa g = gs.PretragaPoIDu(123);
			assertEquals(g.getIdGrupe(), Integer.valueOf(123));
		
	}
	
	@Test
	public void testBrisanjeGrupe() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			Grupa ovasebrise = gs.PretragaPoIDu(123);
			gs.ObrisiGrupu(123);
			ArrayList<Grupa> sve_grupe = gs.sveGrupe();
			boolean ImaJe=false;
			for(Grupa gr:sve_grupe){
				if(gr.getIdGrupe()==123){
					ImaJe=true;
					break;
				}
			}
			assertFalse(ImaJe);
		
	}

	@Test
	public void testDodavanjeGrupe() throws Exception{
		
			Grupa g = new Grupa();
			g.setKapacitet(10);
			g.setNaziv("NekiJedinstveniNaziv");
			g.setRedniBroj(2);
			
			gs.dodajGrupu(g);
			Grupa nadjena = gs.PretragaPoImenu(g.getNaziv());
			assertTrue(nadjena.getNaziv().equals(g.getNaziv()));
			
			
			
		
	}
	
	@Test
	public void testPretragaPoImenu() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			Grupa g = gs.PretragaPoImenu("Grupica");//ovo je ovisno o testu prije
			assertEquals(g.getNaziv(), "Grupica");
		
	}
}
