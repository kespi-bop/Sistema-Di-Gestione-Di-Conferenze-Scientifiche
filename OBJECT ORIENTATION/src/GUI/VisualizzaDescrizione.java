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
import javax.swing.JTextPane;
import javax.swing.JPanel;
import Controller.Controller;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class VisualizzaDescrizione {

	private int mouseX, mouseY;
	public JFrame frame;
	private JLabel dragFrame;
	private JLabel signature;
	private JLabel titoloSessioneLabel;
	private JPanel panel;
	private JScrollPane scrollPane;


	public VisualizzaDescrizione(Controller controller, JFrame frameVisualizzaProgrammi, String titoloSessione,String descrizione) {
		initialize(controller, frameVisualizzaProgrammi, titoloSessione, descrizione);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Controller controller, final JFrame frameVisualizzaProgrammi, String titoloSessione, String descrizione) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 0, 513, 346);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameVisualizzaProgrammi.setVisible(true);
				frameVisualizzaProgrammi.setEnabled(true);
				frame.dispose();
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		//trascino la finestra undecorated
		dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 454, 44);
		panel.add(dragFrame);
		
		signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(322, 313, 191, 33);
		panel.add(signature);
		signature.setForeground(new Color(71, 72, 75));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		
		titoloSessioneLabel = new JLabel("");
		titoloSessioneLabel.setBounds(47, 263, 425, 14);
		panel.add(titoloSessioneLabel);
		titoloSessioneLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloSessioneLabel.setForeground(new Color(126, 87, 194));
		titoloSessioneLabel.setText("Descrizione di "+titoloSessione);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		scrollPane.setBounds(47, 55, 425, 203);
		panel.add(scrollPane);
		
		JTextPane textDescrizionePane = new JTextPane();
		textDescrizionePane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(textDescrizionePane);
		textDescrizionePane.setEditable(false);
		textDescrizionePane.setSelectionColor(new Color(126, 87, 194));
		textDescrizionePane.setBackground(new Color(32, 33, 35));
		textDescrizionePane.setForeground(new Color(255, 255, 255));
		textDescrizionePane.setText(descrizione);
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
		frame.setBounds(100, 100, 513, 348);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}