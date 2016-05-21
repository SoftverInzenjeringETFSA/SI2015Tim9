package app.vrtic.View;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Ref;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

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

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class PrikazDjeteta {
private Session s;
private JFrame frmVrti;
final static Logger logger = Logger.getLogger(login.class);
private JScrollPane scrollPane;


public AktivnostServis aktivnostServis;
private DijeteServis ds;
private GrupaServis gs;
private Date stariDatumUgovora;
private AktivnostDjecaServis ads;
private ZaduzenjeServis zs;
public ArrayList<Aktivnost> listaAktivnosti; 

private GlavniProzorDirektor roditelj;
private PrikazGrupe roditelj1;
private int idDjeteta;

public SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
private JTextField textField_3;
private JTextField textField_4;
private JTextField textField_5;
private JTextField textField_6;
/**
 * Launch the application.
 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazDjeteta window = new PrikazDjeteta(s,roditelj,idDjeteta); //dodatiID
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
	// treba dodati ID
	public PrikazDjeteta(Session s,GlavniProzorDirektor ref, int id) {
		this.s = s;
		this.idDjeteta = id;
		this.roditelj = ref;
		this.ds = new DijeteServis(s);
		this.gs = new GrupaServis(s);
		this.aktivnostServis = new AktivnostServis(s);
		this.ads = new AktivnostDjecaServis(s);
		this.zs = new ZaduzenjeServis(s);
		this.listaAktivnosti = aktivnostServis.SveAktivnosti();
		initialize(); 
	}
  
	public PrikazDjeteta(Session s,PrikazGrupe ref, int id) {
		this.s = s;
		this.idDjeteta = id;
		this.roditelj1 = ref;
		this.ds = new DijeteServis(s);
		this.gs = new GrupaServis(s);
		this.aktivnostServis = new AktivnostServis(s);
		this.ads = new AktivnostDjecaServis(s);
		this.zs = new ZaduzenjeServis(s);
		this.listaAktivnosti = aktivnostServis.SveAktivnosti();
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Profil");
		frmVrti.setBounds(100, 100, 517, 726);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		//JOptionPane.showMessageDialog(null,ds.nadji(idDjeteta).getAdresaPrebivalista());
		final DefaultListModel<Aktivnost> parts = aktivnostServis.vratiAktivnostiDjetetaZaListu(ds.nadji(idDjeteta));
		final JList list_1 = new JList(parts);
		list_1.setEnabled(false);
		final DefaultListModel<Aktivnost> partsSelected = aktivnostServis.vratiAktivnostiNaKojeNeIdeLista(ds.nadji(idDjeteta));
		final JList list = new JList(partsSelected);
		list.setEnabled(false);
		
		//JList list = new JList();
		//JList list_1 = new JList();
		/*JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(365, 11, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		
		JButton btnNewButton = new JButton("Promjena \u0161ifre");
		btnNewButton.setBounds(229, 11, 126, 23);
		frmVrti.getContentPane().add(btnNewButton);
		*/
		JLabel lblIme = new JLabel("Ime djeteta:");
		lblIme.setBounds(71, 62, 79, 14);
		frmVrti.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime djeteta:");
		lblPrezime.setBounds(45, 100, 95, 14);
		frmVrti.getContentPane().add(lblPrezime);
		
		JLabel lblNewLabel = new JLabel("Datum rodjenja:");
		lblNewLabel.setBounds(45, 137, 95, 14);
		frmVrti.getContentPane().add(lblNewLabel);
		
		JLabel lblBrojTelefona = new JLabel("Ime staratelja:");
		lblBrojTelefona.setBounds(55, 172, 95, 14);
		frmVrti.getContentPane().add(lblBrojTelefona);
		
		JLabel lblUloga = new JLabel("Prezime staratelja:");
		lblUloga.setBounds(26, 219, 130, 14);
		frmVrti.getContentPane().add(lblUloga);
		
		JButton btnIzmijeni = new JButton("Zatvori");
		btnIzmijeni.setBounds(255, 653, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		
		JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja:");
		lblAdresaStanovanja.setBounds(22, 262, 128, 14);
		frmVrti.getContentPane().add(lblAdresaStanovanja);
		
		JLabel lblBrojTelefonaStaratelja = new JLabel("Broj telefona staratelja:");
		lblBrojTelefonaStaratelja.setBounds(0, 300, 140, 14);
		frmVrti.getContentPane().add(lblBrojTelefonaStaratelja);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(71, 448, 46, 14);
		frmVrti.getContentPane().add(lblGrupa);
		
		JLabel lblAktivnosti = new JLabel("Aktivnosti:");
		lblAktivnosti.setFont(new Font("Sylfaen", Font.BOLD, 14));
		lblAktivnosti.setBounds(222, 321, 79, 14);
		frmVrti.getContentPane().add(lblAktivnosti);
		
		JLabel lblDatumUpisaU = new JLabel("Datum upisa u vrti\u0107:");
		lblDatumUpisaU.setBounds(22, 484, 128, 14);
		frmVrti.getContentPane().add(lblDatumUpisaU);
		
		JLabel lblDatumIstekaUgovora = new JLabel("Datum isteka ugovora:");
		lblDatumIstekaUgovora.setBounds(6, 520, 128, 14);
		frmVrti.getContentPane().add(lblDatumIstekaUgovora);
		
		JLabel lblNapomena = new JLabel("Napomena:");
		lblNapomena.setBounds(67, 564, 128, 14);
		frmVrti.getContentPane().add(lblNapomena);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(166, 553, 213, 91);
		frmVrti.getContentPane().add(textPane);
		final JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBounds(167, 445, 188, 20);
		frmVrti.getContentPane().add(comboBox);
		
		Date d1 = Calendar.getInstance().getTime();
		final JSpinner spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setModel(new SpinnerDateModel(d1, null, null, Calendar.DAY_OF_YEAR));
		spinner.setEditor(new JSpinner.DateEditor(spinner, "dd-MM-yyyy"));
		spinner.setBounds(166, 481, 189, 20);
		frmVrti.getContentPane().add(spinner);
		
		Date d2 = d1;
		d2.setYear(d1.getYear()+1);
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setEnabled(false);
		spinner_1.setModel(new SpinnerDateModel(d2, null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "dd-MM-yyyy"));
		spinner_1.setBounds(166, 517, 189, 20);
		frmVrti.getContentPane().add(spinner_1);
		//Popunjavamo podatke
	
		
		btnIzmijeni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			frmVrti.setAlwaysOnTop(false);
			
			frmVrti.setAlwaysOnTop(true);
			frmVrti.dispose();
			}
		});
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(167, 59, 188, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(166, 97, 188, 20);
		frmVrti.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(167, 134, 188, 20);
		frmVrti.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(167, 169, 188, 20);
		frmVrti.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(167, 216, 188, 20);
		frmVrti.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(167, 259, 188, 20);
		frmVrti.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(166, 290, 188, 20);
		frmVrti.getContentPane().add(textField_6);
		
		JLabel lblDostupne = new JLabel("Dostupne:");
		lblDostupne.setBounds(106, 343, 89, 14);
		frmVrti.getContentPane().add(lblDostupne);
		
		JLabel lblPohadja = new JLabel("Pohadja:");
		lblPohadja.setBounds(345, 343, 89, 14);
		frmVrti.getContentPane().add(lblPohadja);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(71, 363, 151, 72);
		frmVrti.getContentPane().add(scrollPane_1);
		
		//JList list = new JList();
		scrollPane_1.setViewportView(list);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(295, 362, 151, 72);
		frmVrti.getContentPane().add(scrollPane_2);
		
		//JList list_1 = new JList();
		scrollPane_2.setViewportView(list_1);
		
		JButton button = new JButton("> >");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			    if(list.getSelectedIndex()==-1) {
			    	frmVrti.setAlwaysOnTop(false);
			    	 JOptionPane.showMessageDialog(null,"Niste odabrali aktivnost!");			    	
			    	frmVrti.setAlwaysOnTop(true);
			    }
			     else {
			    	 for (Object selectedValue : list.getSelectedValuesList()){
			    		 parts.addElement((Aktivnost)selectedValue);
			    		 partsSelected.remove(list.getSelectedIndex());
			    	 }
			    
			     }
				
			}
		});
		button.setBounds(232, 377, 53, 23);
		frmVrti.getContentPane().add(button);
		
		JButton button_1 = new JButton("< <");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(list_1.getSelectedIndex()==-1) {
					frmVrti.setAlwaysOnTop(false);
			    	 JOptionPane.showMessageDialog(null,"Niste odabrali aktivnost!");					
					frmVrti.setAlwaysOnTop(true);
				}
			     else {
			    	 for (Object selectedValue : list_1.getSelectedValuesList()){
			    		 partsSelected.addElement((Aktivnost)selectedValue);
			    		 parts.remove(list_1.getSelectedIndex());
			    	 }
			    
			     }
			}
		});
		button_1.setBounds(232, 411, 53, 23);
		frmVrti.getContentPane().add(button_1);
		

		
         //popunjavamo formu
		Dijete d = ds.nadji(idDjeteta);
		stariDatumUgovora = d.getDatumIsteka();
		textField.setText(d.getIme());
		textField_1.setText(d.getPrezime());
		textField_2.setText(d.getDatumRodjenja());
		String str = d.getImeRoditelja();
		String[] pieces = str.split(" ");
		textField_3.setText(pieces[0]);
		textField_4.setText(pieces[1]);
		textField_5.setText(d.getAdresaPrebivalista());
		textField_6.setText(d.getBrojTelefona());
		textPane.setText(d.getPrezimeRoditelja());
		ArrayList<Grupa> grupe = gs.sveGrupe();
		for(int i=0;i< grupe.size();i++){
			comboBox.addItem(grupe.get(i));
		}
		list_1.setModel(parts);
		list.setModel(partsSelected);
	

		comboBox.setSelectedItem(d.getGrupa());
		spinner.getModel().setValue(d.getDatumUpisa());
		spinner_1.getModel().setValue(d.getDatumIsteka());
		
		//napomena fali
		//list.setModel((ListModel) aktivnostServis.vratiAktivnostiDjeteta(d));
		
		
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
			int ocekivanoGodiste = g2-6;  // jer se do 6 godine valjda moze upisati u vrtic :D
			if(g < ocekivanoGodiste) return "Dijete je prestaro za vrtić, ne smije biti starije od "+ ocekivanoGodiste + " godista.";
			if(g > g2) return "Dijete nije rođeno, unesite ispravan datum.";
			
			
			if(g==g2) {
				if(m>m2) return "Dijete nije rođeno, unesite ispravan datum.";
				else {
					if(d>d2) return "Dijete nije rođeno, unesite ispravan datum.";
				}
			}
			
		} catch (Exception e) {
			logger.error(e);
			return "Neispravan datum";
		}
		
		return "";
	}
	
	public Boolean validirajBroj(String broj) {
		
			if (broj.length()<9) return false;
			if (broj.length()>10) return false;
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
	
	//public void popuniFormu(int idDjeteta) {
	
		
		// sad je 1994-01-11
		//String[] s1 = d.getDatumRodjenja().split("-");
		//String[] s2 = d.getDatumUpisa().toString().split("-");
		//String[] s3 = d.getDatumIsteka().toString().split("-");

	//}
	

	

}
