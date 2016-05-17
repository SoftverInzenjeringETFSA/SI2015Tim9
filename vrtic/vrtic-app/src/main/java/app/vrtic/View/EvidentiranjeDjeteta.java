package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.AktivnostidjecaId;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Service.AktivnostDjecaServis;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.ZaduzenjeServis;

import javax.swing.JList;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;

import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class EvidentiranjeDjeteta {
	private Session s;
	private AktivnostServis as;
	private GrupaServis gs;
	private AktivnostDjecaServis ads;
	private DijeteServis ds;
	private ZaduzenjeServis zs;
	GlavniProzorDirektor refreshableRoditelj;
	final static Logger logger = Logger.getLogger(login.class);
	private JFrame frmVrti;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private final JSpinner spinner_1;
	private final JSpinner spinner_2;
	

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EvidentiranjeDjeteta window = new EvidentiranjeDjeteta(s,refreshableRoditelj);
					window.frmVrti.setVisible(true);
					window.frmVrti.setAlwaysOnTop(true);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EvidentiranjeDjeteta(Session s,GlavniProzorDirektor roditelj) {
		this.s = s;		
		this.as = new AktivnostServis(s);
		this.gs = new GrupaServis(s);
		this.ads = new AktivnostDjecaServis(s);
		this.ds = new DijeteServis(s);
		this.zs = new ZaduzenjeServis(s);
		this.refreshableRoditelj=roditelj;
		spinner_1 = new JSpinner();
		spinner_2 = new JSpinner();
    	initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Evidentiranje Djeteta");
		frmVrti.setBounds(100, 100, 517, 726);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel lblIme = new JLabel("Ime djeteta:");
		lblIme.setBounds(61, 27, 69, 14);
		
		JLabel lblPrezime = new JLabel("Prezime djeteta:");
		lblPrezime.setBounds(45, 63, 95, 14);
		
		JLabel lblNewLabel = new JLabel("Datum rodjenja:");
		lblNewLabel.setBounds(45, 106, 95, 14);
		
		JLabel lblBrojTelefona = new JLabel("Ime staratelja:");
		lblBrojTelefona.setBounds(51, 152, 112, 14);
		
		JLabel lblUloga = new JLabel("Prezime staratelja:");
		lblUloga.setBounds(33, 194, 130, 14);
		
		JButton Dodaj = new JButton("Dodaj");
		Dodaj.setBounds(255, 653, 126, 23);
		
		JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja:");
		lblAdresaStanovanja.setBounds(35, 232, 128, 14);
		
		JLabel lblBrojTelefonaStaratelja = new JLabel("Broj telefona staratelja:");
		lblBrojTelefonaStaratelja.setBounds(6, 271, 144, 14);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(84, 449, 46, 14);
		
		JLabel lblAktivnosti = new JLabel("Aktivnosti");
		lblAktivnosti.setBounds(216, 299, 79, 14);
		lblAktivnosti.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JLabel lblDatumUpisaU = new JLabel("Datum upisa u vrti\u0107:");
		lblDatumUpisaU.setBounds(22, 484, 128, 14);
		
		JLabel lblDatumIstekaUgovora = new JLabel("Datum isteka ugovora:");
		lblDatumIstekaUgovora.setBounds(6, 520, 128, 14);
		
		JLabel lblNapomena = new JLabel("Napomena:");
		lblNapomena.setBounds(67, 564, 128, 14);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(166, 553, 213, 91);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(85, 347, 110, 68);
		frmVrti.getContentPane().add(scrollPane_1);
		
		frmVrti.getContentPane().setLayout(null);
		frmVrti.getContentPane().add(lblIme);
		frmVrti.getContentPane().add(lblPrezime);
		frmVrti.getContentPane().add(lblNewLabel);
		frmVrti.getContentPane().add(lblBrojTelefona);
		frmVrti.getContentPane().add(lblUloga);
		frmVrti.getContentPane().add(Dodaj);
		frmVrti.getContentPane().add(lblAdresaStanovanja);
		frmVrti.getContentPane().add(lblBrojTelefonaStaratelja);
		frmVrti.getContentPane().add(lblGrupa);
		frmVrti.getContentPane().add(lblAktivnosti);
		frmVrti.getContentPane().add(lblDatumUpisaU);
		frmVrti.getContentPane().add(lblDatumIstekaUgovora);
		frmVrti.getContentPane().add(lblNapomena);
		frmVrti.getContentPane().add(textPane);
		
		Date d = Calendar.getInstance().getTime();
		spinner_1.setBounds(166, 481, 189, 20);
		spinner_1.setModel(new SpinnerDateModel(d, null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "dd-MM-yyyy"));
		frmVrti.getContentPane().add(spinner_1);
		
		Date d2 = d;
		d2.setYear(d.getYear()+1);
		spinner_2.setBounds(166, 517, 189, 20);
		spinner_2.setModel(new SpinnerDateModel(d2, null, null, Calendar.DAY_OF_YEAR));
		spinner_2.setEditor(new JSpinner.DateEditor(spinner_2, "dd-MM-yyyy"));
		frmVrti.getContentPane().add(spinner_2);
		 
		final DefaultListModel<Aktivnost> parts = as.sveAktivnostiLista();
		
		//final DefaultListModel parts = (as.SveAktivnosti().toArray());
		final JList list = new JList(parts);
		
		scrollPane_1.setColumnHeaderView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(319, 347, 110, 68);
		frmVrti.getContentPane().add(scrollPane_2);
		final DefaultListModel<Aktivnost> partSelected = new DefaultListModel<Aktivnost>();
		final JList list_1 = new JList(partSelected);
		scrollPane_2.setViewportView(list_1);
		
       
		
		JLabel lblDostupne = new JLabel(" Dostupne:");
		lblDostupne.setBounds(132, 310, 74, 14);
		frmVrti.getContentPane().add(lblDostupne);
		
		JButton button = new JButton("> >");
		button.setBounds(237, 363, 58, 23);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		     if(list.getSelectedIndex()==-1) 
		    	 JOptionPane.showMessageDialog(null,"Niste odabrali aktivnost!");
		     else {
		    	 for (Object selectedValue : list.getSelectedValuesList()){
		    		 partSelected.addElement((Aktivnost)selectedValue);
		    		 parts.remove(list.getSelectedIndex());
		    	 }
		    
		     }
			}
		});
		frmVrti.getContentPane().add(button);
		
		JButton button_1 = new JButton("< <");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(list_1.getSelectedIndex()==-1) 
			    	 JOptionPane.showMessageDialog(null,"Niste odabrali aktivnost!");
			     else {
			    	 for (Object selectedValue : list_1.getSelectedValuesList()){
			    		 parts.addElement((Aktivnost)selectedValue);
			    		 partSelected.remove(list_1.getSelectedIndex());
			    	 }
			    
			     }
			}
		});
		button_1.setBounds(237, 392, 58, 23);
		frmVrti.getContentPane().add(button_1);
		
		JLabel lblPohadja = new JLabel("Pohadja:");
		lblPohadja.setBounds(319, 310, 74, 14);
		frmVrti.getContentPane().add(lblPohadja);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(167, 446, 182, 20);
		frmVrti.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(150, 24, 205, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 60, 205, 20);
		frmVrti.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(150, 149, 205, 20);
		frmVrti.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 191, 205, 20);
		frmVrti.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(150, 229, 205, 20);
		frmVrti.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(150, 268, 205, 20);
		frmVrti.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(150, 103, 205, 20);
		frmVrti.getContentPane().add(textField_6);
		
		JLabel label = new JLabel("dd-mm-yyyy");
		label.setBounds(359, 106, 95, 14);
		frmVrti.getContentPane().add(label);
		ArrayList<Grupa> g = gs.sveGrupe();
		for(int i=0;i< g.size();i++){
			comboBox.addItem(g.get(i));
		}
				
		Dodaj.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Pattern patternIme = Pattern.compile("[a-zA-ZĐđŠšČčĆćŽž]{3,}"); //mogu se unijeti velika,mala slova,brojevi
				Pattern patternJmbg = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$");
				Pattern patternAdresa = Pattern.compile("[a-zA-Z0-9\\,\\sĐđŠšČčĆćŽž]{5,}");
				Pattern patternDatum  = Pattern.compile("^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$");
				
				// validacije
				if(textField.getText().length() < 4) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Ime mora sadržavati barem 3 slova.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternIme.matcher(textField.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos imena.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				if(textField_1.getText().length() < 4) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Prezime mora sadržavati barem 3 slova.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternIme.matcher(textField_1.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos prezimena.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				
				if(textField_6.getText().isEmpty() || !patternDatum.matcher(textField_6.getText()).matches()) { 
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Unesite validan datum.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else {
					String[] datum = textField_6.getText().split("-");
					String ispravanDatum = validirajDatum(datum[0], datum[1], datum[2]);
					if (!ispravanDatum.isEmpty()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, ispravanDatum);
						frmVrti.setAlwaysOnTop(true);
				        return;
				    }
				}
				
			
				if(textField_2.getText().length() < 4) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Ime roditelja mora sadržavati barem 3 slova.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternIme.matcher(textField_2.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos imena roditelja.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				
				if(textField_3.getText().length() < 4) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Prezime roditelja mora sadržavati barem 3 slova.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternIme.matcher(textField_3.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos prezimena roditelja.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				if(textField_4.getText().isEmpty()) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Niste unijeli adresu");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternAdresa.matcher(textField_4.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos adrese.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				if(!validirajBroj(textField_5.getText())) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Broj telefona nije u ispravnom formatu.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}	
				
				
				
		int diffM;
		Date upis = (Date)spinner_1.getValue();
		Date kraj = (Date)spinner_2.getValue();
		int diffG = kraj.getYear() - upis.getYear();
		if(kraj.getMonth()<upis.getMonth())
		diffM =11 -Math.abs((kraj.getMonth()  - upis.getMonth()));
		else {
			diffM =11+Math.abs((kraj.getMonth()  - upis.getMonth()));
		}
		
		
		
		if(!(diffG==1 && diffM==11)){
			frmVrti.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null, "Ugovor se potpisuje na godinu dana.");
			frmVrti.setAlwaysOnTop(true);
			return;
		}
		if(kraj.getYear()==upis.getYear()+1 && upis.getMonth()==kraj.getMonth() && upis.getDate()!=kraj.getDate()){
			frmVrti.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null,"Ugovor se potpisuje na godinu dana.");
			frmVrti.setAlwaysOnTop(true);
			return;
		}
				
			//Dodati validaciju za trajanje ugovora	
				/*long var = ((Date)spinner_2.getValue()).getTime()-((Date)spinner_1.getValue()).getTime();
				if(var<){
					JOptionPane.showMessageDialog(null, "Ugovor se potpisuje na godinu dana!");
				    return;
				}
				*/
				
				Dijete d = new Dijete();
				d.setIme(textField.getText());
				d.setPrezime(textField_1.getText());
				d.setImeRoditelja(textField_2.getText());
				d.setPrezimeRoditelja(textField_3.getText());
				d.setAdresaPrebivalista(textField_4.getText());
				d.setBrojTelefona(textField_5.getText());
				//Napomena fali
				d.setDatumRodjenja(textField_6.getText());
				d.setDatumUpisa((Date) spinner_1.getValue());
				d.setDatumIsteka((Date) spinner_2.getValue());
				d.setGrupa((Grupa) comboBox.getSelectedItem());
				
				int id = ds.evidentirajSaId(d);
				d = ds.nadji(id); 
			    s.flush();
			    refreshableRoditelj.refreshajTabeluDjece();
      // JOptionPane.showMessageDialog(null,partSelected.getSize());
				for(int i=0; i < partSelected.getSize(); i++){
					
				     Aktivnost a =  partSelected.getElementAt(i);  
				    // JOptionPane.showMessageDialog(null,a.getNaziv());
				    // JOptionPane.showMessageDialog(null,partSelected.getElementAt(1).getNaziv());
					AktivnostidjecaId adi = new AktivnostidjecaId();
					
					adi.setIdAktivnosti(a.getIdAktivnosti());
					adi.setIdDijete(id);
					
					//Transaction transakcija = s.beginTransaction(); 
		    		Aktivnostidjeca ad = new Aktivnostidjeca();
		    		
		    		ad.setAktivnost(a);
		    		ad.setDijete(d);
		    		ad.setId(adi);
		    		//s.save(adi);
		    	//	s.save(ad);
		    	//	transakcija.commit(); 
		    		ads.dodajAktivnostDijete(ad);
		    		
		    	 }
				//refreshableRoditelj.refreshajTabeluDjece();
			zs.generisiZaduzenjeZaPeriod(id,((Date)spinner_1.getValue()).getMonth(),((Date)spinner_1.getValue()).getYear());	
			//zs.generisiZaduzenje(id,2011);
				//refreshableRoditelj.refreshajTabeluDjece();
			frmVrti.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null,"Uspjesno ste evidentirali dijete");	
			frmVrti.setAlwaysOnTop(true);
			obrisiPolja();
			}
		});
	
	}
	
	public void obrisiPolja() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		
		Date d = Calendar.getInstance().getTime();
		spinner_1.setModel(new SpinnerDateModel(d, null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "dd-MM-yyyy"));
		
		Date d2 = d;
		d2.setYear(d.getYear()+1);
		spinner_2.setModel(new SpinnerDateModel(d2, null, null, Calendar.DAY_OF_YEAR));
		spinner_2.setEditor(new JSpinner.DateEditor(spinner_2, "dd-MM-yyyy"));
		
	}
	
	public String validirajDatum(String dan, String mjesec, String godina){
		try {
			int d = Integer.parseInt(dan);
			int m = Integer.parseInt(mjesec);
			int g = Integer.parseInt(godina);	
			int monthLength[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			if(g % 400 == 0 || (g % 100 != 0 && g % 4 == 0)) monthLength[1] = 29;
			
			Date treunutniDatum = Calendar.getInstance().getTime();
			
			if(d < 1 || d > 31) return "Neispravan datum";
			if (m < 1 || m > 12) return "Neispravan datum";
			if(d>monthLength[m-1]) return "Neispravan datum";
			
			int g2 = treunutniDatum.getYear()+1900;
			int m2 = treunutniDatum.getMonth()+1;
			int d2 = treunutniDatum.getDate();
	
			if(g < g2-6) return "Dijete je prestaro za vrtic";
			if(g > g2) return "Dijete jos uvijek nije rodjeno, unesi ispravan datum.";
			
			
			if(g==g2) {
				if(m>m2) return "Dijete jos uvijek nije rodjeno, unesi ispravan datum.";
				else {
					if(d>d2) return "Dijete jos uvijek nije rodjeno, unesi ispravan datum.";
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
			return "Neispravan datum";
		}
		
		return "";
	}
	
	public Boolean validirajBroj(String broj) {
		
			if (broj.length()!=9) return false;
			
			//String[] brojTel = broj.split("");
			int[] br = new int[broj.length()];
			
			for(int i=0; i<broj.length(); i++) {
				if (!Character.isDigit(broj.charAt(i))) return false;
				if(i==0 && broj.charAt(i)!='0') return false;
				if(i==1 && (broj.charAt(i)!='3' && broj.charAt(i)!='6')) return false;
				if(broj.charAt(i)<'0' && broj.charAt(i)>'9') return false;
			}
		    return true;
	}
}
