package GUI;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;

public class CreazioneConferenza {

	private int mouseX, mouseY;
	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Create the application.
	 */
	public CreazioneConferenza(JFrame frameHomeOrganizzatore) {
		initialize(frameHomeOrganizzatore);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final JFrame frameHomeOrganizzatore) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(new Rectangle(3, 3, 3, 3));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 600, 800);
		frame.getContentPane().add(panel);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
				
		JLabel exitLabel = new JLabel("");
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameHomeOrganizzatore.setEnabled(true);
				frameHomeOrganizzatore.setVisible(true);
				frame.dispose();
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		exitLabel.setBounds(573, 11, 17, 21);
		panel.add(exitLabel);
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
			}
		});
		dragFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();			}
		});
		dragFrame.setBounds(0, 0, 563, 40);
		panel.add(dragFrame);
		
		
		
		JLabel sedeLabel = new JLabel("Sede");
		sedeLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sedeLabel.setBounds(92, 114, 48, 14);
		sedeLabel.setForeground(new Color(57, 113, 177));
		
		JLabel titoloLabel = new JLabel("Titolo");
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBounds(92, 70, 48, 14);
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setBackground(new Color(57, 113, 177));
		
		JLabel dataInizioLabel = new JLabel("Data inizio");
		dataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataInizioLabel.setBounds(74, 148, 66, 14);
		dataInizioLabel.setForeground(new Color(57, 113, 177));
		
		JLabel dataFineLabel = new JLabel("Data fine");
		dataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataFineLabel.setBounds(74, 179, 66, 14);
		dataFineLabel.setForeground(new Color(57, 113, 177));
		
		JLabel sponsorLabel = new JLabel("Sponsor");
		sponsorLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sponsorLabel.setBounds(80, 306, 60, 14);
		sponsorLabel.setForeground(new Color(57, 113, 177));
		
		JLabel descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizioneLabel.setBounds(69, 207, 71, 14);
		descrizioneLabel.setForeground(new Color(57, 113, 177));
		
		textField = new JTextField();
		textField.setCaretColor(new Color(255, 255, 255));
		textField.setDisabledTextColor(new Color(255, 255, 255));
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setForeground(new Color(255, 255, 255));
		textField.setBorder(null);
		textField.setBackground(new Color(32, 33, 35));
		textField.setBounds(150, 145, 154, 20);
		textField.setColumns(10);
		
		JComboBox enumeraSede = new JComboBox();
		enumeraSede.setBorder(null);
		enumeraSede.setBackground(new Color(32, 33, 35));
		enumeraSede.setBounds(150, 111, 154, 21);
		
		textField_1 = new JTextField();
		textField_1.setCaretColor(new Color(255, 255, 255));
		textField_1.setDisabledTextColor(new Color(255, 255, 255));
		textField_1.setSelectionColor(new Color(126, 87, 194));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(32, 33, 35));
		textField_1.setBounds(150, 67, 348, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setCaretColor(new Color(255, 255, 255));
		textField_2.setDisabledTextColor(new Color(255, 255, 255));
		textField_2.setSelectionColor(new Color(126, 87, 194));
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setBorder(null);
		textField_2.setBackground(new Color(32, 33, 35));
		textField_2.setBounds(150, 176, 154, 20);
		textField_2.setColumns(10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setCaretColor(new Color(255, 255, 255));
		editorPane.setDisabledTextColor(new Color(255, 255, 255));
		editorPane.setSelectionColor(new Color(126, 87, 194));
		editorPane.setForeground(new Color(255, 255, 255));
		editorPane.setBorder(null);
		editorPane.setBackground(new Color(32, 33, 35));
		editorPane.setBounds(150, 207, 348, 67);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBorder(null);
		comboBox_1.setBackground(new Color(32, 33, 35));
		comboBox_1.setBounds(150, 302, 130, 22);
		
		textField_3 = new JTextField();
		textField_3.setCaretColor(new Color(255, 255, 255));
		textField_3.setDisabledTextColor(new Color(255, 255, 255));
		textField_3.setSelectionColor(new Color(126, 87, 194));
		textField_3.setForeground(new Color(255, 255, 255));
		textField_3.setBorder(null);
		textField_3.setBackground(new Color(32, 33, 35));
		textField_3.setBounds(150, 335, 142, 20);
		textField_3.setColumns(10);
		
		JLabel as = new JLabel("premi per aggiungere");
		as.setFont(new Font("Tahoma", Font.PLAIN, 11));
		as.setBounds(150, 403, 142, 14);
		as.setForeground(new Color(70, 71, 74));
		
		JLabel sponsorAggiuntiLabel = new JLabel("sponsor aggiunti");
		sponsorAggiuntiLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sponsorAggiuntiLabel.setBounds(302, 416, 200, 14);
		sponsorAggiuntiLabel.setForeground(new Color(70, 71, 74));
		
		table = new JTable();
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setBounds(302, 306, 198, 106);
		table.setBackground(new Color(32, 33, 35));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JTextPane organizzatoreScientificoLabel = new JTextPane();
		organizzatoreScientificoLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		organizzatoreScientificoLabel.setBounds(49, 598, 91, 34);
		organizzatoreScientificoLabel.setText("Organizzatore\r\nscientifico");
		organizzatoreScientificoLabel.setOpaque(false);
		organizzatoreScientificoLabel.setForeground(new Color(57, 113, 177));
		
		JComboBox comboBox_1_1_1 = new JComboBox();
		comboBox_1_1_1.setBounds(147, 598, 130, 22);
		comboBox_1_1_1.setInheritsPopupMenu(true);
		comboBox_1_1_1.setDoubleBuffered(true);
		comboBox_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1_1_1.setBackground(new Color(32, 33, 35));
		comboBox_1_1_1.setBorder(null);
		comboBox_1_1_1.setOpaque(false);
		
		JLabel lblPremiPerAggiungere_1 = new JLabel("premi per aggiungere");
		lblPremiPerAggiungere_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPremiPerAggiungere_1.setBounds(147, 661, 133, 14);
		lblPremiPerAggiungere_1.setForeground(new Color(70, 71, 74));
		
		table_1 = new JTable();
		table_1.setSelectionBackground(new Color(126, 87, 194));
		table_1.setBounds(298, 586, 198, 101);
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table_1.setBackground(new Color(32, 33, 35));
		
		JLabel sponsorAggiuntiLabel_1_1 = new JLabel("organizzatori scientifici aggiunti");
		sponsorAggiuntiLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sponsorAggiuntiLabel_1_1.setBounds(300, 688, 198, 14);
		sponsorAggiuntiLabel_1_1.setForeground(new Color(70, 71, 74));
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBackground(new Color(32, 33, 35));
		comboBox_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_1_1.setBorder(null);
		comboBox_1_1.setBounds(150, 459, 130, 22);
		
		JLabel lblPremiPerAggiungere = new JLabel("premi per aggiungere");
		lblPremiPerAggiungere.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPremiPerAggiungere.setBounds(150, 526, 130, 14);
		lblPremiPerAggiungere.setForeground(new Color(70, 71, 74));
		
		table_2 = new JTable();
		table_2.setSelectionBackground(new Color(126, 87, 194));
		table_2.setBounds(298, 459, 200, 101);
		table_2.setBackground(new Color(32, 33, 35));
		table_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel organizzatoriLAggiunti = new JLabel("organizzatori locali aggiunti");
		organizzatoriLAggiunti.setFont(new Font("Tahoma", Font.PLAIN, 11));
		organizzatoriLAggiunti.setBounds(302, 561, 200, 14);
		organizzatoriLAggiunti.setForeground(new Color(70, 71, 74));
		panel.setLayout(null);
		panel.add(sedeLabel);
		panel.add(titoloLabel);
		panel.add(dataInizioLabel);
		panel.add(dataFineLabel);
		panel.add(sponsorLabel);
		panel.add(descrizioneLabel);
		panel.add(textField);
		panel.add(enumeraSede);
		panel.add(textField_1);
		panel.add(textField_2);
		panel.add(editorPane);
		panel.add(table);
		panel.add(comboBox_1_1);
		panel.add(lblPremiPerAggiungere);
		panel.add(as);
		panel.add(textField_3);
		panel.add(comboBox_1);
		panel.add(organizzatoriLAggiunti);
		panel.add(table_2);
		panel.add(sponsorAggiuntiLabel);
		panel.add(organizzatoreScientificoLabel);
		panel.add(comboBox_1_1_1);
		panel.add(lblPremiPerAggiungere_1);
		panel.add(table_1);
		panel.add(sponsorAggiuntiLabel_1_1);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setBounds(425, 767, 165, 33);
		panel.add(signature);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(150, 359, 130, 14);
		panel.add(separator);
		
		JTextPane organizzatoreLocaleLabel = new JTextPane();
		organizzatoreLocaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		organizzatoreLocaleLabel.setText("Organizzatore\r\nlocale");
		organizzatoreLocaleLabel.setOpaque(false);
		organizzatoreLocaleLabel.setForeground(new Color(57, 113, 177));
		organizzatoreLocaleLabel.setBounds(49, 458, 91, 34);
		panel.add(organizzatoreLocaleLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(150, 198, 154, 2);
		panel.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(150, 89, 348, 2);
		panel.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(150, 165, 154, 2);
		panel.add(separator_1_2);
		
		JLabel spesaLabel = new JLabel("spesa");
		spesaLabel.setForeground(new Color(61, 63, 67));
		spesaLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spesaLabel.setBounds(92, 338, 51, 14);
		panel.add(spesaLabel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(150, 281, 348, 14);
		panel.add(separator_2);
		
		JButton aggiungiSponsorButton = new JButton("aggiungi");
		aggiungiSponsorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiSponsorButton.setForeground(Color.WHITE);
		aggiungiSponsorButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiSponsorButton.setFocusPainted(false);
		aggiungiSponsorButton.setBorder(null);
		aggiungiSponsorButton.setBackground(new Color(126, 87, 194));
		aggiungiSponsorButton.setBounds(150, 372, 130, 28);
		panel.add(aggiungiSponsorButton);
		
		JButton aggiungiOrganizzatoreLButton = new JButton("aggiungi");
		aggiungiOrganizzatoreLButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreLButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreLButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreLButton.setFocusPainted(false);
		aggiungiOrganizzatoreLButton.setBorder(null);
		aggiungiOrganizzatoreLButton.setBackground(new Color(126, 87, 194));
		aggiungiOrganizzatoreLButton.setBounds(150, 494, 130, 28);
		panel.add(aggiungiOrganizzatoreLButton);
		
		JButton aggiungiOrganizzatoreSButton = new JButton("aggiungi");
		aggiungiOrganizzatoreSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreSButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreSButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreSButton.setFocusPainted(false);
		aggiungiOrganizzatoreSButton.setBorder(null);
		aggiungiOrganizzatoreSButton.setBackground(new Color(126, 87, 194));
		aggiungiOrganizzatoreSButton.setBounds(147, 631, 130, 28);
		panel.add(aggiungiOrganizzatoreSButton);
		
		JButton costruisciProgrammaButton = new JButton("Programma");
		costruisciProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AggiuntaProgrammi aggiuntaProgrammi = new AggiuntaProgrammi(frameHomeOrganizzatore, frame);
				frameHomeOrganizzatore.setVisible(true);	//richiamo setVisible di Home affinchè non venga posta sotto altre finestre aperte
				frame.setVisible(true);
				aggiuntaProgrammi.frame.setVisible(true);
				frame.setEnabled(false);
			}
		});
		costruisciProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		costruisciProgrammaButton.setForeground(Color.WHITE);
		costruisciProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		costruisciProgrammaButton.setFocusPainted(false);
		costruisciProgrammaButton.setBorder(null);
		costruisciProgrammaButton.setBackground(new Color(57, 113, 177));
		costruisciProgrammaButton.setBounds(398, 725, 165, 36);
		panel.add(costruisciProgrammaButton);
		
		
		
		
		
		
	}
}
