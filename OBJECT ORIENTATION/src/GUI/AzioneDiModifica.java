package GUI;

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

import Controller.Controller;

import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AzioneDiModifica {

	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;

	
	public AzioneDiModifica(Controller controller,JFrame frameModificaConferenza, JFrame frameHome, Object programma) {
		initialize( controller, frameModificaConferenza, frameHome, programma);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Controller controller,final JFrame frameModificaConferenza, final JFrame frameHome, Object programma) {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.getContentPane().setLayout(null);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 560, 407);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(533, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameModificaConferenza.setEnabled(true);
				frameModificaConferenza.setVisible(true);
				frame.dispose();
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		JButton cancelProgrammaLabel = new JButton("cancella programma");
		cancelProgrammaLabel.setBounds(210, 244, 143, 38);
		panel.add(cancelProgrammaLabel);
		cancelProgrammaLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.vediCancellaProgramma(controller, frame);
			}
		});
		cancelProgrammaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelProgrammaLabel.setForeground(Color.WHITE);
		cancelProgrammaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cancelProgrammaLabel.setFocusPainted(false);
		cancelProgrammaLabel.setBorder(null);
		cancelProgrammaLabel.setBackground(new Color(126, 87, 194));
		
		JButton aggiungiProgrammaButton = new JButton("aggiungi programma");
		aggiungiProgrammaButton.setBounds(210, 195, 143, 38);
		panel.add(aggiungiProgrammaButton);
		aggiungiProgrammaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.vediCreazioneProgrammaEdit(controller, frame);
			}
		});
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
		
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 523, 44);
		panel.add(dragFrame);
		
		JButton ConfermaModificaButton = new JButton("conferma");
		ConfermaModificaButton.setBounds(221, 309, 125, 36);
		panel.add(ConfermaModificaButton);
		ConfermaModificaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameModificaConferenza.dispose();
				controller.tornaAllaHome(controller, frame, frameHome);
			}
		});
		ConfermaModificaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ConfermaModificaButton.setForeground(Color.WHITE);
		ConfermaModificaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ConfermaModificaButton.setFocusPainted(false);
		ConfermaModificaButton.setBorder(null);
		ConfermaModificaButton.setBackground(new Color(57, 113, 177));
		
		JLabel titoloLabel = new JLabel("Titolo");
		titoloLabel.setBounds(91, 61, 48, 14);
		panel.add(titoloLabel);
		titoloLabel.setForeground(new Color(57, 113, 177));
		titoloLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titoloLabel.setBackground(new Color(57, 113, 177));
		
		textField = new JTextField();
		textField.setBounds(160, 61, 256, 14);
		panel.add(textField);
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setForeground(Color.WHITE);
		textField.setDisabledTextColor(Color.WHITE);
		textField.setColumns(10);
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(null);
		textField.setBackground(new Color(32, 33, 35));
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(160, 80, 256, 2);
		panel.add(separator_1_1);
		
		JLabel dataInizioLabel = new JLabel("Data inizio");
		dataInizioLabel.setBounds(91, 93, 66, 14);
		panel.add(dataInizioLabel);
		dataInizioLabel.setForeground(new Color(57, 113, 177));
		dataInizioLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		textField_1 = new JTextField();
		textField_1.setBounds(160, 93, 256, 14);
		panel.add(textField_1);
		textField_1.setSelectionColor(new Color(126, 87, 194));
		textField_1.setForeground(Color.WHITE);
		textField_1.setDisabledTextColor(Color.WHITE);
		textField_1.setColumns(10);
		textField_1.setCaretColor(Color.WHITE);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(32, 33, 35));
		
		textField_3 = new JTextField();
		textField_3.setBounds(160, 125, 256, 14);
		panel.add(textField_3);
		textField_3.setSelectionColor(new Color(126, 87, 194));
		textField_3.setForeground(Color.WHITE);
		textField_3.setDisabledTextColor(Color.WHITE);
		textField_3.setColumns(10);
		textField_3.setCaretColor(Color.WHITE);
		textField_3.setBorder(null);
		textField_3.setBackground(new Color(32, 33, 35));
		
		JLabel dataFineLabel = new JLabel("Data fine");
		dataFineLabel.setBounds(91, 125, 66, 14);
		panel.add(dataFineLabel);
		dataFineLabel.setForeground(new Color(57, 113, 177));
		dataFineLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(160, 111, 256, 2);
		panel.add(separator_1_1_1);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setBounds(160, 142, 256, 2);
		panel.add(separator_1_1_2);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(385, 374, 165, 33);
		panel.add(signature);
		signature.setForeground(new Color(56, 57, 59));
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
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
		
		
		frame.setBounds(100, 100, 560, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
