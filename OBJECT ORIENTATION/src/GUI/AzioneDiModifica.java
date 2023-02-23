package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.Controller;
import Model.Conferenza;
import Model.Programma;

import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JFormattedTextField;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class AzioneDiModifica {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField textField;
	private JTable table;

	
	public AzioneDiModifica(Controller controller,JFrame frameModificaConferenza, JFrame frameHome, Conferenza updateConferenza) {
		initialize( controller, frameModificaConferenza, frameHome, updateConferenza);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller,final JFrame frameModificaConferenza, final JFrame frameHome, Conferenza updateConferenza) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 560, 407);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(533, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateConferenza.programmiConferenza.clear();
				frameModificaConferenza.setEnabled(true);
				frameModificaConferenza.setVisible(true);
				frame.dispose();
				
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 239, 131, 106);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data Programma"
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false); 
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setForeground(Color.WHITE);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		//riempio il model che mostrerà i valori sullo schermo
		for(int i = 0;i< updateConferenza.programmiConferenza.size(); i++)
		{		
			model.addRow(new Object[] {sf.format(updateConferenza.programmiConferenza.get(i).getDataProgramma())});
		}
		
		JButton cancelProgrammaLabel = new JButton("cancella programma");
		cancelProgrammaLabel.setBounds(210, 244, 143, 38);
		panel.add(cancelProgrammaLabel);
		cancelProgrammaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if (table.getSelectionModel().isSelectionEmpty()) {
				    // Non è stata selezionata nessuna cella
					JOptionPane.showMessageDialog(null,"Seleziona un programma!","ERROR:415", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
				    // È stata selezionata almeno una cella
					updateConferenza.programmiConferenza.remove(table.getSelectedRow());
					model.removeRow(table.getSelectedRow());
				}
				
			}
		});
		cancelProgrammaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelProgrammaLabel.setForeground(Color.WHITE);
		cancelProgrammaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cancelProgrammaLabel.setFocusPainted(false);
		cancelProgrammaLabel.setBorder(null);
		cancelProgrammaLabel.setBackground(new Color(126, 87, 194));
		
		JFormattedTextField dateInizioField = new JFormattedTextField((Format) null);
		dateInizioField.setSelectionColor(new Color(126, 87, 194));
		dateInizioField.setSelectedTextColor(Color.WHITE);
		dateInizioField.setForeground(Color.WHITE);
		dateInizioField.setDisabledTextColor(Color.WHITE);
		dateInizioField.setCaretColor(Color.WHITE);
		dateInizioField.setBorder(null);
		dateInizioField.setBackground(new Color(32, 33, 35));
		dateInizioField.setBounds(205, 119, 154, 20);
		panel.add(dateInizioField);
		
		JFormattedTextField dateFineField = new JFormattedTextField((Format) null);
		dateFineField.setSelectionColor(new Color(126, 87, 194));
		dateFineField.setSelectedTextColor(Color.WHITE);
		dateFineField.setForeground(Color.WHITE);
		dateFineField.setDisabledTextColor(Color.WHITE);
		dateFineField.setCaretColor(Color.WHITE);
		dateFineField.setBorder(null);
		dateFineField.setBackground(new Color(32, 33, 35));
		dateFineField.setBounds(205, 148, 154, 20);
		panel.add(dateFineField);
		
		JButton aggiungiProgrammaButton = new JButton("aggiungi programma");
		aggiungiProgrammaButton.setBounds(210, 195, 143, 38);
		panel.add(aggiungiProgrammaButton);
		aggiungiProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				//converto la stringa di data passata in tipo Date		
				Date dateTimeInizio = new Date();
				try {
					dateTimeInizio = sf.parse(dateInizioField.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				Date dateTimeFine = new Date();
				try {
					dateTimeFine = sf.parse(dateFineField.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				
				if(dateInizioField.getText().isEmpty() && dateFineField.getText().isEmpty())
				{
					controller.vediCreazioneProgrammaEdit(controller, frame, updateConferenza, updateConferenza.getDataInizio(), updateConferenza.getDataFine());
				}
				else if(dateFineField.getText().isEmpty())
				{
					controller.vediCreazioneProgrammaEdit(controller, frame, updateConferenza, updateConferenza.getDataInizio(), dateTimeFine);
				}
				else if(dateInizioField.getText().isEmpty())
				{
					controller.vediCreazioneProgrammaEdit(controller, frame, updateConferenza, dateTimeInizio, updateConferenza.getDataFine());
				}
				else
				{	
					controller.vediCreazioneProgrammaEdit(controller, frame, updateConferenza, dateTimeInizio, dateTimeFine);
				}
				
			}
		});
		//aggiungiProgrammaButton.addActionListener(new ActionListener() {
			//AggiuntaProgrammi aggiungiProgramma = new AggiuntaProgrammi();
			//}
		//});
		aggiungiProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiProgrammaButton.setForeground(Color.WHITE);
		aggiungiProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiProgrammaButton.setFocusPainted(false);
		aggiungiProgrammaButton.setBorder(null);
		aggiungiProgrammaButton.setBackground(new Color(126, 87, 194));
		
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 523, 44);
		panel.add(dragFrame);
		
		JButton ConfermaModificaButton = new JButton("conferma");
		ConfermaModificaButton.setBounds(221, 309, 125, 36);
		panel.add(ConfermaModificaButton);
		ConfermaModificaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateConferenza.programmiConferenza.clear();
				frameModificaConferenza.dispose();
				controller.tornaAllaHome(controller, frame, frameHome);
			}
		});
		ConfermaModificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaModificaButton.setForeground(Color.WHITE);
		ConfermaModificaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaModificaButton.setFocusPainted(false);
		ConfermaModificaButton.setBorder(null);
		ConfermaModificaButton.setBackground(new Color(57, 113, 177));
		
		JLabel titoloLabel = new JLabel("Titolo");
		titoloLabel.setBounds(136, 90, 48, 14);
		panel.add(titoloLabel);
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBackground(new Color(57, 113, 177));
		
		textField = new JTextField();
		textField.setBounds(205, 90, 154, 14);
		panel.add(textField);
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setForeground(Color.WHITE);
		textField.setDisabledTextColor(Color.WHITE);
		textField.setColumns(10);
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(null);
		textField.setBackground(new Color(32, 33, 35));
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(205, 109, 154, 2);
		panel.add(separator_1_1);
		
		JLabel dataInizioLabel = new JLabel("Data inizio");
		dataInizioLabel.setBounds(136, 122, 66, 14);
		panel.add(dataInizioLabel);
		dataInizioLabel.setForeground(new Color(57, 113, 177));
		dataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel dataFineLabel = new JLabel("Data fine");
		dataFineLabel.setBounds(136, 154, 66, 14);
		panel.add(dataFineLabel);
		dataFineLabel.setForeground(new Color(57, 113, 177));
		dataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(205, 140, 154, 2);
		panel.add(separator_1_1_1);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setBounds(205, 171, 154, 2);
		panel.add(separator_1_1_2);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(385, 374, 165, 33);
		panel.add(signature);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
		
		
		
		JLabel lblListaDeiProgrammi = new JLabel("Lista dei programmi aggiunti alla conferenza");
		lblListaDeiProgrammi.setForeground(new Color(70, 71, 74));
		lblListaDeiProgrammi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblListaDeiProgrammi.setBounds(38, 349, 497, 14);
		panel.add(lblListaDeiProgrammi);
		
		
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
		
		
		frame.setBounds(100, 100, 560, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
