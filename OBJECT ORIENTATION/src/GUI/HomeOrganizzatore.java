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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Controller.Controller;

public class HomeOrganizzatore {
	
	Controller controller = new Controller();
	private int mouseX, mouseY;
	JFrame frame;


	public HomeOrganizzatore(JFrame frameHome) {
		initialize(frameHome);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final JFrame frameHome) {
		
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
		signature.setBounds(899, 116, 171, 14);
		panel.add(signature);
		
		//se premo login non posso utilzzare la finestra Home finchè non finisco di utilizzare la finestra login
		JButton modificaConferenzaButton = new JButton("Modifica conferenza");
		modificaConferenzaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.vediModificaConferenza(controller, frame);
			}
		});
		
		modificaConferenzaButton.setFocusPainted(false);
		
		modificaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modificaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		modificaConferenzaButton.setBorder(null);
		modificaConferenzaButton.setBackground(new Color(126, 87, 194));
		modificaConferenzaButton.setForeground(new Color(255, 255, 255));
		modificaConferenzaButton.setBounds(378, 22, 313, 50);
		panel.add(modificaConferenzaButton);
		
		JTextPane txtpnSoltantoGliOrganizzatori = new JTextPane();
		txtpnSoltantoGliOrganizzatori.setEditable(false);
		txtpnSoltantoGliOrganizzatori.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnSoltantoGliOrganizzatori.setForeground(new Color(56, 57, 59));
		txtpnSoltantoGliOrganizzatori.setOpaque(false);
		txtpnSoltantoGliOrganizzatori.setSelectionColor(new Color(126, 87, 194));
		txtpnSoltantoGliOrganizzatori.setText("Attenzione! Soltanto gli organizzatori possono aggiungere, cancellare o modificare conferenze");
		txtpnSoltantoGliOrganizzatori.setBounds(269, 83, 587, 50);
		panel.add(txtpnSoltantoGliOrganizzatori);
		
		JButton creaConferenzaButton = new JButton("Crea conferenza");
		creaConferenzaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.vediCreazioneConferenza(controller, frame);
			}
		});
		creaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creaConferenzaButton.setForeground(Color.WHITE);
		creaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		creaConferenzaButton.setFocusPainted(false);
		creaConferenzaButton.setBorder(null);
		creaConferenzaButton.setBackground(new Color(126, 87, 194));
		creaConferenzaButton.setBounds(23, 22, 313, 50);
		panel.add(creaConferenzaButton);
		
		JButton cancellaConferenzaButton = new JButton("Cancella conferenza");
		cancellaConferenzaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.vediCancellaConferenza(controller, frame);
			}
		});
		cancellaConferenzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancellaConferenzaButton.setForeground(Color.WHITE);
		cancellaConferenzaButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cancellaConferenzaButton.setFocusPainted(false);
		cancellaConferenzaButton.setBorder(null);
		cancellaConferenzaButton.setBackground(new Color(126, 87, 194));
		cancellaConferenzaButton.setBounds(742, 22, 313, 50);
		panel.add(cancellaConferenzaButton);
		
		final JLabel logoutLabel = DefaultComponentFactory.getInstance().createLabel("Logout");
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutLabel.setText("<html><u>Logout </u></html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				logoutLabel.setText("Logout");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				frame.setVisible(false);
				frameHome.setVisible(true);
				frameHome.setEnabled(true);
			}
		});
		logoutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logoutLabel.setForeground(new Color(80, 81, 84));
		logoutLabel.setBounds(23, 104, 85, 39);
		panel.add(logoutLabel);
		
		JEditorPane benvenutoTxt = new JEditorPane();
		benvenutoTxt.setSelectionColor(new Color(128, 128, 128));
		benvenutoTxt.setDisabledTextColor(new Color(255, 255, 255));
		benvenutoTxt.setFont(new Font("Tahoma", Font.PLAIN, 19));
		benvenutoTxt.setForeground(new Color(255, 255, 255));
		benvenutoTxt.setOpaque(false);
		benvenutoTxt.setEditable(false);
		benvenutoTxt.setText("Benvenuto Mario Rossi!\r\n\r\n");
		benvenutoTxt.setBounds(433, 173, 311, 131);
		frame.getContentPane().add(benvenutoTxt);
		
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
		frame.getContentPane().add(VisualizzaConferenzeButton);
		
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
		frame.getContentPane().add(riepilogoKSButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/BackGround.jpg")).getImage();
	
		background = new JLabel("");
		background.setIcon(new ImageIcon(img));
		background.setBounds(0,-44,1209,597);
		frame.getContentPane().add(background);
		
	}
}
