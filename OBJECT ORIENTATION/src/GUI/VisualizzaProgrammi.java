package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Seduta;
import Model.Sessione;

import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class VisualizzaProgrammi {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTable table;
	private JLabel dragFrame;
	private JLabel signature;
	private JScrollPane programmiVisualizzatiPanel;
	private JLabel lblNewLabel;
	private JPanel panel;


	public VisualizzaProgrammi(Controller controller, JFrame frameVisualizzaConferenza, String  CodProgramma) {
		initialize(controller, frameVisualizzaConferenza, CodProgramma);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller, final JFrame frameVisualizzaConferenza, String  CodProgramma) {
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
		
		programmiVisualizzatiPanel = new JScrollPane();
		programmiVisualizzatiPanel.setOpaque(false);
		programmiVisualizzatiPanel.setBounds(47, 55, 425, 259);
		panel.add(programmiVisualizzatiPanel);
		programmiVisualizzatiPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		programmiVisualizzatiPanel.setBackground(new Color(0, 0, 0));
		
		table = new JTable();
		table.setFocusable(false);
		table.getTableHeader().setReorderingAllowed(false); 
		table.setGridColor(new Color(0, 0, 0));
		table.setForeground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(126, 87, 194));
		table.setRequestFocusEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][]{},
			new String[] {
				"CodSessione","Titolo", "Inizio", "Fine",
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		programmiVisualizzatiPanel.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		
		ArrayList<Seduta> listaSedute = controller.ottieniSedute(CodProgramma);
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		if(listaSedute!=null)
			for(int i = 0;i<listaSedute.size(); i++)
				//riempiamo il model che mostrerà i valori sullo schermo
				model.addRow(new Object[] {listaSedute.get(i).getCodSeduta(), listaSedute.get(i).getTitolo(), 
						listaSedute.get(i).getOrarioInizio(), listaSedute.get(i).getOrarioFine()});
		
		
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
		panel.add(exitLabel);
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
		
		//trascino la finestra undecorated
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 454, 44);
		panel.add(dragFrame);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(322, 375, 191, 33);
		panel.add(signature);
		signature.setForeground(new Color(71, 72, 75));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
		lblNewLabel = new JLabel("Attenzione! Gli intervalli e gli eventi sociali non dispongono di una descrizione");
		lblNewLabel.setBounds(47, 318, 425, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(71, 72, 75));
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
		
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // seleziona riga
	               //se la zona clickata è una sessione, allora eseguo la riga seguente:
	               try
	               {
	            	   String titoloSessione = target.getValueAt(row, 1).toString();
	            	   String codSessione = target.getValueAt(row, 0).toString();
	            	   if(Integer.parseInt(codSessione) >= 0)
	            	   controller.visualizzaFrameDescrizione(controller, frame, titoloSessione, controller.ottieniDescrizione(codSessione));  //passo il valore della Sessione cliccata
	               }
	               catch(NullPointerException e)
	               {
	            	   JOptionPane.showMessageDialog(null,"Intervalli ed Eventi non hanno descrizioni!","ERROR:404", JOptionPane.ERROR_MESSAGE);
	               }
	               
	               
	            }
	         }
	      });
		
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(100, 100, 513, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
