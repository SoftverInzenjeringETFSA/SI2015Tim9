package app.vrtic.View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class DodavanjeKorisnika {
	
	final static Logger logger = Logger.getLogger(login.class);
	private Session s;
	private JFrame frmVrti;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeKorisnika window = new DodavanjeKorisnika(s);
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
	public DodavanjeKorisnika(Session s) {
		this.s = s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 474, 354);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(100, 62, 46, 14);
		frmVrti.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(80, 100, 66, 14);
		frmVrti.getContentPane().add(lblPrezime);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setBounds(61, 137, 95, 14);
		frmVrti.getContentPane().add(lblNewLabel);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(61, 172, 95, 14);
		frmVrti.getContentPane().add(lblBrojTelefona);
		
		JLabel lblUloga = new JLabel("Uloga:");
		lblUloga.setBounds(96, 250, 46, 14);
		frmVrti.getContentPane().add(lblUloga);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pravnik/Direktor", "Blagajnik"}));
		comboBox.setBounds(166, 247, 149, 20);
		frmVrti.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(166, 59, 149, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(166, 97, 149, 20);
		frmVrti.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(166, 134, 149, 20);
		frmVrti.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(166, 169, 149, 20);
		frmVrti.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnIzmijeni = new JButton("Dodaj korisnika");
		btnIzmijeni.setBounds(322, 281, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		
		JLabel lblKorisnikaifra = new JLabel("Korisni\u010Dka \u0161ifra:");
		lblKorisnikaifra.setBounds(51, 208, 95, 14);
		frmVrti.getContentPane().add(lblKorisnikaifra);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(166, 205, 149, 20);
		frmVrti.getContentPane().add(textField_4);
	}
}
