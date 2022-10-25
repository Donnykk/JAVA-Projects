package Chess;

public class Model {
	private static Model instance = null;
	public static final int WIDTH = 15;
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int SPACE = 0;
	private int[][] arr = new int[WIDTH][WIDTH];
	private static int lastRow;
	private static int lastCol;

	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	public int getLastCol() {
		return lastCol;
	}

	public int getLastRow() {
		return lastRow;
	}

	private Model() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < WIDTH; j++) {
				arr[i][j] = SPACE;
			}
		}
	};

	public boolean putChess(int row, int col, int color) {
		if (row < 0 || row >= WIDTH || col < 0 || col >= WIDTH || arr[row][col] != SPACE) {
			return false;
		}
		arr[row][col] = color;
		lastRow = row;
		lastCol = col;
		return true;
	}

	public boolean win() {
		int num = 0;
		for (int i = 0; i < 5; i++) {
			num = 0;
			for (int j = lastCol + i; num < 5 && j >= 0 && j < WIDTH; j--) {
				if (arr[lastRow][j] == arr[lastRow][lastCol])
					num++;
				else
					break;
			}
			if (num == 5)
				return true;
		}

		for (int i = 0; i < 5; i++) {
			num = 0;
			for (int j = lastRow + i; num < 5 && j >= 0 && j < WIDTH; j--) {
				if (arr[j][lastCol] == arr[lastRow][lastCol])
					num++;
				else
					break;
			}
			if (num == 5)
				return true;
		}

		for (int i = 0; i < 5; i++) {
			num = 0;
			for (int j = lastRow + i, k = lastCol + i; num < 5 && j >= 0 && j < WIDTH && k >= 0
					&& k < WIDTH; j--, k--) {
				if (arr[j][k] == arr[lastRow][lastCol])
					num++;
				else
					break;
			}
			if (num == 5)
				return true;
		}

		for (int i = 0; i < 5; i++) {
			num = 0;
			for (int j = lastRow - i, k = lastCol + i; num < 5 && j >= 0 && j < WIDTH && k >= 0
					&& k < WIDTH; j++, k--) {
				if (arr[j][k] == arr[lastRow][lastCol])
					num++;
				else
					break;
			}
			if (num == 5)
				return true;
		}
		return false;
	}

	public int[][] getChessBoard() {
		return arr;
	}

	public void clearChessBoard() {
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[i].length; j++)
				arr[i][j] = SPACE;
	}

	public void regretChess() {
		arr[lastRow][lastCol] = SPACE;
	}
}
