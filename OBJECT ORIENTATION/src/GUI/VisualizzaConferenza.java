package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;

import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.text.Format;

public class VisualizzaConferenza {

	public JFrame frame;
	private JLabel lblFormatoDataYyyymmdd;
	private JLabel exitLabel;
	private JLabel dataProgrammaLabel;
	private JLabel lblFiltraPerSede;
	private JLabel lblNewLabel;
	private JLabel dragFrame;
	private JLabel signature;
	private JLabel sedeLabel;
	private JTable table;
	private JButton aggiornaListaConferenzeButton;
	private JScrollPane scrollPane;
	private Integer mouseX, mouseY;
	private DefaultTableModel model;	
	private JSeparator separator_1;
	private JPanel panel;
	private JFormattedTextField dateInizialeTextField;
	private JFormattedTextField dateFinaleTextField;
	private JComboBox<String>comboBox;	
	private ArrayList<String> sedi;
	private ArrayList<Integer> listaCodici = new ArrayList<Integer>();
	private ArrayList<String> listaTitoli = new ArrayList<String>();
	private ArrayList<String> listaDate = new ArrayList<String>();
	private ArrayList<String> listaSedi = new ArrayList<String>();
	private Image imgExit;
	private DateFormat format;
	

	
	public VisualizzaConferenza(Controller controller, JFrame frameHome) {
		initialize(controller, frameHome);
	}


	private void initialize(final Controller controller, final JFrame frameHome) {
		
		//SWING COMPONENTS
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(700, 300, 513, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 513, 408);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 152, 425, 212);
		panel.add(scrollPane);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBackground(new Color(0, 0, 0));
		
