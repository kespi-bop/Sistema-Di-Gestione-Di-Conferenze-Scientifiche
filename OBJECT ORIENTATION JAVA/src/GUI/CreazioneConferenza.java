package GUI;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Model.Conferenza;
import Model.Organizzatore_Locale;
import Model.Organizzatore_Scientifico;
import Model.Pubblicità;
import Model.Sede;
import Model.Sponsor;

import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class CreazioneConferenza {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField textFieldTitolo;
	private JTextField textFieldSpesa;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private Controller controller;
	private JFormattedTextField dateInizioField;
	private JFormattedTextField dateFineField;
	private JComboBox<String>enumeraSede;
	private JEditorPane descrizionePane;
	private DefaultTableModel modelL;
	private DefaultTableModel modelS;
	private DefaultTableModel model;
	private Date dateTimeInizio;
	private Date dateTimeFine;
	private Sede sedeSessione;
	private Conferenza conferenzaCreata;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public CreazioneConferenza(Controller controller, JFrame frameHome) {
		initialize(controller,frameHome);
	}
	
	private void initialize(Controller controller, final JFrame frameHome) {
		
		//SWING COMPONENTS
		this.controller = controller;
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(350, 100, 600, 800);
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
		sedeLabel.setBounds(92, 108, 48, 14);
		sedeLabel.setForeground(new Color(57, 113, 177));
		
		JLabel titoloLabel = new JLabel("Titolo*");
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBounds(92, 64, 48, 14);
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setBackground(new Color(57, 113, 177));
		
		JLabel dataInizioLabel = new JLabel("Data inizio*");
		dataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataInizioLabel.setBounds(74, 156, 66, 14);
		dataInizioLabel.setForeground(new Color(57, 113, 177));
		
		JLabel dataFineLabel = new JLabel("Data fine*");
		dataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataFineLabel.setBounds(74, 192, 66, 14);
		dataFineLabel.setForeground(new Color(57, 113, 177));
		
		JLabel sponsorLabel = new JLabel("Sponsor");
		sponsorLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sponsorLabel.setBounds(80, 318, 60, 14);
		sponsorLabel.setForeground(new Color(57, 113, 177));
		
		JLabel descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizioneLabel.setBounds(69, 226, 71, 14);
		descrizioneLabel.setForeground(new Color(57, 113, 177));
		
		enumeraSede = new JComboBox<String>();
		enumeraSede.setForeground(new Color(255, 255, 255));
		enumeraSede.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		enumeraSede.setFocusable(false);
		enumeraSede.setBorder(null);
		enumeraSede.setBackground(new Color(32, 33, 35));
		enumeraSede.setBounds(150, 105, 154, 21);	
		panel.add(enumeraSede);
		RiempiComboBoxSedi();	
		
		textFieldTitolo = new JTextField();
		textFieldTitolo.setCaretColor(new Color(255, 255, 255));
		textFieldTitolo.setDisabledTextColor(new Color(255, 255, 255));
		textFieldTitolo.setSelectionColor(new Color(126, 87, 194));
		textFieldTitolo.setForeground(new Color(255, 255, 255));
		textFieldTitolo.setBorder(null);
		textFieldTitolo.setBackground(new Color(32, 33, 35));
		textFieldTitolo.setBounds(150, 61, 348, 20);
		textFieldTitolo.setColumns(10);
		
		textFieldSpesa = new JTextField();
		textFieldSpesa.setCaretColor(new Color(255, 255, 255));
		textFieldSpesa.setDisabledTextColor(new Color(255, 255, 255));
		textFieldSpesa.setSelectionColor(new Color(126, 87, 194));
		textFieldSpesa.setForeground(new Color(255, 255, 255));
		textFieldSpesa.setBorder(null);
		textFieldSpesa.setBackground(new Color(32, 33, 35));
		textFieldSpesa.setBounds(150, 347, 130, 20);
		textFieldSpesa.setColumns(10);
		
		JLabel as = new JLabel("premi per aggiungere");
		as.setFont(new Font("Tahoma", Font.PLAIN, 11));
		as.setBounds(150, 415, 142, 14);
		as.setForeground(new Color(70, 71, 74));
		
		JLabel sponsorAggiuntiLabel = new JLabel("sponsor aggiunti");
		sponsorAggiuntiLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sponsorAggiuntiLabel.setBounds(302, 428, 200, 14);
		sponsorAggiuntiLabel.setForeground(new Color(70, 71, 74));
		
		JTextPane organizzatoreScientificoLabel = new JTextPane();
		organizzatoreScientificoLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		organizzatoreScientificoLabel.setBounds(49, 594, 91, 34);
		organizzatoreScientificoLabel.setText("Organizzatore\r\nscientifico*");
		organizzatoreScientificoLabel.setOpaque(false);
		organizzatoreScientificoLabel.setForeground(new Color(57, 113, 177));
		
		JComboBox<String> comboBoxOrganizzatoreS = new JComboBox<String>();
		comboBoxOrganizzatoreS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxOrganizzatoreS.setBounds(147, 594, 130, 22);
		comboBoxOrganizzatoreS.setInheritsPopupMenu(true);
		comboBoxOrganizzatoreS.setDoubleBuffered(true);
		comboBoxOrganizzatoreS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxOrganizzatoreS.setBackground(new Color(32, 33, 35));
		comboBoxOrganizzatoreS.setBorder(null);
		comboBoxOrganizzatoreS.setOpaque(false);
		comboBoxOrganizzatoreS.setFocusable(false);
		comboBoxOrganizzatoreS.setForeground(new Color(255, 255, 255));
		RiempiComboBoxOrganizzaotoreS(comboBoxOrganizzatoreS);
		
		JComboBox<String> comboBoxSponsor = new JComboBox<String>();
		comboBoxSponsor.setForeground(new Color(255, 255, 255));
		comboBoxSponsor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxSponsor.setFocusable(false);
		comboBoxSponsor.setBounds(150, 317, 130, 22);
		comboBoxSponsor.setBorder(null);
		comboBoxSponsor.setBackground(new Color(32, 33, 35));
		RiempiComboBoxSponsor(comboBoxSponsor);
		
		JLabel lblPremiPerAggiungere_1 = new JLabel("premi per aggiungere");
		lblPremiPerAggiungere_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPremiPerAggiungere_1.setBounds(147, 663, 133, 14);
		lblPremiPerAggiungere_1.setForeground(new Color(70, 71, 74));
		
		JLabel sponsorAggiuntiLabel_1_1 = new JLabel("organizzatori scientifici aggiunti");
		sponsorAggiuntiLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sponsorAggiuntiLabel_1_1.setBounds(300, 700, 198, 14);
		sponsorAggiuntiLabel_1_1.setForeground(new Color(70, 71, 74));
		
		JComboBox<String> comboBoxOrganizzatoreL = new JComboBox<String>();
		comboBoxOrganizzatoreL.setBackground(new Color(32, 33, 35));
		comboBoxOrganizzatoreL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxOrganizzatoreL.setBorder(null);
		comboBoxOrganizzatoreL.setBounds(150, 467, 130, 22);
		comboBoxOrganizzatoreL.setForeground(new Color(255, 255, 255));
		comboBoxOrganizzatoreL.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxOrganizzatoreL.setFocusable(false);
		RiempiComboBoxOrganizzatoreL(comboBoxOrganizzatoreL);
	
		JLabel lblPremiPerAggiungere = new JLabel("premi per aggiungere");
		lblPremiPerAggiungere.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPremiPerAggiungere.setBounds(150, 538, 130, 14);
		lblPremiPerAggiungere.setForeground(new Color(70, 71, 74));
		
		JLabel organizzatoriLAggiunti = new JLabel("organizzatori locali aggiunti");
		organizzatoriLAggiunti.setFont(new Font("Tahoma", Font.PLAIN, 11));
		organizzatoriLAggiunti.setBounds(302, 573, 200, 14);
		organizzatoriLAggiunti.setForeground(new Color(70, 71, 74));
		panel.setLayout(null);
		panel.add(sedeLabel);
		panel.add(titoloLabel);
		panel.add(dataInizioLabel);
		panel.add(dataFineLabel);
		panel.add(sponsorLabel);
		panel.add(descrizioneLabel);
		panel.add(enumeraSede);
		panel.add(textFieldTitolo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(300, 318, 198, 106);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setGridColor(new Color(0,0,0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Azienda", "Spesa"
			}
		){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane_1.setViewportView(table);
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setBackground(new Color(32, 33, 35));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.getTableHeader().setReorderingAllowed(false); 
		panel.add(comboBoxOrganizzatoreL);
		panel.add(lblPremiPerAggiungere);
		panel.add(as);
		panel.add(textFieldSpesa);
		panel.add(comboBoxSponsor);
		panel.add(organizzatoriLAggiunti);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(new Color(32, 33, 35));
		scrollPane_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_2.setBounds(298, 467, 200, 105);
		panel.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setForeground(new Color(255, 255, 255));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Organizzatore Locale"
			}
		)
		{
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setResizable(false);
		table_2.setSelectionBackground(new Color(126, 87, 194));
		table_2.setBackground(new Color(32, 33, 35));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.getTableHeader().setReorderingAllowed(false); 
		scrollPane_2.setViewportView(table_2);
		
		panel.add(sponsorAggiuntiLabel);
		panel.add(organizzatoreScientificoLabel);
		panel.add(comboBoxOrganizzatoreS);
		panel.add(lblPremiPerAggiungere_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBackground(new Color(32, 33, 35));
		scrollPane_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_3.setBounds(298, 594, 198, 105);
		panel.add(scrollPane_3);
		
		table_1 = new JTable();
		table_1.setForeground(new Color(255, 255, 255));
		table_1.getTableHeader().setReorderingAllowed(false); 
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Organizzatore Scientifico"
			}
		){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_3.setViewportView(table_1);
		table_1.setSelectionBackground(new Color(126, 87, 194));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBackground(new Color(32, 33, 35));
		panel.add(sponsorAggiuntiLabel_1_1);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setBounds(425, 767, 165, 33);
		panel.add(signature);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(150, 371, 130, 14);
		panel.add(separator);
		
		JTextPane organizzatoreLocaleLabel = new JTextPane();
		organizzatoreLocaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		organizzatoreLocaleLabel.setText("Organizzatore\r\nlocale*");
		organizzatoreLocaleLabel.setOpaque(false);
		organizzatoreLocaleLabel.setForeground(new Color(57, 113, 177));
		organizzatoreLocaleLabel.setBounds(49, 467, 91, 34);
		panel.add(organizzatoreLocaleLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(150, 217, 154, 2);
		panel.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(150, 83, 348, 2);
		panel.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(150, 179, 154, 2);
		panel.add(separator_1_2);
		
		JLabel spesaLabel = new JLabel("spesa");
		spesaLabel.setForeground(new Color(61, 63, 67));
		spesaLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spesaLabel.setBounds(92, 350, 51, 14);
		panel.add(spesaLabel);
		
		JButton aggiungiSponsorButton = new JButton("aggiungi");
		aggiungiSponsorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiSponsorButton.setForeground(Color.WHITE);
		aggiungiSponsorButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiSponsorButton.setFocusPainted(false);
		aggiungiSponsorButton.setBorder(null);
		aggiungiSponsorButton.setBackground(new Color(126, 87, 194));
		aggiungiSponsorButton.setBounds(150, 384, 130, 28);
		panel.add(aggiungiSponsorButton);
		
		JButton aggiungiOrganizzatoreLButton = new JButton("aggiungi");
		aggiungiOrganizzatoreLButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreLButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreLButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreLButton.setFocusPainted(false);
		aggiungiOrganizzatoreLButton.setBorder(null);
		aggiungiOrganizzatoreLButton.setBackground(new Color(126, 87, 194));
		aggiungiOrganizzatoreLButton.setBounds(150, 506, 130, 28);
		panel.add(aggiungiOrganizzatoreLButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 226, 346, 81);
		panel.add(scrollPane);
		
		descrizionePane = new JEditorPane();		
		descrizionePane.setSelectionColor(new Color(126, 87, 194));
		descrizionePane.setForeground(Color.WHITE);
		descrizionePane.setDisabledTextColor(Color.WHITE);
		descrizionePane.setCaretColor(Color.WHITE);
		descrizionePane.setBorder(null);
		descrizionePane.setBackground(new Color(32, 33, 35));
		scrollPane.setViewportView(descrizionePane);
		
		JButton aggiungiOrganizzatoreSButton = new JButton("aggiungi");
		aggiungiOrganizzatoreSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreSButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreSButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreSButton.setFocusPainted(false);
		aggiungiOrganizzatoreSButton.setBorder(null);
		aggiungiOrganizzatoreSButton.setBackground(new Color(126, 87, 194));
		aggiungiOrganizzatoreSButton.setBounds(147, 632, 130, 28);
		panel.add(aggiungiOrganizzatoreSButton);

		dateInizioField = new JFormattedTextField(format);
		dateInizioField.setSelectionColor(new Color(126, 87, 194));
		dateInizioField.setSelectedTextColor(Color.WHITE);
		dateInizioField.setForeground(Color.WHITE);
		dateInizioField.setDisabledTextColor(Color.WHITE);
		dateInizioField.setCaretColor(Color.WHITE);
		dateInizioField.setBorder(null);
		dateInizioField.setBackground(new Color(32, 33, 35));
		dateInizioField.setBounds(150, 154, 154, 20);
		panel.add(dateInizioField);
	
		dateFineField = new JFormattedTextField(format);
		dateFineField.setSelectionColor(new Color(126, 87, 194));
		dateFineField.setSelectedTextColor(Color.WHITE);
		dateFineField.setForeground(Color.WHITE);
		dateFineField.setDisabledTextColor(Color.WHITE);
		dateFineField.setCaretColor(Color.WHITE);
		dateFineField.setBorder(null);
		dateFineField.setBackground(new Color(32, 33, 35));
		dateFineField.setBounds(150, 193, 154, 20);
		panel.add(dateFineField);
		
		JLabel lblFormatoDataYyyymmdd = new JLabel("yyyy-MM-dd");
		lblFormatoDataYyyymmdd.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFormatoDataYyyymmdd.setBounds(150, 138, 174, 14);
		panel.add(lblFormatoDataYyyymmdd);
		
		JButton costruisciProgrammaButton = new JButton("Programma");
		costruisciProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		costruisciProgrammaButton.setForeground(Color.WHITE);
		costruisciProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		costruisciProgrammaButton.setFocusPainted(false);
		costruisciProgrammaButton.setBorder(null);
		costruisciProgrammaButton.setBackground(new Color(57, 113, 177));
		costruisciProgrammaButton.setBounds(398, 725, 165, 36);
		panel.add(costruisciProgrammaButton);
		

		
		
		
		
		//PULSANTI & LISTNERS
		
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	RimuoviRiga(me, comboBoxSponsor, table);
	         }
	      });

		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(frame, frameHome);
			}
		});

		table_2.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 RimuoviRiga(me, comboBoxOrganizzatoreL, table_2);
	         }
	      });		
		
		table_1.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 RimuoviRiga(me, comboBoxOrganizzatoreS, table_1);
	         }
	      });

		aggiungiSponsorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				AggiungSponsorAllaTable(textFieldSpesa.getText(), comboBoxSponsor);				
			}
		});

		aggiungiOrganizzatoreLButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				AggiungiOrganizzatoreLAllaTable(comboBoxOrganizzatoreL);			
			}
		});

		aggiungiOrganizzatoreSButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				AggiungiOrganizzatoreSAllaTable(comboBoxOrganizzatoreS);			
			}
		});

		costruisciProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){		
				AggiungiProgrammaCommit(frameHome);
			}
		});

	
	}
	
	
	//METODI IMPLEMENTATIVI
	private void RimuoviRiga(MouseEvent me, JComboBox<String> comboBoxSponsor, JTable table) {	
        if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
            JTable target = (JTable)me.getSource();
            int row = target.getSelectedRow(); // seleziona riga
            comboBoxSponsor.addItem(table.getValueAt(row, 0).toString());
            ((DefaultTableModel)table.getModel()).removeRow(row); //ripristino la combobox col valore eliminato
        }
	}
	

	private void RiempiComboBoxOrganizzaotoreS(JComboBox<String> comboBoxOrganizzatoreS) {
		//riempio la ComboBox chiedendo al DB quali sono gli organizzatori scientifici
		ArrayList<String> OrganizzatoreS = controller.ottieniAllOrganizzatoriS();
		for(String s: OrganizzatoreS)
		{
			comboBoxOrganizzatoreS.addItem(s);
		}
	}

	
	private void RiempiComboBoxOrganizzatoreL(JComboBox<String> comboBoxOrganizzatoreL) {
		//riempio la ComboBox chiedendo al DB quali sono gli organizzatori locali
		ArrayList<String> OrganizzatoreL = controller.ottieniAllOrganizzatoriL();
		for(String l: OrganizzatoreL)
		{
			comboBoxOrganizzatoreL.addItem(l);
		}
	}
	

	private void RiempiComboBoxSponsor(JComboBox<String> comboBoxSponsor) {
		//riempio la ComboBox chiedendo al DB quali sono le sedi
		ArrayList<String> sponsor = controller.ottieniAllSponsor();
		for(String s: sponsor)
		{
			comboBoxSponsor.addItem(s);
		}
	}

	
	private void AggiungiOrganizzatoreSAllaTable(JComboBox<String> comboBoxOrganizzatoreS) {
		DefaultTableModel model = (DefaultTableModel)table_1.getModel();
		try{
			model.addRow(new Object[] {comboBoxOrganizzatoreS.getSelectedItem().toString()});	
			comboBoxOrganizzatoreS.removeItem(comboBoxOrganizzatoreS.getSelectedItem());
		}catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Non sono presenti altri organizzatori scientifici!","ERROR:409", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void AggiungiOrganizzatoreLAllaTable(JComboBox<String> comboBoxOrganizzatoreL) {
		DefaultTableModel model = (DefaultTableModel)table_2.getModel();
		try{
			model.addRow(new Object[] {comboBoxOrganizzatoreL.getSelectedItem().toString()});	
			comboBoxOrganizzatoreL.removeItem(comboBoxOrganizzatoreL.getSelectedItem());
		}catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Non sono presenti altri organizzatori locali!","ERROR:408", JOptionPane.ERROR_MESSAGE);
		}
	}
	

	private void AggiungSponsorAllaTable(String text, JComboBox<String> comboBoxSponsor) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		if(!isEmptySpesa(textFieldSpesa.getText()))
		{
			try{
				model.addRow(new Object[] {comboBoxSponsor.getSelectedItem().toString(),Math.abs(Double.parseDouble(textFieldSpesa.getText()))+"€"});	
				comboBoxSponsor.removeItem(comboBoxSponsor.getSelectedItem());
			}catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null,"Inserisci un numero reale come spesa!","ERROR:406", JOptionPane.ERROR_MESSAGE);
			}catch(NullPointerException exception){
				JOptionPane.showMessageDialog(null,"Non sono presenti altri sponsor!","ERROR:407", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	private boolean isEmptySpesa(String spesaTextField) {
		if(spesaTextField.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Inserisci una spesa!","ERROR:405", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
	

	private void RiempiComboBoxSedi() {
		//riempio la ComboBox chiedendo al DB quali sono le sedi
		ArrayList<String> sedi = controller.ottieniSedi();
		for(String s: sedi)
		{
			enumeraSede.addItem(s);
		}				
	}

	
	private void AggiungiProgrammaCommit(JFrame frameHome) {
		
		modelL = (DefaultTableModel)table_2.getModel();
		modelS = (DefaultTableModel)table_1.getModel();
		model = (DefaultTableModel)table.getModel();		
		if(isAllCampiRiempiti())
		{	
			InizializzaValuesConferenza();			
			if(isConferenzaIdonea() && isOrarioCorretto())	{
				MemorizzaOrganizzatoriEPubblicitàInArray(frameHome);	
			}
			else{
				JOptionPane.showMessageDialog(null,"Date NON conformi!","ERROR:411", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	private void MemorizzaOrganizzatoriEPubblicitàInArray(JFrame frameHome) {
		ArrayList<Organizzatore_Locale> listaOrganizzatoriLocali = new ArrayList<Organizzatore_Locale>();
		InizializzaOrganizzatorilocali(listaOrganizzatoriLocali);
		ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici = new ArrayList<Organizzatore_Scientifico>();
		InizializzaOrganizzatoriScientifici(listaOrganizzatoriScientifici);
		ArrayList<Pubblicità> listaPubblicità = new ArrayList<Pubblicità>();		
		InizializzaPubblicità(listaPubblicità);	
		controller.vediCreazioneProgramma(frame, frameHome, conferenzaCreata, listaOrganizzatoriLocali, listaOrganizzatoriScientifici, listaPubblicità);
	}

	
	private void InizializzaValuesConferenza() {
		sedeSessione = new Sede();
		sedeSessione.setNomeSede(enumeraSede.getSelectedItem().toString());		
		conferenzaCreata = new Conferenza(sedeSessione);
		conferenzaCreata.setTitoloConferenza(textFieldTitolo.getText());
		conferenzaCreata.setDescrizione(descrizionePane.getText());				
		//converto la stringa di data passata in tipo Date
		CastaStringToDataInizio();
		CastaStringToDataFine();
		conferenzaCreata.setDataInizio(dateTimeInizio);
		conferenzaCreata.setDataFine(dateTimeFine);
	}

	
	private void InizializzaPubblicità(ArrayList<Pubblicità> listaPubblicità) {
		
		for(int count = 0; count < model.getRowCount(); count++){
			//costruisco lo sponsor
			Sponsor sponsor = new Sponsor();
			sponsor.setNomeAzienda(model.getValueAt(count, 0).toString());
			//lo inserisco in pubblicità con la spesa sostenuta
			Pubblicità pubblicità = new Pubblicità();
			pubblicità.setCoferenza(conferenzaCreata);
			pubblicità.setSponsor(sponsor);
			String spesa = model.getValueAt(count, 1).toString();
			spesa = spesa.substring(0,spesa.length()-1);
			pubblicità.setSpesa(Double.parseDouble(spesa));
			listaPubblicità.add(pubblicità);					
		}
	}
	

	private void InizializzaOrganizzatoriScientifici(ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici) {
		for(int count = 0; count < modelS.getRowCount(); count++){
			Organizzatore_Scientifico organizzatoreScientifico = new Organizzatore_Scientifico();
			organizzatoreScientifico.setEmail(modelS.getValueAt(count, 0).toString());
			listaOrganizzatoriScientifici.add(organizzatoreScientifico);
		 }
	}
	

	private void InizializzaOrganizzatorilocali(ArrayList<Organizzatore_Locale> listaOrganizzatoriLocali) {		
		for(int count = 0; count < modelL.getRowCount() ; count++){
			 Organizzatore_Locale organizzatoreLocale = new Organizzatore_Locale();
			 organizzatoreLocale.setEmail(modelL.getValueAt(count, 0).toString());
			 listaOrganizzatoriLocali.add(organizzatoreLocale);
		}
	}
	

	private Boolean isOrarioCorretto() {
		if(dateTimeInizio.before(dateTimeFine) || dateTimeInizio.equals(dateTimeFine))
		{
			return true;
		}
		JOptionPane.showMessageDialog(null,"Orario NON idoneo!","ERROR:411", JOptionPane.ERROR_MESSAGE);
		return false;
	}


	private Boolean isConferenzaIdonea() {
		if(!(controller.ottieniConferenzaConflitto(conferenzaCreata.getDataInizio(),conferenzaCreata.getDataFine(),sedeSessione.getNomeSede()).isEmpty()))
		{
			JOptionPane.showMessageDialog(null,"Errore! La sede ospita già una conferenza!","ERROR:411", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	

	private void CastaStringToDataFine() {
		dateTimeFine = new Date();
		try {
			dateTimeFine = format.parse(dateFineField.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	

	private void CastaStringToDataInizio() {
		dateTimeInizio = new Date();
		try {
			dateTimeInizio = format.parse(dateInizioField.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	
	private Boolean isAllCampiRiempiti() {
		if(textFieldTitolo.getText().isEmpty() || dateInizioField.getText().isEmpty() || dateFineField.getText().isEmpty()
				   || modelL.getRowCount() == 0 || modelS.getRowCount() == 0)	
		{
			JOptionPane.showMessageDialog(null,"Riempi tutti i campi contrassegnati da *!","ERROR:410", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}
