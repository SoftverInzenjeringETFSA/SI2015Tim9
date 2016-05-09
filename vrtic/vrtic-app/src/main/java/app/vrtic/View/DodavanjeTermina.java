package app.vrtic.View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class DodavanjeTermina {

	private JFrame frmVrti;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeTermina window = new DodavanjeTermina();
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
	public DodavanjeTermina() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 503, 322);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		
		JLabel lblNazivGrupe = new JLabel("Naziv grupe:");
		lblNazivGrupe.setBounds(105, 61, 72, 14);
		frmVrti.getContentPane().add(lblNazivGrupe);
		
		JLabel lblRedniBrojGrupe = new JLabel("Aktivnost:");
		lblRedniBrojGrupe.setBounds(117, 99, 118, 14);
		frmVrti.getContentPane().add(lblRedniBrojGrupe);
		
		JLabel lblKapacitetGrupe = new JLabel("Dan u sedmici:");
		lblKapacitetGrupe.setBounds(93, 130, 118, 14);
		frmVrti.getContentPane().add(lblKapacitetGrupe);
		
		JLabel lblVaspita = new JLabel("Vrijeme po\u010Detka aktivnosti:");
		lblVaspita.setBounds(22, 169, 183, 14);
		frmVrti.getContentPane().add(lblVaspita);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 96, 173, 20);
		frmVrti.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(198, 127, 173, 20);
		frmVrti.getContentPane().add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(198, 58, 173, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblVrijemeZavretkaAktivnosti = new JLabel("Vrijeme zavr\u0161etka aktivnosti:");
		lblVrijemeZavretkaAktivnosti.setBounds(10, 205, 183, 14);
		frmVrti.getContentPane().add(lblVrijemeZavretkaAktivnosti);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(198, 166, 72, 20);
		frmVrti.getContentPane().add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(198, 202, 72, 20);
		frmVrti.getContentPane().add(formattedTextField_1);
		
		JLabel lblSatiMinuta = new JLabel("sati : minuta");
		lblSatiMinuta.setBounds(280, 169, 72, 14);
		frmVrti.getContentPane().add(lblSatiMinuta);
		
		JLabel label = new JLabel("sati : minuta");
		label.setBounds(280, 205, 72, 14);
		frmVrti.getContentPane().add(label);
		
		JButton btnKraj = new JButton("Kraj");
		btnKraj.setBounds(289, 249, 82, 23);
		frmVrti.getContentPane().add(btnKraj);
		
		JButton btnDalje = new JButton("Dalje");
		btnDalje.setBounds(198, 249, 82, 23);
		frmVrti.getContentPane().add(btnDalje);
	}
}