		table = new JTable();
		table.setForeground(new Color(255, 255, 255));
		table.setGridColor(new Color(0, 0, 0));
		table.getTableHeader().setReorderingAllowed(false); 
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setRequestFocusEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodProgramma","Titolo Conferenza", "Data Programma", "Sede"
			}
			
		){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);	
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		model = (DefaultTableModel)table.getModel();
		scrollPane.setViewportView(table);
		
		lblNewLabel= new JLabel("filtra per data");
		lblNewLabel.setBounds(86, 85, 174, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(71, 72, 75));
		panel.add(lblNewLabel);
				
		lblFiltraPerSede = new JLabel("filtra per sede");
		lblFiltraPerSede.setBounds(86, 131, 174, 14);	
		lblFiltraPerSede.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFiltraPerSede.setForeground(new Color(71, 72, 75));
		panel.add(lblFiltraPerSede);
				
		lblFormatoDataYyyymmdd = new JLabel("yyyy-MM-dd");
		lblFormatoDataYyyymmdd.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFormatoDataYyyymmdd.setBounds(86, 42, 174, 14);
		panel.add(lblFormatoDataYyyymmdd);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(322, 375, 191, 33);
		panel.add(signature);
		signature.setForeground(new Color(71, 72, 75));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
				
		dataProgrammaLabel = new JLabel("Inizio");
		dataProgrammaLabel.setBounds(39, 58, 48, 14);
		panel.add(dataProgrammaLabel);
		dataProgrammaLabel.setForeground(new Color(57, 113, 177));
		dataProgrammaLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataProgrammaLabel.setBackground(new Color(57, 113, 177));
		
		separator_1 = new JSeparator();
		separator_1.setBounds(86, 79, 154, 2);
		panel.add(separator_1);
				
		sedeLabel = new JLabel("Sede");
		sedeLabel.setBounds(39, 110, 48, 14);
		sedeLabel.setForeground(new Color(57, 113, 177));
		sedeLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(sedeLabel);
				
		comboBox = new JComboBox<String>();
		comboBox.addItem("");
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setFocusable(false);
		comboBox.setBounds(86, 107, 154, 21);
		comboBox.setBorder(null);
		comboBox.setBackground(new Color(32, 33, 35));
		//riempio la ComboBox chiedendo al DB quali sono le sedi
		sedi = controller.ottieniSedi();
		for(String s: sedi)
		{
			comboBox.addItem(s);
		}
		panel.add(comboBox);
		
		format = new SimpleDateFormat("yyyy-MM-dd");
		dateInizialeTextField = new JFormattedTextField(format);
		dateInizialeTextField.setForeground(new Color(255, 255, 255));
		dateInizialeTextField.setSelectionColor(new Color(126, 87, 194));
		dateInizialeTextField.setSelectedTextColor(new Color(255, 255, 255));
		dateInizialeTextField.setDisabledTextColor(new Color(255, 255, 255));
		dateInizialeTextField.setBorder(null);
		dateInizialeTextField.setBackground(new Color(32, 33, 35));
		dateInizialeTextField.setCaretColor(new Color(255, 255, 255));
		dateInizialeTextField.setBounds(86, 55, 154, 20);
		panel.add(dateInizialeTextField);
		
		aggiornaListaConferenzeButton = new JButton("aggiorna");
		aggiornaListaConferenzeButton.setBounds(373, 103, 88, 26);
		aggiornaListaConferenzeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiornaListaConferenzeButton.setForeground(Color.WHITE);
		aggiornaListaConferenzeButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiornaListaConferenzeButton.setFocusPainted(false);
		aggiornaListaConferenzeButton.setBorder(null);
		aggiornaListaConferenzeButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiornaListaConferenzeButton);
		
		imgExit= new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);	
		exitLabel.setIcon(new ImageIcon(imgExit));
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(exitLabel);
		
		dateFinaleTextField = new JFormattedTextField((Format) null);
		dateFinaleTextField.setSelectionColor(new Color(126, 87, 194));
		dateFinaleTextField.setSelectedTextColor(Color.WHITE);
		dateFinaleTextField.setForeground(Color.WHITE);
		dateFinaleTextField.setDisabledTextColor(Color.WHITE);
		dateFinaleTextField.setCaretColor(Color.WHITE);
		dateFinaleTextField.setBorder(null);
		dateFinaleTextField.setBackground(new Color(32, 33, 35));
		dateFinaleTextField.setBounds(297, 55, 154, 20);
		panel.add(dateFinaleTextField);
		
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 460, 44);
		panel.add(dragFrame);
		//trascina finestra undecorated
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
			
		JLabel lblDataI = new JLabel("Fine");
		lblDataI.setForeground(new Color(57, 113, 177));
		lblDataI.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDataI.setBackground(new Color(57, 113, 177));
		lblDataI.setBounds(250, 58, 48, 14);
		panel.add(lblDataI);
		
		JLabel lblFormatoDataYyyymmdd_1 = new JLabel("yyyy-MM-dd");
		lblFormatoDataYyyymmdd_1.setForeground(new Color(71, 72, 75));
		lblFormatoDataYyyymmdd_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFormatoDataYyyymmdd_1.setBounds(297, 42, 174, 14);
		panel.add(lblFormatoDataYyyymmdd_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(297, 79, 154, 2);
		panel.add(separator_1_1);
		
		
		
		
		//PULSANTI & LISTNERS
		
		//visualizzo i programmi della conferenza clickata
		table.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent me) {
			      	 if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
						JTable target = (JTable)me.getSource();
						int row = target.getSelectedRow(); // seleziona riga
						controller.visualizzaFrameProgrammi(frame, table.getValueAt(row, 0).toString());  //passo il valore del Programma cliccato
			      	 }
			    }
		});
		
		
		//pulsante di uscita	
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(frame, frameHome);		
			}
		});			

		//aggiorna la jtable in base ai dati inseriti	
		aggiornaListaConferenzeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				aggiornaListaConferenze(controller);			
			}
		});		
	}

	
	//AGGIORNA LA JTABLE CON LE CONFERENZE RICHIESTE 
	private void aggiornaListaConferenze(Controller controller) {
		
		controller.RipulisciTabella(table.getModel());	
		RipulisciArray();
		controller.ottieniConferenzeConProgrammi(listaCodici, listaTitoli, listaDate, listaSedi, dateInizialeTextField.getText(), dateFinaleTextField.getText(), comboBox.getSelectedItem().toString());

		RiempiTabellaConferenzeConProgrammi();	
		RipulisciTextField(dateInizialeTextField);
		RipulisciTextField(dateFinaleTextField);
		
	}

	//RIPULISCE GLI ARRAY PRIMA DI REINSERIRE NUOVI VALORI
	private void RipulisciArray() {
		listaCodici.removeAll(listaCodici);
		listaTitoli.removeAll(listaTitoli);
		listaDate.removeAll(listaDate);
		listaSedi.removeAll(listaSedi);		
	}

	//RIEMPIE LA JTABLE
	private void RiempiTabellaConferenzeConProgrammi() {
		if(listaDate!=null)
			for(int i = 0;i<listaDate.size(); i++)
				//riempiamo il model che mostrerà i valori sullo schermo
				model.addRow(new Object[] {listaCodici.get(i), listaTitoli.get(i),listaDate.get(i), listaSedi.get(i)});
	}
	
	//RIPULISCE IL TEXTFIELD
	private void RipulisciTextField(JFormattedTextField dateTextField) {
		dateTextField.setText("");
	}
}
