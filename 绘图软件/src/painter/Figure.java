package painter;

import java.awt.*;

public class Figure {
	private Shape shape;
	private Color color;
	private int x1;
	private int y1;
	private int x2;
	private String text;
	private int fontSize;
	private boolean whetherFill;
	public boolean isWhetherFill() {
		return whetherFill;
	}
	public void setWhetherFill(boolean whetherFill) {
		this.whetherFill = whetherFill;
	}
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public int getx1() {
		return x1;
	}
	public void setx1(int x1) {
		this.x1 = x1;
	}
	public int gety1() {
		return y1;
	}
	public void sety1(int y1) {
		this.y1 = y1;
	}
	public int getx2() {
		return x2;
	}
	public void setx2(int x2) {
		this.x2 = x2;
	}
	public int gety2() {
		return y2;
	}
	public void sety2(int y2) {
		this.y2 = y2;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private int y2;
	public Figure(Shape shape, Color color, int x1, int y1, boolean whetherFill) {
		this.shape = shape;
		this.color = color;
		this.x1 = x1;
		this.y1 = y1;
		this.whetherFill = whetherFill;
	}
	public Figure(Shape shape, Color color, int x1, int y1, String text, int fontSize) {
		this.shape = shape;
		this.color = color;
		this.x1 = x1;
		this.y1 = y1;
		this.text = text;
		this.fontSize = fontSize;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public Figure(Shape shape, Color color, int x1, int y1, int x2, int y2, boolean whetherFill) {
		this.shape = shape;
		this.color = color;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.whetherFill = whetherFill;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
