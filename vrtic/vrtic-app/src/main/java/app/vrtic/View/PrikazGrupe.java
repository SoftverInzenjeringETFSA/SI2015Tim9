package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Termin;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.TerminServis;


public class PrikazGrupe {
	final static Logger logger = Logger.getLogger(login.class);
	private Session s;
	private JFrame frmVrti;
	private JTextField imeGrupeDynamic;
	private JTable tabelaDijete;
	private JTable table_1;
	private Grupa g2 = new Grupa();
	private int idGrupe;
	private PrikazGrupe roditelj;
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazGrupe window = new PrikazGrupe(s, idGrupe);
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
	public PrikazGrupe(Session s, int id) {
		this.s = s;
		this.idGrupe = id;
		this.roditelj = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final GrupaServis gs = new GrupaServis(this.s);
		Grupa g = gs.PretragaPoIDu(idGrupe);
		AktivnostServis as = new AktivnostServis(this.s);
		final DijeteServis ds = new DijeteServis(this.s);
		frmVrti = new JFrame();
		frmVrti.setResizable(false);
		frmVrti.setTitle("Prikaz Grupa");
		frmVrti.setBounds(100, 100, 657, 369);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		
		JLabel lblGrupa = new JLabel("Grupa ");
		lblGrupa.setBounds(26, 36, 46, 14);
		frmVrti.getContentPane().add(lblGrupa);
		
		imeGrupeDynamic = new JTextField();
		imeGrupeDynamic.setEditable(false);
		imeGrupeDynamic.setBounds(63, 33, 118, 20);
		frmVrti.getContentPane().add(imeGrupeDynamic);
		imeGrupeDynamic.setColumns(10);
		imeGrupeDynamic.setText(g.getNaziv());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 75, 469, 93);
		frmVrti.getContentPane().add(scrollPane);
		
		tabelaDijete = new JTable();
		tabelaDijete.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Ime djeteta", "Prezime djeteta"
			}
		));
		tabelaDijete.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tabelaDijete);
		
		JScrollPane tabelaAktivnosti = new JScrollPane();
		tabelaAktivnosti.setBounds(26, 224, 469, 75);
		frmVrti.getContentPane().add(tabelaAktivnosti);
		
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
		table_1.setDefaultEditor(Object.class, null);
		tabelaAktivnosti.setViewportView(table_1);
		
		JLabel lblStatistikaAktivnosti = new JLabel("Statistika aktivnosti:");
		lblStatistikaAktivnosti.setBounds(26, 199, 155, 14);
		frmVrti.getContentPane().add(lblStatistikaAktivnosti);
		
		
		ArrayList<Integer> djecijiID = sveDjecaZaGrupu(g.getNaziv());
		final ArrayList<Integer> djecijiID2 = djecijiID;
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(505, 111, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		btnIzmijeni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					if(tabelaDijete.getSelectedRow()!=-1) {
						int ID = djecijiID2.get(tabelaDijete.getSelectedRow());
						
						IzmjenaDjeteta novifrejm = new IzmjenaDjeteta(s, roditelj ,ID);
						novifrejm.OtvoriFormu();
						frmVrti.dispose();
					}
					else{
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Niste odabrali dijete");
						frmVrti.setAlwaysOnTop(true);
					}
			}

		});
		g2=g;
		JButton btnObrisi = new JButton("Obri\u0161i");
		btnObrisi.setBounds(505, 145, 126, 23);
		frmVrti.getContentPane().add(btnObrisi);
		
		JButton btnPrikazi = new JButton("Prika\u017Ei");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabelaDijete.getSelectedRow()!=-1) {
					int ID = djecijiID2.get(tabelaDijete.getSelectedRow());
					
					PrikazDjeteta novifrejm = new PrikazDjeteta(s, roditelj ,ID);
					novifrejm.OtvoriFormu();
					frmVrti.dispose();
				}
				else {
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Niste odabrali dijete");
					frmVrti.setAlwaysOnTop(true);
				}
				
			}
		});
		btnPrikazi.setBounds(505, 78, 126, 23);
		frmVrti.getContentPane().add(btnPrikazi);
		btnObrisi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(tabelaDijete.getSelectedRow()!=-1) { 
					int ID = djecijiID2.get(tabelaDijete.getSelectedRow());	
					Dijete d = ds.nadji(ID);
					d.setGrupa(null);
					ds.izmijeni(d);
					ArrayList<Integer> djecijiID3 = sveDjecaZaGrupu(g2.getNaziv());
					popuniTabeluDjece(djecijiID3);
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Uspješno ste obrisali dijete iz grupe.");
					frmVrti.setAlwaysOnTop(true);
				}
				else{
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Niste odabrali dijete");
					frmVrti.setAlwaysOnTop(true);
				}
			}

		});
		
		popuniTabeluDjece(djecijiID);
		StatistikaAktivnosti(as);
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
	
	public void popuniTabeluDjece(ArrayList<Integer> djeca) {
		Object[][] data= new Object[djeca.size()][];
		for(int i = 0; i<djeca.size();i++) {
			DijeteServis servis = new DijeteServis(this.s);
			Dijete d = servis.nadji(djeca.get(i));
			data[i]= new Object[]{d.getIme(), d.getPrezime()};
		}
		tabelaDijete.setModel(new DefaultTableModel(data, new String[] { "Ime djeteta", "Prezime djeteta" }));
		DefaultTableModel tableFire = (DefaultTableModel) tabelaDijete.getModel();
		tableFire.fireTableDataChanged();
	}
	
public void StatistikaAktivnosti(AktivnostServis as){
		
		
		ArrayList<Aktivnost> aktiv = as.SveAktivnosti();
		Object[][] data= new Object[aktiv.size()][];
		for(int i = 0; i<aktiv.size();i++) {
			
				//data[i]= new Object[]{aktiv.get(i).getNaziv(), aktiv.get(i).getBrojDjece().toString()};
			data[i]= new Object[]{aktiv.get(i).getNaziv(), as.vratiBrojClanovaGrupeNaAktivnosti(g2,aktiv.get(i))};    
			
				table_1.setModel(new DefaultTableModel(
				data,
				new String[] {
						"Naziv aktivnosti", "Broj djece"
}
			));
			}
		
		DefaultTableModel tableFire = (DefaultTableModel) table_1.getModel();
		tableFire.fireTableDataChanged();
	//	Export tabele
		
		/*
		File f = new File("D:\\\\adnan.xls");
		try{
			exportTable(table_1, f);
		}
		catch(Exception e){
			logger.info(e);
		}
		*/
		}
}
