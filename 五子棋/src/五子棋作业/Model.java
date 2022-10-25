package 五子棋作业;

public class Model {
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	public static final int SPACE = 0;
	public static final int WIDTH = 21;
	
	private int[][]data = new int[WIDTH][WIDTH];
	private int lastRow,lastCol,currentRow,currentCol;
	
	private Model() {};
	private static Model instance;
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}
	
	public void goBack() {
		data[lastRow][lastCol] = SPACE;
		data[currentRow][currentCol] = SPACE;
	}
	
	public boolean putChess(int row, int col, int color) {
		if(row>=0 && row<WIDTH && col>=0 && col<WIDTH && (color==BLACK || color==WHITE)) {
			if(data[row][col]==SPACE) {
				data[row][col] = color;
				lastRow = currentRow;
				lastCol = currentCol;
				currentRow = row;
				currentCol = col;
				return true;
			}
		}
		return false;
	}
	
	public int getChess(int row, int col) {
		if(row>=0 && row<WIDTH && col>=0 && col<WIDTH) {
			return data[row][col];
		}
		return SPACE;
	}

	public int whoWin() {
		//同行
		for(int i=0; i<WIDTH; i++) {
			for(int j=0; j<WIDTH-4; j++) {
				if(data[i][j] == BLACK && data[i][j+1] == BLACK && data[i][j+2] == BLACK && data[i][j+3]== BLACK && data[i][j+4] == BLACK) {
					return BLACK;
				}
			}
			for(int j=0; j<WIDTH-4; j++) {
				if(data[i][j] == WHITE && data[i][j+1] == WHITE && data[i][j+2] == WHITE && data[i][j+3] == WHITE && data[i][j+4] == WHITE) {
					return WHITE;
				}
			}
		}
		//同列
		for(int i=0; i<WIDTH-4; i++) {
			for(int j=0; j<WIDTH; j++) {
				if(data[i][j] == BLACK && data[i+1][j] == BLACK && data[i+2][j] == BLACK && data[i+3][j]== BLACK && data[i+4][j] == BLACK) {
					return BLACK;
				}
			}
			for(int j=0; j<WIDTH; j++) {
				if(data[i][j] == WHITE && data[i+1][j] == WHITE && data[i+2][j] == WHITE && data[i+3][j] == WHITE && data[i+4][j] == WHITE) {
					return WHITE;
				}
			}
		}
		//对角线
		for(int i=0; i<WIDTH-4;i++){
			for(int j=0; j<WIDTH-4; j++) {
				if(data[i][j] == BLACK && data[i+1][j+1] == BLACK && data[i+2][j+2] == BLACK && data[i+3][j+3]== BLACK && data[i+4][j+4] == BLACK) {
					return BLACK;
				}
			}
			for(int j=0; j<WIDTH-4; j++) {
				if(data[i][j] == WHITE && data[i+1][j+1] == WHITE && data[i+2][j+2] == WHITE && data[i+3][j+3]== WHITE && data[i+4][j+4] == WHITE) {
					return WHITE;
				}
			}
		}
		for(int i=4; i<WIDTH; i++){
			for(int j=0; j<WIDTH-4; j++) {
				if(data[i][j] == BLACK && data[i-1][j+1] == BLACK && data[i-2][j+2] == BLACK && data[i-3][j+3]== BLACK && data[i-4][j+4] == BLACK) {
					return BLACK;
				}
			}
			for(int j=0; j<WIDTH-4; j++) {
				if(data[i][j] == WHITE && data[i-1][j+1] == WHITE && data[i-2][j+2] == WHITE && data[i-3][j+3]== WHITE && data[i-4][j+4] == WHITE) {
					return WHITE;
				}
			}
		}
		return SPACE;
	}
}




	
