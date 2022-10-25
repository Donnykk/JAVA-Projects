package 网络传输;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class UpperPanel extends JPanel {
	Server parent;
	JLabel label = new JLabel("请输入服务端口号：", SwingConstants.RIGHT);
	TextField Tf = new TextField();
	JButton start = new JButton("Start");

	public UpperPanel(Server parent) {
		this.parent = parent;
		Tf.setFont(new Font("黑体", Font.PLAIN, 15));
		this.setLayout(new GridLayout(1, 3));
		this.add(label);
		this.add(Tf);
		this.add(start);
	}
}

class SendArea extends JPanel {
	Server parent;
	TextArea Ta = new TextArea();

	public SendArea(Server parent) {
		this.parent = parent;
		Ta.setEditable(true);
		Ta.setBackground(Color.WHITE);
		this.add(Ta);
	}
}

class CentrePanel extends JPanel {
	Server parent;
	JButton send = new JButton("发送至");

	public CentrePanel(Server parent) {
		this.parent = parent;
		this.add(send);
	}
}

class ReceiveArea extends JPanel {
	Server parent;
	TextArea Ta = new TextArea();

	public ReceiveArea(Server parent) {
		this.parent = parent;
		Ta.setEditable(false);
		Ta.setBackground(Color.WHITE);
		this.add(Ta);
	}
}

class Bottom extends JPanel {
	public JLabel label;

	public Bottom(String str) {
		label = new JLabel(str);
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.WEST);
	}
}

public class Server {
	private JFrame server;
	private UpperPanel up;
	private SendArea sa;
	private CentrePanel cp;
	private JComboBox<String> cb;
	private ReceiveArea ra;
	private Bottom label;
	private static String[] clients = { "Client A", "Client B", "Client C" };
	public String words;
	public String ip_num;
	public String selected_Client = clients[0];

	public Server() throws Exception {
		server = new JFrame("Server");
		up = new UpperPanel(this);
		sa = new SendArea(this);
		cp = new CentrePanel(this);
		cb = new JComboBox<String>(clients);
		ra = new ReceiveArea(this);
		label = new Bottom(words);
		server.setLayout(new FlowLayout(FlowLayout.CENTER));
		server.add(up);
		server.add(sa);
		server.add(cp);
		server.add(cb);
		server.add(ra);
		server.add(label);
		for (int i = 0; i < 30; i++) {
			JLabel temp = new JLabel("          ");
			server.add(temp);
		}
		server.setLocation(200, 300);
		server.setSize(640, 530);
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.setVisible(true);

		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox j = (JComboBox) e.getSource();
				selected_Client = (String) j.getSelectedItem();
			}

		});

		Client Client_A = new Client(selected_Client);
		
		up.start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ip_num = up.Tf.getText();
					words = new String("服务启动，端口为" + ip_num);
					label.label.setText(words);
					if (up.start.getText() == "Start") {
						up.start.setText("Stop");
						Connect(ip_num);
						Client_A.Init(ip_num, selected_Client);
					} else {
						up.start.setText("Start");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void Connect(String ip) throws Exception {
		DatagramSocket ds = new DatagramSocket(Integer.parseInt(ip));
		ra.Ta.setText(words + "\n");
		new Thread() {
			public void run() {
				while (true) {
					try {
						byte[] buff = new byte[1000];
						DatagramPacket dp = new DatagramPacket(buff, buff.length);
						ds.receive(dp);
						String s = new String(buff, 0, dp.getLength());
						ra.Ta.append(s);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		cp.send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String content = "ChatSocket:";
					content += sa.Ta.getText();
					content += "\n";
					String send_Content = "向" + selected_Client + "发送：";
					send_Content += sa.Ta.getText();
					send_Content += "\n" + "成功" + "\n";
					ra.Ta.append(send_Content);
					byte[] buff = content.getBytes();
					DatagramPacket packet;
					packet = new DatagramPacket(buff, buff.length, InetAddress.getByName("localhost"),
							Integer.parseInt(ip) + 1);
					ds.send(packet);
					sa.Ta.setText("");
				} catch (NumberFormatException | IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {
		Server mainServer = new Server();
	}
}
