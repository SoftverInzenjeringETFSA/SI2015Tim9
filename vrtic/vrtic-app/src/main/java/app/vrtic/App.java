package app.vrtic;

import org.hibernate.Transaction;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Korisnik;
import app.vrtic.Util.HibernateUtil;
import app.vrtic.View.login;
/**
 * Hello world!
 *
 */

public class App 
{	final static Logger logger = Logger.getLogger(login.class);
	
	private static Scanner sc = new Scanner(System.in);
    public static void main( String[] args )
    {
    	try{
        System.out.println( "Hello World!" );
        Session session = HibernateUtil.getSessionFactory().openSession();
      //Kod za transakcije
    	/*
      
        		//dodajUsera(session);
        		JOptionPane.showMessageDialog(null, dajKorisnike(session).size());
        		session.close();    		
         */
        login lg = new login(session);
        lg.OtvoriFormu();
    	}
    	catch(Exception e){
    		logger.info(e);
    	}
    }
    /*
    public static ArrayList<Korisnik> dajKorisnike(Session s){
    	List<Korisnik> k= s.createCriteria(Korisnik.class).list();
    	return new ArrayList<Korisnik>(k);
    }
     
    private static void dodajUsera(Session session) {
    Transaction t = session.beginTransaction();
    Korisnik s = new Korisnik();
    s.setIme("Meho");
    s.setPrezime("Mehic");
    s.setKorisnickoIme("admin52");
    s.setSifra("admin2");
    s.setPrivilegije("Blagajnik");
    s.setBrojTelefona("032456789");
   
    session.save(s);
    System.out.println("Dodan korisnika");
    t.commit();
    }
    */
   
}
