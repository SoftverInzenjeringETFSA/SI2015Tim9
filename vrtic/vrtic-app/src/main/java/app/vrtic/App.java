package app.vrtic;

import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import app.vrtic.Util.HibernateUtil;
import app.vrtic.View.login;
import app.vrtic.modeli.Korisnik;
/**
 * Hello world!
 *
 */
public class App 
{
	
	private static Scanner sc = new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
      //Kod za transakcije
    	/*
        Session session = HibernateUtil.getSessionFactory().openSession();
        		//dodajUsera(session);
        		JOptionPane.showMessageDialog(null, dajKorisnike(session).size());
        		session.close();    		
         */
        login lg = new login();
        lg.main(null);
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
