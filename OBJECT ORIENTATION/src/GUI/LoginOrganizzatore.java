package GUI;

import java.awt.EventQueue;
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
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.ComponentOrientation;

public class LoginOrganizzatore {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginOrganizzatore window = new LoginOrganizzatore();
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
	public LoginOrganizzatore() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 33, 35));
		frame.setBounds(750, 350, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		signature.setForeground(new Color(56, 57, 59));
		signature.setBounds(259, 228, 165, 33);
		frame.getContentPane().add(signature);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setCaretColor(new Color(255, 255, 255));
		textField.setDisabledTextColor(new Color(255, 255, 255));
		textField.setForeground(new Color(255, 255, 255));
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setBounds(87, 47, 262, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setCaretColor(new Color(255, 255, 255));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBorder(null);
		passwordField.setOpaque(false);
		passwordField.setBounds(87, 126, 262, 20);
		passwordField.setEchoChar('\u25cf');
		frame.getContentPane().add(passwordField);
		
		
		JButton btnNewButton = new JButton("login\r\n");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		btnNewButton.setBackground(new Color(126, 87, 194));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(87, 170, 262, 34);
		frame.getContentPane().add(btnNewButton);
		
		JLabel Email = new JLabel("Email");
		Email.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Email.setForeground(new Color(57, 113, 177));
		Email.setBounds(87, 24, 46, 14);
		frame.getContentPane().add(Email);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(86, 78, 263, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(87, 157, 262, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Password.setForeground(new Color(57, 113, 177));
		Password.setBounds(87, 105, 84, 14);
		frame.getContentPane().add(Password);
		
		Image imghide = new ImageIcon(this.getClass().getResource("/hide.png")).getImage();
		
		Image imgshow = new ImageIcon(this.getClass().getResource("/show.png")).getImage();
		
		
		
		final JToggleButton passHide = new JToggleButton("");
		passHide.setFocusPainted(false);
		passHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(passHide.isSelected())
					passwordField.setEchoChar((char)0);
				else
					passwordField.setEchoChar('\u25cf');
			}
		});
		passHide.setContentAreaFilled(false);
		passHide.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		passHide.setBorderPainted(false);
		passHide.setSelectedIcon(new ImageIcon(imghide));
		passHide.setBorder(null);
		passHide.setBounds(354, 139, 21, 20);
		passHide.setIcon(new ImageIcon(imgshow));
		frame.getContentPane().add(passHide);
		passHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}
}
