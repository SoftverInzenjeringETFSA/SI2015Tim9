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

public class TerminServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    TerminServis ts = new TerminServis(sesija);
    
	@Test
	public void DodajTerminTest() throws Exception {
		
		
		AktivnostServis as = new AktivnostServis(sesija);
		GrupaServis gs = new GrupaServis(sesija);
		Aktivnost a = as.pretragaPoIDu(1) ; 
		Grupa g = gs.PretragaPoIDu(1);
		Termin t = new Termin(a, g, "11:00", "15:00", "Ponedjeljak");
		ts.dodajTermin(t);
		assertEquals(ts.vratiTerminPoId(t.getIdTermin()), t);
		
		
	}
	
	@Test 
	public void VratiTerminPoIdTest()throws Exception{
		
			assertEquals(Integer.valueOf(1), ts.vratiTerminPoId(1).getIdTermin() );
		
	}
	
	@Test
	public void ObrisiTermin() throws Exception{
		
			Termin t = ts.vratiTerminPoId(1);
			ts.ObrisiTermin(1);
			boolean difference = t != ts.vratiTerminPoId(1);
			assertTrue(difference);
		
	}

}
