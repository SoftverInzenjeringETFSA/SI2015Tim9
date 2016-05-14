package app.vrtic.View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Korisnik;
import app.vrtic.Service.KorisnikServis;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class PromjenaSifre {

	private JFrame frmVrti;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Session s;
	KorisnikServis ks;
	Korisnik k;
	
	final static Logger logger = Logger.getLogger(login.class);
	/**
	 * Launch the application.
	 */

	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromjenaSifre window = new PromjenaSifre(s,k);
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
	public PromjenaSifre(Session s,Korisnik kor) {
		this.s = s;
		k=kor;
		ks=new KorisnikServis(this.s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 450, 300);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JLabel lblKorisnickaSifra = new JLabel("Nova \u0161ifra:");
		lblKorisnickaSifra.setBounds(83, 95, 110, 14);
		frmVrti.getContentPane().add(lblKorisnickaSifra);
		
		final JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(318, 193, 56, 16);
		frmVrti.getContentPane().add(lblNewLabel);
		
		JButton btnPrijava = new JButton("Promijeni \u0161ifru");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//labela koja proslijeduje da li je ispravno proslijeden user sa glavne forme
				lblNewLabel.setText(k.getKorisnickoIme());	
				if(passwordField.getText().equals("") || passwordField_1.getText().equals("") || passwordField_2.getText().equals("")){
					JOptionPane.showMessageDialog(null,
						    "Sva polja nisu popunjena!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				else if(!passwordField.getText().equals(passwordField_1.getText())){
					JOptionPane.showMessageDialog(null,
						    "Nova šifra i ponovljena nova šifra nisu jednake!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				else if(passwordField.getText().equals(passwordField_2.getText())){
					JOptionPane.showMessageDialog(null,
						    "Stara i nova šifra su jednake, molimo promjenite šifru!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				else if(!passwordField_2.getText().equals(k.getSifra())){
					JOptionPane.showMessageDialog(null,
						    "Unijeli ste pogrešnu staru šifru!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				else{
				
					/*	ks.promjeniSifru(k, passwordField.getText());
					JOptionPane.showMessageDialog(null,
							"Sifra je uspješno promjenjena!",
					    "Obavještenje",
					    JOptionPane.PLAIN_MESSAGE);*/
				}
			}
		});
		btnPrijava.setBounds(152, 190, 122, 23);
		frmVrti.getContentPane().add(btnPrijava);
		
		JLabel lblStaraifra = new JLabel("Stara \u0161ifra:");
		lblStaraifra.setBounds(83, 51, 110, 14);
		frmVrti.getContentPane().add(lblStaraifra);
		
		JLabel lblPonovljenaNovaifra = new JLabel("Ponovljena nova \u0161ifra:");
		lblPonovljenaNovaifra.setBounds(23, 135, 146, 14);
		frmVrti.getContentPane().add(lblPonovljenaNovaifra);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(203, 48, 122, 20);
		frmVrti.getContentPane().add(passwordField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(203, 92, 122, 20);
		frmVrti.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(203, 132, 122, 20);
		frmVrti.getContentPane().add(passwordField_1);
		
	}
}
