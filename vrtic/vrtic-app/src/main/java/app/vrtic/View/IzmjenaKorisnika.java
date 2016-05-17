package app.vrtic.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Korisnik;
import app.vrtic.Service.KorisnikServis;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class IzmjenaKorisnika {

    private Session s;
    private KorisnikServis korisnikServis;
    private Korisnik k;
    private String porukaValidacija;
    
	private JFrame frmVrti;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	final static Logger logger = Logger.getLogger(login.class);
	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaKorisnika window = new IzmjenaKorisnika(s,k);
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
	public IzmjenaKorisnika(Session s,Korisnik kor) {
		this.s = s;
		k=kor;
		korisnikServis=new KorisnikServis(this.s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Izmjena Korisnika");
		frmVrti.setBounds(100, 100, 474, 323);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(104, 62, 46, 14);
		frmVrti.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(84, 100, 66, 14);
		frmVrti.getContentPane().add(lblPrezime);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setBounds(55, 137, 95, 14);
		frmVrti.getContentPane().add(lblNewLabel);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(58, 172, 95, 14);
		frmVrti.getContentPane().add(lblBrojTelefona);
		
		JLabel lblUloga = new JLabel("Uloga:");
		lblUloga.setBounds(94, 219, 46, 14);
		frmVrti.getContentPane().add(lblUloga);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pravnik/Direktor", "Blagajnik"}));
		comboBox.setBounds(166, 216, 149, 20);
		frmVrti.getContentPane().add(comboBox);
		
		if(k.getPrivilegije().equals("direktor")) 
			comboBox.setSelectedIndex(0);
		else if(k.getPrivilegije().equals("blagajnik")) 
			comboBox.setSelectedIndex(1);
		
		textField = new JTextField();
		textField.setBounds(166, 59, 149, 20);
		frmVrti.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(k.getIme());
		
		textField_1 = new JTextField();
		textField_1.setBounds(166, 97, 149, 20);
		frmVrti.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(k.getPrezime());
		
		textField_2 = new JTextField();
		textField_2.setBounds(166, 134, 149, 20);
		frmVrti.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(k.getKorisnickoIme());
		
		textField_3 = new JTextField();
		textField_3.setBounds(166, 169, 149, 20);
		frmVrti.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(k.getBrojTelefona());
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validirajFormu(comboBox).equals("")){
					k.setIme(textField.getText());
					k.setPrezime(textField_1.getText());
					k.setKorisnickoIme(textField_2.getText());
					k.setBrojTelefona(textField_3.getText());
					
					if(comboBox.getSelectedIndex()==0){
						k.setPrivilegije("direktor");
					}
					else if(comboBox.getSelectedIndex()==1){
						k.setPrivilegije("blagajnik");
					}			
					korisnikServis.izmjeniKorisnika(k);
					JOptionPane.showMessageDialog(null,
						    "Uspješno ste izmjenili korisnika",
						    "Obavještenje",
						    JOptionPane.PLAIN_MESSAGE);
				}
				else if (!validirajFormu(comboBox).equals(""))
					JOptionPane.showMessageDialog(null,
						    porukaValidacija,
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);				
			}
		});
		btnIzmijeni.setBounds(322, 250, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
	}
	
	private String validirajFormu(JComboBox comboBox){
		porukaValidacija="";
		if(textField.getText().equals(""))
			porukaValidacija="Unesite ime korisnika!";
		else if(textField_1.getText().equals(""))
			porukaValidacija="Unesite prezime korisnika!";
		else if(textField_2.getText().equals(""))
			porukaValidacija="Unesite korisnièko ime korisnika!";
		else if(textField_3.getText().equals(""))
			porukaValidacija="Unesite broj telefona korisnika!";
		else if(!validirajBrojTelefona(textField_3.getText()).equals(""))
			porukaValidacija=validirajBrojTelefona(textField_3.getText());
		else if (comboBox.getSelectedIndex()==-1){
			porukaValidacija="Odaberite privilegiju korisnika!";
		}
		else if(textField_2.getText().length()<5 ||textField_2.getText().length()>10)
			porukaValidacija="Korisnièko ime mora sadržavati više od 5 a manje od 10 znakova!";
		else if(korisnikServis.provjeriDaLiPostojiIstiKorisnik(textField_2.getText())){
			porukaValidacija="Korisnièo ime veæ postoji!";
		}
		return porukaValidacija;		
	}
	
	private  String validirajBrojTelefona(String str)
	{
		String poruka="";
	     Pattern p = Pattern.compile("[0-9]{9,10}");
	     Matcher m = p.matcher(str);
	     if(!m.matches())
	    poruka="Broj telefona bi se trebao sastojati od 9 ili 10 cifara";	 
	     return poruka;
	 }

}



