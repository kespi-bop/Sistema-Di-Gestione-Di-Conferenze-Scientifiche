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
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Controller.Controller;

public class VisualizzaDescrizione {

	private int mouseX, mouseY;
	public JFrame frame;
	private JLabel dragFrame;
	private JLabel signature;
	private JLabel lblNewLabel;


	public VisualizzaDescrizione(Controller controller, JFrame frameVisualizzaProgrammi, Object descrizione) {
		initialize(controller, frameVisualizzaProgrammi, descrizione);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Controller controller, final JFrame frameVisualizzaProgrammi, Object descrizione) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(486, 11, 17, 21);
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
		signature.setBounds(322, 313, 191, 33);
		frame.getContentPane().add(signature);
		
		lblNewLabel = new JLabel("Descrizione di ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(71, 72, 75));
		lblNewLabel.setBounds(47, 263, 425, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setSelectionColor(new Color(126, 87, 194));
		textPane.setBackground(new Color(32, 33, 35));
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setBounds(47, 55, 425, 203);
		frame.getContentPane().add(textPane);
		
		
		frame.setBackground(new Color(32, 33, 35));
		frame.setBounds(100, 100, 513, 348);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}