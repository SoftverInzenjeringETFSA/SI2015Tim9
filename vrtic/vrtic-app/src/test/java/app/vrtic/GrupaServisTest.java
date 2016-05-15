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

public class GrupaServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
   
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
	@Test 
	public void testGrupaPretragaID() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			Grupa g = gs.PretragaPoIDu(1);
			assertEquals(g.getIdGrupe(), Integer.valueOf(1));
		
	}
	
	@Test
	public void testBrisanjeGrupe() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			Grupa ovasebrise = gs.PretragaPoIDu(1);
			gs.ObrisiGrupu(1);
			Grupa sovomporedimo = gs.PretragaPoIDu(1);
			boolean different = ovasebrise.getNaziv() != sovomporedimo.getNaziv();
			assertTrue(different);
		
	}

	@Test
	public void testDodavanjeGrupe() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			VaspitacServis vs = new VaspitacServis(sesija);
			Vaspitac v = vs.nadji(1);
			Set<Vaspitac> set_vaspitaca = new HashSet<Vaspitac>();
			set_vaspitaca.add(v);
			TerminServis ts = new TerminServis(sesija);
			Termin t = ts.vratiTerminPoId(1);
			Set<Termin> set_termina = new HashSet<Termin>();
			set_termina.add(t);
			DijeteServis ds = new DijeteServis(sesija);
			Dijete d = ds.nadji(1);
			Set<Dijete> set_djece = new HashSet<Dijete>();
			set_djece.add(d);
			Grupa g = new Grupa("imeGrupe", Integer.valueOf(1), Integer.valueOf(20),
					set_vaspitaca, set_djece, set_termina );
			gs.dodajGrupu(g);
			Grupa nadjena = gs.PretragaPoImenu(g.getNaziv());
			assertEquals(nadjena.getNaziv(), g.getNaziv());
			
		
	}
	
	@Test
	public void testPretragaPoImenu() throws Exception{
		
			GrupaServis gs = new GrupaServis(sesija);
			Grupa g = gs.PretragaPoImenu("imeGrupe");//ovo je ovisno o testu prije
			assertEquals(g.getNaziv(), "imeGrupe");
		
	}
}
