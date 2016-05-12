package app.vrtic.View;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Aktivnost;
import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Service.AktivnostServis;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.GrupaServis;

public class IzmjenaDjeteta {
private Session s;
private JFrame frmVrti;
private JTextField textFieldIme;
private JTextField textFieldPrezime;
private JTextField textFieldImeRoditelja;
private JTextField textFieldPrezimeRoditelja;
private JTextField textFieldAdresa;
private JTextField textFieldBroj;
private JTextField textFieldDatumUpisa;
private JTextField textFieldDatumIsteka;
private JComboBox comboBox;
final static Logger logger = Logger.getLogger(login.class);
private JTextField textFieldDatumRodjenja;
private JPanel panel;
private JScrollPane scrollPane;

private ArrayList<JCheckBox> cbLista; // lista checkBox aktivnosti
public AktivnostServis aktivnostServis;
public ArrayList<Aktivnost> listaAktivnosti; 


private int idDjeteta;
public SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
/**
 * Launch the application.
 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaDjeteta window = new IzmjenaDjeteta(s, idDjeteta); //dodatiID
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
	// treba dodati ID
	public IzmjenaDjeteta(Session s, int id) {
		this.s = s;
		this.idDjeteta = id;
		this.cbLista = new ArrayList<JCheckBox>();
		this.aktivnostServis = new AktivnostServis(s);
		this.listaAktivnosti = aktivnostServis.SveAktivnosti();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVrti = new JFrame();
		frmVrti.setTitle("Vrti\u0107");
		frmVrti.setBounds(100, 100, 517, 726);
		frmVrti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVrti.getContentPane().setLayout(null);
		
		/*JButton btnOdjava = new JButton("Odjava");
		btnOdjava.setBounds(365, 11, 126, 23);
		frmVrti.getContentPane().add(btnOdjava);
		
		JButton btnNewButton = new JButton("Promjena \u0161ifre");
		btnNewButton.setBounds(229, 11, 126, 23);
		frmVrti.getContentPane().add(btnNewButton);
		*/
		JLabel lblIme = new JLabel("Ime djeteta:");
		lblIme.setBounds(71, 62, 79, 14);
		frmVrti.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime djeteta:");
		lblPrezime.setBounds(45, 100, 95, 14);
		frmVrti.getContentPane().add(lblPrezime);
		
		JLabel lblNewLabel = new JLabel("Datum rodjenja:");
		lblNewLabel.setBounds(45, 137, 95, 14);
		frmVrti.getContentPane().add(lblNewLabel);
		
		JLabel lblBrojTelefona = new JLabel("Ime staratelja:");
		lblBrojTelefona.setBounds(55, 172, 95, 14);
		frmVrti.getContentPane().add(lblBrojTelefona);
		
		JLabel lblUloga = new JLabel("Prezime staratelja:");
		lblUloga.setBounds(26, 219, 130, 14);
		frmVrti.getContentPane().add(lblUloga);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Grupa 1", "Grupa 2", "Grupa 3", "Grupa 4", "Grupa 5"}));
		comboBox.setBounds(166, 445, 189, 20);
		frmVrti.getContentPane().add(comboBox);
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(166, 59, 189, 20);
		frmVrti.getContentPane().add(textFieldIme);
		textFieldIme.setColumns(10);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setBounds(166, 97, 189, 20);
		frmVrti.getContentPane().add(textFieldPrezime);
		textFieldPrezime.setColumns(10);
		
		textFieldDatumRodjenja = new JTextField();
		textFieldDatumRodjenja.setBounds(166, 134, 189, 20);
		frmVrti.getContentPane().add(textFieldDatumRodjenja);
		textFieldDatumRodjenja.setColumns(10);
		
		textFieldImeRoditelja = new JTextField();
		textFieldImeRoditelja.setBounds(166, 169, 189, 20);
		frmVrti.getContentPane().add(textFieldImeRoditelja);
		textFieldImeRoditelja.setColumns(10);
		
		JButton btnIzmijeni = new JButton("Izmjeni podatke");
		btnIzmijeni.setBounds(255, 653, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		
		textFieldPrezimeRoditelja = new JTextField();
		textFieldPrezimeRoditelja.setColumns(10);
		textFieldPrezimeRoditelja.setBounds(166, 216, 189, 20);
		frmVrti.getContentPane().add(textFieldPrezimeRoditelja);
		
		JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja:");
		lblAdresaStanovanja.setBounds(22, 262, 128, 14);
		frmVrti.getContentPane().add(lblAdresaStanovanja);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setColumns(10);
		textFieldAdresa.setBounds(166, 259, 189, 20);
		frmVrti.getContentPane().add(textFieldAdresa);
		
		JLabel lblBrojTelefonaStaratelja = new JLabel("Broj telefona staratelja:");
		lblBrojTelefonaStaratelja.setBounds(0, 300, 140, 14);
		frmVrti.getContentPane().add(lblBrojTelefonaStaratelja);
		
		textFieldBroj = new JTextField();
		textFieldBroj.setColumns(10);
		textFieldBroj.setBounds(166, 297, 189, 20);
		frmVrti.getContentPane().add(textFieldBroj);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(88, 408, 46, 14);
		frmVrti.getContentPane().add(lblGrupa);
		
		JLabel lblAktivnosti = new JLabel("Aktivnosti:");
		lblAktivnosti.setBounds(71, 346, 79, 14);
		frmVrti.getContentPane().add(lblAktivnosti);
		
		JLabel lblDatumUpisaU = new JLabel("Datum upisa u vrti\u0107:");
		lblDatumUpisaU.setBounds(22, 484, 128, 14);
		frmVrti.getContentPane().add(lblDatumUpisaU);
		
		JLabel lblDatumIstekaUgovora = new JLabel("Datum isteka ugovora:");
		lblDatumIstekaUgovora.setBounds(6, 520, 128, 14);
		frmVrti.getContentPane().add(lblDatumIstekaUgovora);
		
		JLabel lblNapomena = new JLabel("Napomena:");
		lblNapomena.setBounds(67, 564, 128, 14);
		frmVrti.getContentPane().add(lblNapomena);
		
		textFieldDatumUpisa = new JTextField();
		textFieldDatumUpisa.setColumns(10);
		textFieldDatumUpisa.setBounds(166, 481, 189, 20);
		frmVrti.getContentPane().add(textFieldDatumUpisa);
		
		textFieldDatumIsteka = new JTextField();
		textFieldDatumIsteka.setColumns(10);
		textFieldDatumIsteka.setBounds(166, 517, 189, 20);
		frmVrti.getContentPane().add(textFieldDatumIsteka);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(166, 553, 213, 91);
		frmVrti.getContentPane().add(textPane);
		
		btnIzmijeni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textFieldIme.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Ime mora sadržavati barem 3 slova.");
					return;
				}
				
