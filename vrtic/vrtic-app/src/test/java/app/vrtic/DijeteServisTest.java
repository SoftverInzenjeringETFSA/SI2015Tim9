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

public class DijeteServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    DijeteServis ds = new DijeteServis(sesija);
    GrupaServis gs = new GrupaServis(sesija);
    Grupa g = new Grupa();
    @Before
    public void setUp() throws Exception{
    	boolean nasli = false;
    	ArrayList<Dijete> svaDjeca = ds.svaDjeca();
    	for(Dijete dijete:svaDjeca){
    		if(dijete.getIdDijete()==1){
    			nasli=true;
    			break;
    		}
    	}
    	if(!nasli){
    	g.setIdGrupe(1);
    	g.setKapacitet(12);
    	g.setNaziv("Grupica");
    	g.setRedniBroj(1);
    	gs.dodajGrupu(g);
    	}
    	
    	
    	
    	
    	
    	
    }

	@Test
	public void testEvidentiraj() throws Exception {
		
		Dijete d = new Dijete();
		GrupaServis gs = new GrupaServis(sesija);
		Calendar c = Calendar.getInstance();
		Date datum = c.getTime();
		d.setAdresaPrebivalista("test");
		d.setBrojTelefona("033225883");
		d.setDatumIsteka(datum);
		d.setDatumRodjenja("1999-02-02");
		d.setDatumUpisa(datum);
		d.setGrupa(g);
		d.setIme("TestDijete");
		d.setImeRoditelja("TestRoditelj");
		d.setPrezime("testPrezime");
		d.setPrezimeRoditelja("prezimeRoditelja");
		ds.evidentiraj(d);
		ArrayList<Dijete> sva_djeca = ds.svaDjeca();
		Dijete nadjeno = new Dijete();
		for(Dijete dijete: sva_djeca){
			if(dijete.getIme().equalsIgnoreCase("TestDijete")){
				nadjeno = dijete;
				break;
			}
		}
		
		assertTrue(d.getAdresaPrebivalista().equals("test"));
		assertTrue(d.getBrojTelefona().equals("033225883"));
		assertEquals(d.getDatumIsteka().compareTo(datum), 0);
		assertTrue(d.getDatumRodjenja().equals("1999-02-02"));
		assertEquals(d.getDatumUpisa().compareTo(datum), 0);
		assertEquals(d.getGrupa(), gs.PretragaPoIDu(1));
		assertTrue(d.getIme().equals("TestDijete"));
		assertTrue(d.getImeRoditelja().equals("TestRoditelj"));
		assertTrue(d.getPrezimeRoditelja().equals("prezimeRoditelja"));
		assertTrue(d.getPrezime().equals("testPrezime"));
		
		
		
	}
	@Test
	
	public void ObrisiDijeteTest() throws Exception{
		
					
	}
	
	@Test
	public void nadjiDijeteTest() throws Exception{
//ovdje kreirati dijete pa ga provjeriti sa nadji
		
			assertEquals(ds.nadji(1).getIdDijete(), Integer.valueOf(1));
		
	}
	
	@Test
	public void izmijeniDijeteTest() throws Exception{
		
			Dijete d = ds.nadji(1);
			d.setIme("NovoImeDjeteta");;
			ds.izmijeni(d);
			assertTrue(ds.nadji(1).getIme().equals("NovoImeDjeteta"));
		
	}
	
	/*trebalo bi jos ovu skolarinu testirati, ali onda mi trebaju aktivnosti..*/

}
