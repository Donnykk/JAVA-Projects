package Chess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Connector {
	private static Connector instance;
	private ServerSocket server;
	private Socket socket;
	private String port;
	private BufferedReader reader;
	private BufferedWriter writer;

	public Connector() throws Exception {
		server = new ServerSocket(0);
		port = String.valueOf(server.getLocalPort());
		waitForConnect();
	}

	public static Connector getInstance() throws Exception {
		if (instance == null) {
			instance = new Connector();
		}
		return instance;
	}

	private void waitForConnect() {
		new Thread() {
			public void run() {
				try {
					while (true) {
						Socket tempS = server.accept();
						BufferedWriter tempOut = new BufferedWriter(new OutputStreamWriter(tempS.getOutputStream()));
						System.out.println(1);
						tempOut.write("Connected\n");
						tempOut.flush();
						socket = tempS;
						reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
						Controller.getInstance().Connected(Model.WHITE);
						ProcessMessageThead();
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	protected void Connected(int color) {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			Controller.getInstance().Connected(color);
			ProcessMessageThead();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tryConnect(String iP, String port) {
		try {
			socket = new Socket("localhost", Integer.parseInt(port));
			if (socket == null)
				JOptionPane.showMessageDialog(null, "连接失败", "Over", JOptionPane.DEFAULT_OPTION);
			else {
				new Thread() {
					@Override
					public void run() {
						try {
							BufferedReader tempIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							while (true) {
								String result = tempIn.readLine();
								if (result.equals("Connected")) {
									System.out.println("同意连接");
									reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
									writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
									Controller.getInstance().Connected(Model.BLACK);
									ProcessMessageThead();
									return;
								} else {
									socket = null;
									return;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private void ProcessMessageThead() {
		new Thread() {
			public void run() {
				try {
					while (true) {
						String input = reader.readLine();
						if (input == null) {
							continue;
						}
						System.out.println(input);
						if (input.startsWith("Chess:")) {
							String vars = input.substring(input.indexOf(':') + 1);
							int row = Integer.parseInt(vars.substring(0, vars.indexOf(',')));
							vars = vars.substring(vars.indexOf(',') + 1);
							int col = Integer.parseInt(vars.substring(0, vars.indexOf(',')));
							vars = vars.substring(vars.indexOf(',') + 1);
							int color = Integer.parseInt(vars);
							Controller.getInstance().putChess(row, col, color);
						} else if (input.startsWith("Message:")) {
							String content = "Enemy:" + input.substring(input.indexOf(':') + 1);
							Controller.getInstance().getMessageFromOpponent(content);
						} else if (input.startsWith("Win:")) {
							int color = Integer.parseInt(input.substring(input.indexOf(':') + 1));
							Controller.getInstance().gameOver(color);
						} else if (input.startsWith("Regret:")) {
							Controller.getInstance().opponentRegret();
						} else if (input.startsWith("GiveUp:")) {
							Controller.getInstance().answerGiveUp();
						} else if (input.startsWith("AgreeGiveUp:")) {
							View.getInstance(port).giveUp();
							Controller.getInstance().setPlaying(false);
						} else if (input.startsWith("RefuseGiveUp:")) {
							View.getInstance(port).showMessage("对方拒绝和局");
						} else if (input.startsWith("Restart:")) {
							Controller.getInstance().answerRestart();
						} else if (input.startsWith("AgreeRestart:")) {
							Controller.getInstance().restart();
							Model.getInstance().clearChessBoard();
							View.getInstance(port).restartInit();
						} else if (input.startsWith("RefuseRestart:")) {
							View.getInstance(port).showMessage("对方拒绝开局");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	protected void putChess(int row, int col, int color) {
		String output = "Chess:" + row + "," + col + "," + color;
		try {
			writer.write(output + "\n");
			writer.flush();
		} catch (IOException e) {
			return;
		}
	}

	public void sendMessage(String str) {
		try {
			writer.write(str + "\n");
			writer.flush();
		} catch (IOException e) {
			return;
		}
	}

	public String getPort() {
		return port;
	}
}