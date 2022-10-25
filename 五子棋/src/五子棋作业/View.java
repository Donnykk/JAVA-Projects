package 五子棋作业;

import java.util.Scanner;

public class View {
	public static int nowColor = Model.BLACK;
	public void input() {
		Scanner sc = new Scanner(System.in);
		if(nowColor == Model.BLACK){
			System.out.print("黑棋");
		}else {
			System.out.print("白棋");
		}
		nowColor = -nowColor;
		System.out.println("请输入行数：");
		int row = sc.nextInt();
		System.out.println("请输入列数：");
		int col = sc.nextInt();
		Control.getInstance().localUserPutChess(row,col);
	}
	
	public void goBack(int color) { 
		if(color == Model.BLACK) {
			System.out.println("黑棋还有1次悔棋机会，是否使用？");
			System.out.println("请输入Y或N");
			
			Scanner sc = new Scanner(System.in);
			char ans = sc.next().charAt(0);
			if(ans == 'Y') {
				Model.getInstance().goBack();
			}
			else if(ans == 'N') {
				return;
			}else {
				System.out.println("Wrong Input! 请重新输入！");
			}
			
		}else {
			System.out.println("白棋还有1次悔棋机会，是否使用？");
			System.out.println("请输入Y或N");
			
			Scanner sc = new Scanner(System.in);
			char ans = sc.next().charAt(0);
			if(ans == 'Y') {
				Model.getInstance().goBack();
			}
			else if(ans == 'N') {
				return;
			}
			
		}
	}
	
	private View() {}
	private static View instance;
	public static View getInstance() {
		if (instance==null) {
			instance = new View();
		}
		return instance;
	}
	
	public void outputChess() {
		for (int row = 0; row < Model.WIDTH; row++) {
			for (int col = 0; col < Model.WIDTH; col++) {
				int chess = Model.getInstance().getChess(row,col);
				switch(chess){
					case Model.BLACK:
						System.out.print("@");
						break;
					case Model.WHITE:
						System.out.print("O");
						break;
					case Model.SPACE:
						System.out.print("-");
						break;
				}
			}
			System.out.println();
		}
	}

}