package app.vrtic.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Spring;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import app.vrtic.Model.Dijete;
import app.vrtic.Model.Zaduzenja;

public class ZaduzenjeServis {
	private Session s;
	private DijeteServis ds;

	
	public ZaduzenjeServis(Session s){
		this.s = s;
		ds = new DijeteServis(s);
	
	}
	
	public boolean generisiZaduzenje(int idDjeteta,int godina){
		String[] mjeseci = new String[] {"Januar","Februar","Mart","April","Maj","Juni","Juli","August","Septembar","Oktobar","Novembar","Decembar"};
		Dijete d = ds.nadji(idDjeteta);
		Transaction t = s.beginTransaction();
		Zaduzenja z = new Zaduzenja();
		z.setGodina(godina);
		z.setDijete(d);
		for(int i=0; i<12;i++){
			z.setMjesec(mjeseci[i]);
		}
		t.commit();
		return true;
		}
	
	public boolean generisiZaduzenjeZaPeriod(int idDjeteta,int mjesec,int godina){
		String[] mjeseci = new String[] {"Januar","Februar","Mart","April","Maj","Juni","Juli","August","Septembar","Oktobar","Novembar","Decembar"};
		Dijete d = ds.nadji(idDjeteta);
		int brojMjeseci=0;
		Transaction t = s.beginTransaction();
		
		for(int i=mjesec; i<12;i++){
			//Transaction t = s.beginTransaction();
			Zaduzenja z = new Zaduzenja();	
			z.setDijete(d);
			z.setMjesec(mjeseci[i]);
			z.setGodina(godina+1900);
			brojMjeseci++;
			s.save(z);
			//t.commit();
		}	
		
			for(int i=0;i<12-brojMjeseci;i++){
				//Transaction t = s.beginTransaction();
				Zaduzenja z = new Zaduzenja();	
				z.setDijete(d);		
			z.setGodina(godina+1+1900);
			z.setMjesec(mjeseci[i]);
			s.save(z);
			//t.commit();
			}
		
		
		t.commit();
		return true;
		}
	
	public ArrayList<Zaduzenja> vratiSvaZaduzenja(){
		List<Zaduzenja> zaduzenja = s.createCriteria(Zaduzenja.class).list();
		return new ArrayList<Zaduzenja>(zaduzenja);
	}
	public ArrayList<Zaduzenja> vratiSvaZaduzenjaPoIdDjeteta(int idDjeteta){
		Criteria c = s.createCriteria(Zaduzenja.class);
		List <Zaduzenja> listaZaduzenja = (List <Zaduzenja>) c.add(Restrictions.eq("idDijete", idDjeteta)).list();
	    return new ArrayList<Zaduzenja>(listaZaduzenja);
	}
	
	public ArrayList<Zaduzenja> vratiZaduzenjaPoMjesecu(String mjesec){
		Criteria c = s.createCriteria(Zaduzenja.class);
		List <Zaduzenja> listaZaduzenja = (List <Zaduzenja>) c.add(Restrictions.eq("mjesec",mjesec)).list();
		return new ArrayList<Zaduzenja>(listaZaduzenja);
	}
	
	public ArrayList<Zaduzenja> vratiZaduzenjaPoGodini(int godina){
		Criteria c = s.createCriteria(Zaduzenja.class);
		List <Zaduzenja> listaZaduzenja = (List <Zaduzenja>) c.add(Restrictions.eq("godina",godina)).list();
		return new ArrayList<Zaduzenja>(listaZaduzenja);
	}
	
    public ArrayList<Zaduzenja> vratiZaduzenjaZaGodinu(Dijete d,int godina){
    	Criteria c = s.createCriteria(Zaduzenja.class);
    	c.add(Restrictions.eq("godina", godina));
    	c.add(Restrictions.eq("dijete",d));
    	List <Zaduzenja> listaZaduzenja = (List <Zaduzenja>) c.list();
    	return new ArrayList<Zaduzenja>(listaZaduzenja);
    	
    }
	
	public ArrayList<Zaduzenja> vratiZaduzenjaPoGodiniIMjesecu(int godina,String mjesec){
		Criteria c = s.createCriteria(Zaduzenja.class);
		c.add(Restrictions.eq("godina",godina));
		c.add(Restrictions.eq("mjesec",mjesec));
		List <Zaduzenja> listaZaduzenja = (List <Zaduzenja>) c.list();
		return new ArrayList<Zaduzenja>(listaZaduzenja);
	}
	
