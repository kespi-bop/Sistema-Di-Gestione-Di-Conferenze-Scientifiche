package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Home {
	
	private int mouseX, mouseY;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Home");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(450, 100, 543, 393);
		
		//definisco il pulsante di uscita
		Image imgExit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel exitLabel = new JLabel("");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		
		exitLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitLabel.setIcon(new ImageIcon(imgExit));
		exitLabel.setBounds(1053,11,17,21);
		frame.getContentPane().add(exitLabel);
		
		
		//definisco il pulsante minimize
		Image imgMinimize = new ImageIcon(this.getClass().getResource("/minimize.png")).getImage();
		
		JLabel minimizeLabel = new JLabel("");
		minimizeLabel.setOpaque(true);
		
		minimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		minimizeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizeLabel.setAutoscrolls(true);
		minimizeLabel.setIcon(new ImageIcon(imgMinimize));
		minimizeLabel.setBounds(1023, 25, 18, 3);
		frame.getContentPane().add(minimizeLabel);
		
		
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
		dragFrame.setBounds(10, 0, 1060, 52);
		frame.getContentPane().add(dragFrame);
		
		//definisco il titolo
		JLabel titleConferenze = new JLabel("CONFERENZE SCIENTIFICHE");
		titleConferenze.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 40));
		titleConferenze.setForeground(new Color(57, 113, 177));
		titleConferenze.setBounds(300, 63, 682, 142);
		frame.getContentPane().add(titleConferenze);
		
		
		JLabel background;
		frame.setSize(1080,700);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 553, 1080, 166);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//©copyright
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setFont(new Font("Tahoma", Font.PLAIN, 12));
		signature.setForeground(new Color(56, 57, 59));
		signature.setBounds(893, 110, 171, 14);
		panel.add(signature);
		
		//se premo login non posso utilzzare la finestra Home finchè non finisco di utilizzare la finestra login
		JButton loginButton = new JButton("login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginOrganizzatore log = new LoginOrganizzatore(frame);
				log.frame.setVisible(true);
				frame.setEnabled(false);	//non può essere toccata la finestra Home
			}
		});
		loginButton.setFocusPainted(false);
		
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		loginButton.setBorder(null);
		loginButton.setBackground(new Color(126, 87, 194));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBounds(385, 22, 306, 50);
		panel.add(loginButton);
		
		JTextPane txtpnSoltantoGliOrganizzatori = new JTextPane();
		txtpnSoltantoGliOrganizzatori.setEditable(false);
		txtpnSoltantoGliOrganizzatori.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnSoltantoGliOrganizzatori.setForeground(new Color(56, 57, 59));
		txtpnSoltantoGliOrganizzatori.setOpaque(false);
		txtpnSoltantoGliOrganizzatori.setSelectionColor(new Color(126, 87, 194));
		txtpnSoltantoGliOrganizzatori.setText("Attenzione! Soltanto gli organizzatori possono aggiungere, cancellare o modificare conferenze");
		txtpnSoltantoGliOrganizzatori.setBounds(269, 83, 587, 69);
		panel.add(txtpnSoltantoGliOrganizzatori);
		
		JEditorPane benvenutoTxt = new JEditorPane();
		benvenutoTxt.setSelectionColor(new Color(128, 128, 128));
		benvenutoTxt.setDisabledTextColor(new Color(255, 255, 255));
		benvenutoTxt.setFont(new Font("Tahoma", Font.PLAIN, 19));
		benvenutoTxt.setForeground(new Color(255, 255, 255));
		benvenutoTxt.setOpaque(false);
		benvenutoTxt.setEditable(false);
		benvenutoTxt.setText("Benvenuto nel nostro programma! Rendiamo disponibili diverse operazioni:\r\n- puoi visualzzare conferenze filtrandole per data o per sede,\r\n- puoi ottenere un riepilogo mensile e annuale sulla percentuale delle istituzioni di afferenza\r\n  a cui appartengono i keynote speaker\r\n- aggiunta, cancellazione e modifica di una conferenza scientifica");
		benvenutoTxt.setBounds(167, 166, 838, 131);
		frame.getContentPane().add(benvenutoTxt);
		
		JButton VisualizzaConferenzeButton = new JButton("Visualizza conferenze");
		VisualizzaConferenzeButton.setFocusPainted(false);
		VisualizzaConferenzeButton.setForeground(new Color(255, 255, 255));
		VisualizzaConferenzeButton.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		VisualizzaConferenzeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		VisualizzaConferenzeButton.setBorder(null);
		VisualizzaConferenzeButton.setBackground(new Color(57, 113, 177));
		VisualizzaConferenzeButton.setBounds(193, 363, 169, 66);
		frame.getContentPane().add(VisualizzaConferenzeButton);
		
		JButton riepilogoKSButton = new JButton("RiepilogoKS");
		riepilogoKSButton.setFocusPainted(false);
		riepilogoKSButton.setForeground(new Color(255, 255, 255));
		riepilogoKSButton.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		riepilogoKSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		riepilogoKSButton.setBackground(new Color(57, 113, 177));
		riepilogoKSButton.setBorder(null);
		riepilogoKSButton.setBounds(700, 363, 169, 66);
		frame.getContentPane().add(riepilogoKSButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/BackGround.jpg")).getImage();
	
		background = new JLabel("");
		background.setIcon(new ImageIcon(img));
		background.setBounds(0,-44,1209,597);
		frame.getContentPane().add(background);
		
	}
}
