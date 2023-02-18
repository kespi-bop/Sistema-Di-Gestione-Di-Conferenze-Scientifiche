package GUI;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;

import javax.swing.border.LineBorder;
import javax.swing.JPanel;

public class CancellaProgramma {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane scrollPane;
	private JPanel panel;

	
	public CancellaProgramma(Controller controller, JFrame frameAzioniDiModifica) {
		initialize(controller, frameAzioniDiModifica);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Controller controller, final JFrame frameAzioniDiModifica) {
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
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 0));
		scrollPane.setBounds(37, 49, 378, 187);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRequestFocusEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(126, 87, 194));
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
				frameAzioniDiModifica.setEnabled(true);
				frameAzioniDiModifica.setVisible(true);
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
		
		JButton ConfermaModificaButton = new JButton("conferma");
		ConfermaModificaButton.setBounds(318, 242, 97, 26);
		panel.add(ConfermaModificaButton);
		ConfermaModificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaModificaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAzioniDiModifica.setEnabled(true);
				frameAzioniDiModifica.setVisible(true);
				frame.dispose();
			}
		});
		ConfermaModificaButton.setForeground(Color.WHITE);
		ConfermaModificaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaModificaButton.setFocusPainted(false);
		ConfermaModificaButton.setBorder(null);
		ConfermaModificaButton.setBackground(new Color(57, 113, 177));
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