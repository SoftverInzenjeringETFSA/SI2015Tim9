package app.vrtic.View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class DodavanjeVaspitaca {

	private JFrame frmVrti;
	private Session s;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	final static Logger logger = Logger.getLogger(login.class);
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
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 504, 269);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		JLabel lblNazivAktivnosti = new JLabel("Ime:");
		lblNazivAktivnosti.setBounds(90, 72, 103, 14);
		frmVrti.getContentPane().add(lblNazivAktivnosti);
		
		JLabel lblCijenaAktivnosti = new JLabel("Prezime:");
		lblCijenaAktivnosti.setBounds(66, 114, 103, 14);
		frmVrti.getContentPane().add(lblCijenaAktivnosti);
		
		JButton btnDodajAktivnost = new JButton("Dodaj vaspita\u010Da");
		btnDodajAktivnost.setBounds(352, 196, 126, 23);
		frmVrti.getContentPane().add(btnDodajAktivnost);
		
		textField = new JTextField();
		textField.setBounds(160, 69, 182, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 111, 182, 20);
		frmVrti.getContentPane().add(textField_1);
		
		JLabel lblKm = new JLabel("Broj telefona:");
		lblKm.setBounds(41, 163, 103, 14);
		frmVrti.getContentPane().add(lblKm);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(160, 157, 182, 20);
		frmVrti.getContentPane().add(textField_2);
	}

}
