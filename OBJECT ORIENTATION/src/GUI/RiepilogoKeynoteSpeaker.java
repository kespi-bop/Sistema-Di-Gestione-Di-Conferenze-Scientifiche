package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Ente;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RiepilogoKeynoteSpeaker {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane riepilogoKSPanel;
	private JLabel AnnoLabel;
	private JButton aggiornaListaConferenzeButton_1;
	private JComboBox<String> enumeraMese;
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
		riepilogoKSPanel.setBounds(47, 153, 425, 185);
		panel.add(riepilogoKSPanel);
		riepilogoKSPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		riepilogoKSPanel.setBackground(new Color(0, 0, 0));
		

		JComboBox<String> inserisciAnnoField = new JComboBox<String>();
		inserisciAnnoField.setForeground(Color.WHITE);
		inserisciAnnoField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		inserisciAnnoField.setFocusable(false);
		inserisciAnnoField.setBorder(null);
		inserisciAnnoField.setBackground(new Color(32, 33, 35));
		inserisciAnnoField.setBounds(123, 106, 109, 21);
		panel.add(inserisciAnnoField);
		ArrayList<String> anniConfrenze = controller.ottieniAnniConferenze();
		for(String i: anniConfrenze)
			inserisciAnnoField.addItem(i);
		
		table = new JTable();
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setRequestFocusEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false); 
		table.setGridColor(new Color(0,0,0));
		table.setModel(new DefaultTableModel(
			new Object[][] {		},
			new String[] {
				"Istituzione", "Percentuale dei KS"
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
		table.setForeground(Color.WHITE);
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
				controller.tornaAllaHome(frame, frameHome);
			}
		});
		backToHomeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backToHomeButton.setForeground(Color.WHITE);
		backToHomeButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		backToHomeButton.setFocusPainted(false);
		backToHomeButton.setBorder(null);
		backToHomeButton.setBackground(new Color(126, 87, 194));
		
		AnnoLabel = new JLabel("Anno");
		AnnoLabel.setBounds(47, 110, 66, 14);
		panel.add(AnnoLabel);
		AnnoLabel.setForeground(new Color(57, 113, 177));
		AnnoLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		aggiornaListaConferenzeButton_1 = new JButton("aggiorna");
		aggiornaListaConferenzeButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
					
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				ArrayList<Ente> istituzioni = new ArrayList<Ente>();
				ArrayList<Integer> KSperEnte = controller.ottieniRiepilogoKS(istituzioni, enumeraMese.getSelectedItem().toString(), inserisciAnnoField.getSelectedItem().toString());
				Integer sommaKS = 0;
			
				for(Integer i: KSperEnte)
					sommaKS = sommaKS + i;
				for(int i = 0; i < istituzioni.size(); i++)
				{
					
					double percentuale = ((double)KSperEnte.get(i) * 100.00)/(double)sommaKS;
					String percentualeString  = String.format("%.2f%%", percentuale);
					model.addRow(new Object[] {istituzioni.get(i).getNomeIstituazione(),percentualeString});
				}
			}
		});
		aggiornaListaConferenzeButton_1.setBounds(368, 103, 88, 26);
		panel.add(aggiornaListaConferenzeButton_1);
		aggiornaListaConferenzeButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiornaListaConferenzeButton_1.setForeground(Color.WHITE);
		aggiornaListaConferenzeButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiornaListaConferenzeButton_1.setFocusPainted(false);
		aggiornaListaConferenzeButton_1.setBorder(null);
		aggiornaListaConferenzeButton_1.setBackground(new Color(126, 87, 194));
		
		enumeraMese = new JComboBox<String>();
		enumeraMese.setFont(new Font("Tahoma", Font.PLAIN, 11));
		enumeraMese.setForeground(new Color(255, 255, 255));
		enumeraMese.setFocusable(false);
		enumeraMese.setBounds(123, 72, 109, 21);
		panel.add(enumeraMese);
		enumeraMese.setBorder(null);
		enumeraMese.setBackground(new Color(32, 33, 35));
		enumeraMese.addItem("");
		for(Integer i = 1; i <= 12; i++)
			enumeraMese.addItem(i.toString());
		
		sedeLabel = new JLabel("Mese");
		sedeLabel.setBounds(47, 75, 66, 14);
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

