package app.vrtic.View;
import java.awt.EventQueue;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Korisnik;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class PrikazProfilaKorisnika {
    private Session s;
    private Korisnik k;
    
	private JFrame frmVrti;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	final static Logger logger = Logger.getLogger(login.class);
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProfilaKorisnika window = new PrikazProfilaKorisnika(s,k);
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
	public PrikazProfilaKorisnika(Session s, Korisnik kor) {
		this.s = s;
		this.k=kor;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 474, 300);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(104, 62, 46, 14);
		frmVrti.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(84, 100, 66, 14);
		frmVrti.getContentPane().add(lblPrezime);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setBounds(55, 137, 95, 14);
		frmVrti.getContentPane().add(lblNewLabel);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(58, 172, 95, 14);
		frmVrti.getContentPane().add(lblBrojTelefona);
		
		JLabel lblUloga = new JLabel("Uloga:");
		lblUloga.setBounds(94, 219, 46, 14);
		frmVrti.getContentPane().add(lblUloga);
		
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(166, 59, 149, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(k.getIme());
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(166, 97, 149, 20);
		frmVrti.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(k.getPrezime());
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(166, 134, 149, 20);
		frmVrti.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(k.getKorisnickoIme());
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(166, 169, 149, 20);
		frmVrti.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(k.getBrojTelefona());
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(166, 216, 149, 20);
		frmVrti.getContentPane().add(textField_4);
		if(k.getPrivilegije().equals("direktor")) 
			textField_4.setText("direktor");
		else if(k.getPrivilegije().equals("blagajnik")) 
			textField_4.setText("blagajnik");
	}
}
