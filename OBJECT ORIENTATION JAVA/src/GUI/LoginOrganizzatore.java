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

import Controller.Controller;

public class LoginOrganizzatore {
	
	private int mouseX, mouseY;
	public JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private Controller controller;
	private JCheckBox ricordaPasswordchckBox;
	private JLabel Email;
	private JLabel Password;
	private JLabel lblNewLabel;
	private JLabel incorrectLabel;
	
	
	
	public LoginOrganizzatore(Controller controller, JFrame frameHome) {
		initialize(controller, frameHome);
	}

	private void initialize(Controller controller, JFrame frameHome) {
		
		this.controller = controller;
		frame = new JFrame("Login");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.setBounds(750, 350, 450, 275);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Image imghide = new ImageIcon(this.getClass().getResource("/hide.png")).getImage();	
		Image imgshow = new ImageIcon(this.getClass().getResource("/show.png")).getImage();
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 450, 275);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JLabel exitLabel = new JLabel("");
		exitLabel.setBounds(423, 11, 17, 21);
		panel.add(exitLabel);
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.setIcon(new ImageIcon(imgExit));
		
		//trascino la finestra undecorated
		JLabel dragFrame = new JLabel("");
		dragFrame.setBounds(0, 0, 418, 40);
		panel.add(dragFrame);
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
				mouseY = e.getY();			
			}
		});
		
		incorrectLabel= new JLabel("email o password errate!");
		incorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		incorrectLabel.setVisible(false);
		incorrectLabel.setForeground(new Color(179, 0, 0));
		incorrectLabel.setBounds(164, 230, 172, 14);
		panel.add(incorrectLabel);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setBounds(275, 242, 165, 33);
		panel.add(signature);
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setForeground(new Color(56, 57, 59));
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(87, 76, 262, 20);
		textFieldEmail.setSelectionColor(new Color(126, 87, 194));
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldEmail.setCaretColor(new Color(255, 255, 255));
		textFieldEmail.setDisabledTextColor(new Color(255, 255, 255));
		textFieldEmail.setForeground(new Color(255, 255, 255));
		textFieldEmail.setBorder(null);
		textFieldEmail.setOpaque(false);
		textFieldEmail.setColumns(10);
		panel.add(textFieldEmail);	
			
		Email = new JLabel("Email");
		Email.setBounds(87, 58, 46, 14);
		Email.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Email.setForeground(new Color(57, 113, 177));
		panel.add(Email);		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 134, 262, 20);	
		passwordField.setSelectionColor(new Color(126, 87, 194));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setCaretColor(new Color(255, 255, 255));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBorder(null);
		passwordField.setOpaque(false);
		passwordField.setEchoChar('\u25cf');
		panel.add(passwordField);
		
		Password= new JLabel("Password");
		Password.setBounds(87, 116, 84, 14);
		Password.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Password.setForeground(new Color(57, 113, 177));
		panel.add(Password);
		
		lblNewLabel = new JLabel("email o passwod errate!");
		lblNewLabel.setForeground(new Color(179, 0, 0));
		lblNewLabel.setBounds(162, 230, 187, 14);
		
		ricordaPasswordchckBox = new JCheckBox("ricorda");
		ricordaPasswordchckBox.setBounds(185, 161, 97, 23);
		ricordaPasswordchckBox.setRolloverEnabled(false);
		ricordaPasswordchckBox.setRequestFocusEnabled(false);
		ricordaPasswordchckBox.setOpaque(false);
		ricordaPasswordchckBox.setFocusable(false);
		ricordaPasswordchckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ricordaPasswordchckBox.setForeground(new Color(57, 58, 60));
		ricordaPasswordchckBox.setBackground(new Color(32, 33, 35));
		panel.add(ricordaPasswordchckBox);			
		
		JButton loginButton = new JButton("login\r\n");
		loginButton.setBounds(87, 191, 262, 34);	
		loginButton.setFocusPainted(false);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		loginButton.setBackground(new Color(126, 87, 194));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBorder(null);
		panel.add(loginButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(87, 96, 263, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(87, 157, 262, 2);
		panel.add(separator_1);
		
		JToggleButton passHide = new JToggleButton("");
		passHide.setBounds(354, 134, 21, 20);
		panel.add(passHide);
		passHide.setFocusPainted(false);	
		passHide.setContentAreaFilled(false);
		passHide.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		passHide.setBorderPainted(false);
		passHide.setSelectedIcon(new ImageIcon(imghide));
		passHide.setBorder(null);
		passHide.setIcon(new ImageIcon(imgshow));
		passHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		
		
		
		
		//PULSANTI & LISTNERS
		
		
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.tornaAllaHome(frame, frameHome);
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					RichiediAccesso(frameHome);
			}
		});

		passHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				if(passHide.isSelected())
					passwordField.setEchoChar((char)0);	//mostro la password
				else
					passwordField.setEchoChar('\u25cf');	//nascondo la password
			}
		});
	}

	
	//METODI IMPLEMENTATIVI
	private void RichiediAccesso(JFrame frameHome) {
		@SuppressWarnings("deprecation")
		String nomeLogin = controller.isCorrectCredenziali(textFieldEmail.getText(), passwordField.getText());
		//se la stringa è vuola(la select non trova alcuna corrispondenza) eseguo l'accesso
		if(!nomeLogin.isEmpty())
		{
			//se è sputata la checkBox, allora inserisco la password nel db
			if(ricordaPasswordchckBox.isSelected()) {
				controller.storeOrganizzatoreRicordato(textFieldEmail.getText());
			 }
			ImpostaNuovoFrameHome(frameHome, nomeLogin);
		}	
		else
		{
			ShowPasswordIncorrect();
		}
	}

	
	private void ImpostaNuovoFrameHome(JFrame frameHome, String nomeLogin) {
		HomeOrganizzatore homeOrganizzatore = new HomeOrganizzatore(frameHome, nomeLogin);
		frame.setVisible(false);
		frameHome.setVisible(false);	//richiamo setVisible di Home affinchè non venga posta sotto altre finestre aperte
		homeOrganizzatore.frame.setVisible(true);
	}

	
	private void ShowPasswordIncorrect() {
		Email.setText("<html><u>Email</u></html>");
		Email.setForeground(new Color(179, 0, 0));
		Password.setText("<html><u>Password</u></html>");
		Password.setForeground(new Color(179, 0, 0));
		incorrectLabel.setVisible(true);
	}
	
}
