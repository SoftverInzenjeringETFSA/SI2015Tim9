package app.vrtic.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import javax.swing.table.TableModel;

import app.vrtic.Model.Dijete;
import app.vrtic.Model.Korisnik;
import app.vrtic.Model.Uplata;
import app.vrtic.Model.Zaduzenja;
import app.vrtic.Service.AktivnostDjecaServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.KorisnikServis;
import app.vrtic.Service.UplataServis;
import app.vrtic.Service.ZaduzenjeServis;

import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

public class GlavniProzorBlagajnik {
	final static Logger logger = Logger.getLogger(login.class);
	private JFrame frmVrti;
	private JTable table_5;
	private JTable table_6;
	private DijeteServis ds;
	private UplataServis us;
	private ZaduzenjeServis zs;
	private Session s;
	int id;
	Korisnik user = new Korisnik();
	private JTextField textField_1;
	private AktivnostDjecaServis ad;
	private KorisnikServis serviskorisnik;

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzorBlagajnik window = new GlavniProzorBlagajnik(s, id);
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
	public GlavniProzorBlagajnik(Session s, int id) {
		KorisnikServis us = new KorisnikServis(s);
		user = us.dajKorisnika(id);
		this.id = id;
		this.s = s;
		this.ds = new DijeteServis(s);
		this.us = new UplataServis(s);
		this.zs = new ZaduzenjeServis(s);
		this.ad = new AktivnostDjecaServis(s);
		// JOptionPane.showMessageDialog(null, ds.vratiCijenuSkolarine(1));
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

		JLabel lblDijete = new JLabel("Dijete, roditelj:");
		lblDijete.setBounds(22, 49, 103, 14);
		panel_5.add(lblDijete);

		JLabel lblDatumUplate = new JLabel("Datum uplate:");
		lblDatumUplate.setBounds(22, 104, 108, 14);
		panel_5.add(lblDatumUplate);

		JLabel lblGodina_2 = new JLabel("Godina:");
		lblGodina_2.setBounds(51, 163, 46, 14);
		panel_5.add(lblGodina_2);

		JLabel lblIznos = new JLabel("Iznos:");
		lblIznos.setBounds(61, 219, 46, 14);
		panel_5.add(lblIznos);
		final ArrayList<JCheckBox> listaCheckboxova = new ArrayList<JCheckBox>();
		final JComboBox comboBox_1 = new JComboBox();
		final JSpinner spinner_3 = new JSpinner();
		final JSpinner spinner_2 = new JSpinner();

		JButton btnIzracunaj = new JButton("Izra\u010Dunaj");
		btnIzracunaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int brojac = 0;
				for (int i = 0; i < listaCheckboxova.size(); i++) {
					if (listaCheckboxova.get(i).isSelected())
						brojac++;
				}
				double izraz = ds.vratiCijenuSkolarine(((Dijete) comboBox_1.getSelectedItem()).getIdDijete()) * brojac;
				textField_1.setText(Double.toString(izraz));

			}
		});
		btnIzracunaj.setBounds(135, 215, 89, 23);
		panel_5.add(btnIzracunaj);

		JLabel lblKm = new JLabel("KM");
		lblKm.setBounds(272, 219, 46, 14);
		panel_5.add(lblKm);

		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// da li je mjesec cekiran
				if (ProvjeriDaLiJeChekiran(listaCheckboxova)) {
					// pohada aktivnost
					if (ad.daLiDijetePohadaAktivnost(((Dijete) comboBox_1.getSelectedItem()).getIdDijete())) {
						// izracunata cijena
						if (!textField_1.getText().equals("")) {
							// dodaj uplatu i cisti formu
							// Kod za brisanje zaduzenja
							int godina = (Integer) spinner_3.getValue();
							String mjesec = null;
							for (int i = 0; i < listaCheckboxova.size(); i++) {
								if (listaCheckboxova.get(i).isSelected())
									mjesec = listaCheckboxova.get(i).getText();
								zs.obrisiZaduzenje(((Dijete) comboBox_1.getSelectedItem()), godina, mjesec);
							}
							// Kod za evidentiranje uplate
							for (int i = 0; i < listaCheckboxova.size(); i++) {

								if (listaCheckboxova.get(i).isSelected()) {
									mjesec = listaCheckboxova.get(i).getText();

									Uplata u = new Uplata();
									int rBrMjeseca = 0;

									if (mjesec.equals("januar"))
										rBrMjeseca = 1;
									else if (mjesec.equals("februar"))
										rBrMjeseca = 2;
									else if (mjesec.equals("mart"))
										rBrMjeseca = 3;
									else if (mjesec.equals("april"))
										rBrMjeseca = 4;
									else if (mjesec.equals("maj"))
										rBrMjeseca = 5;
									else if (mjesec.equals("juni"))
										rBrMjeseca = 6;
									else if (mjesec.equals("juli"))
										rBrMjeseca = 7;
									else if (mjesec.equals("august"))
										rBrMjeseca = 8;
									else if (mjesec.equals("septembar"))
										rBrMjeseca = 9;
									else if (mjesec.equals("oktobar"))
										rBrMjeseca = 10;
									else if (mjesec.equals("novembar"))
										rBrMjeseca = 11;
									else if (mjesec.equals("decembar"))
										rBrMjeseca = 12;
									u.setDatumUplate((Date) spinner_2.getValue());
									u.setDijete((Dijete) comboBox_1.getSelectedItem());
									u.setVisinaUplate(Double.parseDouble(textField_1.getText()));
									u.setZaGodinu(godina);
									u.setZaMjesec(rBrMjeseca);
									us.evidentirajUplatu(u);
								}
							}
							textField_1.setText("brise");
							frmVrti.setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Uplata školarine je uspješno evidentirana!",
									"Obavještenje", JOptionPane.PLAIN_MESSAGE);
							frmVrti.setAlwaysOnTop(true);
							OcistiFormu(listaCheckboxova);
						}
						// nije izracunata cijena
						else if (textField_1.getText().equals("")) {
							frmVrti.setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Nije izracunata cijena!", "Obavjestenje",
									JOptionPane.WARNING_MESSAGE);
							frmVrti.setAlwaysOnTop(true);
						}
					}
					// ne pohada aktivnost -nije bitno je li izracunata
					// cijena-ne provjeravamo textB
					else if (!ad.daLiDijetePohadaAktivnost(((Dijete) comboBox_1.getSelectedItem()).getIdDijete())) {
						// dodaje uplatu i cisti formu
						// Kod za brisanje zaduzenja
						int godina = (Integer) spinner_3.getValue();
						String mjesec = null;
						for (int i = 0; i < listaCheckboxova.size(); i++) {
							if (listaCheckboxova.get(i).isSelected())
								mjesec = listaCheckboxova.get(i).getText();
							zs.obrisiZaduzenje(((Dijete) comboBox_1.getSelectedItem()), godina, mjesec);
						}
						// Kod za evidentiranje uplate
						for (int i = 0; i < listaCheckboxova.size(); i++) {

							if (listaCheckboxova.get(i).isSelected()) {
								mjesec = listaCheckboxova.get(i).getText();

								Uplata u = new Uplata();
								int rBrMjeseca = 0;
								if (mjesec.equals("januar"))
									rBrMjeseca = 1;
								else if (mjesec.equals("februar"))
									rBrMjeseca = 2;
								else if (mjesec.equals("mart"))
									rBrMjeseca = 3;
								else if (mjesec.equals("april"))
									rBrMjeseca = 4;
								else if (mjesec.equals("maj"))
									rBrMjeseca = 5;
								else if (mjesec.equals("juni"))
									rBrMjeseca = 6;
								else if (mjesec.equals("juli"))
									rBrMjeseca = 7;
								else if (mjesec.equals("august"))
									rBrMjeseca = 8;
								else if (mjesec.equals("septembar"))
									rBrMjeseca = 9;
								else if (mjesec.equals("oktobar"))
									rBrMjeseca = 10;
								else if (mjesec.equals("novembar"))
									rBrMjeseca = 11;
								else if (mjesec.equals("decembar"))
									rBrMjeseca = 12;
								u.setDatumUplate((Date) spinner_2.getValue());
								u.setDijete((Dijete) comboBox_1.getSelectedItem());
								if (textField_1.equals("0.0")) {
									u.setVisinaUplate(Double.parseDouble(textField_1.getText()));
								} else {
									u.setVisinaUplate(Double.parseDouble("0.0"));
								}
								u.setZaGodinu(godina);
								u.setZaMjesec(rBrMjeseca);
								us.evidentirajUplatu(u);
							}
						}
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null,
								"Dijete ne pohaða dodatne aktivnosti, evidantirana je samo uplata školarine!",
								"Obavještenje", JOptionPane.PLAIN_MESSAGE);
						frmVrti.setAlwaysOnTop(true);
						OcistiFormu(listaCheckboxova);
					}
				} else {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Niste odabrali mjesec za koji želite izvršiti uplatu!!",
							"Obavještenje", JOptionPane.WARNING_MESSAGE);
					frmVrti.setAlwaysOnTop(true);
				}
				//////////////////////////////////////////////////////////////////////////////////////////////////////
			}

		});
		btnPotvrdi.setBounds(142, 296, 127, 23);
		panel_5.add(btnPotvrdi);

		comboBox_1.setBounds(135, 46, 248, 20);
		ArrayList<Dijete> djeca = ds.svaDjeca();
		for (int i = 0; i < djeca.size(); i++) {
			comboBox_1.addItem(djeca.get(i));
		}
		panel_5.add(comboBox_1);

		JLabel lblMjeseci = new JLabel("Mjeseci:");
		lblMjeseci.setBounds(412, 11, 74, 14);
		panel_5.add(lblMjeseci);

		JCheckBox chckbxJanuar = new JCheckBox("Januar");
		chckbxJanuar.setBounds(412, 31, 97, 23);
		panel_5.add(chckbxJanuar);

		JCheckBox chckbxFebruar = new JCheckBox("Februar");
		chckbxFebruar.setBounds(412, 57, 97, 23);
		panel_5.add(chckbxFebruar);

		JCheckBox chckbxMart = new JCheckBox("Mart");
		chckbxMart.setBounds(412, 85, 97, 23);
		panel_5.add(chckbxMart);

		JCheckBox chckbxApril = new JCheckBox("April");
		chckbxApril.setBounds(412, 111, 97, 23);
		panel_5.add(chckbxApril);

		JCheckBox chckbxMaj = new JCheckBox("Maj");
		chckbxMaj.setBounds(412, 137, 97, 23);
		panel_5.add(chckbxMaj);

		JCheckBox chckbxJuni = new JCheckBox("Juni");
		chckbxJuni.setBounds(412, 163, 97, 23);
		panel_5.add(chckbxJuni);

		JCheckBox chckbxJuli = new JCheckBox("Juli");
		chckbxJuli.setBounds(412, 189, 97, 23);
		panel_5.add(chckbxJuli);

		JCheckBox chckbxAugust = new JCheckBox("August");
		chckbxAugust.setBounds(412, 215, 97, 23);
		panel_5.add(chckbxAugust);

		JCheckBox chckbxSeptembar = new JCheckBox("Septembar");
		chckbxSeptembar.setBounds(412, 241, 97, 23);
		panel_5.add(chckbxSeptembar);

		JCheckBox chckbxOktobar = new JCheckBox("Oktobar");
		chckbxOktobar.setBounds(412, 267, 97, 23);
		panel_5.add(chckbxOktobar);

		JCheckBox chckbxNovembar = new JCheckBox("Novembar");
		chckbxNovembar.setBounds(412, 290, 97, 23);
		panel_5.add(chckbxNovembar);

		JCheckBox chckbxDecembar = new JCheckBox("Decembar");
		chckbxDecembar.setBounds(412, 311, 97, 23);
		panel_5.add(chckbxDecembar);
		listaCheckboxova.add(chckbxJanuar);
		listaCheckboxova.add(chckbxFebruar);
		listaCheckboxova.add(chckbxMart);
		listaCheckboxova.add(chckbxApril);
		listaCheckboxova.add(chckbxMaj);
		listaCheckboxova.add(chckbxJuni);
		listaCheckboxova.add(chckbxJuli);
		listaCheckboxova.add(chckbxAugust);
		listaCheckboxova.add(chckbxSeptembar);
		listaCheckboxova.add(chckbxOktobar);
		listaCheckboxova.add(chckbxNovembar);
		listaCheckboxova.add(chckbxDecembar);
		for (int i = 0; i < listaCheckboxova.size(); i++) {
			listaCheckboxova.get(i).setVisible(false);

		}

		spinner_2.setModel(new SpinnerDateModel(Calendar.getInstance().getTime(), null, null, Calendar.DAY_OF_YEAR));
		spinner_2.setBounds(135, 101, 134, 20);
		panel_5.add(spinner_2);

		spinner_3.setModel(new SpinnerNumberModel(new Integer(2016), new Integer(2000), null, new Integer(1)));
		spinner_3.setBounds(135, 160, 134, 20);

		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner_3, "#");

		spinner_3.setEditor(editor);

		panel_5.add(spinner_3);

		JButton btnPrikaziMjesece = new JButton("Prikaži mjesece");
		btnPrikaziMjesece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int i = 0; i < listaCheckboxova.size(); i++) {
					listaCheckboxova.get(i).setVisible(false);
					listaCheckboxova.get(i).setText("");
					listaCheckboxova.get(i).setSelected(false);
					textField_1.setText("");
				}

				int godina = (Integer) spinner_3.getValue();
				ArrayList<Zaduzenja> godisnjaZaduzenja = zs
						.vratiZaduzenjaZaGodinu(((Dijete) comboBox_1.getSelectedItem()), godina);
				for (int i = 0; i < godisnjaZaduzenja.size(); i++) {

					listaCheckboxova.get(i).setText(godisnjaZaduzenja.get(i).getMjesec());
					listaCheckboxova.get(i).setVisible(true);
				}
			}
		});
		btnPrikaziMjesece.setBounds(471, 7, 126, 23);
		panel_5.add(btnPrikaziMjesece);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(223, 216, 46, 20);
		panel_5.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Uplate koje kasne", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblGodina_1 = new JLabel("Godina:");
		lblGodina_1.setBounds(20, 37, 46, 14);
		panel_6.add(lblGodina_1);

		JLabel lblMjesec = new JLabel("Mjesec:");
		lblMjesec.setBounds(20, 11, 46, 14);
		panel_6.add(lblMjesec);

		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(12);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Januar", "Februar", "Mart", "April", "Maj", "Juni",
				"Juli", "August", "Septembar", "Oktobar", "Novembar", "Decembar" }));
		comboBox.setBounds(70, 8, 100, 20);
		panel_6.add(comboBox);

		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(2016, 2000, 3000, 1));
		JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(spinner_1, "#");
		spinner_1.setBounds(70, 34, 100, 20);
		spinner_1.setEditor(editor1);
		panel_6.add(spinner_1);

		JButton btnPrikai_1 = new JButton("Prika\u017Ei");
		btnPrikai_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mjesec = comboBox.getSelectedItem().toString();
				int godina = (Integer) spinner_1.getValue();
				ArrayList<Zaduzenja> listaZaduzenja = zs.vratiZaduzenjaPoGodiniIMjesecu(godina, mjesec);
				Object[][] podaci = new Object[listaZaduzenja.size()][];

				for (int i = 0; i < listaZaduzenja.size(); i++) {
					// podaci[i]= new
					// Object[]{listaZaduzenja.get(i).getMjesec(),listaZaduzenja.get(i).getGodina().toString()};
					podaci[i] = new Object[] {
							zs.vratiPodatkeZaIzvjestajPrvaKolona((int) listaZaduzenja.get(i).getIdZaduzenja()),
							zs.vratiPodatkeZaIzvjestajDrugaKolona((int) listaZaduzenja.get(i).getIdZaduzenja()) };
				}
				table_6.setModel(
						new DefaultTableModel(podaci, new String[] { "Ime i prezime roditelja", "Broj telefona" }));

			}
		});
		btnPrikai_1.setBounds(194, 33, 100, 23);
		panel_6.add(btnPrikai_1);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(20, 79, 653, 233);
		panel_6.add(scrollPane_6);

		table_6 = new JTable();
		table_6.setEnabled(false);
		table_6.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Ime i prezime roditelja", "Broj telefona" }));
		scrollPane_6.setViewportView(table_6);

		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Izvje\u0161taj", null, panel_7, null);
		panel_7.setLayout(null);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(22, 66, 653, 246);
		panel_7.add(scrollPane_5);

		table_5 = new JTable();
		table_5.setEnabled(false);
		table_5.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Ime i prezime roditelja", "Broj telefona", "Mjesec", "Godina", "Iznos" }));
		scrollPane_5.setViewportView(table_5);

		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(20, 21, 54, 14);
		panel_7.add(lblGodina);

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(2016), new Integer(2000), null, new Integer(1)));
		JSpinner.NumberEditor editor2 = new JSpinner.NumberEditor(spinner, "#");
		spinner.setBounds(64, 18, 76, 20);
		spinner.setEditor(editor2);
		panel_7.add(spinner);

		JButton btnGeneriiIzvjetaj = new JButton("Generi\u0161i izvje\u0161taj");
		btnGeneriiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int godina = (Integer) spinner.getValue();
				ArrayList<Zaduzenja> listaZaduzenja = zs.vratiZaduzenjaPoGodini(godina);
				Object[][] podaci = new Object[listaZaduzenja.size()][];

				for (int i = 0; i < listaZaduzenja.size(); i++)
					// podaci[i]= new
					// Object[]{listaZaduzenja.get(i).getMjesec(),listaZaduzenja.get(i).getGodina().toString()};
					podaci[i] = new Object[] {
							zs.vratiPodatkeZaIzvjestajPrvaKolona((int) listaZaduzenja.get(i).getIdZaduzenja()),
							zs.vratiPodatkeZaIzvjestajDrugaKolona((int) listaZaduzenja.get(i).getIdZaduzenja()),
							listaZaduzenja.get(i).getMjesec(), godina,
							ds.vratiCijenuSkolarine(listaZaduzenja.get(i).getDijete().getIdDijete()) };

				table_5.setModel(new DefaultTableModel(podaci,
						new String[] { "Ime i prezime roditelja", "Broj telefona", "Mjesec", "Godina", "Iznos" }));

				// Export tabele

				File f = new File("izvjestaj.xls");
				try {
					exportTable(table_5, f);
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Generisali ste izvjestaj!");
					frmVrti.setAlwaysOnTop(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Doslo je do greske, izvjestaj nije generisan.");

					logger.info(e);
				}

			}

			// Export tabele

		});
		btnGeneriiIzvjetaj.setBounds(186, 17, 489, 23);
		panel_7.add(btnGeneriiIzvjetaj);

		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(581, 8, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		btnOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login NoviLogin = new login(s);
				frmVrti.dispose();
				NoviLogin.OtvoriFormu();

			}
		});

		JButton btnPromjenaSifre = new JButton("Promjena \u0161ifre");
		btnPromjenaSifre.setBounds(445, 8, 126, 23);
		frmVrti.getContentPane().add(btnPromjenaSifre);

		btnPromjenaSifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KorisnikServis noviservis = new KorisnikServis(s);
				PromjenaSifre novifrejm = new PromjenaSifre(s, id);
				novifrejm.OtvoriFormu();

			}

		});
	}

	public void exportTable(JTable jTable1, File file) throws IOException {
		TableModel model = jTable1.getModel();
		FileWriter out = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(out);
		for (int i = 0; i < model.getColumnCount(); i++) {
			bw.write(model.getColumnName(i) + "\t");
		}
		bw.write("\n");
		for (int i = 0; i < model.getRowCount(); i++) {
			for (int j = 0; j < model.getColumnCount(); j++) {
				bw.write(model.getValueAt(i, j).toString() + "\t");
			}
			bw.write("\n");
		}
		bw.close();
		System.out.print("Pisanje u " + file);

	}

	private boolean ProvjeriDaLiJeChekiran(ArrayList<JCheckBox> listaCheckboxova) {
		for (int i = 0; i < listaCheckboxova.size(); i++) {
			if (listaCheckboxova.get(i).isSelected())
				return true;
		}
		return false;
	}

	private void OcistiFormu(ArrayList<JCheckBox> listaCheckboxova) {
		// Kod za ciscenje forme
		for (int i = 0; i < listaCheckboxova.size(); i++) {
			listaCheckboxova.get(i).setVisible(false);
			listaCheckboxova.get(i).setText("");
			listaCheckboxova.get(i).setSelected(false);
			textField_1.setText("");
		}
	}

}
