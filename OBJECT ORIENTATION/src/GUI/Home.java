package GUI;

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
import Controller.Controller;

public class Home {
	
	Controller controller = new Controller();
	private int mouseX, mouseY;
	public JFrame frame;

	
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
		frame.setBounds(450, 100, 1080, 700);
		
		JPanel bcPanel = new JPanel();
		bcPanel.setBorder(null);
		bcPanel.setOpaque(false);
		bcPanel.setBounds(0, 0, 1080, 700);
		frame.getContentPane().add(bcPanel);
		
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
		bcPanel.add(exitLabel);
		
		
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
		bcPanel.add(minimizeLabel);
		
		
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
		bcPanel.add(dragFrame);
		
		//definisco il titolo
		JLabel titleConferenze = new JLabel("CONFERENZE SCIENTIFICHE");
		titleConferenze.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 40));
		titleConferenze.setForeground(new Color(57, 113, 177));
		titleConferenze.setBounds(300, 63, 682, 142);
		bcPanel.add(titleConferenze);
		
		
		JLabel background;
		bcPanel.setSize(1080,700);
		bcPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 553, 1080, 147);
		bcPanel.add(panel);
		panel.setLayout(null);
		
		//©copyright
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setFont(new Font("Tahoma", Font.PLAIN, 12));
		signature.setForeground(new Color(56, 57, 59));
		signature.setBounds(899, 116, 171, 14);
		panel.add(signature);
		
		//se premo login non posso utilzzare la finestra Home finchè non finisco di utilizzare la finestra login
		JButton loginButton = new JButton("login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginOrganizzatore log = new LoginOrganizzatore(controller, frame);
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
		loginButton.setBounds(378, 22, 313, 50);
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
		bcPanel.add(benvenutoTxt);
		
		
		//apro una nuova finestra per visualizzare le conferenze
		JButton VisualizzaConferenzeButton = new JButton("Visualizza conferenze");
		VisualizzaConferenzeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.visualizzaFrameConferenze(controller, frame);
			}
		});
		
		VisualizzaConferenzeButton.setFocusPainted(false);
		VisualizzaConferenzeButton.setForeground(new Color(255, 255, 255));
		VisualizzaConferenzeButton.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		VisualizzaConferenzeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		VisualizzaConferenzeButton.setBorder(null);
		VisualizzaConferenzeButton.setBackground(new Color(57, 113, 177));
		VisualizzaConferenzeButton.setBounds(193, 363, 169, 66);
		bcPanel.add(VisualizzaConferenzeButton);
		
		JButton riepilogoKSButton = new JButton("RiepilogoKS");
		riepilogoKSButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.visualizzaFrameRiepilogoKS(controller, frame);
			}
		});
		riepilogoKSButton.setFocusPainted(false);
		riepilogoKSButton.setForeground(new Color(255, 255, 255));
		riepilogoKSButton.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		riepilogoKSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		riepilogoKSButton.setBackground(new Color(57, 113, 177));
		riepilogoKSButton.setBorder(null);
		riepilogoKSButton.setBounds(700, 363, 169, 66);
		bcPanel.add(riepilogoKSButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/BackGround.jpg")).getImage();
	
		background = new JLabel("");
		background.setIcon(new ImageIcon(img));
		background.setBounds(0,-44,1209,597);
		frame.getContentPane().add(background);
		
		
		
	}
}
