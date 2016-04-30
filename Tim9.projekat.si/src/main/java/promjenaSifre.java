import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class promjenaSifre {

	private JFrame frmVrti;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					promjenaSifre window = new promjenaSifre();
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
	public promjenaSifre() {
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
		
		JLabel lblKorisnickaSifra = new JLabel("Nova \u0161ifra:");
		lblKorisnickaSifra.setBounds(83, 139, 110, 14);
		frmVrti.getContentPane().add(lblKorisnickaSifra);
		
		JButton btnPrijava = new JButton("Promijeni \u0161ifru");
		btnPrijava.setBounds(203, 227, 122, 23);
		frmVrti.getContentPane().add(btnPrijava);
		
		JLabel lblStaraifra = new JLabel("Stara \u0161ifra:");
		lblStaraifra.setBounds(83, 95, 110, 14);
		frmVrti.getContentPane().add(lblStaraifra);
		
		JLabel lblPonovljenaNovaifra = new JLabel("Ponovljena nova \u0161ifra:");
		lblPonovljenaNovaifra.setBounds(83, 179, 110, 14);
		frmVrti.getContentPane().add(lblPonovljenaNovaifra);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(203, 89, 122, 20);
		frmVrti.getContentPane().add(passwordField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(203, 136, 122, 20);
		frmVrti.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(203, 176, 122, 20);
		frmVrti.getContentPane().add(passwordField_1);
	}
}
