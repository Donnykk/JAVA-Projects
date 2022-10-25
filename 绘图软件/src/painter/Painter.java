package painter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Painter {
	private JFrame painter;
	private LeftPanel leftPanel;
	private UpperPanel upperPanel;
	private DrawingPanel drawingPanel;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("文件");
	private JMenu editMenu = new JMenu("编辑");
	private JMenuItem auto = new JMenuItem("自动换行");
	private JMenuItem copy = new JMenuItem("复制");
	private JMenuItem paste = new JMenuItem("粘贴");
	private JMenu formatMenu = new JMenu("格式");
	private JMenuItem comment = new JMenuItem("注释");
	private JMenuItem cancelComment = new JMenuItem("取消注释");

	Painter() {
		init();
	}

	private void init() {
		painter = new JFrame("Java Painter");
		painter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painter.setLayout(new BorderLayout());
		painter.setSize(1000, 600);
		drawingPanel = new DrawingPanel(this);
		upperPanel = new UpperPanel(this);
		leftPanel = new LeftPanel(this);
		painter.add(drawingPanel);
		painter.add(upperPanel, BorderLayout.NORTH);
		painter.add(leftPanel, BorderLayout.WEST);

		formatMenu.add(comment);
		formatMenu.add(cancelComment);
		editMenu.add(auto);
		editMenu.addSeparator();
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.addSeparator();
		editMenu.add(formatMenu);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		painter.setJMenuBar(menuBar);

		painter.setVisible(true);
	}

	public static void main(String[] args) {
		Painter p = new Painter();
	}

	public void changeCanvasBackground(Color color) {
		drawingPanel.changeBackground(color);
	}

	public void upDate() {
		drawingPanel.update();
	}

	public void changeShapColor(Color color) {
		drawingPanel.changeShapeColor(color);
	}

	public String getText() {
		String text = upperPanel.getText();
		return text;
	}

	public void setshapeToDraw(Shape shape) {
		drawingPanel.setshapeToDraw(shape);
	}

	public int getFontSize() {
		return upperPanel.getFontSize();
	}

	public boolean getWheTherFill() {
		return upperPanel.getWheTherFill();
	}

}
