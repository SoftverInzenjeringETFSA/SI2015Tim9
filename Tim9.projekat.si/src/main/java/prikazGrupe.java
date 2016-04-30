import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class prikazGrupe {

	private JFrame frmVrti;
	private JTextField textField;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prikazGrupe window = new prikazGrupe();
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
	public prikazGrupe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 657, 345);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JButton btnPromjena = new JButton("Promjena \u0161ifre");
		btnPromjena.setBounds(369, 11, 126, 23);
		frmVrti.getContentPane().add(btnPromjena);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(505, 11, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		
		JLabel lblGrupa = new JLabel("Grupa ");
		lblGrupa.setBounds(26, 36, 46, 14);
		frmVrti.getContentPane().add(lblGrupa);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(63, 33, 24, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 75, 469, 121);
		frmVrti.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Ime djeteta", "Prezime djeteta"
			}
		));
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 224, 469, 75);
		frmVrti.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Naziv aktivnosti", "Broj djece"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(101);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(91);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblStatistikaAktivnosti = new JLabel("Statistika aktivnosti:");
		lblStatistikaAktivnosti.setBounds(26, 199, 155, 14);
		frmVrti.getContentPane().add(lblStatistikaAktivnosti);
		
		JButton btnPrikai = new JButton("Prika\u017Ei");
		btnPrikai.setBounds(505, 105, 126, 23);
		frmVrti.getContentPane().add(btnPrikai);
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(505, 139, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		
		JButton btnObrii = new JButton("Obri\u0161i");
		btnObrii.setBounds(505, 173, 126, 23);
		frmVrti.getContentPane().add(btnObrii);
	}

}
