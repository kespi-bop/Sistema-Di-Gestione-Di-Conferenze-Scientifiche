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
import Model.Programma;
import Model.Seduta;
import Model.Sessione;

import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
import java.text.Format;

public class AggiungiProgrammiEdit {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField orarioFine;
	private JTable table;
	private JTextField orarioInizio;
	private ArrayList<Seduta> listaSedute = new ArrayList<Seduta>();
	private ArrayList<Sessione> listaSessioni = new ArrayList<Sessione>();
	private ArrayList<Evento_Sociale> listaEventi = new ArrayList<Evento_Sociale>();
	private ArrayList<Intervallo> listaIntervalli = new ArrayList<Intervallo>();
	private SimpleDateFormat tipoTempo = new SimpleDateFormat("HH:mm");
	private Date timeFine = new Date();
	private Date timeInizio = new Date();
	private Date dataProgramma = new Date();
	private JFormattedTextField dataTextField;
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public AggiungiProgrammiEdit(Controller controller, JFrame frameAzioniDiModifica, Conferenza updateConferenza, Date dataInizio, Date dataFine) {
		initialize(controller, frameAzioniDiModifica, updateConferenza, dataInizio, dataFine);
	}

	
	private void initialize(Controller controller, JFrame frameAzioniDiModifica, Conferenza updateConferenza, Date dataInizio, Date dataFine) {
		
		//SWING COMPONENTS
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 600, 880);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 600, 880);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
				
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(573, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.TornaAllaPaginaPrecedente(frame,frameAzioniDiModifica);		
			}
		});		
		exitLabel.setIcon(new ImageIcon(imgExit));
		panel.add(exitLabel);
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 563, 32);
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
		panel.add(dataProgrammaLabel);
		
		JLabel orarioInizioLabel = new JLabel("Orario inizio");
		orarioInizioLabel.setBounds(74, 121, 66, 14);
		orarioInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		orarioInizioLabel.setForeground(new Color(57, 113, 177));
		panel.add(orarioInizioLabel);
		
		JLabel OrarioFineLabel = new JLabel("Orario fine");
		OrarioFineLabel.setBounds(74, 158, 66, 14);
		OrarioFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		OrarioFineLabel.setForeground(new Color(57, 113, 177));
		panel.add(OrarioFineLabel);
		
		JLabel locazioneLabel = new JLabel("Locazione*");
		locazioneLabel.setBounds(74, 189, 60, 14);
		locazioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		locazioneLabel.setForeground(new Color(57, 113, 177));
		panel.add(locazioneLabel);
		
		JLabel descrizioneLabel = new JLabel("Descrizione*");
		descrizioneLabel.setBounds(74, 294, 71, 14);
		descrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizioneLabel.setForeground(new Color(57, 113, 177));
		panel.add(descrizioneLabel);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		dataTextField = new JFormattedTextField(format);
		dataTextField.setForeground(new Color(255, 255, 255));
		dataTextField.setBackground(new Color(32, 33, 35));
		dataTextField.setBorder(null);
		dataTextField.setBounds(150, 80, 154, 20);
		panel.add(dataTextField);
		
		orarioFine = new JTextField();
		orarioFine.setBounds(150, 155, 154, 20);
		orarioFine.setCaretColor(new Color(255, 255, 255));
		orarioFine.setDisabledTextColor(new Color(255, 255, 255));
		orarioFine.setSelectionColor(new Color(126, 87, 194));
		orarioFine.setForeground(new Color(255, 255, 255));
		orarioFine.setBorder(null);
		orarioFine.setBackground(new Color(32, 33, 35));
		orarioFine.setColumns(10);
		panel.add(orarioFine);
		
		orarioInizio = new JTextField();
		orarioInizio.setBounds(150, 120, 154, 20);
		orarioInizio.setSelectionColor(new Color(126, 87, 194));
		orarioInizio.setForeground(Color.WHITE);
		orarioInizio.setDisabledTextColor(Color.WHITE);
		orarioInizio.setColumns(10);
		orarioInizio.setCaretColor(Color.WHITE);
		orarioInizio.setBorder(null);
		orarioInizio.setBackground(new Color(32, 33, 35));
		panel.add(orarioInizio);
		
		JLabel as = new JLabel("Attenzione! Per aggiungere una sessione, riempire i campi contrassegnati da *");
		as.setBounds(29, 524, 497, 14);
		as.setFont(new Font("Tahoma", Font.PLAIN, 11));
		as.setForeground(new Color(70, 71, 74));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(32, 33, 35));
		scrollPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setBounds(29, 389, 544, 131);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
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
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 RimuoviRigaSedute(me);
	         }
	      });	
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(new Color(0, 0, 0));
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setForeground(new Color(255,255,255));
		table.setBackground(new Color(32, 33, 35));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.getTableHeader().setReorderingAllowed(false); 
		scrollPane_1.setViewportView(table);
		panel.add(as);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(425, 847, 165, 33);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		panel.add(signature);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(150, 101, 154, 8);
		panel.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(150, 178, 154, 8);
		panel.add(separator_1_2);
			
		JComboBox<String> comboBoxLocazione = new JComboBox<String>();	
		RiempiComboBoxLocazione(controller, comboBoxLocazione, updateConferenza);
		
		comboBoxLocazione.setForeground(Color.WHITE);
		comboBoxLocazione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxLocazione.setFocusable(false);
		comboBoxLocazione.setBorder(null);
		comboBoxLocazione.setBackground(new Color(32, 33, 35));
		comboBoxLocazione.setBounds(150, 185, 154, 21);
		panel.add(comboBoxLocazione);
		

		JLabel lblTipointervallo = new JLabel("Intervallo");
		lblTipointervallo.setForeground(new Color(57, 113, 177));
		lblTipointervallo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipointervallo.setBounds(327, 189, 72, 14);
		panel.add(lblTipointervallo);
		
		JComboBox<String> comboBoxIntervallo = new JComboBox<String>();
		comboBoxIntervallo.setForeground(Color.WHITE);
		comboBoxIntervallo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxIntervallo.setFocusable(false);
		comboBoxIntervallo.setBorder(null);
		comboBoxIntervallo.setBackground(new Color(32, 33, 35));
		comboBoxIntervallo.setBounds(409, 185, 154, 21);
		comboBoxIntervallo.addItem("CoffeBreak");
		comboBoxIntervallo.addItem("Pranzo");
		panel.add(comboBoxIntervallo);
		
		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setForeground(new Color(57, 113, 177));
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEvento.setBounds(327, 221, 72, 14);
		panel.add(lblEvento);
		
		JComboBox<String> comboBoxEvento = new JComboBox<String>();
		comboBoxEvento.setForeground(Color.WHITE);
		comboBoxEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxEvento.setFocusable(false);
		comboBoxEvento.setBorder(null);
		comboBoxEvento.setBackground(new Color(32, 33, 35));
		comboBoxEvento.addItem("Cena");
		comboBoxEvento.addItem("Gita");
		comboBoxEvento.setBounds(409, 217, 154, 21);
		panel.add(comboBoxEvento);
		
		JButton aggiungiIntervalloButton = new JButton("aggiungi intevallo");
		aggiungiIntervalloButton.setBounds(39, 549, 143, 50);
		aggiungiIntervalloButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiIntervalloButton.setForeground(Color.WHITE);
		aggiungiIntervalloButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiIntervalloButton.setFocusPainted(false);
		aggiungiIntervalloButton.setBorder(null);
		aggiungiIntervalloButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiIntervalloButton);
		
		JButton aggiungiEventoButton = new JButton("aggiungi \r\nevento");
		aggiungiEventoButton.setBounds(421, 549, 142, 50);
		aggiungiEventoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiEventoButton.setForeground(Color.WHITE);
		aggiungiEventoButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiEventoButton.setFocusPainted(false);
		aggiungiEventoButton.setBorder(null);
		aggiungiEventoButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiEventoButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 285, 356, 88);
		panel.add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		editorPane.setSelectionColor(new Color(126, 87, 194));
		editorPane.setForeground(Color.WHITE);
		editorPane.setDisabledTextColor(Color.WHITE);
		editorPane.setCaretColor(Color.WHITE);
		editorPane.setBorder(null);
		editorPane.setBackground(new Color(32, 33, 35));
		
		JComboBox<String> comboBoxKS = new JComboBox<String>();
		RiempiComboBoxKS(controller, comboBoxKS, updateConferenza);
		
		comboBoxKS.setForeground(Color.WHITE);
		comboBoxKS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxKS.setFocusable(false);
		comboBoxKS.setBorder(null);
		comboBoxKS.setBackground(new Color(32, 33, 35));
		comboBoxKS.setBounds(150, 218, 154, 21);
		panel.add(comboBoxKS);
		
		JComboBox<String> comboBoxChair = new JComboBox<String>();
		RiempiComboBoxChair(controller, comboBoxChair, updateConferenza);
		
		comboBoxChair.setForeground(Color.WHITE);
		comboBoxChair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxChair.setFocusable(false);
		comboBoxChair.setBorder(null);
		comboBoxChair.setBackground(new Color(32, 33, 35));
		comboBoxChair.setBounds(150, 253, 154, 21);
		panel.add(comboBoxChair);
		
		JFormattedTextField textFieldTitolo = new JFormattedTextField((Format) null);
		textFieldTitolo.setForeground(Color.WHITE);
		textFieldTitolo.setBorder(null);
		textFieldTitolo.setBackground(new Color(32, 33, 35));
		textFieldTitolo.setBounds(150, 27, 154, 20);
		panel.add(textFieldTitolo);
		
		JLabel titoloLabel = new JLabel("Titolo*");
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBackground(new Color(57, 113, 177));
		titoloLabel.setBounds(74, 30, 48, 14);
		panel.add(titoloLabel);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(150, 48, 154, 8);
		panel.add(separator_1_1_1);
		
		
		JButton aggiungiSessioneButton = new JButton("aggiungi sessione");
		aggiungiSessioneButton.setBounds(232, 549, 142, 50);
		aggiungiSessioneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiSessioneButton.setForeground(Color.WHITE);
		aggiungiSessioneButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiSessioneButton.setFocusPainted(false);
		aggiungiSessioneButton.setBorder(null);
		aggiungiSessioneButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiSessioneButton);
		
		JLabel keynoteSpekaerLabel = new JLabel("Keynote*");
		keynoteSpekaerLabel.setBounds(74, 221, 66, 14);
		keynoteSpekaerLabel.setForeground(new Color(57, 113, 177));
		keynoteSpekaerLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(keynoteSpekaerLabel);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(150, 141, 154, 2);
		panel.add(separator_1_2_1);
			
		JLabel lblChair = new JLabel("Chair*");
		lblChair.setBounds(74, 253, 60, 14);
		lblChair.setForeground(new Color(57, 113, 177));
		lblChair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblChair);
		
		JButton AggiungiProgrammaButton = new JButton("AGGIUNGI PROGRAMMA");
		AggiungiProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AggiungiProgrammaButton.setForeground(Color.WHITE);
		AggiungiProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		AggiungiProgrammaButton.setFocusPainted(false);
		AggiungiProgrammaButton.setBorder(null);
		AggiungiProgrammaButton.setBackground(new Color(57, 113, 177));
		AggiungiProgrammaButton.setBounds(398, 675, 165, 36);
		panel.add(AggiungiProgrammaButton);
		
		JLabel lblFormatoDataYyyymmdd = new JLabel("yyyy-MM-dd");
		lblFormatoDataYyyymmdd.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDataYyyymmdd.setBounds(150, 56, 174, 14);
		panel.add(lblFormatoDataYyyymmdd);
		
		JLabel lblFormatoDataYyyymmdd_1_1 = new JLabel("(HH:mm)");
		lblFormatoDataYyyymmdd_1_1.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDataYyyymmdd_1_1.setBounds(25, 158, 174, 14);
		panel.add(lblFormatoDataYyyymmdd_1_1);

		JLabel lblFormatoDataYyyymmdd_1_1_1 = new JLabel("(HH:mm)");
		lblFormatoDataYyyymmdd_1_1_1.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDataYyyymmdd_1_1_1.setBounds(25, 120, 174, 14);
		panel.add(lblFormatoDataYyyymmdd_1_1_1);	
		
		
		
		//PULSANTI & LISTNERS
		
		aggiungiIntervalloButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				CreaIntervallo(controller, updateConferenza, dataTextField.getText(), comboBoxIntervallo.getSelectedItem().toString());
			}
		});
		
		aggiungiEventoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreaEvento(controller, dataTextField.getText(), updateConferenza, comboBoxEvento.getSelectedItem().toString());
			}
		});
		
		aggiungiSessioneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				CreaSessione(controller, updateConferenza, dataTextField.getText(), comboBoxLocazione.getSelectedItem().toString(), comboBoxKS.getSelectedItem().toString(), 
									comboBoxChair.getSelectedItem().toString(), textFieldTitolo.getText(), editorPane.getText());
			}
		});
			
		AggiungiProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreaProgramma(controller, frameAzioniDiModifica, dataTextField.getText(), updateConferenza, dataInizio, dataFine);	
			}
		});
			
	}
	
	
	

	//METODI IMPLEMENTATIVI
	private void RimuoviRigaSedute(MouseEvent me) {
		if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
            JTable target = (JTable)me.getSource();
            int row = target.getSelectedRow(); // seleziona riga
            ((DefaultTableModel)table.getModel()).removeRow(row); //elimino il la riga con doppio click      
            listaSedute.remove(row);	                
         }
	}
	
	private void CreaProgramma(Controller controller, JFrame frameAzioniDiModifica, String dataInserita, Conferenza updateConferenza, Date dataInizio, Date dataFine) {
			
		if(isDataInserita())
		{
			CastaStringToData();
			if(isNuovaDataIdoneaAllaConferenza(updateConferenza) && isDataIdoneaAlProgramma(dataInizio, dataFine))
				IstanziaSeduteSpecializzate();	
			controller.commitAggiungiProgramma(dataInserita,updateConferenza, listaIntervalli, listaSessioni, listaEventi);
			controller.tornaAllaHome(frame, frameAzioniDiModifica);	
			JOptionPane.showMessageDialog(null,"Programma aggiunto con successo!","SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
		}				
	}

	private Boolean isDataIdoneaAlProgramma(Date dataInizio, Date dataFine) {
		CastaStringToData();
		if(dataProgramma.after(dataFine) || dataProgramma.before(dataInizio))
		{
			JOptionPane.showMessageDialog(null,"Data non Conforme!","ERROR:414", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private Boolean isDataInserita() {
		if(dataTextField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Devi inserire una data!","ERROR:413", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void CreaSessione(Controller controller, Conferenza updateConferenza, String dataInserita, String locazione, String keynoteS, String Chair, String titolo, String descrizione) {
		
		if(isDataInserita())		
		{
			CastaDataeOrario(controller, dataInserita);
			if(isNuovaDataIdoneaAllaConferenza(updateConferenza) && isOrarioSessioneIdoneo(locazione))
			{	
				if(isParametriCorretti(dataInserita, updateConferenza) && isTitoloInserito(titolo))
					IstanziaSessione(controller, locazione, keynoteS, Chair, titolo, descrizione);
			}
		}
	}

	private void IstanziaSessione(Controller controller, String Locazione, String KeynoteS, String Chair, String Titolo, String Descrizione) {
		try{
			AggiungiSessioneAllaTable(Titolo, Locazione, KeynoteS, Chair, Descrizione);
			IstanziaSessioneEdAggiungiAdArray(controller, Titolo, Locazione, KeynoteS, Chair, Descrizione);
		}
		catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Non sono presenti altri chair!","ERROR:407", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void IstanziaSessioneEdAggiungiAdArray(Controller controller, String titolo, String locazione,
								String keynoteS, String chair, String descrizione) {
		Sessione sessioneNuova = controller.creaSessionedaFrame(titolo, timeInizio, timeFine, locazione, keynoteS, chair, descrizione);
		listaSedute.add(sessioneNuova);
	}

	private void AggiungiSessioneAllaTable(String titolo, String locazione, String keynoteS, String chair, String descrizione) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(new Object[] {titolo, orarioInizio.getText(), orarioFine.getText(), locazione, 
									keynoteS, chair, descrizione});
	}

	private Boolean isTitoloInserito(String TitoloInserito) {
		
		if(TitoloInserito.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Devi inserire un titolo alla sessione!","ERROR:420", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private Boolean isOrarioSessioneIdoneo(String ComboBoxLocazioneValue) {
		//controllo se non ci sono altre sessioni in corso
		if(table.getRowCount() != 0)
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

	private Boolean isNotIdoneaSessione(Seduta s, String comboBoxLocazioneValue) {
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

	private Boolean isNotIdoneaSeduta(Seduta s) {
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

	private void CreaEvento(Controller controller, String dataInserita, Conferenza updateConferenza, String ComboBoxEventoValue) {	
		if(isDataInserita())
		{
			CastaDataeOrario(controller, dataInserita);
			if(isNuovaDataIdoneaAllaConferenza(updateConferenza) && isOrarioIntervalloOrEventoIdoneo())
			{
				if(isParametriCorretti(dataInserita, updateConferenza))
					IstanziaEvento(ComboBoxEventoValue);
			}
		}
			
	}

	private void IstanziaEvento(String ComboBoxEventoValue) {
		try{
			AggiungiEventoAllaTable(ComboBoxEventoValue);
			IstanziaEventoAggiungiAdArraySedute(ComboBoxEventoValue);				
		}catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Errore!","ERROR:407", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void IstanziaEventoAggiungiAdArraySedute(String ComboBoxEventoValue) {
		Evento_Sociale eventoNuovo = new Evento_Sociale();
		eventoNuovo.setOrarioInizio(timeInizio);
		eventoNuovo.setOrarioFine(timeFine);
		eventoNuovo.setTitolo(ComboBoxEventoValue);
		listaSedute.add(eventoNuovo);
	}

	private void AggiungiEventoAllaTable(String ComboBoxEventoValue) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(new Object[] {ComboBoxEventoValue, orarioInizio.getText(), orarioFine.getText(), null, null, null, null});
	}

	private void CreaIntervallo(Controller controller, Conferenza updateConferenza, String dataInserita, String comboBoxIntervalloValue) {
		if(isDataInserita())
		{
			CastaDataeOrario(controller, dataInserita);
			if(isNuovaDataIdoneaAllaConferenza(updateConferenza) && isOrarioIntervalloOrEventoIdoneo())
			{			
				if(isParametriCorretti(dataInserita, updateConferenza))			
					IstanziaIntervallo(comboBoxIntervalloValue);					
			}
		}	
	}

	private void CastaDataeOrario(Controller controller, String dataInserita) {
		if(!dataInserita.isEmpty())
		{
			CastaStringToData();		
			CastaStringToTime();
		}			
	}
	
	private void IstanziaIntervallo(String comboBoxIntervalloValue) {
		try{
			AggiungiIntervalloAllaTable(comboBoxIntervalloValue);		
			IstanziaIntervalloAggiungiAdArraySedute(comboBoxIntervalloValue);		
		}
		catch(NullPointerException exception){
			JOptionPane.showMessageDialog(null,"Errore!","ERROR:407", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void AggiungiIntervalloAllaTable(String comboBoxIntervalloValue) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(new Object[] {comboBoxIntervalloValue, orarioInizio.getText(), orarioFine.getText(), null, null, null, null});
	}

	private void IstanziaIntervalloAggiungiAdArraySedute(String comboBoxIntervalloValue) {
		Intervallo intervalloNuovo = new Intervallo();
		intervalloNuovo.setOrarioInizio(timeInizio);
		intervalloNuovo.setOrarioFine(timeFine);
		intervalloNuovo.setTitolo(comboBoxIntervalloValue);
		listaSedute.add(intervalloNuovo);
	}

	private Boolean isParametriCorretti(String dataInserita, Conferenza updateConferenza) {
		
		if(isOrarioDataNOTEmpty(dataInserita) && isDataProgrammaInConferenza(updateConferenza) && isOrarioConforme())
			return true;
		return false;
	}

	private Boolean isOrarioConforme() {
		if(timeFine.before(timeInizio) || timeFine.equals(timeInizio))
		{
			JOptionPane.showMessageDialog(null,"Gli orari non sono conformi!","ERROR:412", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		return true;
	}

	private Boolean isDataProgrammaInConferenza(Conferenza updateConferenza) {
		CastaStringToData();
		if(dataProgramma.before(updateConferenza.getDataInizio()) || dataProgramma.after(updateConferenza.getDataFine()))
		{
			JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza!","ERROR:413", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		return true;
	}

	private Boolean isOrarioDataNOTEmpty(String dataInserita) {
		if(orarioFine.getText().isEmpty() || orarioInizio.getText().isEmpty() || dataInserita.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Devi compilare la data e gli orari!","ERROR:412", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private Boolean isOrarioIntervalloOrEventoIdoneo() {
		if(table.getRowCount() != 0)
		{
			for(Seduta s: listaSedute)
			{
				if(isNotIdoneaSeduta(s))
					return false;						
			}
		}
		return true;
	}

	private void CastaStringToTime() {
		try {
			timeFine = tipoTempo.parse(orarioFine.getText());	
			timeInizio = tipoTempo.parse(orarioInizio.getText());
		} catch (ParseException e1) {
			System.out.println("Orario non conforme!");
		}
	}

	private Boolean isNuovaDataIdoneaAllaConferenza(Conferenza updateConferenza) {
		for(Programma p: updateConferenza.programmiConferenza)
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

	private void RiempiComboBoxChair(Controller controller, JComboBox<String> comboBoxChair,
			Conferenza updateConferenza) {
		//riempio la ComboBox chiedendo al DB quali sono le locazioni della sede passata
		for(String chair: controller.ottieniAllPossibiliChair(updateConferenza))
		{
				comboBoxChair.addItem(chair);
		}
	}

	private void RiempiComboBoxKS(Controller controller, JComboBox<String> comboBoxKS, Conferenza updateConferenza) {
		//riempio la ComboBox chiedendo al DB quali sono i KS 
		for(String ks: controller.ottieniAllKS())
		{
				comboBoxKS.addItem(ks);
		}
	}

	private void RiempiComboBoxLocazione(Controller controller, JComboBox<String> comboBox, Conferenza updateConferenza) {
		//riempio la ComboBox chiedendo al DB quali sono le locazioni della sede passata
		ArrayList<String> listaLocazioni = controller.ottieniLocazioni(updateConferenza.sedeOspitante);
		for(String s: listaLocazioni)
		{
			comboBox.addItem(s);
		}
	}
	
	public void CastaStringToData() {
		try {
			dataProgramma = format.parse(dataTextField.getText());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
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
}