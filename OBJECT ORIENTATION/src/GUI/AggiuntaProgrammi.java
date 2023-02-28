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
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Model.Conferenza;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Organizzatore_Locale;
import Model.Organizzatore_Scientifico;
import Model.Programma;
import Model.Pubblicità;
import Model.Seduta;
import Model.Sessione;

import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
import java.text.Format;

public class AggiuntaProgrammi {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat tipoTempo = new SimpleDateFormat("HH:mm");
	JFormattedTextField formattedTextFieldData = new JFormattedTextField(format);
	private DefaultTableModel model;
	private Programma programmaNuovo;
	private Date dataProgramma;
	private Date timeFine;
	private Date timeInizio;
	private Integer mouseX, mouseY;
	public JFrame frame;
	private JLabel as;
	private JTextField orarioFine;
	private JTable tableSessioniAggiunte;
	private JTextField orarioInizio;
	private JTable tableProgrammiAggiunti;
	private JLabel exitLabel;
	private JLabel lblChair; 
	private JLabel lblFormatoDataYyyymmdd_1_1;
	private JLabel lblListaDeiProgrammi;
	private JLabel lblFormatoDataYyyymmdd;
	private JButton creaConferenzaButton;
	private JLabel dragFrame;
	private JPanel panel;
	private JLabel locazioneLabel;
	private JLabel OrarioFineLabel;
	private JLabel orarioInizioLabel;
	private JLabel dataProgrammaLabel;
	private JLabel descrizioneLabel;
	private JLabel lblFormatoDataYyyymmdd_1_1_1;
	private JLabel signature;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JLabel lblTipointervallo;
	private JSeparator separator_1_1;
	private JSeparator separator_1_2;
	private JLabel lblEvento;
	private JButton aggiungiIntervalloButton;
	private JButton aggiungiEventoButton;
	private JScrollPane scrollPane;
	private JEditorPane editorPaneDescrizione;
	private JFormattedTextField textFieldTitolo;
	private JLabel titoloLabel;
	private JSeparator separator_1_2_1;
	private JSeparator separator_1_1_1;
	private JButton btnAggiungiProgramma;
	private JButton aggiungiSessioneButton;
	private JLabel keynoteSpekaerLabel;
	private JComboBox<String> comboBoxLocazione;
	private JComboBox<String> comboBoxChair;
	private JComboBox<String> comboBoxKS;
	private JComboBox<String> comboBoxEvento;
	private JComboBox<String> comboBoxIntervallo;
	private ArrayList<Programma> listaProgrammi = new ArrayList<Programma>();
	private ArrayList<Seduta> listaSedute = new ArrayList<Seduta>();
	private ArrayList<Sessione> listaSessioni = new ArrayList<Sessione>();
	private ArrayList<Evento_Sociale> listaEventi = new ArrayList<Evento_Sociale>();
	private ArrayList<Intervallo> listaIntervalli = new ArrayList<Intervallo>();
	private Controller controller;
	private Image imgExit;

	
	public AggiuntaProgrammi(Controller controller, JFrame frameCreazioneConferenza, JFrame frameHomeOrganizzatore,
							 Conferenza conferenzaCreata, ArrayList<Organizzatore_Locale> listaOrganizzatoriLocali,
							 ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici, ArrayList<Pubblicità> listaPubblicità) {
		initialize(controller, frameCreazioneConferenza, frameHomeOrganizzatore, conferenzaCreata, listaOrganizzatoriLocali, listaOrganizzatoriScientifici, listaPubblicità);
	}

	private void initialize( Controller controller,  JFrame frameCreazioneConferenza,  JFrame frameHome,
			 				 Conferenza conferenzaCreata, ArrayList<Organizzatore_Locale> listaOrganizzatoriLocali,
			 				ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici, ArrayList<Pubblicità> listaPubblicità) {
		this.controller = controller;
		//SWING COMPONENTS
		conferenzaCreata.setOrganizzatoriLocali(listaOrganizzatoriLocali);
		conferenzaCreata.setOrganizzatoriScientifici(listaOrganizzatoriScientifici);
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(850, 80, 600, 880);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 600, 880);
		frame.getContentPane().add(panel);
		
		imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();		
		exitLabel = new JLabel("");
		exitLabel.setBounds(573, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel.setLayout(null);
		exitLabel.setIcon(new ImageIcon(imgExit));
		panel.add(exitLabel);
		
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 563, 32);
		panel.add(dragFrame);		
		//Trascino Frame Undecorated
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 285, 356, 88);
		panel.add(scrollPane);
		
		dataProgrammaLabel = new JLabel("Data");
		dataProgrammaLabel.setBounds(74, 86, 48, 14);
		dataProgrammaLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataProgrammaLabel.setForeground(new Color(57, 113, 177));
		dataProgrammaLabel.setBackground(new Color(57, 113, 177));
		
		orarioInizioLabel = new JLabel("Orario inizio");
		orarioInizioLabel.setBounds(74, 123, 66, 14);
		orarioInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		orarioInizioLabel.setForeground(new Color(57, 113, 177));
		
		OrarioFineLabel = new JLabel("Orario fine");
		OrarioFineLabel.setBounds(74, 158, 66, 14);
		OrarioFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		OrarioFineLabel.setForeground(new Color(57, 113, 177));
		
		locazioneLabel = new JLabel("Locazione*");
		locazioneLabel.setBounds(74, 189, 60, 14);
		locazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		locazioneLabel.setForeground(new Color(57, 113, 177));
		
		descrizioneLabel = new JLabel("Descrizione*");
		descrizioneLabel.setBounds(74, 294, 71, 14);
		descrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizioneLabel.setForeground(new Color(57, 113, 177));
		
		formattedTextFieldData.setDisabledTextColor(new Color(255, 255, 255));	
		formattedTextFieldData.setForeground(new Color(255, 255, 255));
		formattedTextFieldData.setBackground(new Color(32, 33, 35));
		formattedTextFieldData.setBorder(null);
		formattedTextFieldData.setBounds(150, 87, 154, 20);
		panel.add(formattedTextFieldData);
		
		orarioFine = new JTextField();
		orarioFine.setBounds(150, 155, 154, 20);
		orarioFine.setCaretColor(new Color(255, 255, 255));
		orarioFine.setDisabledTextColor(new Color(255, 255, 255));
		orarioFine.setSelectionColor(new Color(126, 87, 194));
		orarioFine.setForeground(new Color(255, 255, 255));
		orarioFine.setBorder(null);
		orarioFine.setBackground(new Color(32, 33, 35));
		orarioFine.setColumns(10);
		
		orarioInizio = new JTextField();
		orarioInizio.setBounds(150, 124, 154, 20);
		orarioInizio.setSelectionColor(new Color(126, 87, 194));
		orarioInizio.setForeground(Color.WHITE);
		orarioInizio.setDisabledTextColor(Color.WHITE);
		orarioInizio.setColumns(10);
		orarioInizio.setCaretColor(Color.WHITE);
		orarioInizio.setBorder(null);
		orarioInizio.setBackground(new Color(32, 33, 35));
		panel.add(orarioInizio);
		
		as = new JLabel("Attenzione! Per aggiungere una sessione, riempire i campi contrassegnati da *");
		as.setBounds(29, 524, 497, 14);
		as.setFont(new Font("Tahoma", Font.PLAIN, 11));
		as.setForeground(new Color(70, 71, 74));
		panel.add(as);
		
		panel.add(dataProgrammaLabel);
		panel.add(orarioInizioLabel);
		panel.add(OrarioFineLabel);
		panel.add(locazioneLabel);
		panel.add(descrizioneLabel);
		panel.add(orarioFine);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(32, 33, 35));
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setBounds(29, 389, 544, 131);
		panel.add(scrollPane_1);
		
		tableSessioniAggiunte = new JTable();
		tableSessioniAggiunte.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titolo","Inizio", "Fine", "Locazione", "Keynote", "Chair", "Descrizione"
			}
		){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableSessioniAggiunte.getTableHeader().setReorderingAllowed(false);
		tableSessioniAggiunte.setSelectionBackground(new Color(126, 87, 194));
		tableSessioniAggiunte.setForeground(new Color(255,255,255));
		tableSessioniAggiunte.setBackground(new Color(32, 33, 35));
		tableSessioniAggiunte.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableSessioniAggiunte.getTableHeader().setReorderingAllowed(false); 
		scrollPane_1.setViewportView(tableSessioniAggiunte);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(425, 847, 165, 33);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		panel.add(signature);
		
		separator_1_1 = new JSeparator();
		separator_1_1.setBounds(150, 109, 154, 8);
		panel.add(separator_1_1);
		
		separator_1_2 = new JSeparator();
		separator_1_2.setBounds(150, 178, 154, 8);
		panel.add(separator_1_2);
		
		comboBoxLocazione = new JComboBox<String>();	
		comboBoxLocazione.setForeground(Color.WHITE);
		comboBoxLocazione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxLocazione.setFocusable(false);
		comboBoxLocazione.setBorder(null);
		comboBoxLocazione.setBackground(new Color(32, 33, 35));
		comboBoxLocazione.setBounds(150, 185, 154, 21);
		panel.add(comboBoxLocazione);
		RiempiComboBoxLocazione(conferenzaCreata, comboBoxLocazione);
		
		lblTipointervallo = new JLabel("Intervallo");
		lblTipointervallo.setForeground(new Color(57, 113, 177));
		lblTipointervallo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipointervallo.setBounds(327, 189, 72, 14);
		panel.add(lblTipointervallo);
		
		comboBoxIntervallo = new JComboBox<String>();
		comboBoxIntervallo.setForeground(Color.WHITE);
		comboBoxIntervallo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxIntervallo.setFocusable(false);
		comboBoxIntervallo.setBorder(null);
		comboBoxIntervallo.setBackground(new Color(32, 33, 35));
		comboBoxIntervallo.setBounds(409, 185, 154, 21);
		comboBoxIntervallo.addItem("CoffeBreak");
		comboBoxIntervallo.addItem("Pranzo");
		panel.add(comboBoxIntervallo);
		
		lblEvento = new JLabel("Evento");
		lblEvento.setForeground(new Color(57, 113, 177));
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEvento.setBounds(327, 221, 72, 14);
		panel.add(lblEvento);
		
		comboBoxEvento = new JComboBox<String>();
		comboBoxEvento.setForeground(Color.WHITE);
		comboBoxEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxEvento.setFocusable(false);
		comboBoxEvento.setBorder(null);
		comboBoxEvento.setBackground(new Color(32, 33, 35));
		comboBoxEvento.addItem("Cena");
		comboBoxEvento.addItem("Gita");
		comboBoxEvento.setBounds(409, 217, 154, 21);
		panel.add(comboBoxEvento);
		
		aggiungiIntervalloButton = new JButton("aggiungi intevallo");	
		aggiungiIntervalloButton.setBounds(39, 549, 143, 50);
		aggiungiIntervalloButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiIntervalloButton.setForeground(Color.WHITE);
		aggiungiIntervalloButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiIntervalloButton.setFocusPainted(false);
		aggiungiIntervalloButton.setBorder(null);
		aggiungiIntervalloButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiIntervalloButton);
		
		aggiungiEventoButton = new JButton("aggiungi \r\nevento");	
		aggiungiEventoButton.setBounds(421, 549, 142, 50);
		aggiungiEventoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiEventoButton.setForeground(Color.WHITE);
		aggiungiEventoButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiEventoButton.setFocusPainted(false);
		aggiungiEventoButton.setBorder(null);
		aggiungiEventoButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiEventoButton);
		
		editorPaneDescrizione = new JEditorPane();	
		editorPaneDescrizione.setSelectionColor(new Color(126, 87, 194));
		editorPaneDescrizione.setForeground(Color.WHITE);
		editorPaneDescrizione.setDisabledTextColor(Color.WHITE);
		editorPaneDescrizione.setCaretColor(Color.WHITE);
		editorPaneDescrizione.setBorder(null);
		editorPaneDescrizione.setBackground(new Color(32, 33, 35));
		scrollPane.setViewportView(editorPaneDescrizione);		
		
		comboBoxKS = new JComboBox<String>();
		comboBoxKS.addItem("");		
		comboBoxKS.setForeground(Color.WHITE);
		comboBoxKS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxKS.setFocusable(false);
		comboBoxKS.setBorder(null);
		comboBoxKS.setBackground(new Color(32, 33, 35));
		comboBoxKS.setBounds(150, 218, 154, 21);
		panel.add(comboBoxKS);
		RiempiComboBoxKS(comboBoxKS);
		
		comboBoxChair = new JComboBox<String>();	
		comboBoxChair.setForeground(Color.WHITE);
		comboBoxChair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxChair.setFocusable(false);
		comboBoxChair.setBorder(null);
		comboBoxChair.setBackground(new Color(32, 33, 35));
		comboBoxChair.setBounds(150, 253, 154, 21);
		panel.add(comboBoxChair);
		RiempiComboBoxChair(listaOrganizzatoriScientifici, comboBoxChair);
		
		textFieldTitolo = new JFormattedTextField((Format) null);
		textFieldTitolo.setDisabledTextColor(new Color(255, 255, 255));
		textFieldTitolo.setForeground(Color.WHITE);
		textFieldTitolo.setBorder(null);
		textFieldTitolo.setBackground(new Color(32, 33, 35));
		textFieldTitolo.setBounds(150, 49, 154, 20);
		panel.add(textFieldTitolo);
		
		titoloLabel = new JLabel("Titolo*");
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBackground(new Color(57, 113, 177));
		titoloLabel.setBounds(74, 49, 48, 14);
		panel.add(titoloLabel);
		
		separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(150, 71, 154, 8);
		panel.add(separator_1_1_1);
			
		aggiungiSessioneButton = new JButton("aggiungi sessione");
		aggiungiSessioneButton.setBounds(232, 549, 142, 50);
		aggiungiSessioneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiSessioneButton.setForeground(Color.WHITE);
		aggiungiSessioneButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiSessioneButton.setFocusPainted(false);
		aggiungiSessioneButton.setBorder(null);
		aggiungiSessioneButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiSessioneButton);
		
		lblListaDeiProgrammi = new JLabel("Lista dei programmi aggiunti alla conferenza");
		lblListaDeiProgrammi.setForeground(new Color(70, 71, 74));
		lblListaDeiProgrammi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblListaDeiProgrammi.setBounds(29, 754, 497, 14);
		panel.add(lblListaDeiProgrammi);
		
		lblFormatoDataYyyymmdd = new JLabel("yyyy-MM-dd");
		lblFormatoDataYyyymmdd.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDataYyyymmdd.setBounds(150, 74, 174, 14);
		panel.add(lblFormatoDataYyyymmdd);
		
		lblFormatoDataYyyymmdd_1_1 = new JLabel("(HH:mm)");
		lblFormatoDataYyyymmdd_1_1.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDataYyyymmdd_1_1.setBounds(25, 158, 174, 14);
		panel.add(lblFormatoDataYyyymmdd_1_1);
		
		lblFormatoDataYyyymmdd_1_1_1 = new JLabel("(HH:mm)");
		lblFormatoDataYyyymmdd_1_1_1.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDataYyyymmdd_1_1_1.setBounds(25, 123, 174, 14);
		panel.add(lblFormatoDataYyyymmdd_1_1_1);
		
		keynoteSpekaerLabel = new JLabel("Keynote*");
		keynoteSpekaerLabel.setBounds(74, 221, 66, 14);
		keynoteSpekaerLabel.setForeground(new Color(57, 113, 177));
		keynoteSpekaerLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(keynoteSpekaerLabel);
		
		separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(150, 145, 154, 2);
		panel.add(separator_1_2_1);
		
		lblChair = new JLabel("Chair*");
		lblChair.setBounds(74, 253, 60, 14);
		lblChair.setForeground(new Color(57, 113, 177));
		lblChair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblChair);
		
		creaConferenzaButton = new JButton("CREA CONFERENZA");	
		creaConferenzaButton.setForeground(Color.WHITE);
		creaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		creaConferenzaButton.setFocusPainted(false);
		creaConferenzaButton.setBorder(null);
		creaConferenzaButton.setBackground(new Color(57, 113, 177));
		creaConferenzaButton.setBounds(398, 805, 165, 36);
		panel.add(creaConferenzaButton);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(new Color(130, 135, 144), 0));
		scrollPane_2.setBounds(29, 627, 208, 124);
		panel.add(scrollPane_2);
		
		tableProgrammiAggiunti = new JTable();
		tableProgrammiAggiunti.setForeground(Color.WHITE);
		tableProgrammiAggiunti.getTableHeader().setReorderingAllowed(false); 
		tableProgrammiAggiunti.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Data"
				}
			){
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});				
		tableProgrammiAggiunti.setSelectionBackground(new Color(126, 87, 194));
		tableProgrammiAggiunti.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableProgrammiAggiunti.setBackground(new Color(32, 33, 35));
		scrollPane_2.setViewportView(tableProgrammiAggiunti);
		
		btnAggiungiProgramma = new JButton("aggiungi programma");	
		btnAggiungiProgramma.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungiProgramma.setForeground(Color.WHITE);
		btnAggiungiProgramma.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnAggiungiProgramma.setFocusPainted(false);
		btnAggiungiProgramma.setBorder(null);
		btnAggiungiProgramma.setBackground(new Color(126, 87, 194));
		btnAggiungiProgramma.setBounds(49, 779, 165, 36);
		panel.add(btnAggiungiProgramma);
		
		
		
		
		
		
		//PULSANTI & LISTNERS
			
		//pulsante di uscita
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TornaAlFramePrecedente(frame, frameHome, frameCreazioneConferenza);		
			}
		});
		
		
		//rimuovo righe di Programmi
		tableProgrammiAggiunti.addMouseListener(new MouseAdapter() {
			@Override
	         public void mouseClicked(MouseEvent me) { 
	        	 RimuoviRigaProgramma(me);           
	         }
	      });
		
		//riempi l'arrayList e la tabella di programmi aggiunti
		btnAggiungiProgramma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isDataInserita())	
					AggiungiProgramma(conferenzaCreata, tableSessioniAggiunte.getRowCount(), tableProgrammiAggiunti.getRowCount());	
			}
		});
		
		//effettua la creazione della nuova conferenza passando i valori al DB
		creaConferenzaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CommitCreazione(conferenzaCreata, frameCreazioneConferenza, frameHome, listaProgrammi, listaPubblicità);							
			}
		});
				
		aggiungiIntervalloButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				CreaIntervallo(conferenzaCreata, comboBoxIntervallo.getSelectedItem().toString());
			}
		});
		
		
		aggiungiEventoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreaEvento(conferenzaCreata, comboBoxEvento.getSelectedItem().toString());
			}
		});
		
		
		aggiungiSessioneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreaSessione(conferenzaCreata, textFieldTitolo.getText(), editorPaneDescrizione.getText());	
			}
		});
		
		
		tableSessioniAggiunte.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 RimuoviRigaSessione(me);
	         }
	    });
		
}
	
	//IMPLEMENTAZIONE DEI METODI CHIAMATI DAL FRAME
	private Boolean isDataInserita() {
		if(formattedTextFieldData.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Devi inserire una data!","ERROR:413", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	
	private void CreaSessione( Conferenza updateConferenza, String titolo, String descrizione) {
		if(isDataInserita())
		{
			CastaDataeOrario();
			if(isNuovaDataIdoneaAllaConferenza(updateConferenza) && isOrarioSessioneIdoneo(comboBoxLocazione.getSelectedItem().toString()))
			{
				if(isParametriCorretti(updateConferenza) && isTitoloInserito(titolo))
					IstanziaSessione(titolo, descrizione);
			}
		}	
	}

	
	private void IstanziaSessione(String titolo, String descrizione) {
		try{
			AggiungiSessioneAllaTable(titolo, descrizione);
			IstanziaSessioneEdAggiungiAdArray(titolo, descrizione);
		}
		catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Non sono presenti altri chair!","ERROR:407", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void IstanziaSessioneEdAggiungiAdArray(String titolo, String descrizione) {
		Sessione sessioneNuova = controller.creaSessionedaFrame(titolo, timeInizio, timeFine, comboBoxLocazione.getSelectedItem().toString(), 
				comboBoxKS.getSelectedItem().toString(), comboBoxChair.getSelectedItem().toString(), descrizione);
		listaSedute.add(sessioneNuova);
	}

	
	private void AggiungiSessioneAllaTable(String titolo, String descrizione) {
		DefaultTableModel model = (DefaultTableModel)tableSessioniAggiunte.getModel();
		model.addRow(new Object[] {titolo, orarioInizio.getText(), orarioFine.getText(), comboBoxLocazione.getSelectedItem().toString(), 
						comboBoxKS.getSelectedItem().toString(), comboBoxChair.getSelectedItem().toString(), descrizione});
	}
	

	private boolean isTitoloInserito(String titolo) {
		if(titolo.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Devi insrire un titolo alla sessione!","ERROR:420", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	private void CreaEvento(Conferenza conferenzaCreata, String ComboBoxEventoValue) {
		if(isDataInserita())
		{
			CastaDataeOrario();
			if(isNuovaDataIdoneaAllaConferenza(conferenzaCreata) && isOrarioIntervalloOrEventoIdoneo())
			{
				if(isParametriCorretti(conferenzaCreata))
					IstanziaEvento(ComboBoxEventoValue);	
			}
		}	
	}
	

	private void IstanziaEvento(String comboBoxEventoValue) {
		try{
			AggiungiEventoAllaTable(comboBoxEventoValue);
			IstanziaEventoAggiungiAdArraySedute(comboBoxEventoValue);				
		} catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Errore!","ERROR:407", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void IstanziaEventoAggiungiAdArraySedute(String comboBoxEventoValue) {
		Evento_Sociale eventoNuovo = new Evento_Sociale();
		eventoNuovo.setOrarioInizio(timeInizio);
		eventoNuovo.setOrarioFine(timeFine);
		eventoNuovo.setTitolo(comboBoxEventoValue);
		listaSedute.add(eventoNuovo);
	}

	
	private void AggiungiEventoAllaTable(String comboBoxEventoValue) {
		DefaultTableModel model = (DefaultTableModel)tableSessioniAggiunte.getModel();
		model.addRow(new Object[] {comboBoxEventoValue, orarioInizio.getText(), orarioFine.getText(), null, null, null, null});
	}

	
	protected void CreaIntervallo(Conferenza conferenzaCreata, String comboBoxIntervallo) {
		if(isDataInserita())
		{
			CastaDataeOrario();
			if(isNuovaDataIdoneaAllaConferenza(conferenzaCreata) && isOrarioIntervalloOrEventoIdoneo())
			{
				if(isParametriCorretti(conferenzaCreata))			
					IstanziaIntervallo(comboBoxIntervallo);				
			}
		}
	
	}

	
	private void IstanziaIntervallo(String comboBoxIntervallo) {
		try{
			AggiungiIntervalloAllaTable(comboBoxIntervallo);		
			IstanziaIntervalloAggiungiAdArraySedute(comboBoxIntervallo);		
		}
		catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Errore!","ERROR:407", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void IstanziaIntervalloAggiungiAdArraySedute(String comboBoxIntervallo) {
		Intervallo intervalloNuovo = new Intervallo();
		intervalloNuovo.setOrarioInizio(timeInizio);
		intervalloNuovo.setOrarioFine(timeFine);
		intervalloNuovo.setTitolo(comboBoxIntervallo);
		listaSedute.add(intervalloNuovo);
	}

	
	private void AggiungiIntervalloAllaTable(String comboBoxIntervallo) {
		DefaultTableModel model = (DefaultTableModel)tableSessioniAggiunte.getModel();
		model.addRow(new Object[] {comboBoxIntervallo, orarioInizio.getText(), orarioFine.getText(), null, null, null, null});
	}
	

	private boolean isParametriCorretti(Conferenza conferenzaCreata) {
		if(isOrarioDataNOTEmpty(formattedTextFieldData.getText()) && isDataProgrammaInConferenza(conferenzaCreata) && isOrarioConforme())
			return true;
		return false;
	}

	
	private boolean isOrarioConforme() {
		if(timeFine.before(timeInizio) || timeFine.equals(timeInizio))
		{
			JOptionPane.showMessageDialog(null,"Gli orari non sono conformi!","ERROR:412", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		return true;
	}

	
	private boolean isOrarioDataNOTEmpty(String dataInserita) {
		if(orarioFine.getText().isEmpty() || orarioInizio.getText().isEmpty() || dataInserita.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Devi compilare la data e gli orari!","ERROR:412", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	

	private boolean isDataProgrammaInConferenza(Conferenza conferenzaCreata) {
		CastaStringToData();
		if(dataProgramma.before(conferenzaCreata.getDataInizio()) || dataProgramma.after(conferenzaCreata.getDataFine()))
		{
			JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza!","ERROR:413", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		return true;
	}
	

	private boolean isNuovaDataIdoneaAllaConferenza(Conferenza conferenzaCreata) {
		for(Programma p: conferenzaCreata.programmiConferenza)
		{	
			CastaStringToData();
			if(dataProgramma.equals(p.getDataProgramma()))
			{
				JOptionPane.showMessageDialog(null,"Questa data è già occupata!","ERROR:413", JOptionPane.ERROR_MESSAGE);
				return false;
			}					
		}
		return true;
	}
	

	private boolean isOrarioIntervalloOrEventoIdoneo() {
		if(tableSessioniAggiunte.getRowCount() != 0)
		{
			for(Seduta s: listaSedute)
			{
				if(isNotIdoneaSeduta(s))
					return false;						
			}
		}
		return true;
	}
	

	private boolean isNotIdoneaSeduta(Seduta s) {
		if( ( timeInizio.after(s.getOrarioInizio()) && timeInizio.before(s.getOrarioFine()) )
				|| ( timeFine.after(s.getOrarioInizio()) && timeFine.before(s.getOrarioFine()) ) 
				|| ( timeInizio.before(s.getOrarioInizio()) && timeFine.after(s.getOrarioFine()) ) 
				|| ( timeInizio.equals(s.getOrarioInizio()) || timeFine.equals(s.getOrarioFine()) ))
			{
				JOptionPane.showMessageDialog(null,"In questo orario è in corso un'altra sessione!","ERROR:413", JOptionPane.ERROR_MESSAGE);
				return true;
			}
		return false;
	}
	

	private void CastaDataeOrario() {
		dataProgramma = CastaStringToData();		
		CastaStringToTime();
	}
	

	private void CastaStringToTime() {
		try {
			timeFine = tipoTempo.parse(orarioFine.getText());	
			timeInizio = tipoTempo.parse(orarioInizio.getText());
		}catch (ParseException e1){
			System.out.println("Orario non conforme!");
		}
	}

	
	private Date CastaStringToData() {
		try {
			dataProgramma = format.parse(formattedTextFieldData.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return dataProgramma;
	}

	
	protected void CastaOrariProgramma(String orarioIniziale, String orarioFinale) {
		//casto l'orario iniziale e finale a DataTime per poterli confrontare			
		try {
			timeFine = tipoTempo.parse(orarioFinale);	
			timeInizio = tipoTempo.parse(orarioIniziale);
		} catch (ParseException e1) {
			System.out.println("Orario non conforme!");
		}	
	}

	
	protected void CastaDataProgramma(String data) {
		//casto la data inserita al tipo Date di java
		try {
			dataProgramma = format.parse(data);
		} catch (ParseException e1) {
			System.out.println("Data non conforme alla conferenza!");
		}
	}

	
	private void RiempiComboBoxKS(JComboBox<String> comboBoxKS) {
		//riempio la ComboBox chiedendo al DB quali sono le locazioni della sede passata
		for(String s: controller.ottieniAllKS()){
			comboBoxKS.addItem(s);
		}
	}
	

	private void RiempiComboBoxChair(ArrayList<Organizzatore_Scientifico> listaOrganizzatoriScientifici,JComboBox<String> comboBoxChair) {
		//riempio la ComboBox con i possibili chair
				for(Organizzatore_Scientifico chair: listaOrganizzatoriScientifici){
						comboBoxChair.addItem(chair.getEmail().toString());
				}
	}

	
	private void RiempiComboBoxLocazione(Conferenza conferenzaCreata, JComboBox<String> comboBoxKS) {
		//riempio la ComboBox chiedendo al DB quali i possibili KS
		for(String ks: controller.ottieniLocazioni(conferenzaCreata.sedeOspitante)){
			comboBoxLocazione.addItem(ks);
		}
	}

	
	private void AggiungiProgramma(Conferenza conferenzaCreata,int numeroRigheSessioni, int numeroRigheProgrammi) {
		
		if(isNuovoProgrammaIdoneo(conferenzaCreata, numeroRigheProgrammi, numeroRigheSessioni) && isParametriCorretti(conferenzaCreata))
		{
			IstanziaSeduteSpecializzate();
			IstanziaNuovoProgramma(listaProgrammi, programmaNuovo, listaSessioni, listaEventi, listaIntervalli);	
			RipulisciLeArrayList();		
			RipulisciTabellaSessioni();
		}				
	}

	
	private boolean isNuovoProgrammaIdoneo(Conferenza conferenzaCreata, int numeroRigheProgrammi, int numeroRigheSessioni) {

		CastaStringToData();
		//se tutti e tre i vincoli sono soddisfatti allora posso eseguire la creazione di nuove conferenze
		return (isDataIdonea(conferenzaCreata, formattedTextFieldData.getText())) && (isDataAppenaAggiuntaIdonea(numeroRigheProgrammi, listaProgrammi)) && isProgrammaConSedute(numeroRigheSessioni);
	}

	
	private void RipulisciTabellaSessioni() {
		//ripulisco la tabella
		DefaultTableModel dtm = (DefaultTableModel) tableSessioniAggiunte.getModel();
		dtm.setRowCount(0);
	}
	

	private void RipulisciLeArrayList() {
		//dopo aver aggiunto il programma rimuovo le ArrayList occupate
		listaSessioni.removeAll(listaSessioni);
		listaEventi.removeAll(listaEventi);
		listaIntervalli.removeAll(listaIntervalli);		
		listaSedute.removeAll(listaSedute);
	}


	private void AggiungiProgrammaAllaTable(Programma programmaNuovo) {
		model = (DefaultTableModel)tableProgrammiAggiunti.getModel();
		model.addRow(new Object[] {format.format(programmaNuovo.getDataProgramma())});
	}
	
	

	//IMPEDISCE DI CREARE CONFERENZE SENZA SESSIONI
	private boolean isProgrammaConSedute(int rowCount) {
		if(rowCount < 1)
		{
			JOptionPane.showMessageDialog(null,"Devi aggiungere delle sedute!","ERROR:412", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		return true;
	}

	
	//IMPEDISCO DI CREARE PROGRAMMI CON LA STESSA DATA
	private boolean isDataAppenaAggiuntaIdonea(int rowCount, ArrayList<Programma> listaProgrammi) {
		if(rowCount != 0)
		{
			for(Programma p: listaProgrammi)
			{
				CastaStringToData();
				if(p.getDataProgramma().equals(dataProgramma))
				{
					JOptionPane.showMessageDialog(null,"Data non disponibile!","ERROR:412", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		return true;
	}
	

	//CHIEDE AL DB DI CREARE LA CONFERENZA E POI TORNA ALLA HOME
	private void CommitCreazione(Conferenza conferenzaCreata, JFrame frameCreazioneConferenza, JFrame frameHome, ArrayList<Programma> listaProgrammi, ArrayList<Pubblicità> listaPubblicità) {
		frameCreazioneConferenza.dispose();
		controller.commitCreazioneConferenza(conferenzaCreata, listaProgrammi, listaPubblicità);
		controller.tornaAllaHome(frame, frameHome);	
	}

	
	//ISTANZIA SESSIONI, EVENTI E INTERVALLI
	private void IstanziaSeduteSpecializzate() {
		//istanzia le varie sedute in base alla loro specializzazione	
		for(Seduta s: listaSedute)
		{
			if(s instanceof Sessione)
				listaSessioni.add((Sessione)s);

			else if(s instanceof Evento_Sociale)
				listaEventi.add((Evento_Sociale)s);				

			else
				listaIntervalli.add((Intervallo)s);
		}
	}

	
	//CONTROLLA SE LA DATA PROGRAMMA E' CONFORME ALLA DATA CONFERENZA
	private boolean isDataIdonea(Conferenza conferenzaCreata, String stringaConferenza) {
		//casto la data inserita al tipo Date di java
		DateFromStringa(dataProgramma, stringaConferenza);
		//nel caso in cui la data del programma non rientra nella data della conferenza
		if(dataProgramma.before(conferenzaCreata.getDataInizio()) || dataProgramma.after(conferenzaCreata.getDataFine()))
		{
			JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza creata!","ERROR:413", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	
	//CONVERTE UNA STRINGA IN DATA
	private void DateFromStringa(Date dataProgramma, String dataStringa) {
		try {
			dataProgramma = format.parse(dataStringa);
		} catch (ParseException e1) {
			System.out.println("Data non conforme alla conferenza!");
		}
	}

	
	//RIMUOVO UNA SESSIONE SE L'HO AGGIUNTA PER SBAGLIO
	private void RimuoviRigaSessione(MouseEvent me) {
		if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
	           JTable target = (JTable)me.getSource();
	           int row = target.getSelectedRow(); // seleziona riga
	           ((DefaultTableModel)tableSessioniAggiunte.getModel()).removeRow(row); //elimino il la riga con doppio click
	           listaSedute.remove(row);
	        }
	}
	
		
	//RIMUOVO UN PROGRAMMA SE L'HO AGGIUNTO PER SBAGLIO
	private void RimuoviRigaProgramma(MouseEvent me) {
		if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
            JTable target = (JTable)me.getSource();
            int row = target.getSelectedRow(); // seleziona riga
            ((DefaultTableModel)tableProgrammiAggiunti.getModel()).removeRow(row); //elimino il la riga con doppio click      
            listaProgrammi.remove(row);	                
         }
	}
	
	
	private boolean isOrarioSessioneIdoneo(String ComboBoxLocazioneValue) {
		//controllo se non ci sono altre sessioni in corso
		if(tableSessioniAggiunte.getRowCount() != 0)
		{
			for(Seduta s: listaSedute)
			{			
				if(!(s instanceof Sessione))
				{
					if(isNotIdoneaSeduta(s))
						return false;
				}											
				else
				{
					if(isNotIdoneaSessione(s, ComboBoxLocazioneValue))
						return false;	
				}					
			}
		}
		return true;
	}

	
	private boolean isNotIdoneaSessione(Seduta s, String comboBoxLocazioneValue) {
		if( (s.getLocazione().getNomeLocazione().compareTo(comboBoxLocazioneValue)) == 0 )
		{
			if( ( timeInizio.after(s.getOrarioInizio()) && timeInizio.before(s.getOrarioFine()) )
					|| ( timeFine.after(s.getOrarioInizio()) && timeFine.before(s.getOrarioFine()) ) 
					|| ( timeInizio.before(s.getOrarioInizio()) && timeFine.after(s.getOrarioFine()) ) 
					|| ( timeInizio.equals(s.getOrarioInizio()) || timeFine.equals(s.getOrarioFine()) ))
				{
					JOptionPane.showMessageDialog(null,"In questa locazione è in corso un'altra sessione!","ERROR:413", JOptionPane.ERROR_MESSAGE);
					return true;						
				}
		}
		return false;
	}

	
	private void IstanziaNuovoProgramma(ArrayList<Programma> listaProgrammi, Programma programmaNuovo,
			ArrayList<Sessione> listaSessioni, ArrayList<Evento_Sociale> listaEventi,
			ArrayList<Intervallo> listaIntervalli) {

		programmaNuovo = new Programma();
		programmaNuovo.setDataProgramma(dataProgramma);
		programmaNuovo.sessioniProgrammate = new ArrayList<Sessione>(listaSessioni);
		programmaNuovo.eventiProgrammati = new ArrayList<Evento_Sociale>(listaEventi);
		programmaNuovo.intervalliProgrammati = new ArrayList<Intervallo>(listaIntervalli);
		listaProgrammi.add(programmaNuovo);
		AggiungiProgrammaAllaTable(programmaNuovo);	
	}
	
	
	//TORNO AL FRAME PRECEDENTE(chiudo questo)
	private void TornaAlFramePrecedente(JFrame thisframe, JFrame frameHome, JFrame frameCreazioneConferenza) {
		thisframe.dispose();
		frameHome.setVisible(true);
		frameCreazioneConferenza.setVisible(true);		
		frameCreazioneConferenza.setEnabled(true);
	}
}