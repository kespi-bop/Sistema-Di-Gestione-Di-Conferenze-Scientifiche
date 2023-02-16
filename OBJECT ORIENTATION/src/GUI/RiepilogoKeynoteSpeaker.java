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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RiepilogoKeynoteSpeaker {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane riepilogoKSPanel;
	private JTextField inserisciAnnoField;
	private JSeparator separator_1;
	private JLabel AnnoLabel;
	private JButton aggiornaListaConferenzeButton_1;
	private JComboBox enumeraSede;
	private JLabel sedeLabel;
	private JPanel panel;


	public RiepilogoKeynoteSpeaker(Controller controller, JFrame frameHome) {
		initialize(controller, frameHome);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller, final JFrame frameHome) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 513, 408);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		riepilogoKSPanel = new JScrollPane();
		riepilogoKSPanel.setBounds(47, 129, 425, 197);
		panel.add(riepilogoKSPanel);
		riepilogoKSPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		riepilogoKSPanel.setBackground(new Color(0, 0, 0));
		
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
		riepilogoKSPanel.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameHome.setEnabled(true);
				frameHome.setVisible(true);
				frame.dispose();		
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		//trascino la finestra undecorated
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 472, 44);
		panel.add(dragFrame);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(322, 375, 191, 33);
		panel.add(signature);
		signature.setForeground(new Color(71, 72, 75));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
		JButton backToHomeButton = new JButton("back");
		backToHomeButton.setBounds(47, 349, 96, 33);
		panel.add(backToHomeButton);
		backToHomeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(controller, frame, frameHome);
			}
		});
		backToHomeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backToHomeButton.setForeground(Color.WHITE);
		backToHomeButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		backToHomeButton.setFocusPainted(false);
		backToHomeButton.setBorder(null);
		backToHomeButton.setBackground(new Color(126, 87, 194));
		
		inserisciAnnoField = new JTextField();
		inserisciAnnoField.setBounds(123, 55, 154, 20);
		panel.add(inserisciAnnoField);
		inserisciAnnoField.setSelectionColor(new Color(126, 87, 194));
		inserisciAnnoField.setForeground(Color.WHITE);
		inserisciAnnoField.setDisabledTextColor(Color.WHITE);
		inserisciAnnoField.setColumns(10);
		inserisciAnnoField.setCaretColor(Color.WHITE);
		inserisciAnnoField.setBorder(null);
		inserisciAnnoField.setBackground(new Color(32, 33, 35));
		
		separator_1 = new JSeparator();
		separator_1.setBounds(123, 75, 154, 2);
		panel.add(separator_1);
		
		AnnoLabel = new JLabel("Anno");
		AnnoLabel.setBounds(47, 58, 66, 14);
		panel.add(AnnoLabel);
		AnnoLabel.setForeground(new Color(57, 113, 177));
		AnnoLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		aggiornaListaConferenzeButton_1 = new JButton("aggiorna");
		aggiornaListaConferenzeButton_1.setBounds(384, 84, 88, 26);
		panel.add(aggiornaListaConferenzeButton_1);
		aggiornaListaConferenzeButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiornaListaConferenzeButton_1.setForeground(Color.WHITE);
		aggiornaListaConferenzeButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiornaListaConferenzeButton_1.setFocusPainted(false);
		aggiornaListaConferenzeButton_1.setBorder(null);
		aggiornaListaConferenzeButton_1.setBackground(new Color(126, 87, 194));
		
		enumeraSede = new JComboBox();
		enumeraSede.setBounds(123, 86, 154, 21);
		panel.add(enumeraSede);
		enumeraSede.setBorder(null);
		enumeraSede.setBackground(new Color(32, 33, 35));
		
		sedeLabel = new JLabel("Sede");
		sedeLabel.setBounds(47, 91, 66, 14);
		panel.add(sedeLabel);
		sedeLabel.setForeground(new Color(57, 113, 177));
		sedeLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
		frame.setBounds(100, 100, 513, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

