package app.vrtic;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;


import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JCheckBox;

public class GlavniProzorBlagajnik {

	private JFrame frmVrti;
	private JTable table_5;
	private JTable table_6;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzorBlagajnik window = new GlavniProzorBlagajnik();
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
	public GlavniProzorBlagajnik() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 733, 454);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 42, 702, 362);
		frmVrti.getContentPane().add(tabbedPane);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Evidencija uplate", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblDijete = new JLabel("Dijete:");
		lblDijete.setBounds(107, 49, 46, 14);
		panel_5.add(lblDijete);
		
		JLabel lblRoditelj = new JLabel("Roditelj:");
		lblRoditelj.setBounds(98, 91, 46, 14);
		panel_5.add(lblRoditelj);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(106, 141, 46, 14);
		panel_5.add(lblGrupa);
		
		JLabel lblDatumUplate = new JLabel("Datum uplate:");
		lblDatumUplate.setBounds(65, 182, 108, 14);
		panel_5.add(lblDatumUplate);
		
		JLabel lblGodina_2 = new JLabel("Godina:");
		lblGodina_2.setBounds(100, 213, 46, 14);
		panel_5.add(lblGodina_2);
		
		JLabel lblIznos = new JLabel("Iznos:");
		lblIznos.setBounds(109, 245, 46, 14);
		panel_5.add(lblIznos);
		
		JButton btnIzracunaj = new JButton("Izra\u010Dunaj");
		btnIzracunaj.setBounds(167, 241, 89, 23);
		panel_5.add(btnIzracunaj);
		
		JLabel lblKm = new JLabel("KM");
		lblKm.setBounds(306, 245, 46, 14);
		panel_5.add(lblKm);
		
		textField = new JTextField();
		textField.setBounds(257, 242, 39, 20);
		panel_5.add(textField);
		textField.setColumns(10);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setBounds(207, 290, 89, 23);
		panel_5.add(btnPotvrdi);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(167, 46, 129, 20);
		panel_5.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(167, 88, 129, 20);
		panel_5.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(167, 138, 129, 20);
		panel_5.add(comboBox_3);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(167, 179, 129, 20);
		spinner_3.setModel(new SpinnerDateModel(new Date(1461794400000L), null, null, Calendar.DAY_OF_YEAR));
		panel_5.add(spinner_3);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(167, 210, 129, 20);
		panel_5.add(spinner_2);
		
		JLabel lblMjeseci = new JLabel("Mjeseci:");
		lblMjeseci.setBounds(389, 11, 74, 14);
		panel_5.add(lblMjeseci);
		
		JCheckBox chckbxJanuar = new JCheckBox("Januar");
		chckbxJanuar.setBounds(389, 31, 97, 23);
		panel_5.add(chckbxJanuar);
		
		JCheckBox chckbxFebruar = new JCheckBox("Februar");
		chckbxFebruar.setBounds(389, 57, 97, 23);
		panel_5.add(chckbxFebruar);
		
		JCheckBox chckbxMart = new JCheckBox("Mart");
		chckbxMart.setBounds(389, 85, 97, 23);
		panel_5.add(chckbxMart);
		
		JCheckBox chckbxApril = new JCheckBox("April");
		chckbxApril.setBounds(389, 111, 97, 23);
		panel_5.add(chckbxApril);
		
		JCheckBox chckbxMaj = new JCheckBox("Maj");
		chckbxMaj.setBounds(389, 137, 97, 23);
		panel_5.add(chckbxMaj);
		
		JCheckBox chckbxJuni = new JCheckBox("Juni");
		chckbxJuni.setBounds(389, 163, 97, 23);
		panel_5.add(chckbxJuni);
		
		JCheckBox chckbxJuli = new JCheckBox("Juli");
		chckbxJuli.setBounds(389, 189, 97, 23);
		panel_5.add(chckbxJuli);
		
		JCheckBox chckbxAugust = new JCheckBox("August");
		chckbxAugust.setBounds(389, 215, 97, 23);
		panel_5.add(chckbxAugust);
		
		JCheckBox chckbxSeptembar = new JCheckBox("Septembar");
		chckbxSeptembar.setBounds(389, 241, 97, 23);
		panel_5.add(chckbxSeptembar);
		
		JCheckBox chckbxOktobar = new JCheckBox("Oktobar");
		chckbxOktobar.setBounds(389, 267, 97, 23);
		panel_5.add(chckbxOktobar);
		
		JCheckBox chckbxNovembar = new JCheckBox("Novembar");
		chckbxNovembar.setBounds(389, 290, 97, 23);
		panel_5.add(chckbxNovembar);
		
		JCheckBox chckbxDecembar = new JCheckBox("Decembar");
		chckbxDecembar.setBounds(389, 311, 97, 23);
		panel_5.add(chckbxDecembar);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Uplate koje kasne", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblGodina_1 = new JLabel("Godina:");
		lblGodina_1.setBounds(20, 37, 46, 14);
		panel_6.add(lblGodina_1);
		
		JLabel lblMjesec = new JLabel("Mjesec:");
		lblMjesec.setBounds(20, 11, 46, 14);
		panel_6.add(lblMjesec);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(12);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Januar", "Februar", "Mart", "April", "Maj", "Juni", "Juli", "August", "Septembar", "Oktobar", "Novembar", "Decembar"}));
		comboBox.setBounds(70, 8, 100, 20);
		panel_6.add(comboBox);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(70, 34, 100, 20);
		panel_6.add(spinner_1);
		
		JButton btnPrikai_1 = new JButton("Prika\u017Ei");
		btnPrikai_1.setBounds(194, 33, 100, 23);
		panel_6.add(btnPrikai_1);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(20, 79, 653, 233);
		panel_6.add(scrollPane_6);
		
		table_6 = new JTable();
		table_6.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Ime i prezime roditelja", "Broj telefona"
			}
		));
		scrollPane_6.setViewportView(table_6);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Izvje\u0161taj", null, panel_7, null);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(22, 66, 653, 246);
		panel_7.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Ime i prezime roditelja", "Broj telefona", "Mjesec", "Godina", "Iznos"
			}
		));
		scrollPane_5.setViewportView(table_5);
		
		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(20, 21, 54, 14);
		panel_7.add(lblGodina);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(64, 18, 76, 20);
		panel_7.add(spinner);
		
		JButton btnGeneriiIzvjetaj = new JButton("Generi\u0161i izvje\u0161taj");
		btnGeneriiIzvjetaj.setBounds(186, 17, 489, 23);
		panel_7.add(btnGeneriiIzvjetaj);
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(581, 8, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		
		JButton btnPromjenaSifre = new JButton("Promjena \u0161ifre");
		btnPromjenaSifre.setBounds(445, 8, 126, 23);
		frmVrti.getContentPane().add(btnPromjenaSifre);
	}
}
