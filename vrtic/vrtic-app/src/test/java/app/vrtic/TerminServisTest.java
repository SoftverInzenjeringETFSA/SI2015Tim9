package app.vrtic;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.Test;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Termin;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.TerminServis;
import app.vrtic.Util.HibernateUtil;
import app.vrtic.View.login;

public class TerminServisTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    TerminServis ts = new TerminServis(sesija);
	@Test
	public void DodajTerminTest() {
		
		try{
		AktivnostServis as = new AktivnostServis(sesija);
		GrupaServis gs = new GrupaServis(sesija);
		Aktivnost a = as.pretragaPoIDu(1) ; 
		Grupa g = gs.PretragaPoIDu(1);
		Termin t = new Termin(a, g, "11:00", "15:00", "Ponedjeljak");
		ts.dodajTermin(t);
		assertEquals(ts.vratiTerminPoId(t.getIdTermin()), t);
		}
		catch(Exception e){
			logger.info(e);
		}
		
		
	}
	
	@Test 
	public void VratiTerminPoIdTest(){
		try{
			
			assertEquals(Integer.valueOf(1), ts.vratiTerminPoId(1).getIdTermin() );
		}
		catch(Exception e){
			
		}
	}
	
	@Test
	public void ObrisiTermin(){
		try{
			Termin t = ts.vratiTerminPoId(1);
			ts.ObrisiTermin(1);
			boolean difference = t != ts.vratiTerminPoId(1);
			assertTrue(difference);
		}
		catch(Exception e){
			logger.info(e);
		}
	}

}
