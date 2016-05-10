package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Service.AktivnostServis;


	
public class DodavanjeAktivnosti {
	final static Logger logger = Logger.getLogger(login.class);
	private Session s;
	private JFrame frmVrti;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeAktivnosti window = new DodavanjeAktivnosti(s);
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
	public DodavanjeAktivnosti(Session s) {
		this.s = s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 504, 231);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		Aktivnost akt = new Aktivnost();
		
		
		
		JLabel lblNazivAktivnosti = new JLabel("Naziv aktivnosti:");
		lblNazivAktivnosti.setBounds(41, 50, 103, 14);
		frmVrti.getContentPane().add(lblNazivAktivnosti);
		
		JLabel lblCijenaAktivnosti = new JLabel("Cijena aktivnosti:");
		lblCijenaAktivnosti.setBounds(41, 114, 103, 14);
		frmVrti.getContentPane().add(lblCijenaAktivnosti);
		
		JButton btnDodajAktivnost = new JButton("Dodaj aktivnost");
		btnDodajAktivnost.setBounds(352, 159, 126, 23);
		frmVrti.getContentPane().add(btnDodajAktivnost);
		btnDodajAktivnost.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				    Aktivnost a = new Aktivnost();
					AktivnostServis akts = new AktivnostServis(s);
					a.setNaziv(textField.getText());
					a.setCijena(Integer.valueOf(textField_1.getText()));
					a.setBrojDjece(30);
					boolean uspjesnoDodavanje = akts.dodajAktivnost(a);
					frmVrti.dispose();
										
			}

		});
		
		textField = new JTextField();
		textField.setBounds(160, 47, 182, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 111, 182, 20);
		frmVrti.getContentPane().add(textField_1);
		
		JLabel lblKm = new JLabel("KM");
		lblKm.setBounds(352, 114, 46, 14);
		frmVrti.getContentPane().add(lblKm);
	}

}
