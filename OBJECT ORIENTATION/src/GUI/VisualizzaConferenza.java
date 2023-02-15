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
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VisualizzaConferenza {

	private int mouseX, mouseY;
	private JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane scrollPane;
	private JLabel dataProgrammaLabel;
	private JTextField textField;
	private JSeparator separator_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaConferenza window = new VisualizzaConferenza();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizzaConferenza() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0));
		scrollPane.setBounds(39, 152, 425, 212);
		frame.getContentPane().add(scrollPane);
		
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
		table.setBorder(null);
		table.setBackground(new Color(32, 33, 35));
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
		dragFrame.setBounds(0, 0, 460, 44);
		frame.getContentPane().add(dragFrame);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setForeground(new Color(71, 72, 75));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setBounds(322, 375, 191, 33);
		frame.getContentPane().add(signature);
		
		dataProgrammaLabel = new JLabel("Data");
		dataProgrammaLabel.setForeground(new Color(57, 113, 177));
		dataProgrammaLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataProgrammaLabel.setBackground(new Color(57, 113, 177));
		dataProgrammaLabel.setBounds(39, 55, 48, 14);
		frame.getContentPane().add(dataProgrammaLabel);
		
		textField = new JTextField();
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setForeground(Color.WHITE);
		textField.setDisabledTextColor(Color.WHITE);
		textField.setColumns(10);
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(null);
		textField.setBackground(new Color(32, 33, 35));
		textField.setBounds(86, 55, 174, 20);
		frame.getContentPane().add(textField);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(86, 79, 174, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel sedeLabel = new JLabel("Sede");
		sedeLabel.setForeground(new Color(57, 113, 177));
		sedeLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sedeLabel.setBounds(39, 110, 48, 14);
		frame.getContentPane().add(sedeLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBorder(null);
		comboBox.setBackground(new Color(32, 33, 35));
		comboBox.setBounds(86, 107, 154, 21);
		frame.getContentPane().add(comboBox);
		
		JButton aggiornaListaConferenzeButton = new JButton("aggiorna");
		aggiornaListaConferenzeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiornaListaConferenzeButton.setForeground(Color.WHITE);
		aggiornaListaConferenzeButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiornaListaConferenzeButton.setFocusPainted(false);
		aggiornaListaConferenzeButton.setBorder(null);
		aggiornaListaConferenzeButton.setBackground(new Color(126, 87, 194));
		aggiornaListaConferenzeButton.setBounds(373, 103, 88, 26);
		frame.getContentPane().add(aggiornaListaConferenzeButton);
		
		JLabel lblNewLabel = new JLabel("filtra per data");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(71, 72, 75));
		lblNewLabel.setBounds(86, 85, 174, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFiltraPerSede = new JLabel("filtra per sede");
		lblFiltraPerSede.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFiltraPerSede.setForeground(new Color(71, 72, 75));
		lblFiltraPerSede.setBounds(86, 131, 174, 14);
		frame.getContentPane().add(lblFiltraPerSede);
		
		
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(100, 100, 513, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
