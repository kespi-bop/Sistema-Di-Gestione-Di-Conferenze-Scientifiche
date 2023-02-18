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
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.LineBorder;

import Controller.Controller;

public class AggiungiProgrammiEdit {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;

	
	public AggiungiProgrammiEdit(Controller controller, JFrame frameAzioniDiModifica) {
		initialize(controller,  frameAzioniDiModifica);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller, final JFrame frameAzioniDiModifica) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 600, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 600, 750);
		frame.getContentPane().add(panel);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
				
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(573, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frameAzioniDiModifica.setVisible(true);		
				frameAzioniDiModifica.setEnabled(true);
			}
		});
		panel.setLayout(null);
		exitLabel.setIcon(new ImageIcon(imgExit));
		panel.add(exitLabel);
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 563, 40);
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
		panel.add(dragFrame);
		
		JLabel dataProgrammaLabel = new JLabel("Data");
		dataProgrammaLabel.setBounds(74, 78, 48, 14);
		dataProgrammaLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataProgrammaLabel.setForeground(new Color(57, 113, 177));
		dataProgrammaLabel.setBackground(new Color(57, 113, 177));
		
		JLabel orarioInizioLabel = new JLabel("Orario inizio");
		orarioInizioLabel.setBounds(74, 115, 66, 14);
		orarioInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		orarioInizioLabel.setForeground(new Color(57, 113, 177));
		
		JLabel OrarioFineLabel = new JLabel("Orario fine");
		OrarioFineLabel.setBounds(74, 152, 66, 14);
		OrarioFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		OrarioFineLabel.setForeground(new Color(57, 113, 177));
		
		JLabel locazioneLabel = new JLabel("Locazione*");
		locazioneLabel.setBounds(74, 189, 60, 14);
		locazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		locazioneLabel.setForeground(new Color(57, 113, 177));
		
		JLabel descrizioneLabel = new JLabel("Descrizione*");
		descrizioneLabel.setBounds(74, 294, 71, 14);
		descrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizioneLabel.setForeground(new Color(57, 113, 177));
		
		textField = new JTextField();
		textField.setBounds(150, 149, 154, 20);
		textField.setCaretColor(new Color(255, 255, 255));
		textField.setDisabledTextColor(new Color(255, 255, 255));
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setForeground(new Color(255, 255, 255));
		textField.setBorder(null);
		textField.setBackground(new Color(32, 33, 35));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 73, 348, 20);
		textField_1.setCaretColor(new Color(255, 255, 255));
		textField_1.setDisabledTextColor(new Color(255, 255, 255));
		textField_1.setSelectionColor(new Color(126, 87, 194));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(32, 33, 35));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 218, 154, 20);
		textField_2.setCaretColor(new Color(255, 255, 255));
		textField_2.setDisabledTextColor(new Color(255, 255, 255));
		textField_2.setSelectionColor(new Color(126, 87, 194));
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setBorder(null);
		textField_2.setBackground(new Color(32, 33, 35));
		textField_2.setColumns(10);
		
		JComboBox enumeraLocazione = new JComboBox();
		enumeraLocazione.setBounds(150, 185, 154, 22);
		enumeraLocazione.setBorder(null);
		enumeraLocazione.setBackground(new Color(32, 33, 35));
		
		textField_3 = new JTextField();
		textField_3.setBounds(150, 254, 142, 20);
		textField_3.setCaretColor(new Color(255, 255, 255));
		textField_3.setDisabledTextColor(new Color(255, 255, 255));
		textField_3.setSelectionColor(new Color(126, 87, 194));
		textField_3.setForeground(new Color(255, 255, 255));
		textField_3.setBorder(null);
		textField_3.setBackground(new Color(32, 33, 35));
		textField_3.setColumns(10);
		
		JLabel as = new JLabel("Attenzione! Per aggiungere una sessione, riempire i campi contrassegnati da *");
		as.setBounds(42, 610, 506, 14);
		as.setFont(new Font("Tahoma", Font.PLAIN, 11));
		as.setForeground(new Color(70, 71, 74));
		
		table = new JTable();
		table.setBounds(29, 374, 544, 131);
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setBackground(new Color(32, 33, 35));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(dataProgrammaLabel);
		panel.add(orarioInizioLabel);
		panel.add(OrarioFineLabel);
		panel.add(locazioneLabel);
		panel.add(descrizioneLabel);
		panel.add(textField);
		panel.add(textField_1);
		panel.add(textField_2);
		panel.add(table);
		panel.add(as);
		panel.add(textField_3);
		panel.add(enumeraLocazione);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(425, 717, 165, 33);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		panel.add(signature);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(150, 274, 154, 14);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(150, 238, 154, 14);
		panel.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(150, 98, 348, 2);
		panel.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(150, 172, 154, 8);
		panel.add(separator_1_2);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(150, 357, 348, 14);
		panel.add(separator_2);
		
		JButton aggiungiSponsorButton = new JButton("aggiungi intevallo");
		aggiungiSponsorButton.setBounds(41, 549, 143, 50);
		aggiungiSponsorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiSponsorButton.setForeground(Color.WHITE);
		aggiungiSponsorButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiSponsorButton.setFocusPainted(false);
		aggiungiSponsorButton.setBorder(null);
		aggiungiSponsorButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiSponsorButton);
		
		JButton aggiungiOrganizzatoreLButton = new JButton("aggiungi \r\nevento");
		aggiungiOrganizzatoreLButton.setBounds(413, 549, 142, 50);
		aggiungiOrganizzatoreLButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreLButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreLButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreLButton.setFocusPainted(false);
		aggiungiOrganizzatoreLButton.setBorder(null);
		aggiungiOrganizzatoreLButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiOrganizzatoreLButton);
		
		JButton aggiungiOrganizzatoreSButton = new JButton("aggiungi sessione");
		aggiungiOrganizzatoreSButton.setBounds(230, 549, 142, 50);
		aggiungiOrganizzatoreSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreSButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreSButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreSButton.setFocusPainted(false);
		aggiungiOrganizzatoreSButton.setBorder(null);
		aggiungiOrganizzatoreSButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiOrganizzatoreSButton);
		
		JLabel keynoteSpekaerLabel = new JLabel("Keynote*");
		keynoteSpekaerLabel.setBounds(74, 221, 66, 14);
		keynoteSpekaerLabel.setForeground(new Color(57, 113, 177));
		keynoteSpekaerLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(keynoteSpekaerLabel);
		
		textField_4 = new JTextField();
		textField_4.setBounds(150, 114, 154, 20);
		textField_4.setSelectionColor(new Color(126, 87, 194));
		textField_4.setForeground(Color.WHITE);
		textField_4.setDisabledTextColor(Color.WHITE);
		textField_4.setColumns(10);
		textField_4.setCaretColor(Color.WHITE);
		textField_4.setBorder(null);
		textField_4.setBackground(new Color(32, 33, 35));
		panel.add(textField_4);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(150, 135, 154, 2);
		panel.add(separator_1_2_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(150, 285, 348, 67);
		editorPane.setSelectionColor(new Color(126, 87, 194));
		editorPane.setForeground(Color.WHITE);
		editorPane.setDisabledTextColor(Color.WHITE);
		editorPane.setCaretColor(Color.WHITE);
		editorPane.setBorder(null);
		editorPane.setBackground(new Color(32, 33, 35));
		panel.add(editorPane);
		
		JLabel lblChair = new JLabel("Chair*");
		lblChair.setBounds(74, 257, 60, 14);
		lblChair.setForeground(new Color(57, 113, 177));
		lblChair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblChair);
		
		JButton okAggiungiProgrammaButton = new JButton("OK");
		okAggiungiProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAzioniDiModifica.setVisible(true);
				frameAzioniDiModifica.setEnabled(true);
				frame.dispose();			
			}
		});
		okAggiungiProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okAggiungiProgrammaButton.setForeground(Color.WHITE);
		okAggiungiProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		okAggiungiProgrammaButton.setFocusPainted(false);
		okAggiungiProgrammaButton.setBorder(null);
		okAggiungiProgrammaButton.setBackground(new Color(57, 113, 177));
		okAggiungiProgrammaButton.setBounds(460, 655, 103, 36);
		panel.add(okAggiungiProgrammaButton);
		
		
		
		
		
		
	}
}