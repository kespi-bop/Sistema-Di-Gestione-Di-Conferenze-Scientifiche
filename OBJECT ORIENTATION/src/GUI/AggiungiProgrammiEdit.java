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
import java.awt.Container;

import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Model.Conferenza;
import Model.Evento_Sociale;
import Model.Intervallo;
import Model.Locazione;
import Model.Organizzatore_Locale;
import Model.Organizzatore_Scientifico;
import Model.Partecipante;
import Model.Programma;
import Model.Pubblicità;
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
	private JTable table_1;
	private ArrayList<Programma> listaProgrammi = new ArrayList<Programma>();
	private ArrayList<Seduta> listaSedute = new ArrayList<Seduta>();
	private ArrayList<Sessione> listaSessioni = new ArrayList<Sessione>();
	private ArrayList<Evento_Sociale> listaEventi = new ArrayList<Evento_Sociale>();
	private ArrayList<Intervallo> listaIntervalli = new ArrayList<Intervallo>();

	
	public AggiungiProgrammiEdit(Controller controller, JFrame frameAzioniDiModifica, Conferenza updateConferenza, Date dataInizio, Date dataFine) {
		initialize(controller, frameAzioniDiModifica, dataInizio, dataFine);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Controller controller, JFrame frameAzioniDiModifica, Date dataInizio, Date dataFine) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 600, 880);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 600, 880);
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
				frame.setVisible(false);
				frameAzioniDiModifica.setVisible(true);		
				frameAzioniDiModifica.setEnabled(true);
			}
		});
		panel.setLayout(null);
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
		
		JLabel orarioInizioLabel = new JLabel("Orario inizio");
		orarioInizioLabel.setBounds(74, 121, 66, 14);
		orarioInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		orarioInizioLabel.setForeground(new Color(57, 113, 177));
		
		JLabel OrarioFineLabel = new JLabel("Orario fine");
		OrarioFineLabel.setBounds(74, 158, 66, 14);
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
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final JFormattedTextField formattedTextField = new JFormattedTextField(format);
		formattedTextField.setForeground(new Color(255, 255, 255));
		formattedTextField.setBackground(new Color(32, 33, 35));
		formattedTextField.setBorder(null);
		formattedTextField.setBounds(150, 80, 154, 20);
		panel.add(formattedTextField);
		
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
		panel.add(dataProgrammaLabel);
		panel.add(orarioInizioLabel);
		panel.add(OrarioFineLabel);
		panel.add(locazioneLabel);
		panel.add(descrizioneLabel);
		panel.add(orarioFine);
		
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
			/**
			 * 
			 */
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
	            if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // seleziona riga
	               ((DefaultTableModel)table.getModel()).removeRow(row); //elimino il la riga con doppio click
	               listaSedute.remove(row);
	            }
	         }
	      });
		scrollPane_1.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setForeground(new Color(255,255,255));
		table.setBackground(new Color(32, 33, 35));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.getTableHeader().setReorderingAllowed(false); 
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
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		//riempio la ComboBox chiedendo al DB quali sono le locazioni della sede passata
		ArrayList<String> listaLocazioni = controller.ottieniLocazioni(conferenzaCreata.ospitaConferenza);
		for(String s: listaLocazioni)
		{
			comboBox.addItem(s);
		}
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setFocusable(false);
		comboBox.setBorder(null);
		comboBox.setBackground(new Color(32, 33, 35));
		comboBox.setBounds(150, 185, 154, 21);
		panel.add(comboBox);
		

		JLabel lblTipointervallo = new JLabel("Intervallo");
		lblTipointervallo.setForeground(new Color(57, 113, 177));
		lblTipointervallo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTipointervallo.setBounds(327, 189, 72, 14);
		panel.add(lblTipointervallo);
		
		final JComboBox<String> comboBoxIntervallo = new JComboBox<String>();
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
		
		final JComboBox<String> comboBoxEvento = new JComboBox<String>();
		comboBoxEvento.setForeground(Color.WHITE);
		comboBoxEvento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxEvento.setFocusable(false);
		comboBoxEvento.setBorder(null);
		comboBoxEvento.setBackground(new Color(32, 33, 35));
		comboBoxEvento.addItem("Cena");
		comboBoxEvento.addItem("Gita");
		comboBoxEvento.setBounds(409, 217, 154, 21);
		panel.add(comboBoxEvento);
		
		final JButton aggiungiIntervalloButton = new JButton("aggiungi intevallo");
		aggiungiIntervalloButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				//casto la data inserita al tipo Date di java
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dataProgramma = new Date();
				try {
					dataProgramma = sf.parse(formattedTextField.getText());
				} catch (ParseException e1) {
					System.out.println("Data non conforme alla conferenza!");
				}
				
				
				//casto l'orario iniziale e finale a DataTime per poterli confrontare
				SimpleDateFormat tipoTempo = new SimpleDateFormat("HH:mm");
				Date timeFine = new Date();
				Date timeInizio = new Date();
				try {
					timeFine = tipoTempo.parse(orarioFine.getText());	
					timeInizio = tipoTempo.parse(orarioInizio.getText());
				} catch (ParseException e1) {
					System.out.println("Orario non conforme!");
				}
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				//controllo se non ci sono altre sessioni in corso
				if(table.getRowCount() != 0)
				{
					for(Seduta s: listaSedute)
					{
						if( ( timeInizio.after(s.getOrarioInizio()) && timeInizio.before(s.getOrarioFine()) )
							|| ( timeFine.after(s.getOrarioInizio()) && timeFine.before(s.getOrarioFine()) ) 
							|| ( timeInizio.before(s.getOrarioInizio()) && timeFine.after(s.getOrarioFine()) ) 
							|| ( timeInizio.equals(s.getOrarioInizio()) || timeFine.equals(s.getOrarioFine()) ))
						{
							JOptionPane.showMessageDialog(null,"In questo orario è in corso un'altra sessione!","ERROR:413", JOptionPane.ERROR_MESSAGE);
							return;						
						}
						
					}
				}
				
				
				//nel caso in cui non sono stati inseriti orari
				if(orarioFine.getText().isEmpty() || orarioInizio.getText().isEmpty() || formattedTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Devi compilare la data e gli orari!","ERROR:412", JOptionPane.ERROR_MESSAGE);
				}
				//nel caso in cui la data del programma non rientra nella data della conferenza
				else if(dataProgramma.before(conferenzaCreata.getDataInizio()) || dataProgramma.after(conferenzaCreata.getDataFine()))
				{
					JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza creata!","ERROR:413", JOptionPane.ERROR_MESSAGE);
				}			
				//nel caso in cui orario inizale >= orario finale
				else if(timeFine.before(timeInizio) || timeFine.equals(timeInizio))
				{
					JOptionPane.showMessageDialog(null,"Gli orari non sono conformi!","ERROR:412", JOptionPane.ERROR_MESSAGE);
				}	
				else
				{
					try
					{
						model.addRow(new Object[] {comboBoxIntervallo.getSelectedItem().toString(), orarioInizio.getText(), orarioFine.getText(), null, null, null, null});
						Intervallo intervalloNuovo = new Intervallo();
						intervalloNuovo.setOrarioInizio(timeInizio);
						intervalloNuovo.setOrarioFine(timeFine);
						intervalloNuovo.setTitolo(comboBoxIntervallo.getSelectedItem().toString());
						listaSedute.add(intervalloNuovo);
					}
					catch(NullPointerException exception)
					{
						JOptionPane.showMessageDialog(null,"Errore!","ERROR:407", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		aggiungiIntervalloButton.setBounds(39, 549, 143, 50);
		aggiungiIntervalloButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiIntervalloButton.setForeground(Color.WHITE);
		aggiungiIntervalloButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiIntervalloButton.setFocusPainted(false);
		aggiungiIntervalloButton.setBorder(null);
		aggiungiIntervalloButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiIntervalloButton);
		
		JButton aggiungiOrganizzatoreLButton = new JButton("aggiungi \r\nevento");
		aggiungiOrganizzatoreLButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//casto la data inserita al tipo Date di java
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dataProgramma = new Date();
				try {
					dataProgramma = sf.parse(formattedTextField.getText());
				} catch (ParseException e1) {
					System.out.println("Data non conforme alla conferenza!");
				}
				
				
				//casto l'orario iniziale e finale a DataTime per poterli confrontare
				SimpleDateFormat tipoTempo = new SimpleDateFormat("HH:mm");
				Date timeFine = new Date();
				Date timeInizio = new Date();
				try {
					timeFine = tipoTempo.parse(orarioFine.getText());	
					timeInizio = tipoTempo.parse(orarioInizio.getText());
				} catch (ParseException e1) {
					System.out.println("Orario non conforme!");
				}
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				//controllo se non ci sono altre sessioni in corso
				if(table.getRowCount() != 0)
				{
					for(Seduta s: listaSedute)
					{
						if( ( timeInizio.after(s.getOrarioInizio()) && timeInizio.before(s.getOrarioFine()) )
							|| ( timeFine.after(s.getOrarioInizio()) && timeFine.before(s.getOrarioFine()) ) 
							|| ( timeInizio.before(s.getOrarioInizio()) && timeFine.after(s.getOrarioFine()) ) 
							|| ( timeInizio.equals(s.getOrarioInizio()) || timeFine.equals(s.getOrarioFine()) ))
						{
							JOptionPane.showMessageDialog(null,"In questo orario è in corso un'altra sessione!","ERROR:413", JOptionPane.ERROR_MESSAGE);
							return;						
						}
						
					}
				}
				
				
				//nel caso in cui non sono stati inseriti orari
				if(orarioFine.getText().isEmpty() || orarioInizio.getText().isEmpty() || formattedTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Devi compilare la data e gli orari!","ERROR:412", JOptionPane.ERROR_MESSAGE);
				}
				//nel caso in cui la data del programma non rientra nella data della conferenza
				else if(dataProgramma.before(conferenzaCreata.getDataInizio()) || dataProgramma.after(conferenzaCreata.getDataFine()))
				{
					JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza creata!","ERROR:413", JOptionPane.ERROR_MESSAGE);
				}			
				//nel caso in cui orario inizale >= orario finale
				else if(timeFine.before(timeInizio) || timeFine.equals(timeInizio))
				{
					JOptionPane.showMessageDialog(null,"Gli orari non sono conformi!","ERROR:412", JOptionPane.ERROR_MESSAGE);
				}	
				else
				{
					try
					{
						model.addRow(new Object[] {comboBoxEvento.getSelectedItem().toString(), orarioInizio.getText(), orarioFine.getText(), null, null, null, null});
						Evento_Sociale eventoNuovo = new Evento_Sociale();
						eventoNuovo.setOrarioInizio(timeInizio);
						eventoNuovo.setOrarioFine(timeFine);
						eventoNuovo.setTitolo(comboBoxEvento.getSelectedItem().toString());
						listaSedute.add(eventoNuovo);
					}
					catch(NullPointerException exception)
					{
						JOptionPane.showMessageDialog(null,"Errore!","ERROR:407", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		aggiungiOrganizzatoreLButton.setBounds(421, 549, 142, 50);
		aggiungiOrganizzatoreLButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiOrganizzatoreLButton.setForeground(Color.WHITE);
		aggiungiOrganizzatoreLButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiOrganizzatoreLButton.setFocusPainted(false);
		aggiungiOrganizzatoreLButton.setBorder(null);
		aggiungiOrganizzatoreLButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiOrganizzatoreLButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 285, 356, 88);
		panel.add(scrollPane);
		
		final JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		editorPane.setSelectionColor(new Color(126, 87, 194));
		editorPane.setForeground(Color.WHITE);
		editorPane.setDisabledTextColor(Color.WHITE);
		editorPane.setCaretColor(Color.WHITE);
		editorPane.setBorder(null);
		editorPane.setBackground(new Color(32, 33, 35));
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		//riempio la ComboBox chiedendo al DB quali sono le locazioni della sede passata
		ArrayList<String> listaKS = controller.ottieniAllKS();
		comboBox_1.addItem("");
		for(String ks: listaKS)
		{
				comboBox_1.addItem(ks);
		}
		comboBox_1.setForeground(Color.WHITE);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox_1.setFocusable(false);
		comboBox_1.setBorder(null);
		comboBox_1.setBackground(new Color(32, 33, 35));
		comboBox_1.setBounds(150, 218, 154, 21);
		panel.add(comboBox_1);
		
		final JComboBox<String> comboBox_2 = new JComboBox<String>();
		//riempio la ComboBox chiedendo al DB quali sono le locazioni della sede passata	
		for(Organizzatore_Scientifico chair: listaOrganizzatoriScientifici)
		{
				comboBox_2.addItem(chair.getEmail().toString());
		}
		comboBox_2.setForeground(Color.WHITE);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox_2.setFocusable(false);
		comboBox_2.setBorder(null);
		comboBox_2.setBackground(new Color(32, 33, 35));
		comboBox_2.setBounds(150, 253, 154, 21);
		panel.add(comboBox_2);
		
		final JFormattedTextField textFieldTitolo = new JFormattedTextField((Format) null);
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
		
		
		JButton aggiungiOrganizzatoreSButton = new JButton("aggiungi sessione");
		aggiungiOrganizzatoreSButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//casto la data inserita al tipo Date di java
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dataProgramma = new Date();
				try {
					dataProgramma = sf.parse(formattedTextField.getText());
				} catch (ParseException e1) {
					System.out.println("Data non conforme alla conferenza!");
				}
				
				
				//casto l'orario iniziale e finale a DataTime per poterli confrontare
				SimpleDateFormat tipoTempo = new SimpleDateFormat("HH:mm");
				Date timeFine = new Date();
				Date timeInizio = new Date();
				try {
					timeFine = tipoTempo.parse(orarioFine.getText());	
					timeInizio = tipoTempo.parse(orarioInizio.getText());
				} catch (ParseException e1) {
					System.out.println("Orario non conforme!");
				}
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				//controllo se non ci sono altre sessioni in corso
				if(table.getRowCount() != 0)
				{
					for(Seduta s: listaSedute)
					{		
						if(!(s instanceof Sessione))
						{
							if( ( timeInizio.after(s.getOrarioInizio()) && timeInizio.before(s.getOrarioFine()) )
								|| ( timeFine.after(s.getOrarioInizio()) && timeFine.before(s.getOrarioFine()) ) 
								|| ( timeInizio.before(s.getOrarioInizio()) && timeFine.after(s.getOrarioFine()) ) 
								|| ( timeInizio.equals(s.getOrarioInizio()) || timeFine.equals(s.getOrarioFine()) ))
							{
								JOptionPane.showMessageDialog(null,"In questo orario è in corso un'altra sessione!","ERROR:413", JOptionPane.ERROR_MESSAGE);
								return;						
							}
						}
						else
						{
							if( (s.getLocazione().getNomeLocazione().compareTo(comboBox.getSelectedItem().toString())) == 0 )
							{
								if( ( timeInizio.after(s.getOrarioInizio()) && timeInizio.before(s.getOrarioFine()) )
										|| ( timeFine.after(s.getOrarioInizio()) && timeFine.before(s.getOrarioFine()) ) 
										|| ( timeInizio.before(s.getOrarioInizio()) && timeFine.after(s.getOrarioFine()) ) 
										|| ( timeInizio.equals(s.getOrarioInizio()) || timeFine.equals(s.getOrarioFine()) ))
									{
										JOptionPane.showMessageDialog(null,"In questa locazione è in corso un'altra sessione!","ERROR:413", JOptionPane.ERROR_MESSAGE);
										return;						
									}
							}
						}
						
					}
				}
				
				
				//nel caso in cui non sono stati inseriti orari
				if(orarioFine.getText().isEmpty() || orarioInizio.getText().isEmpty() || formattedTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Devi compilare la data e gli orari!","ERROR:412", JOptionPane.ERROR_MESSAGE);
				}
				//nel caso in cui la data del programma non rientra nella data della conferenza
				else if(dataProgramma.before(conferenzaCreata.getDataInizio()) || dataProgramma.after(conferenzaCreata.getDataFine()))
				{
					JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza creata!","ERROR:413", JOptionPane.ERROR_MESSAGE);
				}			
				//nel caso in cui orario inizale >= orario finale
				else if(timeFine.before(timeInizio) || timeFine.equals(timeInizio))
				{
					JOptionPane.showMessageDialog(null,"Gli orari non sono conformi!","ERROR:412", JOptionPane.ERROR_MESSAGE);
				}	
				else
				{
					try
					{
						model.addRow(new Object[] {textFieldTitolo.getText(), orarioInizio.getText(), orarioFine.getText(), comboBox.getSelectedItem().toString(), 
									 			   comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString(), editorPane.getText()});
						
						Sessione sessioneNuova = controller.creaSessionedaFrame(textFieldTitolo.getText(), timeInizio, timeFine, comboBox.getSelectedItem().toString(), 
									 			   comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString(), editorPane.getText());

						listaSedute.add(sessioneNuova);
					}
					catch(NullPointerException exception)
					{
						JOptionPane.showMessageDialog(null,"Non sono presenti altri chair!","ERROR:407", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		aggiungiOrganizzatoreSButton.setBounds(232, 549, 142, 50);
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
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(150, 141, 154, 2);
		panel.add(separator_1_2_1);
		
		
		
		
		
		JLabel lblChair = new JLabel("Chair*");
		lblChair.setBounds(74, 253, 60, 14);
		lblChair.setForeground(new Color(57, 113, 177));
		lblChair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblChair);
		
		JButton creaConferenzaButton = new JButton("CREA CONFERENZA");
		creaConferenzaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameCreazioneConferenza.dispose();
				controller.commitCreazioneConferenza(conferenzaCreata, listaProgrammi, listaPubblicità);
				controller.tornaAllaHome(controller, frame, frameHome);				
			}
		});
		creaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaConferenzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		creaConferenzaButton.setForeground(Color.WHITE);
		creaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		creaConferenzaButton.setFocusPainted(false);
		creaConferenzaButton.setBorder(null);
		creaConferenzaButton.setBackground(new Color(57, 113, 177));
		creaConferenzaButton.setBounds(398, 805, 165, 36);
		panel.add(creaConferenzaButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(new Color(130, 135, 144), 0));
		scrollPane_2.setBounds(29, 624, 131, 124);
		panel.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setForeground(Color.WHITE);
		table_1.getTableHeader().setReorderingAllowed(false); 
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Data"
				}
			){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		table_1.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // seleziona riga
	               ((DefaultTableModel)table_1.getModel()).removeRow(row); //elimino il la riga con doppio click      
	               listaProgrammi.remove(row);	                
	            }
	         }
	      });
		scrollPane_2.setViewportView(table_1);
		table_1.setSelectionBackground(new Color(126, 87, 194));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBackground(new Color(32, 33, 35));
		
		
		
		JButton btnAggiungiProgramma = new JButton("aggiungi programma");
		btnAggiungiProgramma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//casto la data inserita al tipo Date di java
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dataProgramma = new Date();
				try {
					dataProgramma = sf.parse(formattedTextField.getText());
				} catch (ParseException e1) {
					System.out.println("Data non conforme alla conferenza!");
				}
				
				//nel caso in cui la data del programma non rientra nella data della conferenza
				if(dataProgramma.before(conferenzaCreata.getDataInizio()) || dataProgramma.after(conferenzaCreata.getDataFine()))
				{
					JOptionPane.showMessageDialog(null,"Data non conforme alla conferenza creata!","ERROR:413", JOptionPane.ERROR_MESSAGE);
					return;
				}	
				
							
				DefaultTableModel model = (DefaultTableModel)table_1.getModel();
				Programma programmaNuovo = new Programma();
				for(Seduta s: listaSedute)
				{
					if(s instanceof Sessione)
						listaSessioni.add((Sessione)s);

					else if(s instanceof Evento_Sociale)
						listaEventi.add((Evento_Sociale)s);				

					else

						listaIntervalli.add((Intervallo)s);
				}
				
				if(table_1.getRowCount() != 0)
				{
					for(Programma p: listaProgrammi)
					{
						if(p.getDataProgramma().equals(dataProgramma))
						{
							JOptionPane.showMessageDialog(null,"Data non disponibile!","ERROR:412", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				
				if(table.getRowCount()<1)
				{
					JOptionPane.showMessageDialog(null,"Devi aggiungere delle sedute!","ERROR:412", JOptionPane.ERROR_MESSAGE);
					return;
				}			
				programmaNuovo.setDataProgramma(dataProgramma);
				programmaNuovo.sessioniProgrammate = new ArrayList<Sessione>(listaSessioni);
				programmaNuovo.eventiProgrammati = new ArrayList<Evento_Sociale>(listaEventi);
				programmaNuovo.intervalliProgrammati = new ArrayList<Intervallo>(listaIntervalli);
				listaProgrammi.add(programmaNuovo);
				model.addRow(new Object[] {sf.format(programmaNuovo.getDataProgramma())});
				//dopo aver aggiunto il programma rimuovo le ArrayList occupate
				listaSessioni.removeAll(listaSessioni);
				listaEventi.removeAll(listaEventi);
				listaIntervalli.removeAll(listaIntervalli);		
				listaSedute.removeAll(listaSedute);
				//ripulisco la tabella
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				}
		});
		btnAggiungiProgramma.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungiProgramma.setForeground(Color.WHITE);
		btnAggiungiProgramma.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnAggiungiProgramma.setFocusPainted(false);
		btnAggiungiProgramma.setBorder(null);
		btnAggiungiProgramma.setBackground(new Color(126, 87, 194));
		btnAggiungiProgramma.setBounds(39, 805, 165, 36);
		panel.add(btnAggiungiProgramma);
		
		
		JLabel lblListaDeiProgrammi = new JLabel("Lista dei programmi aggiunti alla conferenza");
		lblListaDeiProgrammi.setForeground(new Color(70, 71, 74));
		lblListaDeiProgrammi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblListaDeiProgrammi.setBounds(29, 759, 497, 14);
		panel.add(lblListaDeiProgrammi);
		
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
		
	}
}