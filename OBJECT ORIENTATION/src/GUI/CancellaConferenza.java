package GUI;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CancellaConferenza {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane scrollPane;
	private JButton ConfermaCancellazioneButton;
	private JPanel panel;

	
	public CancellaConferenza(Controller controller, JFrame frameHome) {
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
		scrollPane.setBounds(37, 49, 378, 185);
		panel.add(scrollPane);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		scrollPane.setBackground(new Color(0, 0, 0));
		
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
		
		ConfermaCancellazioneButton = new JButton("conferma");
		ConfermaCancellazioneButton.setBounds(318, 245, 97, 26);
		panel.add(ConfermaCancellazioneButton);
		ConfermaCancellazioneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(controller, frame, frameHome);
			}
		});
		ConfermaCancellazioneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaCancellazioneButton.setForeground(Color.WHITE);
		ConfermaCancellazioneButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaCancellazioneButton.setFocusPainted(false);
		ConfermaCancellazioneButton.setBorder(null);
		ConfermaCancellazioneButton.setBackground(new Color(57, 113, 177));
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
