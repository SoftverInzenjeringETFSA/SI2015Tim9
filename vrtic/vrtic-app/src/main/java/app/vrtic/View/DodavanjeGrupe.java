package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Grupa;
import app.vrtic.Model.Vaspitac;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.GrupaServis;
import app.vrtic.Service.VaspitacServis;

import javax.swing.SpinnerNumberModel;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class DodavanjeGrupe {
    
	private JFrame frmVrti;
	private Session s;
	private JTextField nazivGrupe;
	private GlavniProzorDirektor ref;
	final static Logger logger = Logger.getLogger(login.class);
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeGrupe window = new DodavanjeGrupe(s, ref);
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
	public DodavanjeGrupe(Session s, GlavniProzorDirektor ref) {
		this.s = s;
		this.ref = ref;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final GrupaServis gs= new GrupaServis(this.s);
		frmVrti = new JFrame();
		frmVrti.setResizable(false);
		frmVrti.setTitle("Dodavanje Grupe");
		frmVrti.setBounds(100, 100, 503, 355);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
	
		
		JLabel lblNazivGrupe = new JLabel("Naziv grupe:");
		lblNazivGrupe.setBounds(99, 66, 72, 14);
		frmVrti.getContentPane().add(lblNazivGrupe);
		
		JLabel lblRedniBrojGrupe = new JLabel("Redni broj grupe:");
		lblRedniBrojGrupe.setBounds(73, 104, 118, 14);
		frmVrti.getContentPane().add(lblRedniBrojGrupe);
		
		JLabel lblKapacitetGrupe = new JLabel("Kapacitet grupe:");
		lblKapacitetGrupe.setBounds(73, 135, 118, 14);
		frmVrti.getContentPane().add(lblKapacitetGrupe);
		
		JLabel lblVaspita = new JLabel("Vaspita\u010D 1:");
		lblVaspita.setBounds(99, 174, 92, 14);
		frmVrti.getContentPane().add(lblVaspita);
		
		JLabel lblVaspita_1 = new JLabel("Vaspita\u010D 2:");
		lblVaspita_1.setBounds(99, 214, 92, 14);
		frmVrti.getContentPane().add(lblVaspita_1);
		
		final JComboBox vaspitac1 = new JComboBox();
		vaspitac1.setSelectedIndex(-1);
		vaspitac1.setToolTipText("Odabir prvog vaspita\u010Da za ovu grupu");
		vaspitac1.setBounds(217, 174, 173, 20);
		frmVrti.getContentPane().add(vaspitac1);
		
		final JComboBox vaspitac2 = new JComboBox();
		vaspitac2.setSelectedIndex(-1);
		vaspitac2.setToolTipText("Odabir drugog vaspita\u010Da za ovu grupu");
		vaspitac2.setBounds(217, 211, 173, 20);
		frmVrti.getContentPane().add(vaspitac2);
		
		final JSpinner kapacitetGrupe = new JSpinner();
		kapacitetGrupe.setToolTipText("Kapacitet grupe");
		kapacitetGrupe.setModel(new SpinnerNumberModel(20, 5, 40, 1));
		kapacitetGrupe.setBounds(217, 132, 92, 20);
		frmVrti.getContentPane().add(kapacitetGrupe);
		
		final JSpinner redniBrojGrupe = new JSpinner();
		redniBrojGrupe.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		redniBrojGrupe.setBounds(217, 101, 92, 20);
		frmVrti.getContentPane().add(redniBrojGrupe);
		
		nazivGrupe = new JTextField();
		nazivGrupe.setBounds(217, 63, 173, 20);
		frmVrti.getContentPane().add(nazivGrupe);
		nazivGrupe.setColumns(10);
		
		
		JButton btnDodajGrupu = new JButton("Dodaj grupu");
		btnDodajGrupu.setBounds(351, 282, 126, 23);
		frmVrti.getContentPane().add(btnDodajGrupu);
		btnDodajGrupu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					Grupa g = new Grupa();
					if(validiraj(nazivGrupe.getText(), kapacitetGrupe.getValue().toString()) && vaspitac1.getSelectedIndex()!=-1 && vaspitac2.getSelectedIndex()!=-1){
					g.setNaziv(nazivGrupe.getText());
					g.setKapacitet((Integer)kapacitetGrupe.getValue());
					g.setRedniBroj((Integer)redniBrojGrupe.getValue());
					gs.dodajGrupu(g);
					
					VaspitacServis vaspitacServis = new VaspitacServis(s);
					ArrayList<Vaspitac> sviVaspitaci = vaspitacServis.sviVaspitaci();
					int prviSelektovani = vaspitac1.getSelectedIndex();
					int drugiSelektovani = vaspitac2.getSelectedIndex();
					
					ArrayList<Grupa> sveGrupe = gs.sveGrupe();	// sva djeca da bi mogao pristupiti zadnjem dodanom		
					Grupa zadnja = sveGrupe.get(sveGrupe.size()-1);
					
					if(prviSelektovani!=-1) {
						Vaspitac v = sviVaspitaci.get(prviSelektovani);
						v.setGrupa(zadnja);
						vaspitacServis.izmijeni(v);
					}
					
					if(drugiSelektovani!=-1) {
						Vaspitac v = sviVaspitaci.get(drugiSelektovani);
						v.setGrupa(zadnja);
						vaspitacServis.izmijeni(v);
					}
					
					final JDialog dialog = new JDialog();
					frmVrti.setAlwaysOnTop(false);
					dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog, "Uspješno ste dodali novu grupu!");
					frmVrti.setAlwaysOnTop(true);
					frmVrti.dispose();
					
					ref.postaviListuGrupa();	
					}
					else{
					frmVrti.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null, "Naziv grupe ili redni broj veæ postoje!");
					frmVrti.setAlwaysOnTop(true);
					}
			}
			
		});

		sviVaspitaci(vaspitac1);
		sviVaspitaci(vaspitac2);
		//frmVrti.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNazivGrupe, nazivGrupe, lblRedniBrojGrupe, redniBrojGrupe, lblKapacitetGrupe, kapacitetGrupe, lblVaspita, vaspitac1, lblVaspita_1, vaspitac2, btnDodajGrupu}));
		
		
	}
	
	public void sviVaspitaci(JComboBox jcb){
		VaspitacServis vaspitaci = new VaspitacServis(this.s);
		ArrayList<Vaspitac> vas = vaspitaci.sviVaspitaci();
		
		for(int i = 0; i<vas.size();i++){
			String str = vas.get(i).getIme();
			str = str.concat(" ");
			str = str.concat(vas.get(i).getPrezime());
			jcb.addItem(str);
			
		}
		
	}
	
	public boolean validiraj(String naziv, String redniBroj){
		GrupaServis gs = new GrupaServis(s);
		if(gs.provjeriDaLiPostojiIstiNaziv(naziv) || gs.provjeriDaLiPostojiIstiRedniBroj(redniBroj)){
			return false;
		}
		else return true;
	}
	
}
