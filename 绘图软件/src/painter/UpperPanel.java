package painter;

import java.awt.*;
import javax.swing.*;

public class UpperPanel extends JPanel{
	private static final long serialVersionUID = 9084445860981852851L;
	private Painter parent;
	private LeftTop leftTop;
	private RightTop rightTop;
	private LeftBottom leftBottom;
	private RightBottom rightBottom;
	public UpperPanel(Painter parent) {
		this.parent = parent;
		this.setLayout(new GridLayout(2, 2));
		leftTop = new LeftTop(parent);
		this.add(leftTop);
		rightTop = new RightTop();
		this.add(rightTop);
		leftBottom = new LeftBottom(parent);
		this.add(leftBottom);
		rightBottom = new RightBottom();
		this.add(rightBottom);
	}
	public String getText() {
		String text = rightTop.getText();
		return text;
	}
	public int getFontSize() {
		return rightBottom.getFontSize();
	}
	public boolean getWheTherFill() {
		return rightBottom.getWheTherFill();
	}
}
