package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Aktivnostidjeca;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Korisnik;
import app.vrtic.Model.Termin;
import app.vrtic.Model.Vaspitac;
import app.vrtic.Model.Zaduzenja;
import app.vrtic.Service.AktivnostDjecaServis;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.KorisnikServis;
import app.vrtic.Service.TerminServis;
import app.vrtic.Service.UplataServis;
import app.vrtic.Service.VaspitacServis;
import app.vrtic.Service.ZaduzenjeServis;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GlavniProzorDirektor {

	private JFrame frmVrti;
	private JTable table_0;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private JList listGrupe;
	private boolean pokretanje = false; 
	final static Logger logger = Logger.getLogger(login.class);
	private Session s;
	int id;
	
	private Korisnik user = new Korisnik();
	private KorisnikServis serviskorisnik;
	private DijeteServis dijeteServis;
	private ZaduzenjeServis zaduzenjeServis;
	private AktivnostServis aktivnostServis;
	//private GlavniProzorDirektor ref;
	
	
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzorDirektor window = new GlavniProzorDirektor(s, id);
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
	public GlavniProzorDirektor(Session s, int id) {
		KorisnikServis us = new KorisnikServis(s);
		user = us.dajKorisnika(id);
		//this.ref = this;
		this.s = s;
		this.zaduzenjeServis = new ZaduzenjeServis(s);
		this.aktivnostServis = new AktivnostServis(s);
		this.dijeteServis = new DijeteServis(s);
		this.serviskorisnik= new KorisnikServis(this.s);
	    
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final GlavniProzorDirektor ref = this;
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 733, 331);
		frmVrti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		Termin termin = new Termin();
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 42, 702, 239);
		frmVrti.getContentPane().add(tabbedPane);
		
		// on load i ovdje √Ñ‚Ä°e se pozivat sve funkcije koje ce recimo ucitavat 
		// stvari iz baze u tabelu u odredjenom tabu
		tabbedPane.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    // korisnik
                    if(pane.getSelectedIndex()==0 && !pokretanje) {
                    	// popuniTabeluKorisnici();
                    }
                    // djeca
                    else if(pane.getSelectedIndex()==1) {
                    	popuniTabeluDjeca();
                    }
                    // grupe
                    else if(pane.getSelectedIndex()==2) {
                    	postaviListuGrupa();
                    }
                    // aktivnosti
                    else if(pane.getSelectedIndex()==3) {
                    	popuniTabeluAktivnosti();
                    }
                    // vaspitaci
                    else if(pane.getSelectedIndex()==4) {
                    	popuniTabeluVaspitaci();
                    }
                    // raspored
                    else if(pane.getSelectedIndex()==5) {
                    	refreshujRaspored();
                    }
                    // uplate koje kase
                    else if(pane.getSelectedIndex()==6) {
                    	// nista :D
                    }
                    // pregled svih uplata
                    else {
                    	
                    }
                    
                }
            }
        });
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Korisnici", panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 452, 122);
		panel.add(scrollPane);
		
		table_0 = new JTable();
		table_0.setModel(new DefaultTableModel(
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
		table_0.getColumnModel().getColumn(0).setPreferredWidth(96);
		table_0.getColumnModel().getColumn(1).setPreferredWidth(93);
		table_0.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table_0);
		
		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.setBounds(564, 14, 123, 23);
		panel.add(btnPrikazi);
		btnPrikazi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					Korisnik k = serviskorisnik.dajKorisnika((Integer)table_0.getModel().getValueAt(table_0.getSelectedRow(),2));
					PrikazProfilaKorisnika novifrejm = new PrikazProfilaKorisnika(s,k);
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
				Korisnik k=serviskorisnik.dajKorisnika((Integer)table_0.getModel().getValueAt(table_0.getSelectedRow(),2));
				IzmjenaKorisnika novifrejm = new IzmjenaKorisnika(s,k);
				novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnObrisi = new JButton("Obri\u0161i");
		btnObrisi.setBounds(564, 101, 123, 23);
		panel.add(btnObrisi);
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	        serviskorisnik.izbrisiKorisnika((Integer)table_0.getModel().getValueAt(table_0.getSelectedRow(),2));
	        refreshujKorisnike();
			}
		});
		
		JButton btnDodajKorisnika = new JButton("Dodaj korisnika");
		btnDodajKorisnika.setBounds(564, 177, 123, 23);
		panel.add(btnDodajKorisnika);
		btnDodajKorisnika.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeKorisnika novifrejm = new DodavanjeKorisnika(s);
					novifrejm.OtvoriFormu();
					refreshujKorisnike();
										
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
				
			},
			new String[] {
				"Ime djeteta", "Prezime djeteta", "Grupa"
			}
		));
		
		
		
		
		table_1.setDefaultEditor(Object.class, null);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnIzmijeniPodatke = new JButton("Izmijeni podatke");
		btnIzmijeniPodatke.setBounds(58, 164, 126, 23);
		panel_1.add(btnIzmijeniPodatke);
		btnIzmijeniPodatke.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selektovani = table_1.getSelectedRow();
				// ako je ne≈°to selektvano
				if(selektovani != -1) {
					
					List<Dijete> svaDjeca = dijeteServis.svaDjeca();
					int idSelektovanog = svaDjeca.get(selektovani).getIdDijete();
					IzmjenaDjeteta novifrejm = new IzmjenaDjeteta(s, idSelektovanog);
					novifrejm.OtvoriFormu();
				}
			}
		});
		
		
		JButton btnEvidentirajDijete = new JButton("Evidentiraj dijete");
		btnEvidentirajDijete.setBounds(265, 164, 147, 23);
		panel_1.add(btnEvidentirajDijete);
		btnEvidentirajDijete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					EvidentiranjeDjeteta novifrejm = new EvidentiranjeDjeteta(s, ref);
					novifrejm.OtvoriFormu();
										
			}

		});
		
		JButton btnObrisiDijete = new JButton("Obri\u0161i dijete");
		btnObrisiDijete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selektovani = table_1.getSelectedRow();
				// ako je neöto selektvano
				if(selektovani != -1) {
					List<Dijete> svaDjeca = dijeteServis.svaDjeca();
					int idSelektovanogDjeteta = svaDjeca.get(selektovani).getIdDijete();
					
					AktivnostDjecaServis aktivnostDjecaServis = new AktivnostDjecaServis(s);
					List<Aktivnostidjeca> sveAktivnostiDjeca = aktivnostDjecaServis.sveAktivnostiDjeca();
					
					Dijete selektovanoDijete = dijeteServis.nadji(idSelektovanogDjeteta);
					
					for(int i=0; i<sveAktivnostiDjeca.size(); i++) {
						if(sveAktivnostiDjeca.get(i).getDijete().getIdDijete() == selektovanoDijete.getIdDijete()) {
							aktivnostDjecaServis.obrisi(sveAktivnostiDjeca.get(i));
						}
					}
					
					
					dijeteServis.obrisi(idSelektovanogDjeteta);
					popuniTabeluDjeca();
				}
				
				JOptionPane.showMessageDialog(null, "Uspjesno ste obrisali dijete.");
			}
		});

		btnObrisiDijete.setBounds(488, 164, 126, 23);
		panel_1.add(btnObrisiDijete);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Grupe", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblSpisakGrupa = new JLabel("Spisak grupa:");
		lblSpisakGrupa.setBounds(47, 11, 76, 14);
		panel_2.add(lblSpisakGrupa);
		
		listGrupe = new JList();
		listGrupe.setModel(new AbstractListModel() {
			String[] values = new String[] {"Grupa 1", "Grupa 2", "Grupa 3", "Grupa 4", "Grupa 5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listGrupe.setBounds(47, 36, 140, 96);
		panel_2.add(listGrupe);
		

		JButton btnObrisiGrupu = new JButton("Obri\u0161i");
		btnObrisiGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selektovani = listGrupe.getSelectedIndex();
				if(selektovani!=-1) {
					GrupaServis grupaServis = new GrupaServis(s);
					List<Grupa> sveGrupe = grupaServis.sveGrupe();
					Grupa g = grupaServis.PretragaPoIDu(sveGrupe.get(selektovani).getIdGrupe());
					ArrayList<Integer> djecaTeGrupe = sveDjecaZaGrupu(g.getNaziv());
					for(int i=0; i<djecaTeGrupe.size(); i++) {
						Dijete d = dijeteServis.nadji(djecaTeGrupe.get(i));
						d.setGrupa(null);
						dijeteServis.izmijeni(d);
					}
					
					VaspitacServis vaspitacServis = new VaspitacServis(s);
					ArrayList<Integer> vaspitaciTeGrupe = sviVaspitaciZaGrupu(g.getNaziv());
					for(int i=0; i<vaspitaciTeGrupe.size(); i++) {
						Vaspitac v = vaspitacServis.nadji(vaspitaciTeGrupe.get(i));
						v.setGrupa(null);
						vaspitacServis.izmijeni(v);
					}
					
					grupaServis.ObrisiGrupu(sveGrupe.get(selektovani).getIdGrupe());
					postaviListuGrupa();
				}
				
				JOptionPane.showMessageDialog(null, "Uspjesno ste obrisali grupu");
			}
		});
		btnObrisiGrupu.setBounds(561, 33, 126, 23);
		panel_2.add(btnObrisiGrupu);
		
		JButton btnPrikaziGrupu = new JButton("Prika\u017Ei");
		btnPrikaziGrupu.setBounds(561, 77, 126, 23);
		panel_2.add(btnPrikaziGrupu);
		btnPrikaziGrupu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selektovani = listGrupe.getSelectedIndex();
				if(selektovani!=-1) {
					GrupaServis gs = new GrupaServis(s);
					List<Grupa> sveGrupe = gs.sveGrupe();
					int idSelektovanog = sveGrupe.get(selektovani).getIdGrupe();
					PrikazGrupe novifrejm = new PrikazGrupe(s, idSelektovanog);
					novifrejm.OtvoriFormu();
				}
			}

		});
		
		JButton btnDodajGrupu = new JButton("Dodaj grupu");
		btnDodajGrupu.setBounds(561, 158, 126, 23);
		panel_2.add(btnDodajGrupu);
		btnDodajGrupu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeGrupe novifrejm = new DodavanjeGrupe(s);
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
		table_2.setDefaultEditor(Object.class, null);
		scrollPane_2.setViewportView(table_2);
		
		JButton btnObrisiAktivnost = new JButton("Obri\u0161i aktivnost");
		btnObrisiAktivnost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selektovani = table_2.getSelectedRow();
				// ako je neöto selektvano
				if(selektovani != -1) {
					List<Aktivnost> sveAktivnosti = aktivnostServis.SveAktivnosti();
					int idSelektovaneAktivnosti = sveAktivnosti.get(selektovani).getIdAktivnosti();
					
					AktivnostDjecaServis aktivnostDjecaServis = new AktivnostDjecaServis(s);
					List<Aktivnostidjeca> sveAktivnostiDjeca = aktivnostDjecaServis.sveAktivnostiDjeca();
					
					Aktivnost selektovanaAktivnost = aktivnostServis.pretragaPoIDu(idSelektovaneAktivnosti);
					
					for(int i=0; i<sveAktivnostiDjeca.size(); i++) {
						if(sveAktivnostiDjeca.get(i).getAktivnost().getIdAktivnosti() == selektovanaAktivnost.getIdAktivnosti()) {
							aktivnostDjecaServis.obrisi(sveAktivnostiDjeca.get(i));
						}
					}
					
					
					aktivnostServis.ObrisiAktivnost(idSelektovaneAktivnosti);
					popuniTabeluAktivnosti();
				}
				
				JOptionPane.showMessageDialog(null, "Uspjesno ste obrisali aktivnost.");
				
			}
		});

		btnObrisiAktivnost.setBounds(550, 162, 126, 23);
		panel_3.add(btnObrisiAktivnost);

		JButton btnDodajAktivnost = new JButton("Dodaj aktivnost");
		btnDodajAktivnost.setBounds(414, 162, 126, 23);
		panel_3.add(btnDodajAktivnost);
		btnDodajAktivnost.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeAktivnosti novifrejm = new DodavanjeAktivnosti(s);
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
		table_3.setDefaultEditor(Object.class, null);
		scrollPane_3.setViewportView(table_3);
		
		JButton btnDodajVaspitaca = new JButton("Dodaj vaspita\u010Da");
		btnDodajVaspitaca.setBounds(549, 164, 126, 23);
		panel_4.add(btnDodajVaspitaca);
		btnDodajVaspitaca.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					DodavanjeVaspitaca novifrejm = new DodavanjeVaspitaca(s);
					novifrejm.OtvoriFormu();
			}

		});
		
		JButton btnObrisiVaspitaca = new JButton("Obri\u0161i");
		btnObrisiVaspitaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selektovani = table_3.getSelectedRow();
				if(selektovani!=-1) {
					VaspitacServis vaspitacServis = new VaspitacServis(s);
					List<Vaspitac> sviVaspitaci = vaspitacServis.sviVaspitaci();
					int idSelektovanog = sviVaspitaci.get(selektovani).getIdVaspitac();
					vaspitacServis.obrisi(idSelektovanog);
					
					JOptionPane.showMessageDialog(null, "Uspjesno ste obrisali vaspitaca");
					popuniTabeluVaspitaci();
				}
			}
		});
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
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				
			},
			new String[] {
				"Dan", "Grupa", "Aktivnost", "Vrijeme po\u010Detka", "Vrijeme zavr\u0161etka"
			}
		));
		table_4.setDefaultEditor(Object.class, null);
		scrollPane_4.setViewportView(table_4);
		
		
		
		JButton btnKreirajRaspored = new JButton("Kreiraj raspored");
		btnKreirajRaspored.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DodavanjeTermina novifrejm = new DodavanjeTermina(s);
				novifrejm.OtvoriFormu();
				
			}
		});
		btnKreirajRaspored.setBounds(533, 158, 126, 23);
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
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(12);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Januar", "Februar", "Mart", "April", "Maj", "Juni", "Juli", "August", "Septembar", "Oktobar", "Novembar", "Decembar"}));
		comboBox.setBounds(70, 8, 100, 20);
		panel_6.add(comboBox);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(2016, 2000, 3000, 1));
		spinner_1.setBounds(70, 34, 100, 20);
		panel_6.add(spinner_1);
		
		JButton btnPrikai_1 = new JButton("Prika\u017Ei");
		btnPrikai_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mjesec = comboBox.getSelectedItem().toString();
				int godina = (Integer) spinner_1.getValue();
				ArrayList<Zaduzenja> listaZaduzenja = zaduzenjeServis.vratiZaduzenjaPoGodiniIMjesecu(godina, mjesec);
				Object[][] podaci = new Object[listaZaduzenja.size()][];
				
					for(int i=0; i<listaZaduzenja.size();i++){
						//podaci[i]= new Object[]{listaZaduzenja.get(i).getMjesec(),listaZaduzenja.get(i).getGodina().toString()};
				podaci[i] = new Object[]{zaduzenjeServis.vratiPodatkeZaIzvjestajPrvaKolona((int)listaZaduzenja.get(i).getIdZaduzenja()),zaduzenjeServis.vratiPodatkeZaIzvjestajDrugaKolona((int)listaZaduzenja.get(i).getIdZaduzenja())};
					}
				table_6.setModel(new DefaultTableModel(
						podaci,
						new String[] {
							"Ime i prezime roditelja", "Broj telefona"
						}
					));
				
			}
		});
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
		table_6.setDefaultEditor(Object.class, null);
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
		table_5.setDefaultEditor(Object.class, null);
		scrollPane_5.setViewportView(table_5);
		
		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(20, 21, 54, 14);
		panel_7.add(lblGodina);
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2016, 2000, 3000, 1));
		spinner.setBounds(64, 18, 76, 20);
		panel_7.add(spinner);
		
		JButton btnGeneriiIzvjetaj = new JButton("Generi\u0161i izvje\u0161taj");
		btnGeneriiIzvjetaj.setBounds(186, 17, 489, 23);
		panel_7.add(btnGeneriiIzvjetaj);
		
		btnGeneriiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int godina = (Integer) spinner.getValue();
				ArrayList<Zaduzenja> listaZaduzenja = zaduzenjeServis.vratiZaduzenjaPoGodini(godina);
				Object[][] podaci = new Object[listaZaduzenja.size()][];
				
				for(int i=0; i<listaZaduzenja.size();i++)
					//podaci[i]= new Object[]{listaZaduzenja.get(i).getMjesec(),listaZaduzenja.get(i).getGodina().toString()};
			podaci[i] = new Object[]{zaduzenjeServis.vratiPodatkeZaIzvjestajPrvaKolona((int)listaZaduzenja.get(i).getIdZaduzenja()),zaduzenjeServis.vratiPodatkeZaIzvjestajDrugaKolona((int)listaZaduzenja.get(i).getIdZaduzenja()),listaZaduzenja.get(i).getMjesec(), godina, dijeteServis.vratiCijenuSkolarine(listaZaduzenja.get(i).getDijete().getIdDijete())};
			
				table_5.setModel(new DefaultTableModel(
						podaci,
						new String[] {
								"Ime i prezime roditelja", "Broj telefona", "Mjesec", "Godina", "Iznos"
						}
					));	
			//generisanje izvjestaja
				File f = new File("izvjestaj.xls");
				try{
					exportTable(table_5, f);
					JOptionPane.showMessageDialog(null,"Generisali ste izvjestaj na Desktop Vaseg racunara");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,"Doslo je do greske, izvjestaj nije generisan.");
					
					logger.info(e);
				}
			
			}
		});
		
		JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(581, 8, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		btnOdjava.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       login NoviLogin = new login(s);
		       frmVrti.dispose();
		       NoviLogin.OtvoriFormu();
		       
		    }
		});
		
		JButton btnPromjenaSifre = new JButton("Promjena \u0161ifre");
		btnPromjenaSifre.setBounds(445, 8, 126, 23);
		frmVrti.getContentPane().add(btnPromjenaSifre);
		
		btnPromjenaSifre.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					PromjenaSifre novifrejm = new PromjenaSifre(s, user);
					novifrejm.OtvoriFormu();
										
			}

		});
		
	//popuniTabeluKorisnici();
		refreshujKorisnike();
		
	}
	
	public void popuniTabeluKorisnici(){
		KorisnikServis korisnikServis = new KorisnikServis(s);
		ArrayList<Korisnik> korisnici = korisnikServis.dajKorisnike();
		
		Object[][] data= new Object[korisnici.size()][];
		for(int i = 0; i<korisnici.size();i++) {
			String naziv = naziv = (String) korisnici.get(i).getIme() + " " + korisnici.get(i).getPrezime();
			String privilegija = "Nema privilegije";
			if(!korisnici.get(i).getPrivilegije().equals(null))
				privilegija = (String) korisnici.get(i).getPrivilegije();
			
			data[i]= new Object[]{naziv, privilegija};
		}
		table_0.setModel(new DefaultTableModel(data, new String[] { "Naziv korisnika", "Privilegija" }));
		DefaultTableModel table0 = (DefaultTableModel) table_0.getModel();
		table0.fireTableDataChanged();
		
		}
	
	public void popuniTabeluDjeca(){
		DijeteServis dijeteServis = new DijeteServis(s);
		ArrayList<Dijete> djeca = dijeteServis.svaDjeca();

		Object[][] data= new Object[djeca.size()][];
		for(int i = 0; i<djeca.size();i++) { 
			if(djeca.get(i).getGrupa()!=null) { 
				data[i]= new Object[]{(String)djeca.get(i).getIme(), (String)djeca.get(i).getPrezime(), (String) djeca.get(i).getGrupa().toString()};
			}
			else { 
				data[i]= new Object[]{(String)djeca.get(i).getIme(), (String)djeca.get(i).getPrezime(), "Nema grupe"};
			}
		}
		table_1.setModel(new DefaultTableModel(data, new String[] {"Ime djeteta", "Prezime djeteta", "Grupa"}));
		DefaultTableModel table1 = (DefaultTableModel) table_1.getModel();
		table1.fireTableDataChanged();
		
		
		}
	
	public void postaviListuGrupa() {
		GrupaServis gs = new GrupaServis(s);
		List<Grupa> grupe = gs.sveGrupe();
		
		DefaultListModel model=new DefaultListModel();
		
		for(Grupa g : grupe){
			model.addElement(g);
		}
		
		listGrupe.setModel(model);

	}
	
	public void popuniTabeluAktivnosti(){
		AktivnostServis aktivnostServis = new AktivnostServis(s);
		ArrayList<Aktivnost> aktivnosti = aktivnostServis.SveAktivnosti();
		
		Object[][] data= new Object[aktivnosti.size()][];
		for(int i = 0; i<aktivnosti.size();i++) {
			String naziv = naziv = (String) aktivnosti.get(i).getNaziv();
			String broj = ""+(aktivnosti.get(i).getAktivnostidjecas().size());
			
			data[i]= new Object[]{naziv, broj};
		}
		table_2.setModel(new DefaultTableModel(data, new String[] { "Naziv aktivnosti", "Broj djece" }));
		DefaultTableModel table2 = (DefaultTableModel) table_2.getModel();
		table2.fireTableDataChanged();
		
		}
	
	public void popuniTabeluVaspitaci(){
		VaspitacServis vaspitacServis = new VaspitacServis(s);
		ArrayList<Vaspitac> vaspitaci = vaspitacServis.sviVaspitaci();
		
		Object[][] data= new Object[vaspitaci.size()][];
		for(int i = 0; i<vaspitaci.size();i++) {
			String ime = (String) vaspitaci.get(i).getIme();
			String prezime = (String) vaspitaci.get(i).getPrezime();
			String grupa = "Nema grupe";
			
			if(vaspitaci.get(i).getGrupa()!=null)
				grupa = (String) vaspitaci.get(i).getGrupa().getNaziv();
			data[i]= new Object[]{ime, prezime, grupa};
		}
		table_3.setModel(new DefaultTableModel(data, new String[] { "Ime", "Prezime", "Grupa" }));
		DefaultTableModel table3 = (DefaultTableModel) table_3.getModel();
		table3.fireTableDataChanged();
		
		}
	
	
	public void refreshujRaspored(){
		TerminServis servistermin = new TerminServis(this.s);
		ArrayList<Termin> termini = servistermin.SviTermini();
		Object[][] data= new Object[termini.size()][];
		for(int i = 0; i<termini.size();i++) 
			data[i]= new Object[]{(String)termini.get(i).getDan(), (String) termini.get(i).getGrupa().getNaziv(), (String) termini.get(i).getAktivnost().getNaziv(), (String) termini.get(i).getVrijemePocetka(), (String)termini.get(i).getVrijemeZavrsetka()};
		
		table_4.setModel(new DefaultTableModel(
				data,
				new String[] {
						"Dan", "Grupa", "Aktivnost", "Vrijeme po\u010Detka", "Vrijeme zavr\u0161etka"
}
			));
		DefaultTableModel table4 = (DefaultTableModel) table_4.getModel();
		table4.fireTableDataChanged();
		
		
		}


	private void refreshujKorisnike(){	
		ArrayList<Korisnik> korisnici= serviskorisnik.dajKorisnike();
		Object [][] data= new Object[korisnici.size()][];
		for(int i=0;i<korisnici.size();i++)
			data[i]=new Object[]{(String)korisnici.get(i).getIme()+" "+(String)korisnici.get(i).getPrezime(),(String)korisnici.get(i).getPrivilegije(),(Integer)korisnici.get(i).getIdKorisnika()
					};
		table_0.setModel(new DefaultTableModel(
				data,
				new String[] {
						"Naziv korisnika", "Privilegija","Id"
				}
			));
		table_0.getColumnModel().removeColumn(table_0.getColumnModel().getColumn(2));
		DefaultTableModel tableM = (DefaultTableModel) table_0.getModel();
		tableM.fireTableDataChanged();
	}
	
	public ArrayList<Integer> sveDjecaZaGrupu(String grupa) {
			
			DijeteServis servis = new DijeteServis(this.s);
			ArrayList<Dijete> djeca = servis.svaDjeca();
			ArrayList<Integer> djecaTeGrupe = new ArrayList<Integer>(0);
			for(int i = 0; i<djeca.size();i++) {
				if(djeca.get(i).getGrupa() != null) {
					if(grupa.equals(djeca.get(i).getGrupa().getNaziv())){
						djecaTeGrupe.add(djeca.get(i).getIdDijete());	
					}
				}
			}
			
			return djecaTeGrupe;
		}
	
	public void exportTable(JTable jTable1,File file) throws IOException{
	    TableModel model = jTable1.getModel();
	    FileWriter out = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(out);
	    for (int i=0;i<model.getColumnCount();i++){
	      bw.write(model.getColumnName(i)+"\t");
	    }
	    bw.write("\n");
	    for (int i=0;i<model.getRowCount();i++){
	      for (int j=0;j<model.getColumnCount();j++){
	        bw.write(model.getValueAt(i,j).toString()+"\t");
	      }
	      bw.write("\n");
	    }
	    bw.close();
	 System.out.print("Pisanje u " + file);


	}
	
	public void refreshajTabeluDjece(){
		ArrayList<Dijete> djeca = dijeteServis.svaDjeca();
		Object[][] podaci = new Object[djeca.size()][];
		
		for(int i=0; i< djeca.size();i++){
			
			if(djeca.get(i).getGrupa()!=null){
			
                podaci[i] = new Object[]{(String)djeca.get(i).getIme(),
						  (String)djeca.get(i).getPrezime(),
						   (String)djeca.get(i).getGrupa().getNaziv()
						   };	
		}
		
		else {
			
			
               podaci[i] = new Object[]{(String)djeca.get(i).getIme(),
						  (String)djeca.get(i).getPrezime(),
						   "Bez grupe"
						   };	
		}
		}
		table_1.setModel(new DefaultTableModel(
				podaci,
				new String[] {
					"Ime djeteta", "Prezime djeteta", "Grupa"
				}
			));
		
	}
	
public ArrayList<Integer> sviVaspitaciZaGrupu(String grupa) {
		
		VaspitacServis servis = new VaspitacServis(this.s);
		ArrayList<Vaspitac> sviVaspitaci = servis.sviVaspitaci();
		ArrayList<Integer> vaspitaciTeGrupe = new ArrayList<Integer>(0);
		for(int i = 0; i<sviVaspitaci.size();i++) {
			if(sviVaspitaci.get(i).getGrupa() != null) {
				if(grupa.equals(sviVaspitaci.get(i).getGrupa().getNaziv())){
					vaspitaciTeGrupe.add(sviVaspitaci.get(i).getIdVaspitac());	
				}
			}
		}
		
		return vaspitaciTeGrupe;
	}
		
}



