package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import Controller.Controller;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ModificaConferenza {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane scrollPane;
	private JPanel panel;

	
	public ModificaConferenza(Controller controller, JFrame frameHome) {
		initialize(controller, frameHome);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller, final JFrame frameHome) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 450, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 49, 378, 192);
		panel.add(scrollPane);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 0));
		scrollPane.setBackground(new Color(0, 0, 0));
		
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // seleziona riga
	               int column = target.getSelectedColumn(); // seleziona colonna
	               controller.vediAzioniDiModifica(controller, frame, frameHome, table.getValueAt(row, column));  //passo il valore del Programma cliccato
	            }
	         }
	      });
		
		table.setRequestFocusEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"New column", "New column", "New column"
				}
			) {
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
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(423, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(controller, frame, frameHome);
				frame.dispose();
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
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
				mouseY = e.getY();			}
		});
		
		
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
