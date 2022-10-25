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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Client_UpperPanel extends JPanel {
	Client parent;
	JLabel label1 = new JLabel("请输入服务器地址： ", SwingConstants.RIGHT);
	TextField Tf1 = new TextField();
	JLabel label2 = new JLabel("端口:  ", SwingConstants.RIGHT);
	TextField Tf2 = new TextField();

	public Client_UpperPanel(Client parent) {
		this.parent = parent;
		Tf1.setFont(new Font("黑体", Font.PLAIN, 15));
		Tf2.setFont(new Font("黑体", Font.PLAIN, 15));
		this.setLayout(new GridLayout(1, 4));
		this.add(label1);
		this.add(Tf1);
		this.add(label2);
		this.add(Tf2);
	}
}

class Client_SendArea extends JPanel {
	Client parent;
	TextArea Ta = new TextArea();

	public Client_SendArea(Client parent) {
		this.parent = parent;
		Ta.setEditable(true);
		Ta.setBackground(Color.WHITE);
		this.add(Ta);
	}
}

class Client_CentrePanel extends JPanel {
	Client parent;
	JButton send = new JButton("发送");

	public Client_CentrePanel(Client parent) {
		this.parent = parent;
		this.add(send);
	}
}

class Client_ReceiveArea extends JPanel {
	Client parent;
	TextArea Ta = new TextArea();

	public Client_ReceiveArea(Client parent) {
		this.parent = parent;
		Ta.setEditable(false);
		Ta.setBackground(Color.WHITE);
		this.add(Ta);
	}
}

class Client_Bottom extends JPanel {
	public JLabel label;

	public Client_Bottom(String str) {
		label = new JLabel(str);
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.WEST);
	}
}

public class Client {
	private JFrame client;
	private Client_UpperPanel cu;
	private Client_SendArea cs;
	private Client_CentrePanel cc;
	private Client_ReceiveArea cr;
	private Client_Bottom cb;
	private String address;
	private String client_ip;
	private String bottom_str;

	public void Init(String ip, String name) throws Exception {
		client.setTitle(name);
		address = cu.Tf1.getText();
		client_ip = cu.Tf2.getText();
		if (!client_ip.equals(ip)) {
			cr.Ta.setText("连接服务器失败,请输入正确的ip地址 ");
			return;
		}
		cr.Ta.setText("连接服务器" + address + ":" + client_ip + "成功！" + "\n");
		cb.label.setText("连接服务器" + address + ":" + client_ip + "成功！");
		DatagramSocket ds = new DatagramSocket(Integer.parseInt(ip) + 1);
		new Thread() {
			public void run() {
				while (true) {
					try {
						byte[] buff = new byte[1000];
						DatagramPacket dp = new DatagramPacket(buff, buff.length);
						ds.receive(dp);
						String s = new String(buff, 0, dp.getLength());
						cr.Ta.append(s);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}.start();
		cc.send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String content = name + ":";
					content += cs.Ta.getText();
					content += "\n";
					String send_Content = "发送：";
					send_Content += cs.Ta.getText();
					send_Content += "\n" + "成功" + "\n";
					cr.Ta.append(send_Content);
					byte[] buff = content.getBytes();
					DatagramPacket packet;
					packet = new DatagramPacket(buff, buff.length, InetAddress.getByName("localhost"),
							Integer.parseInt(ip));
					ds.send(packet);
					cs.Ta.setText("");
				} catch (NumberFormatException | IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
	}

	public Client(String name) throws Exception {
		client = new JFrame();
		cu = new Client_UpperPanel(this);
		cs = new Client_SendArea(this);
		cc = new Client_CentrePanel(this);
		cr = new Client_ReceiveArea(this);
		cb = new Client_Bottom("");
		client.setLayout(new FlowLayout(FlowLayout.CENTER));
		client.add(cu);
		client.add(cs);
		client.add(cc);
		client.add(cr);
		client.add(cb);
		for (int i = 0; i < 30; i++) {
			JLabel temp = new JLabel("          ");
			client.add(temp);
		}
		client.setLocation(860, 300);
		client.setSize(640, 530);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setVisible(true);
	}
}
