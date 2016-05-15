package app.vrtic;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.Test;

import app.vrtic.Model.Dijete;
import app.vrtic.Model.Uplata;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.UplataServis;
import app.vrtic.Util.HibernateUtil;
import app.vrtic.View.login;

public class UplataServistTest {
	final static Logger logger = Logger.getLogger(login.class);
    static Session sesija = HibernateUtil.getSessionFactory().openSession();
    static UplataServis us = new UplataServis(sesija);
	@Test

	public void EvidentirajUplatuTest() throws Exception{
	
			DijeteServis ds = new DijeteServis(sesija);
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();	
		
			Uplata u = new Uplata(ds.nadji(1), d, 100.5, Integer.valueOf(1), Integer.valueOf(1));
			us.evidentirajUplatu(u);
			
			//ovdje dodati assertion
		
		
	}

}
