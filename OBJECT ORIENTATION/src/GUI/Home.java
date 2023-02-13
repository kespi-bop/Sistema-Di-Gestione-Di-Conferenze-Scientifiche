package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTextPane;

public class Home {

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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 543, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titleConferenze = new JLabel("CONFERENZE SCIENTIFICHE");
		titleConferenze.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 40));
		titleConferenze.setForeground(new Color(57, 113, 177));
		titleConferenze.setBounds(300, 63, 528, 142);
		frame.getContentPane().add(titleConferenze);
		
		JLabel background;
		frame.setSize(1080,850);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 630, 1064, 231);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel signature = new JLabel("Duminuco&Grieco.Company©");
		signature.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		signature.setForeground(new Color(56, 57, 59));
		signature.setBounds(842, 160, 212, 14);
		panel.add(signature);
		
		JTextPane loginTxt = new JTextPane();
		loginTxt.setSelectionColor(new Color(126, 87, 194));
		loginTxt.setEditable(false);
		loginTxt.setFont(new Font("Century Gothic", Font.BOLD, 18));
		loginTxt.setForeground(new Color(56, 57, 59));
		loginTxt.setOpaque(false);
		loginTxt.setText("Nel caso in cui tu sia un organizzatore di conferenze puoi loggare per:\r\n- creare una nuova conferenza\r\n- modificare conferenze esistenti\r\n- eliminare conferenze");
		loginTxt.setBounds(10, 11, 645, 98);
		panel.add(loginTxt);
		
		JButton loginButton = new JButton("login");
		
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		loginButton.setBorder(null);
		loginButton.setBackground(new Color(126, 87, 194));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBounds(483, 96, 120, 50);
		panel.add(loginButton);
		
		JEditorPane benvenutoTxt = new JEditorPane();
		benvenutoTxt.setSelectionColor(new Color(128, 128, 128));
		benvenutoTxt.setDisabledTextColor(new Color(255, 255, 255));
		benvenutoTxt.setFont(new Font("Tahoma", Font.PLAIN, 19));
		benvenutoTxt.setForeground(new Color(255, 255, 255));
		benvenutoTxt.setOpaque(false);
		benvenutoTxt.setEditable(false);
		benvenutoTxt.setText("Benvenuto nel nostro programma! Rendiamo disponibili diverse operazioni:\r\n- puoi visualzzare conferenze filtrandole per data o per sede,\r\n- puoi ottenere un riepilogo mensile e annuale sulla percentuale delle istituzioni di afferenza\r\n  a cui appartengono i keynote speaker");
		benvenutoTxt.setBounds(167, 166, 782, 121);
		frame.getContentPane().add(benvenutoTxt);
		
		JButton VisualizzaConferenzeButton = new JButton("visualizza conferenze");
		VisualizzaConferenzeButton.setForeground(new Color(255, 255, 255));
		VisualizzaConferenzeButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		VisualizzaConferenzeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		VisualizzaConferenzeButton.setBorder(null);
		VisualizzaConferenzeButton.setBackground(new Color(57, 113, 177));
		VisualizzaConferenzeButton.setBounds(193, 363, 169, 66);
		frame.getContentPane().add(VisualizzaConferenzeButton);
		
		JButton riepilogoKSButton = new JButton("riepilogoKS");
		riepilogoKSButton.setForeground(new Color(255, 255, 255));
		riepilogoKSButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		riepilogoKSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		riepilogoKSButton.setBackground(new Color(57, 113, 177));
		riepilogoKSButton.setBorder(null);
		riepilogoKSButton.setBounds(700, 363, 169, 66);
		frame.getContentPane().add(riepilogoKSButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/BackGround.jpg")).getImage();
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(img));
		background.setBounds(0,-44,1209,674);
		frame.getContentPane().add(background);
		
	}
}
