package app.vrtic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.Test;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Termin;
import app.vrtic.Model.Dijete;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.TerminServis;
import app.vrtic.Util.HibernateUtil;
import app.vrtic.View.login;

public class AktivnostiServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();

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
	@Test
	public void testAktivnostPretragaID() throws Exception{
		
			AktivnostServis as = new AktivnostServis(sesija);
			Aktivnost akt = as.pretragaPoIDu(Integer.valueOf(1));
			System.out.println(akt.getNaziv());
			Integer id_akt = akt.getIdAktivnosti();
			assertEquals(Integer.valueOf(id_akt), Integer.valueOf(1));
			
		
	}
	
	@Test
	public void testAktivnostObrisi() throws Exception{
		
			AktivnostServis as = new AktivnostServis(sesija);
			Aktivnost akt = as.pretragaPoIDu(1);
			String ime = akt.getNaziv();
			as.ObrisiAktivnost(1);
			akt = as.pretragaPoIDu(1);
			String s1 = akt.getNaziv();
			boolean different = ime != s1;
			assertTrue(different);
			
	
	}

	@Test
	public void testDodajAktivnost() throws Exception{
		
			AktivnostServis as = new AktivnostServis(sesija);
			Aktivnost a = new Aktivnost();
			TerminServis t = new TerminServis(sesija);
			t.vratiTerminPoId(1);
			Set<Termin> set_t = new HashSet<Termin>();
			a.setNaziv("TestImeAktivnosti");
			a.setBrojDjece(12);
			a.setCijena(12);
			a.setTermins(set_t);
			as.dodajAktivnost(a);
			ArrayList<Aktivnost> sveaktivnosti = as.SveAktivnosti();
			assertTrue(sveaktivnosti.contains(a));	
			
	}
	

}
