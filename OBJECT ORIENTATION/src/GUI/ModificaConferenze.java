package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificaConferenze {

	private int mouseX, mouseY;
	public JFrame frame;
	/**
	 * Create the application.
	 */
	public ModificaConferenze(JFrame framechiamante) {
		initialize(framechiamante);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final JFrame framechiamante) {
		
		
		frame = new JFrame("Login");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.setBounds(750, 350, 450, 275);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(423, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				framechiamante.setVisible(true);
				framechiamante.setEnabled(true);
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		frame.getContentPane().add(exitLabel);
		
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
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
		dragFrame.setBounds(0, 0, 418, 40);
		frame.getContentPane().add(dragFrame);
		
		
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setForeground(new Color(56, 57, 59));
		signature.setBounds(275, 242, 165, 33);
		frame.getContentPane().add(signature);
		
		
		JButton cancellaConferenzaButton = new JButton("cancella conferenza");
		cancellaConferenzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancellaConferenzaButton.setFocusPainted(false);
		cancellaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancellaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		cancellaConferenzaButton.setBackground(new Color(126, 87, 194));
		cancellaConferenzaButton.setForeground(new Color(255, 255, 255));
		cancellaConferenzaButton.setBorder(null);
		cancellaConferenzaButton.setBounds(111, 194, 224, 34);
		frame.getContentPane().add(cancellaConferenzaButton);
		
		JButton modificaConferenzaButton = new JButton("modifica conferenza");
		modificaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaConferenzaButton.setForeground(Color.WHITE);
		modificaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		modificaConferenzaButton.setFocusPainted(false);
		modificaConferenzaButton.setBorder(null);
		modificaConferenzaButton.setBackground(new Color(126, 87, 194));
		modificaConferenzaButton.setBounds(111, 140, 224, 34);
		frame.getContentPane().add(modificaConferenzaButton);
		
		JButton creaConferenzaButton = new JButton("crea conferenza");
		creaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaConferenzaButton.setForeground(Color.WHITE);
		creaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		creaConferenzaButton.setFocusPainted(false);
		creaConferenzaButton.setBorder(null);
		creaConferenzaButton.setBackground(new Color(126, 87, 194));
		creaConferenzaButton.setBounds(111, 89, 224, 33);
		frame.getContentPane().add(creaConferenzaButton);
		
		JLabel benvenutoLabel = new JLabel("Benvenuto!");
		benvenutoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		benvenutoLabel.setForeground(new Color(255, 255, 255));
		benvenutoLabel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		benvenutoLabel.setBounds(185, 42, 93, 25);
		frame.getContentPane().add(benvenutoLabel);
		
		
		
	}
}
