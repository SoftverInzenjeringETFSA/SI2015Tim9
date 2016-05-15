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
	final static Logger logger = Logger.getLogger(login.class);
	private JTextField textFieldAdresa;
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeVaspitaca window = new DodavanjeVaspitaca(s);
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
	public DodavanjeVaspitaca(Session s) {
		this.s = s;
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
				if(textFieldPrezime.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Ime mora sadržavati barem 3 slova.");
					return;
				}
				
				if(textFieldPrezime.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Prezime mora sadržavati barem 3 slova.");
					return;
				}
				
				if(textFieldAdresa.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Unesite adresu.");
					return;
				}
				
				// Pattern patternTelefon = Pattern.compile("(\\+|00)?387\\d{2}\\-?\\d{3,4}\\-?\\d{3,4}");
				// dodati validaciju za broj telefona
				if(textFieldBroj.getText().length() <= 8) {
					JOptionPane.showMessageDialog(null, "Broj telefona nije u ispravnom formatu.");
					return;
				}
				
				VaspitacServis vs = new VaspitacServis(s);
				
				Vaspitac v = new Vaspitac();
				v.setIme(textFieldIme.getText());
				v.setPrezime(textFieldPrezime.getText());
				v.setBrojTelefona(textFieldBroj.getText());
				v.setAdresaPrebivalista(textFieldAdresa.getText());
				
				vs.evidentiraj(v);
				JOptionPane.showMessageDialog(null, "Uspjesno ste dodali novog vaspitaca.");
			}
		});
	}
}
