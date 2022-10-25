package Chess;

public class Node {
	private int col;
	private int row;
	private int color;

	public Node(int row, int col, int color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public int getColor() {
		return color;
	}
}
