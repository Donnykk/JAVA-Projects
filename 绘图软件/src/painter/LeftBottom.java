package painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftBottom extends JPanel{
	private Painter parent;
	private JLabel labelShapColor;
	private ColorSelect colorSelect;
	public LeftBottom(Painter parent) {
		this.parent = parent;
		init();
		listner();
	}
	private void listner() {
		colorSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox box = (JComboBox)e.getSource();
				Color color = (Color)box.getSelectedItem();
				box.setEditable(true);
				box.getEditor().getEditorComponent().setBackground(color);
				parent.changeShapColor(color);
			}
		});		
	}
	private void init() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		this.setLayout(fl);
		labelShapColor = new JLabel("Shape Color");
		this.add(labelShapColor);
		colorSelect = new ColorSelect();
		this.add(colorSelect);
	}
}
