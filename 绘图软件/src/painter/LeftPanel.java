package painter;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class LeftPanel extends JPanel{
	private Painter parent;
	private JToggleButton[] buttom = new JToggleButton[5];
	LeftPanel(Painter parent){
		this.parent = parent;
		init();
		bindListener();
	}
	private void bindListener() {
		buttom[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				parent.setshapeToDraw(Shape.Circle);
			}
		});
		buttom[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				parent.setshapeToDraw(Shape.Oval);
			}
		});
		buttom[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				parent.setshapeToDraw(Shape.Line);
			}
		});
		buttom[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				parent.setshapeToDraw(Shape.Rectangle);
			}
		});
		buttom[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				parent.setshapeToDraw(Shape.Text);
			}
		});
	}
	private void init() {
		this.setLayout(new GridLayout(5, 1));
		for (int i = 0; i < buttom.length; i++)
			buttom[i] = new JToggleButton();
		buttom[0].setText("Circle");
		buttom[1].setText("Oval");
		buttom[2].setText("Line");
		buttom[3].setText("Rectangle");
		buttom[4].setText("Text");
		ButtonGroup tbg = new ButtonGroup();
		for (int i = 0; i < buttom.length; i++)
			tbg.add(buttom[i]);
		for (int i = 0; i < buttom.length; i++)
			this.add(buttom[i]);
	}
}
