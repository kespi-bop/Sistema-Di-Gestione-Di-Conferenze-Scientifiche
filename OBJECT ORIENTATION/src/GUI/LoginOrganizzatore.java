package GUI;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LoginOrganizzatore {
	
	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	
	public LoginOrganizzatore(JFrame frameHome) {
		initialize(frameHome);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final JFrame frameHome) {
		
		frame = new JFrame("Login");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.setBounds(750, 350, 450, 275);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Image imghide = new ImageIcon(this.getClass().getResource("/hide.png")).getImage();
		
		Image imgshow = new ImageIcon(this.getClass().getResource("/show.png")).getImage();
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 450, 275);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(423, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frameHome.setVisible(true);
				frameHome.setEnabled(true);
			}
		});
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 418, 40);
		panel.add(dragFrame);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(275, 242, 165, 33);
		panel.add(signature);
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setForeground(new Color(56, 57, 59));
		
		textField = new JTextField();
		textField.setBounds(87, 76, 262, 20);
		panel.add(textField);
		textField.setSelectionColor(new Color(126, 87, 194));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setCaretColor(new Color(255, 255, 255));
		textField.setDisabledTextColor(new Color(255, 255, 255));
		textField.setForeground(new Color(255, 255, 255));
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 134, 262, 20);
		panel.add(passwordField);
		passwordField.setSelectionColor(new Color(126, 87, 194));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setCaretColor(new Color(255, 255, 255));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBorder(null);
		passwordField.setOpaque(false);
		passwordField.setEchoChar('\u25cf');
		
		
		JButton loginButton = new JButton("login\r\n");
		loginButton.setBounds(87, 191, 262, 34);
		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeOrganizzatore homeOrganizzatore = new HomeOrganizzatore(frameHome);
				frame.setVisible(false);
				frameHome.setVisible(false);	//richiamo setVisible di Home affinchè non venga posta sotto altre finestre aperte
				homeOrganizzatore.frame.setVisible(true);
			}
		});
		
		
		
		loginButton.setFocusPainted(false);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		loginButton.setBackground(new Color(126, 87, 194));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBorder(null);
		
		JLabel Email = new JLabel("Email");
		Email.setBounds(87, 58, 46, 14);
		panel.add(Email);
		Email.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Email.setForeground(new Color(57, 113, 177));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(87, 96, 263, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(87, 157, 262, 2);
		panel.add(separator_1);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(87, 116, 84, 14);
		panel.add(Password);
		Password.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Password.setForeground(new Color(57, 113, 177));
		
		
		
		final JToggleButton passHide = new JToggleButton("");
		passHide.setBounds(354, 134, 21, 20);
		panel.add(passHide);
		passHide.setFocusPainted(false);
		passHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				if(passHide.isSelected())
					passwordField.setEchoChar((char)0);	//mostro la password
				else
					passwordField.setEchoChar('\u25cf');	//nascondo la password
			}
		});
		passHide.setContentAreaFilled(false);
		passHide.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		passHide.setBorderPainted(false);
		passHide.setSelectedIcon(new ImageIcon(imghide));
		passHide.setBorder(null);
		passHide.setIcon(new ImageIcon(imgshow));
		passHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("ricorda");
		chckbxNewCheckBox.setBounds(185, 161, 97, 23);
		panel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setRolloverEnabled(false);
		chckbxNewCheckBox.setRequestFocusEnabled(false);
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.setFocusable(false);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxNewCheckBox.setForeground(new Color(57, 58, 60));
		chckbxNewCheckBox.setBackground(new Color(32, 33, 35));
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
		
		
		
		frame.getContentPane().setLayout(null);
		
		
		
	}
}
