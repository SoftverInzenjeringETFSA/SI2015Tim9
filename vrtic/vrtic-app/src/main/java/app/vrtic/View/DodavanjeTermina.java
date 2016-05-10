package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Termin;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.TerminServis;

import javax.swing.JFormattedTextField;

public class DodavanjeTermina {
	final static Logger logger = Logger.getLogger(login.class);
	private Session s;
	private JFrame frmVrti;
	

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeTermina window = new DodavanjeTermina(s);
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
	public DodavanjeTermina(Session s) {
		this.s = s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 503, 322);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		final JComboBox comboBox = new JComboBox(); //Odabrana aktivnost
		comboBox.setBounds(198, 96, 173, 20);
		frmVrti.getContentPane().add(comboBox);
		
		final JComboBox grupeComboBox = new JComboBox();
		grupeComboBox.setBounds(198, 58, 173, 20);
		frmVrti.getContentPane().add(grupeComboBox);
		
		
		
		final JComboBox comboBox_1 = new JComboBox();  //Dan u sedmici
		comboBox_1.setBounds(198, 127, 173, 20);
		frmVrti.getContentPane().add(comboBox_1);
		comboBox_1.addItem("Ponedjeljak");
		comboBox_1.addItem("Utorak");
		comboBox_1.addItem("Srijeda");
		comboBox_1.addItem("\u010Detvrtak");
		comboBox_1.addItem("Petak");
		comboBox_1.addItem("Subota");
		comboBox_1.addItem("Nedjelja");
		
		JLabel lblVrijemeZavretkaAktivnosti = new JLabel("Vrijeme zavr\u0161etka aktivnosti:");
		lblVrijemeZavretkaAktivnosti.setBounds(10, 205, 183, 14);
		frmVrti.getContentPane().add(lblVrijemeZavretkaAktivnosti);
		
		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(198, 166, 72, 20);
		frmVrti.getContentPane().add(formattedTextField);
		
		final JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(198, 202, 72, 20);
		frmVrti.getContentPane().add(formattedTextField_1);
		
		JLabel lblSatiMinuta = new JLabel("sati : minuta");
		lblSatiMinuta.setBounds(280, 169, 72, 14);
		frmVrti.getContentPane().add(lblSatiMinuta);
		
		JLabel label = new JLabel("sati : minuta");
		label.setBounds(280, 205, 72, 14);
		frmVrti.getContentPane().add(label);
		
		JButton btnKraj = new JButton("Zatvori");
		btnKraj.setBounds(289, 249, 82, 23);
		frmVrti.getContentPane().add(btnKraj);
		btnKraj.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frmVrti.dispose();
										
			}

		});
		
		JButton btnDalje = new JButton("Dodaj");
		btnDalje.setBounds(198, 249, 82, 23);
		frmVrti.getContentPane().add(btnDalje);
		btnDalje.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Termin NoviTermin = new Termin();	
				NoviTermin.setDan(comboBox_1.getSelectedItem().toString());
			//	NoviTermin.setGrupa(grupeComboBox.getSelectedItem().toString());
				//NoviTermin.setVrijemePocetka(t.getVrijemePocetka());
		//		NoviTermin.setVrijemeZavrsetka(t.getVrijemeZavrsetka());
			//	NoviTermin.setAktivnost(comboBox.getSelectedItem().toString());
				
				
				
				
				
				formattedTextField.setText("");
				formattedTextField_1.setText("");
										
			}

		});
		
		
		dodajAktivnosti(comboBox);
		dodajGrupeuCombo(grupeComboBox);
				
		
	}
	
	public void dodajAktivnosti(JComboBox jcb){
		AktivnostServis aktivnosti = new AktivnostServis(this.s);
		ArrayList<Aktivnost> akt = aktivnosti.SveAktivnosti();
		
		for(int i = 0; i<akt.size();i++){
			jcb.addItem(akt.get(i).getNaziv());
			
		}
		
	}
	
	public void dodajGrupeuCombo(JComboBox jcb){
		GrupaServis gr = new GrupaServis(this.s);
		ArrayList<Grupa> grupe = gr.sveGrupe();
		
		for(int i = 0; i<grupe.size();i++){
			jcb.addItem(grupe.get(i).getNaziv());
		}
		
	}
	
	public void dodajTermin(){
		
	}
}
