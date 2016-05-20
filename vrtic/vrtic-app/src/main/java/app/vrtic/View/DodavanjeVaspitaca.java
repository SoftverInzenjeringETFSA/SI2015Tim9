package app.vrtic.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Vaspitac;
import app.vrtic.Service.VaspitacServis;

public class DodavanjeVaspitaca {

	private JFrame frmVrti;
	private Session s;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldBroj;
	private GlavniProzorDirektor ref;
	final static Logger logger = Logger.getLogger(login.class);
	private JTextField textFieldAdresa;
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeVaspitaca window = new DodavanjeVaspitaca(s, ref);
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
	public DodavanjeVaspitaca(Session s, GlavniProzorDirektor ref) {
		this.s = s;
		this.ref = ref;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Dodavanje Vaspita\u010Da");
		frmVrti.setBounds(100, 100, 504, 308);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		JLabel lblNazivAktivnosti = new JLabel("Ime:");
		lblNazivAktivnosti.setBounds(97, 72, 103, 14);
		frmVrti.getContentPane().add(lblNazivAktivnosti);
		
		JLabel lblCijenaAktivnosti = new JLabel("Prezime:");
		lblCijenaAktivnosti.setBounds(76, 111, 103, 14);
		frmVrti.getContentPane().add(lblCijenaAktivnosti);
		
		JButton btnDodajVaspitaca = new JButton("Dodaj vaspita\u010Da");
		btnDodajVaspitaca.setBounds(352, 235, 126, 23);
		frmVrti.getContentPane().add(btnDodajVaspitaca);
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(160, 69, 182, 20);
		frmVrti.getContentPane().add(textFieldIme);
		textFieldIme.setColumns(10);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setColumns(10);
		textFieldPrezime.setBounds(160, 108, 182, 20);
		frmVrti.getContentPane().add(textFieldPrezime);
		
		textFieldBroj = new JTextField();
		textFieldBroj.setColumns(10);
		textFieldBroj.setBounds(160, 148, 182, 20);
		frmVrti.getContentPane().add(textFieldBroj);
		
		JLabel labelBroj = new JLabel("Broj telefona:");
		labelBroj.setBounds(54, 151, 96, 14);
		frmVrti.getContentPane().add(labelBroj);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setColumns(10);
		textFieldAdresa.setBounds(160, 191, 182, 20);
		frmVrti.getContentPane().add(textFieldAdresa);
		
		JLabel labelAdresa = new JLabel("Adresa prebivališta:");
		labelAdresa.setBounds(12, 194, 126, 14);
		frmVrti.getContentPane().add(labelAdresa);
		
		btnDodajVaspitaca.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Pattern patternIme = Pattern.compile("[a-zA-ZĐđŠšČčĆćŽž]{3,}"); //mogu se unijeti velika,mala slova,brojevi
				if(textFieldIme.getText().length() < 3) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Ime mora sadržavati barem 3 slova.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternIme.matcher(textFieldIme.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos imena.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				if(textFieldPrezime.getText().length() < 3) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Prezime mora sadržavati barem 3 slova.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					if(!patternIme.matcher(textFieldPrezime.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos prezimena.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
				}
				
				// Pattern patternTelefon = Pattern.compile("(\\+|00)?387\\d{2}\\-?\\d{3,4}\\-?\\d{3,4}");
				// dodati validaciju za broj telefona
				if(!validirajBroj(textFieldBroj.getText())) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Broj telefona nije u ispravnom formatu.Zahtjeva se u formatu:06xxxxxxx ili 03xxxxxxx,pri cemu je duzina 10 ili 9 cifara.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				
				if(textFieldAdresa.getText().isEmpty()) {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Unesite adresu.");
					frmVrti.setAlwaysOnTop(true);
					return;
				}
				else { 
					/*if(!patternIme.matcher(textFieldAdresa.getText()).matches()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Neispravan unos adrese.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
					*/
				}
				
				
				
				VaspitacServis vs = new VaspitacServis(s);
				
				Vaspitac v = new Vaspitac();
				v.setIme(textFieldIme.getText());
				v.setPrezime(textFieldPrezime.getText());
				v.setBrojTelefona(textFieldBroj.getText());
				v.setAdresaPrebivalista(textFieldAdresa.getText());
				
				vs.evidentiraj(v);
				frmVrti.setAlwaysOnTop(false);
				JOptionPane.showMessageDialog(null, "Uspjesno ste dodali novog vaspitaca.");
				frmVrti.setAlwaysOnTop(true);
				frmVrti.dispose();
				ref.popuniTabeluVaspitaci();
			}
		});
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
}
