package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class VisualizzaProgrammi {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane programmiVisualizzatiPanel;
	private JLabel lblNewLabel;


	public VisualizzaProgrammi(Controller controller, JFrame frameVisualizzaConferenza, Object programma) {
		initialize(controller, frameVisualizzaConferenza, programma);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller, final JFrame frameVisualizzaConferenza, Object programma) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		programmiVisualizzatiPanel = new JScrollPane();
		programmiVisualizzatiPanel.setBorder(null);
		programmiVisualizzatiPanel.setBackground(new Color(0, 0, 0));
		programmiVisualizzatiPanel.setBounds(47, 55, 425, 259);
		frame.getContentPane().add(programmiVisualizzatiPanel);
		
		table = new JTable();
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setRequestFocusEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		programmiVisualizzatiPanel.setViewportView(table);
		table.setBorder(null);
		table.setBackground(new Color(32, 33, 35));
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // seleziona riga
	               int column = target.getSelectedColumn(); // seleziona colonna
	               //se la zona clickata è una sessione, allora eseguo la riga seguente:
	               controller.visualizzaFrameDescrizione(controller, frame, table.getValueAt(row, column));  //passo il valore della Sessione cliccata
	            }
	         }
	      });
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameVisualizzaConferenza.setVisible(true);
				frameVisualizzaConferenza.setEnabled(true);
				frame.dispose();
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		frame.getContentPane().add(exitLabel);
		
		//trascino la finestra undecorated
		dragFrame = new JLabel("");
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
		dragFrame.setBounds(0, 0, 454, 44);
		frame.getContentPane().add(dragFrame);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setForeground(new Color(71, 72, 75));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setBounds(322, 375, 191, 33);
		frame.getContentPane().add(signature);
		
		lblNewLabel = new JLabel("Attenzione! Gli intervalli e gli eventi sociali non dispongono di una descrizione");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(71, 72, 75));
		lblNewLabel.setBounds(47, 318, 425, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(100, 100, 513, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
