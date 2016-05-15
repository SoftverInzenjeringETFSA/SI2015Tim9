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

import java.awt.Component;
public class PromjenaSifre {

	private JFrame frmVrti;
	private JPasswordField stara;
	private JPasswordField nova;
	private JPasswordField ponovljenaNova;
	private Session s;
	int id;
	Korisnik k;
	
	final static Logger logger = Logger.getLogger(login.class);
	/**
	 * Launch the application.
	 */

	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromjenaSifre window = new PromjenaSifre(s,id);
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
	public PromjenaSifre(Session s,int id) {
		this.id=id;
		KorisnikServis us = new KorisnikServis(s);
		k = us.dajKorisnika(id);
		this.s = s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setResizable(false);
		frmVrti.setTitle("Vrti\u0107 - Promjena \u0160ifre");
		frmVrti.setBounds(100, 100, 450, 300);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JLabel lblKorisnickaSifra = new JLabel("Nova \u0161ifra:");
		lblKorisnickaSifra.setBounds(83, 95, 110, 14);
		frmVrti.getContentPane().add(lblKorisnickaSifra);
		
		JButton btnPrijava = new JButton("Promijeni \u0161ifru");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//labela koja proslijeduje da li je ispravno proslijeden user sa glavne forme
				KorisnikServis ks = new KorisnikServis(s);
				if(String.valueOf(nova.getPassword()).equals("") || String.valueOf(stara.getPassword()).equals("") || String.valueOf(ponovljenaNova.getPassword()).equals("")){
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,
						    "Sva polja nisu popunjena!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					frmVrti.setAlwaysOnTop(true);
				}
				else if(String.valueOf(nova.getPassword()).equals(String.valueOf(ponovljenaNova.getPassword()))==false){
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,
						    "Nova šifra i ponovljena nova šifra nisu jednake!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					frmVrti.setAlwaysOnTop(true);
				}
				else if(String.valueOf(nova.getPassword()).equals(String.valueOf(stara.getPassword()))){
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,
						    "Stara i nova šifra su jednake, molimo promjenite šifru!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					frmVrti.setAlwaysOnTop(true);
				}
				else if(String.valueOf(stara.getPassword()).equals(ks.dajKorisnika(id).getSifra()) == false){
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,
						    "Unijeli ste pogrešnu staru šifru!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					frmVrti.setAlwaysOnTop(true);
				}
				else{
					
					ks.promjeniSifru(k, String.valueOf(nova.getPassword()));
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,
							"Sifra je uspješno promjenjena!",
					    "Obavještenje",
					    JOptionPane.PLAIN_MESSAGE);
					frmVrti.setAlwaysOnTop(true);
				}
			}
		});
		btnPrijava.setBounds(203, 187, 122, 23);
		frmVrti.getContentPane().add(btnPrijava);
		
		JLabel lblStaraifra = new JLabel("Stara \u0161ifra:");
		lblStaraifra.setBounds(83, 51, 110, 14);
		frmVrti.getContentPane().add(lblStaraifra);
		
		JLabel lblPonovljenaNovaifra = new JLabel("Ponovljena nova \u0161ifra:");
		lblPonovljenaNovaifra.setBounds(23, 135, 146, 14);
		frmVrti.getContentPane().add(lblPonovljenaNovaifra);
		
		stara = new JPasswordField();
		stara.setBounds(203, 48, 122, 20);
		frmVrti.getContentPane().add(stara);
		
		nova = new JPasswordField();
		nova.setBounds(203, 92, 122, 20);
		frmVrti.getContentPane().add(nova);
		
		ponovljenaNova = new JPasswordField();
		ponovljenaNova.setBounds(203, 132, 122, 20);
		frmVrti.getContentPane().add(ponovljenaNova);
		
		
	}
}
