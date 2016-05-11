package app.vrtic.View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import app.vrtic.Model.Dijete;
import app.vrtic.Model.Grupa;
import app.vrtic.Service.DijeteServis;
import app.vrtic.Service.GrupaServis;
import javax.swing.JList;

public class EvidentiranjeDjeteta {
	private Session s;
	final static Logger logger = Logger.getLogger(login.class);
	private JFrame frmVrti;
	private JTextField textFieldImeDjeteta;
	private JTextField textFieldPrezimeDjeteta;
	private JTextField textFieldDatumRodjenjaDjeteta;
	private JTextField textFieldImeRoditelja;
	private JTextField textFieldPrezimeRoditelja;
	private JTextField textFieldAdresaStanovanja;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldDatumUpisa;
	private JTextField textFieldDatumIsteka;
	private JComboBox comboBoxGrupa;
	
	public SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Launch the application.
	 */
	public void OtvoriFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EvidentiranjeDjeteta window = new EvidentiranjeDjeteta(s);
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
	public EvidentiranjeDjeteta(Session s) {
		this.s = s;
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
		/*
		JButton btnOdjava = new JButton("Odjava");
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
		
		
		comboBoxGrupa = new JComboBox();
		comboBoxGrupa.setEditable(true);
		comboBoxGrupa.setBounds(166, 405, 189, 20);
		frmVrti.getContentPane().add(comboBoxGrupa);
		
		
		textFieldImeDjeteta = new JTextField();
		textFieldImeDjeteta.setBounds(166, 59, 189, 20);
		frmVrti.getContentPane().add(textFieldImeDjeteta);
		textFieldImeDjeteta.setColumns(10);
		
		textFieldPrezimeDjeteta = new JTextField();
		textFieldPrezimeDjeteta.setBounds(166, 97, 189, 20);
		frmVrti.getContentPane().add(textFieldPrezimeDjeteta);
		textFieldPrezimeDjeteta.setColumns(10);
		
		textFieldDatumRodjenjaDjeteta = new JTextField();
		textFieldDatumRodjenjaDjeteta.setBounds(166, 134, 189, 20);
		frmVrti.getContentPane().add(textFieldDatumRodjenjaDjeteta);
		textFieldDatumRodjenjaDjeteta.setColumns(10);
		
		textFieldImeRoditelja = new JTextField();
		textFieldImeRoditelja.setBounds(166, 169, 189, 20);
		frmVrti.getContentPane().add(textFieldImeRoditelja);
		textFieldImeRoditelja.setColumns(10);
		
		JButton btnIzmijeni = new JButton("Dodaj");
		btnIzmijeni.setBounds(255, 653, 126, 23);
		frmVrti.getContentPane().add(btnIzmijeni);
		
		textFieldPrezimeRoditelja = new JTextField();
		textFieldPrezimeRoditelja.setColumns(10);
		textFieldPrezimeRoditelja.setBounds(166, 216, 189, 20);
		frmVrti.getContentPane().add(textFieldPrezimeRoditelja);
		
		JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja:");
		lblAdresaStanovanja.setBounds(22, 262, 128, 14);
		frmVrti.getContentPane().add(lblAdresaStanovanja);
		
		textFieldAdresaStanovanja = new JTextField();
		textFieldAdresaStanovanja.setColumns(10);
		textFieldAdresaStanovanja.setBounds(166, 259, 189, 20);
		frmVrti.getContentPane().add(textFieldAdresaStanovanja);
		
		JLabel lblBrojTelefonaStaratelja = new JLabel("Broj telefona staratelja:");
		lblBrojTelefonaStaratelja.setBounds(0, 300, 140, 14);
		frmVrti.getContentPane().add(lblBrojTelefonaStaratelja);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setColumns(10);
		textFieldBrojTelefona.setBounds(166, 297, 189, 20);
		frmVrti.getContentPane().add(textFieldBrojTelefona);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(88, 408, 46, 14);
		frmVrti.getContentPane().add(lblGrupa);
		
		JLabel lblAktivnosti = new JLabel("Aktivnosti:");
		lblAktivnosti.setBounds(71, 346, 79, 14);
		frmVrti.getContentPane().add(lblAktivnosti);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Engleski jezik");
		chckbxNewCheckBox.setBounds(153, 342, 149, 23);
		frmVrti.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNjemackiJezik = new JCheckBox("Njema\u010Dki jezik");
		chckbxNjemackiJezik.setBounds(327, 342, 139, 23);
		frmVrti.getContentPane().add(chckbxNjemackiJezik);
		
		JCheckBox chckbxPenjanjePoStijenama = new JCheckBox("Penjanje po stijenama");
		chckbxPenjanjePoStijenama.setBounds(153, 375, 162, 23);
		frmVrti.getContentPane().add(chckbxPenjanjePoStijenama);
		
		JCheckBox chckbxRuskiJezik = new JCheckBox("Ruski jezik");
		chckbxRuskiJezik.setBounds(327, 375, 97, 23);
		frmVrti.getContentPane().add(chckbxRuskiJezik);
		
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
		
		JLabel labelFormat = new JLabel("dd-mm-gggg");
		labelFormat.setBounds(365, 137, 79, 14);
		frmVrti.getContentPane().add(labelFormat);
		
		JLabel label = new JLabel("dd-mm-gggg");
		label.setBounds(365, 484, 79, 14);
		frmVrti.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("dd-mm-gggg");
		label_1.setBounds(365, 520, 79, 14);
		frmVrti.getContentPane().add(label_1);
		
		
		btnIzmijeni.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textFieldImeDjeteta.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Ime mora sadr�avati barem 3 slova.");
					return;
				}
				
				if(textFieldPrezimeDjeteta.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Prezime mora sadr�avati barem 3 slova.");
					return;
				}
				
				Pattern datum  = Pattern.compile("^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$");
				if (!datum.matcher(textFieldDatumRodjenjaDjeteta.getText()).matches()) {
					JOptionPane.showMessageDialog(null, "Datum rodjenja nije u ispravnom datumu.");
			        return;
			    }
				if(textFieldImeRoditelja.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Ime roditelja mora sadr�avati barem 3 slova.");
					return;
				}
				
				if(textFieldPrezimeRoditelja.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Prezime roditelja mora sadr�avati barem 3 slova.");
					return;
				}
				
				if(textFieldAdresaStanovanja.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Niste unijeli adresu");
					return;
				}
				
				if(textFieldBrojTelefona.getText().length() <= 8) {
					JOptionPane.showMessageDialog(null, "Broj telefona nije u ispravnom formatu.");
					return;
				}
				
				DijeteServis ds = new DijeteServis(s);
				
				// jer u bazu upisuje kao yyyy-mm-dd
				String[] s = textFieldDatumRodjenjaDjeteta.getText().split("-");
				
				Dijete d = new Dijete();
				d.setIme(textFieldImeDjeteta.getText());
				d.setPrezime(textFieldPrezimeDjeteta.getText());
				d.setDatumRodjenja(s[2]+"-"+s[1]+"-"+s[0]); // yyyy-mm-dd
				d.setAdresaPrebivalista(textFieldAdresaStanovanja.getText());
				d.setImeRoditelja(textFieldImeRoditelja.getText());
				d.setBrojTelefona(textFieldBrojTelefona.getText());
				d.setPrezimeRoditelja(textFieldPrezimeRoditelja.getText());
				try {
					d.setDatumUpisa(dateFormat.parse(textFieldDatumUpisa.getText()));
					d.setDatumIsteka(dateFormat.parse(textFieldDatumIsteka.getText()));
				}
				catch (Exception e1) {
					logger.info(e1);
					JOptionPane.showMessageDialog(null, "Datum upisa i/ili istika nisu u ispravnom formatu");
				}
				
				Grupa g = (Grupa)comboBoxGrupa.getSelectedItem();
				d.setGrupa(g);
				
				ds.evidentiraj(d);
			}
		});
		postaviListu();

	}
	
	public void postaviListu() {
		GrupaServis gs = new GrupaServis(s);
		List<Grupa> grupe = gs.sveGrupe();
		
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		
		for(Grupa g : grupe){
			model.addElement(g);
		}
		
		comboBoxGrupa.setModel(model);
	}
}
