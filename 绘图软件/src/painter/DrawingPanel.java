package painter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;

public class DrawingPanel extends JPanel{
	private Painter parent;
	private Color backGroundColor = Color.white;
	private Color shapeColor = Color.white;
	private Shape shapeToDraw = Shape.NULL;
	private ArrayList<Figure> figures;
	private Image screenShot = null;
	private Graphics g;
	public DrawingPanel(Painter parent) {
		this.parent = parent;
		figures = new ArrayList<Figure>();
		figures.clear();
		bindListner();
	}
	private void bindListner() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x1 = e.getX();
				int y1 = e.getY();
				Figure fig = null;
				if (shapeToDraw == Shape.Text) {
					String text = parent.getText();
					if (text == null)
						return;
					int fontSize = parent.getFontSize();
					fig = new Figure(shapeToDraw, shapeColor, x1, y1, text, fontSize);
				}
				else {
					fig = new Figure(shapeToDraw, shapeColor, x1, y1, x1, y1, parent.getWheTherFill());
				}
				figures.add(fig);
				parent.upDate();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int nx = e.getX();
				int ny = e.getY();
				Figure nowFig = figures.get(figures.size() - 1);
				if (nowFig.getShape() == Shape.Text)
					return;
				nowFig.setx2(nx);
				nowFig.sety2(ny);
				parent.upDate();
			}
		});
	}
	public void update() {
		if (g == null) {
			screenShot = this.createImage(this.getWidth(), this.getHeight());
			if (screenShot != null)	
				g = screenShot.getGraphics();
			super.paint(g);
			g.setColor(backGroundColor);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}else if (g != null) {
			super.paint(g);
			g.setColor(backGroundColor);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			for (int i = 0; i < figures.size(); i++) {
				Figure nowFig = figures.get(i);
				g.setColor(nowFig.getColor());
				Shape nowShape = nowFig.getShape();  
				int x1 = nowFig.getx1();
				int y1 = nowFig.gety1();
				int x2 = nowFig.getx2();
				int y2 = nowFig.gety2();
				int minX = Math.min(x1, x2);
				int maxX = Math.max(x1, x2);
				int minY = Math.min(y1, y2);
				int maxY = Math.max(y1, y2);
				boolean whetherFill = nowFig.isWhetherFill();
				if (nowShape == Shape.Text) {
					Font f = new Font("ËÎÌו" , Font.BOLD, nowFig.getFontSize());
					g.setFont(f);
					g.drawString(nowFig.getText(), nowFig.getx1(), nowFig.gety1());
				}else if (nowShape == Shape.Circle) {
					int absD = Math.min(Math.abs(maxX - minX), Math.abs(maxY - minY));
					if (x1 < x2)
						x2 = x1 + absD;
					else if (x1 > x2)
						x2 = x1 - absD;
					if (y1 < y2)
						y2 = y1 + absD;
					else if (y1 > y2)
						y2 = y1 - absD;
					if (whetherFill)
						g.fillOval(Math.min(x1, x2), Math.min(y1, y2), absD, absD);
					else
						g.drawOval(Math.min(x1, x2), Math.min(y1, y2), absD, absD);
				}else if (nowShape == Shape.Line) {
					g.drawLine(x1, y1, x2, y2);
				}else if (nowShape == Shape.Rectangle) {
					if (whetherFill)
						g.fillRect(minX, minY, maxX - minX, maxY - minY);
					else
						g.drawRect(minX, minY, maxX - minX, maxY - minY);
				}else if (nowShape == Shape.Oval) {
					if (whetherFill)
						g.fillOval(minX, minY, maxX - minX, maxY - minY);
					else
						g.drawOval(minX, minY, maxX - minX, maxY - minY);
				}
			}
		}
		this.repaint();
	}
	public void paint(Graphics g) {
		if (screenShot != null)
			g.drawImage(screenShot, 0, 0, this);
		else {
			g.setColor(backGroundColor);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	public void changeBackground(Color color) {
		backGroundColor = color;
	}
	public void changeShapeColor(Color color) {
		shapeColor = color;
	}
	public void setshapeToDraw(Shape shape) {
		shapeToDraw = shape;
	}
}
