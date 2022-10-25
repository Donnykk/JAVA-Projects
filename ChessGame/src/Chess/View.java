package Chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class UpperPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton regret;
	private JButton giveUp;
	private JButton connecting;
	private JButton review;
	private JButton restart;
	private JButton quit;
	private JLabel player_A;
	private JLabel player_B;
	private JLabel timeCounter;
	private JLabel time;
	private JLabel chance_A;
	private JLabel chance_B;
	private int lastTime;
	private int n1; // A剩余的悔棋机会
	private int n2; // B剩余的悔棋机会

	public UpperPanel() {
		lastTime = 30;
		n1 = 3;
		n2 = 3;
		this.setLayout(new GridLayout(2, 6));

		connecting = new JButton("连接");
		connecting.setFont(new Font("黑体", Font.BOLD, 20));
		connecting.setContentAreaFilled(false);

		restart = new JButton("重新开始");
		restart.setFont(new Font("黑体", Font.BOLD, 20));
		restart.setContentAreaFilled(false);

		quit = new JButton("认输");
		quit.setFont(new Font("黑体", Font.BOLD, 20));
		quit.setContentAreaFilled(false);

		review = new JButton("复盘");
		review.setFont(new Font("黑体", Font.BOLD, 20));
		review.setContentAreaFilled(false);

		regret = new JButton("悔棋");
		regret.setFont(new Font("黑体", Font.BOLD, 20));
		regret.setContentAreaFilled(false);

		giveUp = new JButton("和局");
		giveUp.setFont(new Font("黑体", Font.BOLD, 20));
		giveUp.setContentAreaFilled(false);

		timeCounter = new JLabel("倒计时:", JLabel.CENTER);
		timeCounter.setFont(new Font("黑体", Font.BOLD, 20));
		time = new JLabel(Integer.toString(lastTime) + " s", JLabel.CENTER);
		time.setFont(new Font("黑体", Font.BOLD, 20));

		player_A = new JLabel("You", JLabel.CENTER);
		player_A.setFont(new Font("黑体", Font.BOLD, 20));
		player_B = new JLabel("Enemy", JLabel.CENTER);
		player_B.setFont(new Font("黑体", Font.BOLD, 20));
		chance_A = new JLabel("剩余悔棋机会：" + n1, JLabel.CENTER);
		chance_A.setFont(new Font("黑体", Font.BOLD, 15));
		chance_B = new JLabel("剩余悔棋机会：" + n2, JLabel.CENTER);
		chance_B.setFont(new Font("黑体", Font.BOLD, 15));

		this.add(player_A);
		this.add(timeCounter);
		this.add(player_B);
		this.add(connecting);
		this.add(review);
		this.add(restart);
		this.add(chance_A);
		this.add(time);
		this.add(chance_B);
		this.add(regret);
		this.add(giveUp);
		this.add(quit);

		connecting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String targetAddress = JOptionPane.showInputDialog(null, "格式：IP:Port", "请输入对方地址",
							JOptionPane.PLAIN_MESSAGE);
					if (targetAddress == null || targetAddress.indexOf(':') == -1)
						return;
					String Port = targetAddress.substring(targetAddress.indexOf(':') + 1);
					String IP = targetAddress.substring(0, targetAddress.indexOf(':'));
					Connector.getInstance().tryConnect(IP, Port);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

		regret.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Controller.getInstance().getPlaying()) {
						if (!Controller.getInstance().getYourTurn()) {
							if (regretChessUpdate("You")) {
								Controller.getInstance().regretChess();
							} else {
								JOptionPane.showMessageDialog(null, "悔棋次数已用完！");
							}
						} else {
							JOptionPane.showMessageDialog(null, "后悔得太晚啦！");
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

		giveUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Controller.getInstance().getPlaying()) {
						Controller.getInstance().askGiveUp();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Controller.getInstance().getPlaying()) {
						if (Controller.getInstance().getColor() == Model.WHITE) {
							Connector.getInstance().sendMessage("Win:" + Model.BLACK);
							Controller.getInstance().gameOver(Model.BLACK);
						} else if (Controller.getInstance().getColor() == Model.BLACK) {
							Connector.getInstance().sendMessage("Win:" + Model.WHITE);
							Controller.getInstance().gameOver(Model.WHITE);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Controller.getInstance().getPlaying()) {
					try {
						Controller.getInstance().askRestart();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		review.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Controller.getInstance().getPlaying()) {
					LinkedList<Node> list = Controller.getInstance().getChessRecord();
					if (list == null || list.isEmpty()) {
						JOptionPane.showMessageDialog(null, "你还没下过棋呢！", "Tip", JOptionPane.DEFAULT_OPTION);
						return;
					}
					new Thread() {
						public void run() {
							try {
								Controller.getInstance().clearBoard();
								for (Node n : list) {
									sleep(1000);
									Controller.getInstance().review(n.getRow(), n.getCol(), n.getColor());
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}.start();
				}
			}

		});
	}

	public int getTime() {
		return lastTime;
	}

	public void setTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public boolean regretChessUpdate(String str) {
		if (str.equals("You") && n1 > 0) {
			n1--;
			chance_A.setText("剩余悔棋机会：" + n1);
			return true;
		} else if (str.equals("Enemy") && n2 > 0) {
			n2--;
			chance_B.setText("剩余悔棋机会：" + n2);
			return true;
		}
		return false;
	}

	public void Init() {
		n1 = 3;
		n2 = 3;
		lastTime = 30;
	}

	public void upDateTime(int remainTime) {
		lastTime = remainTime;
		time.setText(Integer.toString(lastTime));
	}

	public void changeYourTurn() {
		player_A.setForeground(Color.RED);
		player_B.setForeground(Color.BLACK);
	}

	public void changeOpponentTurn() {
		player_A.setForeground(Color.BLACK);
		player_B.setForeground(Color.RED);
	}
}

class GameArea extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean over;
	private int offset;
	private int width;
	private int height;
	private int sideLength;
	private int interval;
	private Image image;
	private Image screenShot;
	private Graphics gbuffer;

	public GameArea() {
		over = false;
		image = new ImageIcon("src/img/ChessBoard.jpg").getImage();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (over == false) {
						if (e.getComponent() == null)
							return;
						int x = e.getX();
						int y = e.getY();
						int col = getCol(x, y);
						int row = getRow(x, y);
						if (col == -1 || row == -1)
							return;
						Controller.getInstance().putChess(row, col);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void Init() {
		over = false;
		upDate();
	}
	
	public void reviewInit() {
		upDate();
	}

	public void upDate() {
		if (screenShot == null) {
			screenShot = this.createImage(this.getWidth(), this.getHeight());
			gbuffer = screenShot.getGraphics();
			painting(gbuffer);
			paint(getGraphics());
		}
		screenShot = null;
	}

	private void painting(Graphics g) {
		width = this.getWidth();
		height = this.getHeight();
		sideLength = Math.min(width, height);
		offset = sideLength / 20;
		interval = sideLength / Model.WIDTH;
		sideLength = interval * (Model.WIDTH - 1);
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setColor(Color.BLACK);
		for (int i = 0; i < Model.WIDTH; i++) {
			g.drawLine(offset, offset + i * interval, offset + sideLength, offset + i * interval);
			g.drawLine(offset + i * interval, offset, offset + i * interval, offset + sideLength);
		}
		int[][] array = Model.getInstance().getChessBoard();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				int nowX = offset + j * interval;
				int nowY = offset + i * interval;
				if (array[i][j] == Model.BLACK) {
					g.setColor(Color.BLACK);
				} else if (array[i][j] == Model.WHITE) {
					g.setColor(Color.WHITE);
				} else
					continue;
				g.fillOval(nowX - interval / 2, nowY - interval / 2, interval / 1, interval / 1);
				if (i == Controller.getInstance().getLastRow() && j == Controller.getInstance().getLastCol()) {
					g.setColor(Color.GREEN);
					g.drawOval(nowX - interval / 2 - 1, nowY - interval / 2 - 1, interval / 1 + 1, interval / 1 + 1);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		if (screenShot == null) {
			painting(g);
			return;
		}
		g.drawImage(screenShot, 0, 0, null);
	}

	public int getRow(int x, int y) {
		for (int i = 0; i < Model.WIDTH; i++) {
			for (int j = 0; j < Model.WIDTH; j++) {
				int nowX = offset + j * interval;
				int nowY = offset + i * interval;
				if (Math.abs(x - nowX) <= interval / 2 && Math.abs(y - nowY) <= interval / 2)
					return i;
			}
		}
		return -1;
	}

	public int getCol(int x, int y) {
		for (int i = 0; i < Model.WIDTH; i++) {
			for (int j = 0; j < Model.WIDTH; j++) {
				int nowX = offset + j * interval;
				int nowY = offset + i * interval;
				if (Math.abs(x - nowX) <= interval / 2 && Math.abs(y - nowY) <= interval / 2)
					return j;
			}
		}
		return -1;
	}

	public void gameOver() {
		over = true;
	}
}

class ChatRoom extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel sub_p; // 下方组合小面板
	private JTextArea Ta; // 聊天框
	private JTextField Tf; // 文字输入框
	private JScrollPane Sc;
	private JButton Jb1 = new JButton("发送");
	private JButton Jb2 = new JButton("字体");
	public JButton Jb3 = new JButton("🔈");
	private String font = "宋体";

	public ChatRoom() throws Exception {
		this.setLayout(new BorderLayout());
		sub_p = new JPanel();
		Ta = new JTextArea();
		Tf = new JTextField();
		Ta.setFont(new Font(font, Font.BOLD, 16));
		Ta.setBorder(new TitledBorder(new EtchedBorder(), "聊天室"));
		Ta.setEditable(false);
		Ta.setOpaque(false);
		Ta.setLineWrap(true); // 自动换行
		Sc = new JScrollPane(Ta);
		Sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(Sc, BorderLayout.CENTER);
		sub_p.setLayout(new BorderLayout());
		sub_p.add(Tf, BorderLayout.CENTER);
		sub_p.add(Jb1, BorderLayout.EAST);
		sub_p.add(Jb2, BorderLayout.WEST);
		this.add(sub_p, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(300, 450));

		Jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String send = Tf.getText();
				if (send.equals("")) {
					JOptionPane.showMessageDialog(null, "发送信息不能为空，请重新输入！");
					send = Tf.getText();
				} else {
					Tf.setText(null);
					showMessage("I:" + send);
					Controller.getInstance().sendMessageToOpponent(send);
				}
			}
		});

		Jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new JDialog();
				jd.setLayout(new GridLayout(5, 1));
				ButtonGroup bg = new ButtonGroup();
				JRadioButton jrb1 = new JRadioButton("幼圆");
				JRadioButton jrb2 = new JRadioButton("新宋体");
				JRadioButton jrb3 = new JRadioButton("黑体");
				JRadioButton jrb4 = new JRadioButton("华文行楷");
				JRadioButton jrb5 = new JRadioButton("华文细黑");
				bg.add(jrb1);
				bg.add(jrb2);
				bg.add(jrb3);
				bg.add(jrb4);
				bg.add(jrb5);
				jd.add(jrb1);
				jd.add(jrb2);
				jd.add(jrb3);
				jd.add(jrb4);
				jd.add(jrb5);
				jrb1.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						String font = "幼圆";
						jd.dispose();
						Font f = new Font(font, Font.BOLD, 18);
						Tf.setFont(f);
						Ta.setFont(f);
					}
				});
				jrb2.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						font = "新宋体";
						jd.dispose();
						Font f = new Font(font, Font.BOLD, 16);
						Tf.setFont(f);
						Ta.setFont(f);
					}
				});

				jrb3.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						font = "黑体";
						jd.dispose();
						Font f = new Font(font, Font.BOLD, 16);
						Tf.setFont(f);
						Ta.setFont(f);
					}
				});
				jrb4.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						font = "华文行楷";
						jd.dispose();
						Font f = new Font(font, Font.BOLD, 16);
						Tf.setFont(f);
						Ta.setFont(f);
					}
				});
				jrb5.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						font = "华文细黑";
						jd.dispose();
						Font f = new Font(font, Font.BOLD, 16);
						Tf.setFont(f);
						Ta.setFont(f);
					}
				});
				jd.setTitle("字体");
				jd.setSize(150, 200);
				jd.setLocation(885, 670);
				jd.setVisible(true);
				validate();
			}
		});
	}

	public void showMessage(String str) {
		new Thread() {
			public void run() {
				Ta.append(str + "\n");
				Ta.setCaretPosition(Ta.getText().length());
			}
		}.start();
	}
}

