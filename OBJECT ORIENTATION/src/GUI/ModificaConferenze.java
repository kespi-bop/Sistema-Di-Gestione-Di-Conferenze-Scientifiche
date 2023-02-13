package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;

public class ModificaConferenze {

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
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.setBounds(750, 350, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        framechiamante.setEnabled(true);    
		    }
		});
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setForeground(new Color(56, 57, 59));
		signature.setBounds(259, 228, 165, 33);
		frame.getContentPane().add(signature);
		
		
		JButton cancellaConferenzaButton = new JButton("cancella conferenza");
		cancellaConferenzaButton.setFocusPainted(false);
		cancellaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancellaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		cancellaConferenzaButton.setBackground(new Color(126, 87, 194));
		cancellaConferenzaButton.setForeground(new Color(255, 255, 255));
		cancellaConferenzaButton.setBorder(null);
		cancellaConferenzaButton.setBounds(111, 170, 224, 34);
		frame.getContentPane().add(cancellaConferenzaButton);
		
		JButton modificaConferenzaButton = new JButton("modifica conferenza");
		modificaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaConferenzaButton.setForeground(Color.WHITE);
		modificaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		modificaConferenzaButton.setFocusPainted(false);
		modificaConferenzaButton.setBorder(null);
		modificaConferenzaButton.setBackground(new Color(126, 87, 194));
		modificaConferenzaButton.setBounds(111, 115, 224, 34);
		frame.getContentPane().add(modificaConferenzaButton);
		
		JButton creaConferenzaButton = new JButton("crea conferenza");
		creaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaConferenzaButton.setForeground(Color.WHITE);
		creaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		creaConferenzaButton.setFocusPainted(false);
		creaConferenzaButton.setBorder(null);
		creaConferenzaButton.setBackground(new Color(126, 87, 194));
		creaConferenzaButton.setBounds(111, 60, 224, 33);
		frame.getContentPane().add(creaConferenzaButton);
		
		JLabel benvenutoLabel = new JLabel("Benvenuto!");
		benvenutoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		benvenutoLabel.setForeground(new Color(255, 255, 255));
		benvenutoLabel.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		benvenutoLabel.setBounds(179, 21, 93, 25);
		frame.getContentPane().add(benvenutoLabel);
		
	}
}
