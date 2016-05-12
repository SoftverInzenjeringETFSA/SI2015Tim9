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

public class TestAktivnostiServis {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();

	@Test
	public void testAktivnostKonstr() {


		try{
	        AktivnostServis as = new AktivnostServis(sesija);
			assertEquals(as.getS(), sesija);
		}
		catch(Exception e){
			logger.info(e);
		}
	}
	
	@Test
	public void testAktivnostPretragaID(){
		try{
			AktivnostServis as = new AktivnostServis(sesija);
			Aktivnost akt = as.pretragaPoIDu(Integer.valueOf(1));
			System.out.println(akt.getNaziv());
			Integer id_akt = akt.getIdAktivnosti();
			assertEquals(Integer.valueOf(id_akt), Integer.valueOf(1));
			
		}
		catch(Exception e){
			logger.info(e);
		}
	}
	
	@Test
	public void testAktivnostObrisi(){
		try{
			AktivnostServis as = new AktivnostServis(sesija);
			Aktivnost akt = as.pretragaPoIDu(1);
			String ime = akt.getNaziv();
			as.ObrisiAktivnost(1);
			akt = as.pretragaPoIDu(1);
			String s1 = akt.getNaziv();
			boolean different = ime != s1;
			assertTrue(different);
			
		}
		catch(Exception e){
			logger.info(e);
		}
	}

	@Test
	public void testDodajAktivnost(){
		try{
			AktivnostServis as = new AktivnostServis(sesija);
			TerminServis ts = new TerminServis(sesija);
			DijeteServis ds = new DijeteServis(sesija);
			ArrayList<Dijete> sva_djeca = ds.svaDjeca();
			Termin t = ts.vratiTerminPoId(1);
			Set<Termin> termini = new HashSet<Termin>();
			termini.add(t);
			Set<Dijete> djeca = new HashSet<Dijete>();
			djeca.addAll(sva_djeca);
			
			
			/*Aktivnost a = new Aktivnost("Programiranje", 30, 15, termini, djeca);
			 * ovo ne moze, jer prima set aktivnostidjeca
			 * 
			as.dodajAktivnost(a);*/
			ArrayList<Aktivnost> sve_aktivnosti = as.SveAktivnosti();
		
			Aktivnost pronadjena = new Aktivnost();
			for(int i = 0; i<sve_aktivnosti.size();i++){
				if(sve_aktivnosti.get(i).getNaziv() == "Programiranje"){
					pronadjena = sve_aktivnosti.get(i);
					break;
				}
			}
			assertEquals(pronadjena.getNaziv(), "Programiranje");
			assertEquals(pronadjena.getBrojDjece(), Integer.valueOf(30));
			assertEquals(pronadjena.getCijena(), Integer.valueOf(15));
			assertEquals(pronadjena.getTermins(), termini);
			
			
			
		}
		catch(Exception e){
			logger.info(e);
		}
	}
	
	
	

}
