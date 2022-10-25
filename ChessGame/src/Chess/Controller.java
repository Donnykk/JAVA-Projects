package Chess;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Controller {
	private static String port;
	private static Controller instance = null;
	private static int color;
	private boolean connected = false;
	private boolean playing = false;
	private boolean yourTurn = false;
	private static int time_left = 0;
	private static LinkedList<Node> chessRecord;

	private Controller() {
		chessRecord = new LinkedList<>();
	};

	public int getColor() {
		return color;
	}

	public boolean getYourTurn() {
		return yourTurn;
	}

	public boolean getPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
		return instance;
	}

	public static void main(String[] args) throws Exception {
		port = Connector.getInstance().getPort();
		StartMenu.getInstance(port);
	}

	public void Connected(int color) throws Exception {
		chessRecord.clear();
		View.getInstance(port).showMessage("连接成功!\n开始游戏!");
		createTimingThread();
		connected = true;
		playing = true;
		Controller.color = color;
		if (color == Model.BLACK) {
			yourTurn = true;
			View.getInstance(port).changeYourTurn();
		} else if (color == Model.WHITE) {
			View.getInstance(port).changeOpponentTurn();
		}
		Model.getInstance().clearChessBoard();
	}

	public boolean sendMessageToOpponent(String text) {
		if (connected == false)
			return false;
		try {
			Connector.getInstance().sendMessage("Message:" + text);
		} catch (Exception e) {}
		return true;
	}

	public void getMessageFromOpponent(String text) throws Exception {
		View.getInstance(port).showMessage(text);
	}

	public void putChess(int row, int col) throws Exception {
		if (yourTurn == false)
			return;
		boolean success = Model.getInstance().putChess(row, col, color);
		if (success) {
			chessRecord.add(new Node(row, col, color));
			Connector.getInstance().putChess(row, col, color);
			View.getInstance(port).upDate();
			View.getInstance(port).playPutChessMusic();
			boolean win = Model.getInstance().win();
			if (win == true) {
				Connector.getInstance().sendMessage("Win:" + color);
				gameOver(color);
			}
			yourTurn = false;
			View.getInstance(port).changeOpponentTurn();
		}
	}

	public void putChess(int row, int col, int color) throws Exception {
		yourTurn = true;
		chessRecord.add(new Node(row, col, color));
		Model.getInstance().putChess(row, col, color);
		View.getInstance(port).upDate();
		View.getInstance(port).changeYourTurn();
		View.getInstance(port).playPutChessMusic();
	}

	public int getLastRow() {
		if (chessRecord == null || chessRecord.isEmpty())
			return -1;
		Node lastNode = chessRecord.getLast();
		return lastNode.getRow();
	}

	public int getLastCol() {
		if (chessRecord == null || chessRecord.isEmpty())
			return -1;
		Node lastNode = chessRecord.getLast();
		return lastNode.getCol();
	}

	public void createTimingThread() {
		new Thread() {
			@Override
			public void run() {
				time_left = 30;
				boolean temp = false;
				long baseTime = System.currentTimeMillis();
				while (true) {
					try {
						sleep(100);
						if (!playing)
							return;
						if (yourTurn != temp) {
							temp = yourTurn;
							time_left = 30;
							baseTime = System.currentTimeMillis();
						} else {
							long nowTime = System.currentTimeMillis();
							long passTime = (nowTime - baseTime) / 1000;
							if (passTime <= 30)
								time_left = (int) (30 - passTime);
							else {
								timeOver();
							}
							View.getInstance(port).setRemainTime(time_left);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private void timeOver() throws Exception {
		if (yourTurn == true) {
			yourTurn = false;
			View.getInstance(port).changeOpponentTurn();
		} else {
			yourTurn = true;
			View.getInstance(port).changeYourTurn();
		}
		Connector.getInstance().sendMessage("TimeOver:");
	}

	public void gameOver(int color) throws Exception {
		boolean uWin = color == Controller.color ? true : false;
		View.getInstance(port).gameOver(uWin);
		playing = false;
	}

	public void regretChess() throws Exception {
		yourTurn = true;
		View.getInstance(port).changeYourTurn();
		chessRecord.removeLast();
		Connector.getInstance().sendMessage("Regret:");
		Model.getInstance().regretChess();
		View.getInstance(port).upDate();
	}

	public void opponentRegret() throws Exception {
		yourTurn = false;
		View.getInstance(port).changeOpponentTurn();
		chessRecord.removeLast();
		Model.getInstance().regretChess();
		View.getInstance(port).opponentRegret();
	}

	public void askGiveUp() throws Exception {
		Connector.getInstance().sendMessage("GiveUp:");
		View.getInstance(port).showMessage("正在请求和局······");
	}

	public void answerGiveUp() throws Exception {
		int agree = JOptionPane.showConfirmDialog(null, "对方请求和局，是否同意？", "", JOptionPane.YES_NO_OPTION);
		if (agree == 0) {
			View.getInstance(port).giveUp();
			Connector.getInstance().sendMessage("AgreeGiveUp:");
			playing = false;
		} else if (agree == 1) {
			Connector.getInstance().sendMessage("RefuseGiveUp:");
		}
	}

	public void askRestart() throws Exception {
		Connector.getInstance().sendMessage("Restart:");
		View.getInstance(port).showMessage("正在请求重新开局······");
	}

	public void answerRestart() throws Exception {
		int agree = JOptionPane.showConfirmDialog(null, "对方请求重新开局，是否同意？", "", JOptionPane.YES_NO_OPTION);
		if (agree == 0) {
			Connector.getInstance().sendMessage("AgreeRestart:");
			restart();
		} else if (agree == 1) {
			Connector.getInstance().sendMessage("RefuseRestart:");
		}
	}

	public void restart() throws Exception {
		playing = true;
		chessRecord.clear();
		createTimingThread();
		if (color == Model.BLACK)
			yourTurn = true;
		Model.getInstance().clearChessBoard();
		View.getInstance(port).restartInit();
	}

	public void clearChessRecord() {
		chessRecord.clear();
	}
	
	public void review(int row, int col, int color) throws Exception {
		Model.getInstance().putChess(row, col, color);
		View.getInstance(port).upDate();
	}

	public void clearBoard() throws Exception {
		Model.getInstance().clearChessBoard();
		View.getInstance().reviewInit();
	}

	public LinkedList<Node> getChessRecord() {
		return chessRecord;
	}
}