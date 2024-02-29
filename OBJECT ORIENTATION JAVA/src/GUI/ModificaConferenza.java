package GUI;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.JLabel;
import Controller.Controller;
import Model.Conferenza;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;

public class ModificaConferenza {

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	private int mouseX, mouseY;
	private Controller controller;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane scrollPane;
	private JPanel panel;

	
	public ModificaConferenza(Controller controller, JFrame frameHome, ArrayList<Conferenza> listaConferenze) {
		initialize(controller, frameHome, listaConferenze);
	}

	
	private void initialize(final Controller controller, final JFrame frameHome, ArrayList<Conferenza> listaConferenze) {
		
		this.controller = controller;
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);	
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(750, 350, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 450, 300);
		panel.setLayout(null);
		frame.getContentPane().add(panel);	
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 49, 378, 192);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 0));
		scrollPane.setBackground(new Color(0, 0, 0));
		panel.add(scrollPane);	
		
		table = new JTable();	
		table.setGridColor(new Color(0, 0, 0));
		table.setRequestFocusEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(126, 87, 194));
		table.getTableHeader().setReorderingAllowed(false); 
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(32, 33, 35));		
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"Nome Conferenza", "Data Inizio", "Data Fine"
				}
			) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		scrollPane.setViewportView(table);
		RiempiTable(listaConferenze);
		
		//trascino la finestra undecorated
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 416, 44);
		panel.add(dragFrame);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(275, 267, 165, 33);
		panel.add(signature);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
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
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(423, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.setIcon(new ImageIcon(imgExit));

		
		
		
		
		//PULSANTI & LISTNERS
		
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	ModificaConferenzaSelezionata(me, listaConferenze, frameHome);
	         }
	     });
		
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(frame, frameHome);
			}
		});
	}


	private void ModificaConferenzaSelezionata(MouseEvent me, ArrayList<Conferenza> listaConferenze, JFrame frameHome) {
        if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
            JTable target = (JTable)me.getSource();
            int row = target.getSelectedRow(); // seleziona riga
            controller.ottieniProgrammi(listaConferenze.get(row));
            controller.vediAzioniDiModifica(frame, frameHome, listaConferenze.get(row));  //passo il valore del Programma cliccato
         }
	}


	private void RiempiTable(ArrayList<Conferenza> listaConferenze) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		//riempio il model che mostrerà i valori sullo schermo
		for(int i = 0;i<listaConferenze.size(); i++)
		{		
			model.addRow(new Object[] {listaConferenze.get(i).getTitoloConferenza(), sf.format(listaConferenze.get(i).getDataInizio()), sf.format(listaConferenze.get(i).getDataFine())});
		}
	}
}
