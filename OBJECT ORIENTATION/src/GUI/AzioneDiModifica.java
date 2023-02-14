package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class AzioneDiModifica {

	private int mouseX, mouseY;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AzioneDiModifica window = new AzioneDiModifica();
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
	public AzioneDiModifica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(533, 11, 17, 21);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.dispose();
				
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		frame.getContentPane().add(exitLabel);
		
		JButton cancelProgrammaLabel = new JButton("cancella programma");
		cancelProgrammaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelProgrammaLabel.setForeground(Color.WHITE);
		cancelProgrammaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cancelProgrammaLabel.setFocusPainted(false);
		cancelProgrammaLabel.setBorder(null);
		cancelProgrammaLabel.setBackground(new Color(126, 87, 194));
		cancelProgrammaLabel.setBounds(210, 244, 143, 38);
		frame.getContentPane().add(cancelProgrammaLabel);
		
		JButton aggiungiProgrammaButton = new JButton("aggiungi programma");
		//aggiungiProgrammaButton.addActionListener(new ActionListener() {
			//AggiuntaProgrammi aggiungiProgramma = new AggiuntaProgrammi();
			//}
		//});
		aggiungiProgrammaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aggiungiProgrammaButton.setForeground(Color.WHITE);
		aggiungiProgrammaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		aggiungiProgrammaButton.setFocusPainted(false);
		aggiungiProgrammaButton.setBorder(null);
		aggiungiProgrammaButton.setBackground(new Color(126, 87, 194));
		aggiungiProgrammaButton.setBounds(210, 195, 143, 38);
		frame.getContentPane().add(aggiungiProgrammaButton);
		
		
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
		dragFrame.setBounds(0, 0, 416, 44);
		frame.getContentPane().add(dragFrame);
		
		JButton ConfermaModificaButton = new JButton("conferma");
		ConfermaModificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaModificaButton.setForeground(Color.WHITE);
		ConfermaModificaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaModificaButton.setFocusPainted(false);
		ConfermaModificaButton.setBorder(null);
		ConfermaModificaButton.setBackground(new Color(57, 113, 177));
		ConfermaModificaButton.setBounds(221, 309, 125, 36);
		frame.getContentPane().add(ConfermaModificaButton);
		
		JLabel titoloLabel = new JLabel("Titolo");
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBackground(new Color(57, 113, 177));
		titoloLabel.setBounds(91, 61, 48, 14);
		frame.getContentPane().add(titoloLabel);
		
		textField = new JTextField();
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setForeground(Color.WHITE);
		textField.setDisabledTextColor(Color.WHITE);
		textField.setColumns(10);
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(null);
		textField.setBackground(new Color(32, 33, 35));
		textField.setBounds(160, 61, 256, 14);
		frame.getContentPane().add(textField);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(160, 80, 256, 2);
		frame.getContentPane().add(separator_1_1);
		
		JLabel dataInizioLabel = new JLabel("Data inizio");
		dataInizioLabel.setForeground(new Color(57, 113, 177));
		dataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataInizioLabel.setBounds(91, 93, 66, 14);
		frame.getContentPane().add(dataInizioLabel);
		
		textField_1 = new JTextField();
		textField_1.setSelectionColor(new Color(126, 87, 194));
		textField_1.setForeground(Color.WHITE);
		textField_1.setDisabledTextColor(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setCaretColor(Color.WHITE);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(32, 33, 35));
		textField_1.setBounds(160, 93, 256, 14);
		frame.getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setSelectionColor(new Color(126, 87, 194));
		textField_3.setForeground(Color.WHITE);
		textField_3.setDisabledTextColor(Color.WHITE);
		textField_3.setColumns(10);
		textField_3.setCaretColor(Color.WHITE);
		textField_3.setBorder(null);
		textField_3.setBackground(new Color(32, 33, 35));
		textField_3.setBounds(160, 125, 256, 14);
		frame.getContentPane().add(textField_3);
		
		JLabel dataFineLabel = new JLabel("Data fine");
		dataFineLabel.setForeground(new Color(57, 113, 177));
		dataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dataFineLabel.setBounds(91, 125, 66, 14);
		frame.getContentPane().add(dataFineLabel);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(160, 111, 256, 2);
		frame.getContentPane().add(separator_1_1_1);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setBounds(160, 142, 256, 2);
		frame.getContentPane().add(separator_1_1_2);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setBounds(385, 374, 165, 33);
		frame.getContentPane().add(signature);
		
		
		frame.setBounds(100, 100, 560, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