	public Zaduzenja vratiSlogZaduzenje(Dijete d,int godina,String mjesec){
		Criteria c = s.createCriteria(Zaduzenja.class);
		c.add(Restrictions.eq("godina",godina));
		c.add(Restrictions.eq("mjesec",mjesec));
		c.add(Restrictions.eq("dijete",d));
		return (Zaduzenja) c.uniqueResult();
		
	}
	
	
	public boolean obrisiZaduzenje(Dijete d,int godina,String mjesec){
		//Dijete d = ds.nadji(idDjeteta);
		Transaction t = s.beginTransaction();
		Zaduzenja z = vratiSlogZaduzenje(d,godina,mjesec);
		if (z != null)
			s.delete(z);
		t.commit();
		return true;
		
	}
	
	public boolean obrisiMjesecnoZaduzenje(Dijete d,int godina,int m){
		String mjesec="";
		Zaduzenja z = new Zaduzenja(); 
        if(m==0) mjesec = "januar";
        if(m==1) mjesec = "februar";
        if(m==2) mjesec = "mart";
        if(m==3) mjesec = "april";
        if(m==4) mjesec = "maj";
        if(m==5) mjesec = "juni";
        if(m==6) mjesec = "juli";
        if(m==7) mjesec = "august";
        if(m==8) mjesec = "septembar";
        if(m==9) mjesec = "oktobar";
        if(m==10) mjesec = "novembar";
        if(m==11) mjesec = "decembar";
		Transaction t = s.beginTransaction();
		z = vratiSlogZaduzenje(d,godina,mjesec);
		if (z != null)
			s.delete(z);
		t.commit();
		return true;
		
	}
	//ovu pozivam kad se prekida                  novi,stari,upis
	public void obrisiZaduzenjaZaPeriod(Dijete d,Date d1,Date d2,Date d3){
		int diffG=d2.getYear()-d1.getYear();
		int diffG1=d1.getYear()-d3.getYear();
		int diffM;
		if(d2.getMonth()>d1.getMonth())//
			diffM =12-Math.abs((d2.getMonth()  - d1.getMonth()));
			else {
				diffM =Math.abs((d2.getMonth()  - d1.getMonth()));
			}
		
			if(diffG1==0 && (d1.getMonth()<d3.getMonth())){ 
				JOptionPane.showMessageDialog(null, "Datum kraja ugovora je prije datuma upisa djeteta u vrtic.");
				return;}
			
			//uslov kad se ugovor skracuje ista godina
		if(diffG==0){
				for(int i=d1.getMonth()+1;i<=d2.getMonth();i++){
					obrisiMjesecnoZaduzenje(d,d1.getYear(),i);
					System.out.println("Uso");
				}
				return;
			}
		//d1 novi,d2 stari  13.11.2016, 13.5.2017
		//skracuje,a razlicite godine datuma ugovora
		if(diffG==1){
			for(int i=0;i<=d2.getMonth();i++){
				
				obrisiMjesecnoZaduzenje(d,d2.getYear(),i);
				System.out.println("Uso");
			}
	for(int i=d1.getMonth();i<=11;i++){
				
				obrisiMjesecnoZaduzenje(d,d1.getYear(),i);
				System.out.println("Uso");
			}
			
			return;
		}
		
		//produzava se
		else{
		//if(!(diffG==1 && diffM==11)){
			//JOptionPane.showMessageDialog(null, "Ugovor se  produzava na godinu dana.");
			//return;
		
		
		if(d1.getYear()==d2.getYear()+1 && d2.getMonth()==d1.getMonth() && d2.getDate()!=d1.getDate()){
			JOptionPane.showMessageDialog(null,"Ugovor se potpisuje na godinu dana.");
			return;
		}
		
			generisiZaduzenjeZaPeriod(d.getIdDijete(),d2.getMonth(),d2.getYear());	
		return;	
		}
		
	}
	
	public Zaduzenja nadji(int idZaduzenja)
	{
		Transaction t = s.beginTransaction();
		Zaduzenja z = (Zaduzenja) s.get(Zaduzenja.class, idZaduzenja);
		t.commit();
		return z;
	}
	public String vratiPodatkeZaIzvjestajPrvaKolona(int idZaduzenja){
		
		Criteria c = s.createCriteria(Zaduzenja.class);
		c.add(Restrictions.eq("idZaduzenja",idZaduzenja));
		Zaduzenja z = (Zaduzenja) c.uniqueResult();
		return z.getDijete().getImeRoditelja()+" "+z.getDijete().getPrezimeRoditelja();
	}
	
public String vratiPodatkeZaIzvjestajDrugaKolona(int idZaduzenja){
		
		Criteria c = s.createCriteria(Zaduzenja.class);
		c.add(Restrictions.eq("idZaduzenja",idZaduzenja));
		Zaduzenja z = (Zaduzenja) c.uniqueResult();
		return z.getDijete().getBrojTelefona();
	}
}
