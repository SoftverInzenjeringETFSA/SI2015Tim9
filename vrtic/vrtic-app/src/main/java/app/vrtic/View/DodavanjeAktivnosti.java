package app.vrtic.View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DodavanjeAktivnosti {

	private JFrame frmVrti;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeAktivnosti window = new DodavanjeAktivnosti();
					window.frmVrti.setVisible(true);
					window.frmVrti.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeAktivnosti() {
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
		
		
		
		JLabel lblNazivAktivnosti = new JLabel("Naziv aktivnosti:");
		lblNazivAktivnosti.setBounds(41, 50, 103, 14);
		frmVrti.getContentPane().add(lblNazivAktivnosti);
		
		JLabel lblCijenaAktivnosti = new JLabel("Cijena aktivnosti:");
		lblCijenaAktivnosti.setBounds(41, 114, 103, 14);
		frmVrti.getContentPane().add(lblCijenaAktivnosti);
		
		JButton btnDodajAktivnost = new JButton("Dodaj aktivnost");
		btnDodajAktivnost.setBounds(352, 159, 126, 23);
		frmVrti.getContentPane().add(btnDodajAktivnost);
		
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
