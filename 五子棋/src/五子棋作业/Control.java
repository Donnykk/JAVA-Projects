package 五子棋作业;

public class Control {
	private int color = Model.BLACK;
	public static boolean black_goBack = true; 
	public static boolean white_goBack = true;
	
	private Control() {}
	private static Control instance;
	public static Control getInstance() {
		if (instance == null) {
			instance =  new Control();
		}
		return instance;
	}
	
	public void localUserPutChess(int row, int col) {
		boolean success = Model.getInstance().putChess(row,col,color);
		if(success) {
			color = -color;
			int winner = Model.getInstance().whoWin();
			switch(winner) {     //判定有无获胜者
			case Model.BLACK:
				System.out.println("黑棋胜");
				if(white_goBack){
					View.getInstance().goBack(Model.WHITE);  
					white_goBack = false;       //仅有一次悔棋机会
				}
				break;
			case Model.WHITE:
				System.out.println("白棋胜");
				if(black_goBack){
					View.getInstance().goBack(Model.BLACK);
					black_goBack = false;
				}
				break;
			case Model.SPACE:
				break;
			}
			View.getInstance().outputChess();
		}
		else {
			System.out.println("failure");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("游戏开始！");
		System.out.println("——黑棋先手");
		while(true) {
			View.getInstance().input();
		}
	}
}

