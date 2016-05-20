package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Service.AktivnostServis;


	
public class DodavanjeAktivnosti {
	final static Logger logger = Logger.getLogger(login.class);
	private Session s;
	private GlavniProzorDirektor ref;
	private JFrame frmVrti;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeAktivnosti window = new DodavanjeAktivnosti(s, ref);
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
	public DodavanjeAktivnosti(Session s, GlavniProzorDirektor ref) {
		this.s = s;
		this.ref = ref;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Dodavanje Aktivnosti");
		frmVrti.setBounds(100, 100, 504, 231);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		Aktivnost akt = new Aktivnost();
		
		
		
		JLabel lblNazivAktivnosti = new JLabel("Naziv aktivnosti:");
		lblNazivAktivnosti.setBounds(41, 50, 103, 14);
		frmVrti.getContentPane().add(lblNazivAktivnosti);
		
		JLabel lblCijenaAktivnosti = new JLabel("Cijena aktivnosti:");
		lblCijenaAktivnosti.setBounds(41, 114, 103, 14);
		frmVrti.getContentPane().add(lblCijenaAktivnosti);
		
		JButton btnDodajAktivnost = new JButton("Dodaj aktivnost");
		btnDodajAktivnost.setBounds(352, 159, 126, 23);
		frmVrti.getContentPane().add(btnDodajAktivnost);
		btnDodajAktivnost.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				AktivnostServis akts = new AktivnostServis(s);
					if(textField.getText().isEmpty()) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Niste unijeli naziv aktivnosti.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
					else if(akts.provjeriDaLiPostojiIstaAktivnost(textField.getText())) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Aktivnost pod ovim nazivom veæ postoji!");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
					try {
						if(textField_1.getText().isEmpty() || Integer.parseInt(textField_1.getText()) <0) {
							frmVrti.setAlwaysOnTop(false);
							JOptionPane.showMessageDialog(null, "Niste unijeli validnu cijenu aktivnosti.");
							frmVrti.setAlwaysOnTop(true);
							return;
						}
					     
					}
					catch (NumberFormatException e1) {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Niste unijeli validnu cijenu aktivnosti.");
						frmVrti.setAlwaysOnTop(true);
						return;
					}
					
				    Aktivnost a = new Aktivnost();
					a.setNaziv(textField.getText());
					a.setCijena(Integer.valueOf(textField_1.getText()));
					a.setBrojDjece(0);
					boolean proslo = validiraj(akts);
					boolean uspjesnoDodavanje=false;
					if(proslo) uspjesnoDodavanje = akts.dodajAktivnost(a);
					else {
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Aktivnost veæ postoji ili ste unijeli neispravnu cijenu!"); 
						frmVrti.setAlwaysOnTop(true);
						textField.setText(null);
						textField_1.setText(null);
					}
					if(uspjesnoDodavanje) { 
						frmVrti.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog(null, "Uspješno ste dodali novu aktivnost"); 
						frmVrti.setAlwaysOnTop(true);
						textField.setText(null);
						textField_1.setText(null);
					}
					ref.popuniTabeluAktivnosti();
 				
										
			}

		});
		
		textField = new JTextField();
		textField.setBounds(160, 47, 182, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 111, 182, 20);
		frmVrti.getContentPane().add(textField_1);
		
		JLabel lblKm = new JLabel("KM");
		lblKm.setBounds(352, 114, 46, 14);
		frmVrti.getContentPane().add(lblKm);
	}
	
	public boolean validiraj(AktivnostServis akts){

		if(akts.provjeriDaLiPostojiAktivnost(textField.getText())==false && (Integer.valueOf(textField_1.getText())>0)){
			return true;
		}
		else return false;
	}

}
