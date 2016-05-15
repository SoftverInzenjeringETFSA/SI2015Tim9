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
	    	
	    	
	    	
	    	Set<Vaspitac> set_v = new HashSet<Vaspitac>();
	    	Set<Dijete> set_d = new HashSet<Dijete>();
	    	Calendar c = Calendar.getInstance();
			Date datum = c.getTime();
	    	Grupa g = new Grupa("Grupa", 12, 13, set_v, set_d, set_t);
	    	
	    	Vaspitac v= new Vaspitac(g, "Ime", "Przime", "033225883", "Adresa");
	    	set_v.add(v);
	    	g.setVaspitacs(set_v);
	    	Aktivnost a = new Aktivnost("Aktivnost", 12, 12, set_t, set_ad);
	    	Termin t = new Termin(a, g, "17:00", "20:00", "ponedjeljak");
	    	t.setIdTermin(1);
	    	set_t.add(t);
	    	g.setTermins(set_t);
	    	a.setTermins(set_t);
	    	
	    	
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
	    	
	    	  
 
	    	if(as.pretragaPoIDu(1) == null){
	    		as.dodajAktivnost(a);
	    	}
	    	if(gs.PretragaPoIDu(1) == null){
		    	gs.dodajGrupu(g);
		    	}
	    	if(ts.vratiTerminPoId(1) == null){
		       	ts.dodajTermin(t);
		   
	    	}
	    	if(ks.dajKorisnika(1) == null){
	    	ks.kreirajKorisnika(k);
	    	}
	    	
	    	/*if(== null){
	    	us.evidentirajUplatu(u);
	    	}*/
	    	
	    	if(vs.nadji(1) == null){
		    	vs.evidentiraj(v);
		    	}  
	    	if(ds.nadji(1) == null){
		    	ds.evidentiraj(d);
		    	}
		    	
	  
	}
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