public class View {
	private static View instance;
	private JFrame Chess;
	private ChatRoom cr;
	private UpperPanel up;
	private GameArea ga;
	private JLabel bottom;

	private View(String port) throws Exception {
		Init(port);
	}

	public static View getInstance() throws Exception {
		return instance;
	}

	public static View getInstance(String port) throws Exception {
		if (instance == null) {
			instance = new View(port);
		}
		return instance;
	}

	public void Init(String port) throws Exception {
		Chess = new JFrame("五子棋对战");
		Chess.setLayout(new BorderLayout());
		Chess.setSize(800, 600);
		cr = new ChatRoom();
		up = new UpperPanel();
		ga = new GameArea();
		bottom = new JLabel("     port:" + port, JLabel.LEFT);
		bottom.setOpaque(true);
		bottom.setForeground(Color.WHITE);
		bottom.setBackground(Color.BLACK);
		Chess.add(up, BorderLayout.NORTH);
		Chess.add(cr, BorderLayout.EAST);
		Chess.add(ga, BorderLayout.CENTER);
		Chess.add(bottom, BorderLayout.SOUTH);
		Chess.setLocation(400, 300);
		Chess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Chess.setVisible(true);
	}

	public void showMessage(String message) {
		cr.showMessage(message);
	}

	public void upDate() {
		ga.upDate();
	}

	public void changeYourTurn() {
		up.changeYourTurn();
	}

	public void changeOpponentTurn() {
		up.changeOpponentTurn();
	}

	public void opponentRegret() {
		up.regretChessUpdate("Enemy");
		upDate();
	}

	public void gameOver(boolean uWin) {
		ga.gameOver();
		if (uWin)
			showMessage("你赢了");
		else
			showMessage("你输了");
	}

	public void giveUp() {
		ga.gameOver();
		showMessage("游戏结束，平局");
	}

	public void restartInit() {
		ga.Init();
		up.Init();
		showMessage("开始游戏!");
	}

	public void reviewInit() {
		ga.reviewInit();
	}

	public void playPutChessMusic() throws Exception {
		Clip clip;
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/music/putChess.wav"));
		DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
		clip = (Clip) AudioSystem.getLine(info);
		clip.addLineListener(new LineListener() {
			public void update(LineEvent e) {
				if (e.getType() == LineEvent.Type.STOP) {
					synchronized (clip) {
						clip.notify();
					}
				}
			}
		});
		clip.open(ais);
		clip.start();
		synchronized (clip) {
			clip.wait();
		}
		clip.close();
	}

	public void setRemainTime(int remainTime) {
		up.upDateTime(remainTime);
	}
}
