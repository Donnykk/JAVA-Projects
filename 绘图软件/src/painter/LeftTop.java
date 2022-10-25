package painter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class LeftTop extends JPanel{
	private Painter parent;
	private JLabel labelBackground;
	private ColorSelect colorSelect;
	public LeftTop(Painter parent) {
		this.parent = parent;
		init();
		bindListener();
	}
	private void bindListener() {
		colorSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				Color color = (Color)cb.getSelectedItem();
				cb.setEditable(true);
				cb.getEditor().getEditorComponent().setBackground(color);
				parent.changeCanvasBackground(color);
				parent.upDate();
			}
		});
	}
	private void init() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		this.setLayout(fl);
		labelBackground = new JLabel("Background");
		colorSelect = new ColorSelect();
		this.add(labelBackground);
		this.add(colorSelect);
	}
}
