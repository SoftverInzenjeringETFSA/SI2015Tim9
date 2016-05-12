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
import javax.swing.JTextField;
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
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private Grupa g = new Grupa();
	private int idGrupe;
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GrupaServis gs = new GrupaServis(this.s);
		Grupa g = gs.sveGrupe().get(0);
		AktivnostServis as = new AktivnostServis(this.s);
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 657, 345);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		
		
		JLabel lblGrupa = new JLabel("Grupa ");
		lblGrupa.setBounds(26, 36, 46, 14);
		frmVrti.getContentPane().add(lblGrupa);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(63, 33, 91, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(g.getNaziv());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 75, 469, 93);
		frmVrti.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
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
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(505, 90, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		btnIzmijeni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{/*
					IzmjenaDjeteta novifrejm = new IzmjenaDjeteta(s);
					novifrejm.OtvoriFormu();
					frmVrti.dispose();
				*/						
			}

		});
		
		JButton btnObrii = new JButton("Obri\u0161i");
		btnObrii.setBounds(505, 134, 126, 23);
		frmVrti.getContentPane().add(btnObrii);
		btnObrii.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// obrisati ovaj dio
				/*
					IzmjenaDjeteta novifrejm = new IzmjenaDjeteta(s);
					novifrejm.OtvoriFormu();
					frmVrti.dispose();
				*/						
			}

		});
		
		
		
		TabelaDjeca(g.getNaziv());
		StatistikaAktivnosti(as);
	}
	
	public void TabelaDjeca(String grupa){
		
		
		DijeteServis servis = new DijeteServis(this.s);
		ArrayList<Dijete> djeca = servis.svaDjeca();
		Object[][] data= new Object[djeca.size()][];
		for(int i = 0; i<djeca.size();i++) {
			if(grupa.equals(djeca.get(i).getGrupa().getNaziv())){
				data[i]= new Object[]{djeca.get(i).getIme(), djeca.get(i).getIme()};
		
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
						"Ime djeteta", "Prezime djeteta"
}
			));
			}
		}
		DefaultTableModel tableFire = (DefaultTableModel) table.getModel();
		tableFire.fireTableDataChanged();
		
		
		
		}
	
public void StatistikaAktivnosti(AktivnostServis as){
		
		
		ArrayList<Aktivnost> aktiv = as.SveAktivnosti();
		Object[][] data= new Object[aktiv.size()][];
		for(int i = 0; i<aktiv.size();i++) {
			
				data[i]= new Object[]{aktiv.get(i).getNaziv(), aktiv.get(i).getBrojDjece().toString()};
		
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

// Export tabele
/*
public void exportTable(JTable jTable1,File file) throws IOException{
    TableModel model=jTable1.getModel();
    FileWriter out=new FileWriter(file);
    BufferedWriter bw=new BufferedWriter(out);
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
*/
}
