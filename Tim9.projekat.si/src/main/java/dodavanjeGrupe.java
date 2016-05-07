import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class dodavanjeGrupe {

	private JFrame frmVrti;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dodavanjeGrupe window = new dodavanjeGrupe();
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
	public dodavanjeGrupe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 503, 355);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Promjena \u0161ifre");
		btnNewButton.setBounds(212, 11, 126, 23);
		frmVrti.getContentPane().add(btnNewButton);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(351, 11, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		
		JLabel lblNazivGrupe = new JLabel("Naziv grupe:");
		lblNazivGrupe.setBounds(36, 61, 72, 14);
		frmVrti.getContentPane().add(lblNazivGrupe);
		
		JLabel lblRedniBrojGrupe = new JLabel("Redni broj grupe:");
		lblRedniBrojGrupe.setBounds(10, 99, 118, 14);
		frmVrti.getContentPane().add(lblRedniBrojGrupe);
		
		JLabel lblKapacitetGrupe = new JLabel("Kapacitet grupe:");
		lblKapacitetGrupe.setBounds(10, 130, 118, 14);
		frmVrti.getContentPane().add(lblKapacitetGrupe);
		
		JLabel lblVaspita = new JLabel("Vaspita\u010D 1:");
		lblVaspita.setBounds(36, 169, 92, 14);
		frmVrti.getContentPane().add(lblVaspita);
		
		JLabel lblVaspita_1 = new JLabel("Vaspita\u010D 2:");
		lblVaspita_1.setBounds(36, 209, 92, 14);
		frmVrti.getContentPane().add(lblVaspita_1);
		
		JButton btnDodajGrupu = new JButton("Dodaj grupu");
		btnDodajGrupu.setBounds(351, 282, 126, 23);
		frmVrti.getContentPane().add(btnDodajGrupu);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(154, 169, 173, 20);
		frmVrti.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(154, 206, 173, 20);
		frmVrti.getContentPane().add(comboBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(154, 127, 92, 20);
		frmVrti.getContentPane().add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(154, 96, 92, 20);
		frmVrti.getContentPane().add(spinner_1);
		
		textField = new JTextField();
		textField.setBounds(154, 58, 173, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
	}
}