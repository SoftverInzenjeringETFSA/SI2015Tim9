package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GlavniProzorDirektor {

	private JFrame frmVrti;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	final static Logger logger = Logger.getLogger(login.class);
	/**
	 * Launch the application.
	 */
	public static void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzorDirektor window = new GlavniProzorDirektor();
					window.frmVrti.setVisible(true);
					
				} catch (Exception e) {
					logger.info(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlavniProzorDirektor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 733, 331);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 42, 702, 239);
		frmVrti.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Korisnici", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 452, 122);
		panel.add(scrollPane);
		
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
				"Naziv korisnika", "Privilegija"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(93);
		scrollPane.setViewportView(table);
		
		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.setBounds(564, 14, 123, 23);
		panel.add(btnPrikazi);
		btnPrikazi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					PrikazProfilaKorisnika novifrejm = new PrikazProfilaKorisnika();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(564, 57, 123, 23);
		panel.add(btnIzmijeni);
		btnIzmijeni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					IzmjenaKorisnika novifrejm = new IzmjenaKorisnika();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnObrisi = new JButton("Obri\u0161i");
		btnObrisi.setBounds(564, 101, 123, 23);
		panel.add(btnObrisi);
		
		JButton btnDodajKorisnika = new JButton("Dodaj korisnika");
		btnDodajKorisnika.setBounds(564, 177, 123, 23);
		panel.add(btnDodajKorisnika);
		btnDodajKorisnika.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeKorisnika novifrejm = new DodavanjeKorisnika();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Djeca", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 11, 652, 121);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Ime djeteta", "Prezime djeteta", "Grupa"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JButton btnIzmijeniPodatke = new JButton("Izmijeni podatke");
		btnIzmijeniPodatke.setBounds(58, 164, 126, 23);
		panel_1.add(btnIzmijeniPodatke);
		btnIzmijeniPodatke.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					IzmjenaDjeteta novifrejm = new IzmjenaDjeteta();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnEvidentirajDijete = new JButton("Evidentiraj dijete");
		btnEvidentirajDijete.setBounds(265, 164, 147, 23);
		panel_1.add(btnEvidentirajDijete);
		btnEvidentirajDijete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					EvidentiranjeDjeteta novifrejm = new EvidentiranjeDjeteta();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnObrisiDijete = new JButton("Obri\u0161i dijete");
		btnObrisiDijete.setBounds(488, 164, 126, 23);
		panel_1.add(btnObrisiDijete);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Grupe", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblSpisakGrupa = new JLabel("Spisak grupa:");
		lblSpisakGrupa.setBounds(47, 11, 76, 14);
		panel_2.add(lblSpisakGrupa);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Grupa 1", "Grupa 2", "Grupa 3", "Grupa 4", "Grupa 5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(47, 36, 140, 96);
		panel_2.add(list);
		
		JButton btnObrisiGrupu = new JButton("Obri\u0161i");
		btnObrisiGrupu.setBounds(561, 33, 126, 23);
		panel_2.add(btnObrisiGrupu);
		
		JButton btnPrikaziGrupu = new JButton("Prika\u017Ei");
		btnPrikaziGrupu.setBounds(561, 77, 126, 23);
		panel_2.add(btnPrikaziGrupu);
		btnPrikaziGrupu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					PrikazGrupe novifrejm = new PrikazGrupe();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnDodajGrupu = new JButton("Dodaj grupu");
		btnDodajGrupu.setBounds(561, 158, 126, 23);
		panel_2.add(btnDodajGrupu);
		btnDodajGrupu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeGrupe novifrejm = new DodavanjeGrupe();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Aktivnosti", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(22, 11, 654, 121);
		panel_3.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Naziv aktivnosti", "Broj djece"
			}
		));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(92);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(96);
		scrollPane_2.setViewportView(table_2);
		
		JButton btnObrisiAktivnost = new JButton("Obri\u0161i aktivnost");
		btnObrisiAktivnost.setBounds(550, 162, 126, 23);
		panel_3.add(btnObrisiAktivnost);
		
		
		JButton btnDodajAktivnost = new JButton("Dodaj aktivnost");
		btnDodajAktivnost.setBounds(414, 162, 126, 23);
		panel_3.add(btnDodajAktivnost);
		btnDodajAktivnost.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeAktivnosti novifrejm = new DodavanjeAktivnosti();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Vaspita\u010Di", null, panel_4, null);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(22, 11, 653, 121);
		panel_4.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Ime", "Prezime", "Grupa"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
		JButton btnDodajVaspitaca = new JButton("Dodaj vaspita\u010Da");
		btnDodajVaspitaca.setBounds(549, 164, 126, 23);
		panel_4.add(btnDodajVaspitaca);
		btnDodajVaspitaca.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeAktivnosti novifrejm = new DodavanjeAktivnosti();
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnObrisiVaspitaca = new JButton("Obri\u0161i");
		btnObrisiVaspitaca.setBounds(413, 164, 126, 23);
		panel_4.add(btnObrisiVaspitaca);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Raspored", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(22, 11, 653, 121);
		panel_5.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Dan", "Grupa", "Aktivnost", "Vrijeme po\u010Detka", "Vrijeme zavr\u0161etka"
			}
		));
		scrollPane_4.setViewportView(table_4);
		
		JButton btnKreirajRaspored = new JButton("Kreiraj raspored");
		btnKreirajRaspored.setBounds(549, 161, 126, 23);
		panel_5.add(btnKreirajRaspored);
		
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
		scrollPane_6.setBounds(20, 79, 653, 121);
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
			},
			new String[] {
				"Ime i prezime roditelja", "Broj telefona"
			}
		));
		scrollPane_6.setViewportView(table_6);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Pregled svih uplata", null, panel_7, null);
		panel_7.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(22, 66, 653, 121);
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
		btnOdjava.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       login NoviLogin = new login();
		       frmVrti.dispose();
		       login.main(null);
		       
		    }
		});
		
		JButton btnPromjenaSifre = new JButton("Promjena \u0161ifre");
		btnPromjenaSifre.setBounds(445, 8, 126, 23);
		frmVrti.getContentPane().add(btnPromjenaSifre);
		
		btnPromjenaSifre.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					PromjenaSifre novifrejm = new PromjenaSifre();
					novifrejm.OtvoriFormu();
										
			}

		});
		
	}
}
