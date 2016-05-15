package app.vrtic;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

public class DijeteServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    DijeteServis ds = new DijeteServis(sesija);
	
    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	    	AktivnostServis as = new AktivnostServis(sesija);
	    	DijeteServis ds = new DijeteServis(sesija);
	    	GrupaServis gs = new GrupaServis(sesija);
	    	KorisnikServis ks = new KorisnikServis(sesija);
	    	TerminServis ts = new TerminServis(sesija);
	    	UplataServis us = new UplataServis(sesija);
	    	VaspitacServis vs = new VaspitacServis(sesija);
	    	ZaduzenjeServis zs = new ZaduzenjeServis(sesija);
	    	
	    	Set<Termin> set_t = new HashSet<Termin>();
	    	Set<Aktivnostidjeca> set_ad = new HashSet<Aktivnostidjeca>();
	    	Aktivnost a = new Aktivnost("Aktivnost", 12, 12, set_t, set_ad);
	    	
	    	
	    	Set<Vaspitac> set_v = new HashSet<Vaspitac>();
	    	Set<Dijete> set_d = new HashSet<Dijete>();
	    	Calendar c = Calendar.getInstance();
			Date datum = c.getTime();
	    	Grupa g = new Grupa("Grupa", 12, 13, set_v, set_d, set_t);
	    	
	    	Vaspitac v= new Vaspitac(g, "Ime", "Przime", "033225883", "Adresa");
	    	set_v.add(v);
	    	g.setVaspitacs(set_v);
	    	Termin t = new Termin(a, g, "17:00", "20:00", "ponedjeljak");
	    	set_t.add(t);
	    	g.setTermins(set_t);
	    	 
	       	
	       	Set<Zaduzenja> set_z = new HashSet<Zaduzenja>();
	       	
	    	Set<Uplata> set_u = new HashSet<Uplata>();
	    	
	    	Dijete d = new Dijete(g, "ImeDjeteta", "PrezimeDjeteta", "2011-12-12", "Adresa", "Roditelj", "033225883", "Prezime",
	    			datum, datum, set_u, set_ad, set_z);
	    	Zaduzenja z = new Zaduzenja(d, "januar", 2016);
	    	Uplata u = new Uplata(d, datum, 100.5, 2, 2015);
	    	set_u.add(u);
	    	d.setUplatas(set_u);
	    	set_z.add(z);
	    	d.setZaduzenjas(set_z);
	    	Korisnik k = new Korisnik("ImeKorisnika", "PrezimeKorisnika", "usename", "sifra", "direktor", "033225883");
	    	k.setIdKorisnika(1);
	    	u.setIdUplate(1);
	    	z.setIdZaduzenja(1);
	    	d.setIdDijete(1);
	    	g.setIdGrupe(1);
	    	v.setIdVaspitac(1);
	    	a.setIdAktivnosti(1);
	    	t.setIdTermin(1);
	    	if(ts.vratiTerminPoId(1) == null){
		       	ts.dodajTermin(t);
		    	}
	    	if(as.pretragaPoIDu(1) == null){
	    	as.dodajAktivnost(a);
	    	}
	    
	    	if(ks.dajKorisnika(1) == null){
	    	ks.kreirajKorisnika(k);
	    	}
	    	
	    	/*if(== null){
	    	us.evidentirajUplatu(u);
	    	}*/
	    	  
	    	if(gs.PretragaPoIDu(1) == null){
		    	gs.dodajGrupu(g);
		    	}
	    	if(vs.nadji(1) == null){
		    	vs.evidentiraj(v);
		    	}  
	    	if(ds.nadji(1) == null){
		    	ds.evidentiraj(d);
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
		d.setGrupa(gs.PretragaPoIDu(1));
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
		
			Dijete obrisano = ds.nadji(2);
			ds.obrisi(2);
			Dijete naIndeksuTom = ds.nadji(2);
			assertFalse(obrisano==naIndeksuTom);
		
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
