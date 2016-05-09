package app.vrtic.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class login {
	public JDialog dijalog;

	private JFrame frmVrti;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmVrti.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(203, 136, 121, 20);
		frmVrti.getContentPane().add(passwordField);
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.setBounds(235, 184, 89, 23);
		frmVrti.getContentPane().add(btnPrijava);
		btnPrijava.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				
					GlavniProzorDirektor mainFormaDirektor = new GlavniProzorDirektor();
					mainFormaDirektor.OtvoriFormu();
					//Ovim cemo sakriti login, pa nam moze posluziti kao glavna forma
					frmVrti.setVisible(false);
					
					/*
					//a ovo cemo ubaciti ako se loguje kao blagajnik
					GlavniProzorBlagajnik mainFormaBlagajnik = new GlavniProzorBlagajnik();
					mainFormaBlagajnik.OtvoriFormu();
					//Ovim cemo sakriti login, pa nam moze posluziti kao glavna forma
					frmVrti.setVisible(false);
					*/
					
			}

		});
	}
}



