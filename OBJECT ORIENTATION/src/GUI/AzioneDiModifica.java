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
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;

public class AzioneDiModifica {

	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JTextField titoloTextField;
	private Controller controller = null;
	private DefaultTableModel model;
	
	public AzioneDiModifica(Controller controller,JFrame frameModificaConferenza, JFrame frameHome, Conferenza updateConferenza) {
		initialize( controller, frameModificaConferenza, frameHome, updateConferenza);
	}


	private void initialize(Controller controller,JFrame frameModificaConferenza, JFrame frameHome, Conferenza updateConferenza) {
		
		//SWING COMPONENTS
		this.controller = controller;
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);	
		frame.setBounds(100, 100, 590, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 590, 407);	
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(563, 11, 17, 21);	
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.setIcon(new ImageIcon(imgExit));
		panel.add(exitLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		scrollPane.setBounds(133, 237, 185, 106);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false); 
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setFocusable(false);
		table.setGridColor(new Color(0, 0, 0));
		table.setForeground(Color.WHITE);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodProgramma", "Data Programma"
			}
		){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		RiempiTableProgrammi(updateConferenza);
		scrollPane.setViewportView(table);
		
		JButton cancelProgrammaLabel = new JButton("cancella programma");
		cancelProgrammaLabel.setBounds(339, 305, 143, 38);
		panel.add(cancelProgrammaLabel);
		cancelProgrammaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelProgrammaLabel.setForeground(Color.WHITE);
		cancelProgrammaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cancelProgrammaLabel.setFocusPainted(false);
		cancelProgrammaLabel.setBorder(null);
		cancelProgrammaLabel.setBackground(new Color(126, 87, 194));
		
		JButton aggiungiProgrammaButton = new JButton("aggiungi programma");
		aggiungiProgrammaButton.setBounds(339, 237, 143, 38);
		aggiungiProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiProgrammaButton.setForeground(Color.WHITE);
		aggiungiProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiProgrammaButton.setFocusPainted(false);
		aggiungiProgrammaButton.setBorder(null);
		aggiungiProgrammaButton.setBackground(new Color(126, 87, 194));
		panel.add(aggiungiProgrammaButton);
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(134, 89, 344, 79);
		panel.add(scrollPane_1);	
		
		JEditorPane descrizionePane = new JEditorPane();
		scrollPane_1.setViewportView(descrizionePane);
		descrizionePane.setSelectionColor(new Color(126, 87, 194));
		descrizionePane.setForeground(Color.WHITE);
		descrizionePane.setDisabledTextColor(Color.WHITE);
		descrizionePane.setCaretColor(Color.WHITE);
		descrizionePane.setBorder(null);
		descrizionePane.setBackground(new Color(32, 33, 35));
			
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 523, 44);
		panel.add(dragFrame);
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
		
		JButton ConfermaModificaButton = new JButton("conferma");
		ConfermaModificaButton.setBounds(396, 179, 84, 26);
		ConfermaModificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaModificaButton.setForeground(Color.WHITE);
		ConfermaModificaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaModificaButton.setFocusPainted(false);
		ConfermaModificaButton.setBorder(null);
		ConfermaModificaButton.setBackground(new Color(57, 113, 177));
		panel.add(ConfermaModificaButton);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(415, 374, 165, 33);
		panel.add(signature);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
			
		JLabel lblListaDeiProgrammi = new JLabel("Lista dei programmi aggiunti alla conferenza");
		lblListaDeiProgrammi.setForeground(new Color(70, 71, 74));
		lblListaDeiProgrammi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblListaDeiProgrammi.setBounds(133, 347, 497, 14);
		panel.add(lblListaDeiProgrammi);
		
		titoloTextField = new JTextField();
		titoloTextField.setSelectionColor(new Color(126, 87, 194));
		titoloTextField.setForeground(Color.WHITE);
		titoloTextField.setDisabledTextColor(Color.WHITE);
		titoloTextField.setColumns(10);
		titoloTextField.setCaretColor(Color.WHITE);
		titoloTextField.setBorder(null);
		titoloTextField.setBackground(new Color(32, 33, 35));
		titoloTextField.setBounds(134, 55, 348, 20);
		panel.add(titoloTextField);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(134, 77, 348, 2);
		panel.add(separator_1_1);
		
		JLabel lblTitolo = new JLabel("Titolo");
		lblTitolo.setForeground(new Color(57, 113, 177));
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitolo.setBackground(new Color(57, 113, 177));
		lblTitolo.setBounds(83, 58, 48, 14);
		panel.add(lblTitolo);
		
		JLabel descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setForeground(new Color(57, 113, 177));
		descrizioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizioneLabel.setBounds(53, 87, 71, 14);
		panel.add(descrizioneLabel);
		
		Image imgRefresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		JLabel refreshButton = new JLabel("");
		refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshButton.setBounds(307, 216, 22, 26);	
		refreshButton.setIcon(new ImageIcon(imgRefresh));
		panel.add(refreshButton);
		
		
		
		//PULSANTI & LISTNERS
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChiudiFrame(updateConferenza, frameModificaConferenza);							
			}
		});

		cancelProgrammaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				CancellaProgramma(updateConferenza);			
			}
		});
		

		aggiungiProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AggiungiProgramma(updateConferenza);			
			}
		});
		
		ConfermaModificaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CommitModifica(titoloTextField.getText(), descrizionePane.getText(), updateConferenza);
				RipristinaFrame(frameModificaConferenza, frameHome);							
			}
		});		
		
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RiempiTableProgrammi(updateConferenza);
			}
		});
	
	}
	
	
	private void CancellaProgramma(Conferenza updateConferenza) {
		if(isAlmenoUnaCellaSelezionata())
		{
			controller.commitCancellaProgramma((table.getValueAt(table.getSelectedRow(), 0).toString()));
			updateConferenza.programmiConferenza.remove(table.getSelectedRow());
			model = (DefaultTableModel) table.getModel();
			model.removeRow(table.getSelectedRow());	
			JOptionPane.showMessageDialog(null,"Cancellazione eseguita con successo!","SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private boolean isAlmenoUnaCellaSelezionata() {
		if (table.getSelectionModel().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null,"Seleziona un programma!","ERROR:415", JOptionPane.ERROR_MESSAGE);
			return false;
		} 
		return true;
	}

	private void ChiudiFrame(Conferenza updateConferenza, JFrame frameModificaConferenza) {
		updateConferenza.programmiConferenza.removeAll(updateConferenza.programmiConferenza);
		controller.TornaAllaPaginaPrecedente(frame, frameModificaConferenza);	
	}

	private void AggiungiProgramma(Conferenza updateConferenza) {
		controller.vediCreazioneProgrammaEdit(frame, updateConferenza, updateConferenza.getDataInizio(), updateConferenza.getDataFine());
	}

	private void RipristinaFrame(JFrame frameModificaConferenza, JFrame frameHome) {
		frameModificaConferenza.dispose();
		controller.tornaAllaHome(frame, frameHome);	
	}

	private void CommitModifica(String titolo, String descrizione, Conferenza updateConferenza) {
		if (!titolo.isEmpty() || !descrizione.isEmpty())
		{
			controller.commitModificaConferenza(titolo, descrizione, updateConferenza);
			JOptionPane.showMessageDialog(null,"Aggiornamento eseguito con successo!","SUCCESSO!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void RiempiTableProgrammi(Conferenza updateConferenza) {
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		//riempio il model che mostrerà i valori sullo schermo
		for(int i = 0;i< updateConferenza.programmiConferenza.size(); i++)
		{		
			model.addRow(new Object[] {updateConferenza.programmiConferenza.get(i).getCodProgramma(), sf.format(updateConferenza.programmiConferenza.get(i).getDataProgramma())});
		}	
	}
}
