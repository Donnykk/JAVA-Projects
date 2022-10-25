package Chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu {
	private static StartMenu instance;
	private JFrame jf;
	private ImageIcon icon;
	private JLabel label;
	private JPanel contentPane;

	public static StartMenu getInstance(String port) throws IOException {
		if (instance == null) {
			instance = new StartMenu(port);
		}
		return instance;
	}

	public StartMenu(String port) throws IOException {
		Init(port);
	}

	@SuppressWarnings("removal")
	public void Init(String port) throws IOException {
		jf = new JFrame("五子棋");
		icon = new ImageIcon("src/img/Cover.jpg");
		label = new JLabel(icon);
		label.setSize(icon.getIconWidth(), icon.getIconHeight());
		// 其余分层设为透明
		contentPane = (JPanel) jf.getContentPane();
		contentPane.setOpaque(false);
		Font f = new Font("隶书", Font.BOLD, 20);
		JButton startGame = new JButton("开始游戏");
		startGame.setBorderPainted(false);
		startGame.setContentAreaFilled(false);
		startGame.setFont(f);
		startGame.setForeground(Color.WHITE);
		jf.setLayout(null);
		startGame.setBounds(354, 393, 118, 40);
		contentPane.add(startGame);

		// 开始游戏，连接服务器
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jf.setVisible(false);
					View.getInstance(port);
					Connector.getInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		jf.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 置于底层
		jf.setSize(icon.getIconWidth(), icon.getIconHeight());
		jf.setLocation(500, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