				if(textFieldPrezime.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Prezime mora sadržavati barem 3 slova.");
					return;
				}
				
				Pattern datum  = Pattern.compile("^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$");
				if (!datum.matcher(textFieldDatumRodjenja.getText()).matches()) {
					JOptionPane.showMessageDialog(null, "Datum rodjenja nije u ispravnom datumu.");
			        return;
			    }
				if(textFieldImeRoditelja.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Ime roditelja mora sadržavati barem 3 slova.");
					return;
				}
				
				if(textFieldPrezimeRoditelja.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Prezime roditelja mora sadržavati barem 3 slova.");
					return;
				}
				
				if(textFieldAdresa.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Niste unijeli adresu");
					return;
				}
				
				if(textFieldBroj.getText().length() <= 8) {
					JOptionPane.showMessageDialog(null, "Broj telefona nije u ispravnom formatu.");
					return;
				}
				
				DijeteServis ds = new DijeteServis(s);
				
				// jer u bazu upisuje kao yyyy-mm-dd
				String[] s = textFieldDatumRodjenja.getText().split("-");
				
				Dijete d = new Dijete();
				d.setIme(textFieldIme.getText());
				d.setPrezime(textFieldPrezime.getText());
				d.setDatumRodjenja(s[2]+"-"+s[1]+"-"+s[0]); // yyyy-mm-dd
				d.setAdresaPrebivalista(textFieldAdresa.getText());
				d.setImeRoditelja(textFieldImeRoditelja.getText());
				d.setBrojTelefona(textFieldBroj.getText());
				d.setPrezimeRoditelja(textFieldPrezimeRoditelja.getText());
				try {
					d.setDatumUpisa(dateFormat.parse(textFieldDatumUpisa.getText()));
					d.setDatumIsteka(dateFormat.parse(textFieldDatumIsteka.getText()));
				}
				catch (Exception e1) {
					logger.info(e1);
					JOptionPane.showMessageDialog(null, "Datum upisa i/ili istika nisu u ispravnom formatu");
				}
				
				Grupa g = (Grupa)comboBox.getSelectedItem();
				d.setGrupa(g);
				
				ds.izmijeni(d);
			}
		});
		
		panel = new JPanel();
		panel.setBounds(166, 328, 215, 106);
		frmVrti.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setSize(234, 110);
		scrollPane.setLocation(147, 328);
		frmVrti.getContentPane().add(scrollPane);
		
		postaviListu();
		popuniFormu(idDjeteta);
		postaviAktivnosti(idDjeteta);
	}
	
	
	public void popuniFormu(int idDjeteta) {
		DijeteServis ds = new DijeteServis(s);
	
		Dijete d = ds.nadji(idDjeteta);
		
		// sad je 1994-01-11
		String[] s = d.getDatumRodjenja().split("-");
		
		textFieldIme.setText(d.getIme());
		textFieldPrezime.setText(d.getPrezime());
		textFieldDatumRodjenja.setText(s[2]+"-"+s[1]+"-"+s[0]); // 11-01-1994
		textFieldAdresa.setText(d.getAdresaPrebivalista());
		textFieldImeRoditelja.setText(d.getImeRoditelja());
		textFieldPrezimeRoditelja.setText(d.getPrezimeRoditelja());
		textFieldBroj.setText(d.getBrojTelefona());
		
		comboBox.setSelectedItem(d.getGrupa());

	}
	
	public void postaviListu() {
		GrupaServis gs = new GrupaServis(s);
		List<Grupa> grupe = gs.sveGrupe();
		
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		
		for(Grupa g : grupe){
			model.addElement(g);
		}
		
		comboBox.setModel(model);
	}
	
	public void postaviAktivnosti(int id) {
		DijeteServis ds = new DijeteServis(s);
		Dijete d = ds.nadji(id);
		
		
		for(Aktivnost a: listaAktivnosti) {
			JCheckBox c = new JCheckBox(a.toString());
			cbLista.add(c);
			
            panel.add(c);
            panel.revalidate();
            panel.repaint();
        }
	}
	
	
}
