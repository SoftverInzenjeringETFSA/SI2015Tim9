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
