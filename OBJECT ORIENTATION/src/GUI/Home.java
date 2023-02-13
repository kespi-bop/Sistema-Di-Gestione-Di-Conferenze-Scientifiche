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
		frame.setSize(1080,900);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 33, 35));
		panel.setBounds(0, 630, 1064, 231);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Duminuco&Grieco.Company©");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(56, 57, 59));
		lblNewLabel.setBounds(842, 206, 212, 14);
		panel.add(lblNewLabel);
		
		JTextPane txtpnNelCasoIn = new JTextPane();
		txtpnNelCasoIn.setSelectionColor(new Color(126, 87, 194));
		txtpnNelCasoIn.setEditable(false);
		txtpnNelCasoIn.setFont(new Font("Century Gothic", Font.BOLD, 18));
		txtpnNelCasoIn.setForeground(new Color(56, 57, 59));
		txtpnNelCasoIn.setOpaque(false);
		txtpnNelCasoIn.setText("Nel caso in cui tu sia un organizzatore di conferenze puoi loggare per:\r\n- creare una nuova conferenza\r\n- modificare conferenze esistenti\r\n- eliminare conferenze");
		txtpnNelCasoIn.setBounds(10, 11, 645, 98);
		panel.add(txtpnNelCasoIn);
		
		JButton btnNewButton_2 = new JButton("login");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(126, 87, 194));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(483, 96, 120, 50);
		panel.add(btnNewButton_2);
		
		JEditorPane dtrpnBenvenutoNelNostro = new JEditorPane();
		dtrpnBenvenutoNelNostro.setSelectionColor(new Color(128, 128, 128));
		dtrpnBenvenutoNelNostro.setDisabledTextColor(new Color(255, 255, 255));
		dtrpnBenvenutoNelNostro.setFont(new Font("Tahoma", Font.PLAIN, 19));
		dtrpnBenvenutoNelNostro.setForeground(new Color(255, 255, 255));
		dtrpnBenvenutoNelNostro.setOpaque(false);
		dtrpnBenvenutoNelNostro.setEditable(false);
		dtrpnBenvenutoNelNostro.setText("Benvenuto nel nostro programma! Rendiamo disponibili diverse operazioni:\r\n- puoi visualzzare conferenze filtrandole per data o per sede,\r\n- puoi ottenere un riepilogo mensile e annuale sulla percentuale delle istituzioni di afferenza\r\n  a cui appartengono i keynote speaker");
		dtrpnBenvenutoNelNostro.setBounds(167, 166, 782, 121);
		frame.getContentPane().add(dtrpnBenvenutoNelNostro);
		
		JButton btnNewButton = new JButton("visualizza conferenze");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(57, 113, 177));
		btnNewButton.setBounds(193, 352, 169, 77);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("riepilogoKS");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(57, 113, 177));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(700, 352, 169, 77);
		frame.getContentPane().add(btnNewButton_1);
		
		Image img = new ImageIcon(this.getClass().getResource("/BackGround.jpg")).getImage();
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(img));
		background.setBounds(0,-44,1209,674);
		frame.getContentPane().add(background);
		
	}
}
