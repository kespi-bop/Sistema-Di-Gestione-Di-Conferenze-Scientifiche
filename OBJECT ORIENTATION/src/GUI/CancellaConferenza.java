package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Conferenza;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CancellaConferenza {

	public JFrame frame;
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	private DefaultTableModel model;
	private Integer mouseX, mouseY;	
	private JTable table;
	private JLabel exitLabel;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane scrollPane;
	private JButton ConfermaCancellazioneButton;
	private JPanel panel;
	private Image imgExit;

	
	public CancellaConferenza(Controller controller, JFrame frameHome, ArrayList<Conferenza> listaConferenze) {
		initialize(controller, frameHome, listaConferenze);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller, final JFrame frameHome, ArrayList<Conferenza> listaConferenze) {
		
		//SWING COMPONENTS
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 450, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 49, 378, 185);
		panel.add(scrollPane);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		scrollPane.setBackground(new Color(0, 0, 0));
		
		table = new JTable();
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(Color.WHITE);
		table.setGridColor(new Color(0, 0, 0));
		table.setRequestFocusEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titolo Conferenza", "Data Inizio", "Data Fine"
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);		
		table.getTableHeader().setReorderingAllowed(false); 
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		model = (DefaultTableModel)table.getModel();
		scrollPane.setViewportView(table);
		//riempio la tabella
		RiempiTabellaConferenze(listaConferenze);
		
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 416, 44);
		panel.add(dragFrame);
		
		imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();	
		exitLabel = new JLabel("");
		exitLabel.setBounds(423, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.setIcon(new ImageIcon(imgExit));
		panel.add(exitLabel);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(275, 267, 165, 33);			
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		panel.add(signature);
		
		ConfermaCancellazioneButton = new JButton("conferma");
		ConfermaCancellazioneButton.setBounds(318, 245, 97, 26);
		ConfermaCancellazioneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaCancellazioneButton.setForeground(Color.WHITE);
		ConfermaCancellazioneButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaCancellazioneButton.setFocusPainted(false);
		ConfermaCancellazioneButton.setBorder(null);
		ConfermaCancellazioneButton.setBackground(new Color(57, 113, 177));				
		panel.add(ConfermaCancellazioneButton);
		
		
			
		
		
		//PULSANTI & LISTNERS
		
		//pulsante di uscita	
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(controller, frame, frameHome);
			}
		});
			
		
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
				mouseY = e.getY();			
			}
		});	
			
		
		//commit della cancellazione della conferenza
		ConfermaCancellazioneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				controller.commitCancellaConferenza(listaConferenze.get(table.getSelectedRow() ));
				model.removeRow(table.getSelectedRow());
			}
		});
			
	}

	
	//RIEMPIE LA JTABLE
	private void RiempiTabellaConferenze(ArrayList<Conferenza> listaConferenze) {		
		//riempio il model che mostrerà i valori sullo schermo
		for(int i = 0;i<listaConferenze.size(); i++)
		{		
			model.addRow(new Object[] {listaConferenze.get(i).getTitoloConferenza(), sf.format(listaConferenze.get(i).getDataInizio()), sf.format(listaConferenze.get(i).getDataFine())});
		}	
	}


}
