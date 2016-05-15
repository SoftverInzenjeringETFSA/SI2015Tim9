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
	    	if(as.pretragaPoIDu(1) == null){
	    	as.dodajAktivnost(a);
	    	}
	    	if(ds.nadji(1) == null){
	    	ds.evidentiraj(d);
	    	}
	    	if(gs.PretragaPoIDu(1) == null){
	    	gs.dodajGrupu(g);
	    	}
	    	if(ks.dajKorisnika(1) == null){
	    	ks.kreirajKorisnika(k);
	    	}
	    	if(ts.vratiTerminPoId(1) == null){
	       	ts.dodajTermin(t);
	    	}
	    	/*if(== null){
	    	us.evidentirajUplatu(u);
	    	}*/
	    	if(vs.nadji(1) == null){
	    	vs.evidentiraj(v);
	    	}    	
	  
	}

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
