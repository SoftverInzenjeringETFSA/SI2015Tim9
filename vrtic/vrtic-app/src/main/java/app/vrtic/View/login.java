package app.vrtic.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Korisnik;
import app.vrtic.Service.KorisnikServis;

public class login {
	public JDialog dijalog;
	final static Logger logger = Logger.getLogger(login.class);
	private JFrame frmVrti;
	private JTextField textField;
	private JPasswordField passwordField;
	private Session s;

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login(s);
					window.frmVrti.setVisible(true);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login(Session s) {
		this.s = s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 450, 300);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);

		JLabel lblKorisnickoIme = new JLabel("Korisni\u010Dko ime:");
		lblKorisnickoIme.setBounds(83, 95, 98, 14);
		frmVrti.getContentPane().add(lblKorisnickoIme);

		JLabel lblKorisnickaSifra = new JLabel("Korisni\u010Dka \u0161ifra:");
		lblKorisnickaSifra.setBounds(83, 139, 110, 14);
		frmVrti.getContentPane().add(lblKorisnickaSifra);

		textField = new JTextField();
		textField.setBounds(202, 92, 122, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});

		passwordField = new JPasswordField();
		passwordField.setBounds(203, 136, 121, 20);
		frmVrti.getContentPane().add(passwordField);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});

		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.setBounds(235, 184, 89, 23);
		frmVrti.getContentPane().add(btnPrijava);
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});
		btnPrijava.addKeyListener(new KeyListener(){
			
			public void keyReleased(KeyEvent arg0) {
				loginAction();
				
			}

			public void keyTyped(KeyEvent arg0) {
				loginAction();
				
			}

			public void keyPressed(KeyEvent e) {
				loginAction();
				
			}
		});
	}
	public void loginAction() {
		Korisnik user = new Korisnik();

		KorisnikServis userS = new KorisnikServis(s);
		String passText = new String(passwordField.getPassword());
		if (userS.provjeriSifruKorisnika(textField.getText(), userS.Plus4Hash(passText))) {
			ArrayList<Korisnik> korisnici = userS.dajKorisnike();
			for (int i = 0; i < korisnici.size(); i++) {
				if (korisnici.get(i).getKorisnickoIme().equals(textField.getText())) {
					user = korisnici.get(i);
				}
			}
			if (user.getPrivilegije().equals("blagajnik")) {
				GlavniProzorBlagajnik mainFormaBlagajnik = new GlavniProzorBlagajnik(s, user.getIdKorisnika());
				mainFormaBlagajnik.OtvoriFormu();
				frmVrti.setVisible(false);
			} else if (user.getPrivilegije().equals("direktor")) {
				GlavniProzorDirektor mainForma = new GlavniProzorDirektor(s, user.getIdKorisnika());
				mainForma.OtvoriFormu();
				frmVrti.setVisible(false);
			} else
				JOptionPane.showMessageDialog(null, "Greška!");
		} else
			JOptionPane.showMessageDialog(null, "Neispravna šifra!");
	}

}
