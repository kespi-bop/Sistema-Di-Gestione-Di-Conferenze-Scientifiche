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
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class VisualizzaProgrammi {

	private int mouseX, mouseY;
	private Controller controller;
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

	private void initialize(Controller controller, JFrame frameVisualizzaConferenza, String  CodProgramma) {
		this.controller = controller;
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(800, 400, 513, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 513, 408);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		programmiVisualizzatiPanel = new JScrollPane();
		programmiVisualizzatiPanel.setOpaque(false);
		programmiVisualizzatiPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		programmiVisualizzatiPanel.setBackground(new Color(0, 0, 0));
		programmiVisualizzatiPanel.setBounds(47, 55, 425, 259);
		panel.add(programmiVisualizzatiPanel);
		
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
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(32, 33, 35));
		programmiVisualizzatiPanel.setViewportView(table);
		
		RiempiTabellaProgrammi(CodProgramma);

		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		
		
		
		//PULSANTI & LISTNERS
		
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.TornaAllaPaginaPrecedente(frame, frameVisualizzaConferenza);
			}
		});

		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 VisualizzaDescrizione(me);
	         }
	      });
	}

	
	//METODI IMPLEMENTATIVI
	private void VisualizzaDescrizione(MouseEvent me) {
		
        if (me.getClickCount() == 2) {     //se viene effettuato un doppio click in una zona
            JTable target = (JTable)me.getSource();
            int row = target.getSelectedRow(); // seleziona riga
            try
            {
         	   String titoloSessione = target.getValueAt(row, 1).toString();
         	   String codSessione = target.getValueAt(row, 0).toString();
         	   if(Integer.parseInt(codSessione) >= 0)
         	   controller.visualizzaFrameDescrizione(frame, titoloSessione, controller.ottieniDescrizione(codSessione));  //passo il valore della Sessione cliccata
            }catch(NullPointerException e){
         	   JOptionPane.showMessageDialog(null,"Intervalli ed Eventi non hanno descrizioni!","ERROR:404", JOptionPane.ERROR_MESSAGE);
            }         
         }
	}

	private void RiempiTabellaProgrammi(String codProgramma) {
		ArrayList<Seduta> listaSedute = controller.ottieniSedute(codProgramma);		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		if(listaSedute!=null)
			for(int i = 0;i<listaSedute.size(); i++)
			{
				model.addRow(new Object[] {listaSedute.get(i).getCodSeduta(), listaSedute.get(i).getTitolo(), 
						listaSedute.get(i).getOrarioInizio(), listaSedute.get(i).getOrarioFine()});
			}				
	}
}
