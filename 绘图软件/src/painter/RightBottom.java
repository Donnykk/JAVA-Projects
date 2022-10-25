package painter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RightBottom extends JPanel{
	private JLabel labelSize;
	private JLabel labelFillTheRegion;
	private JRadioButton ftRegion;
	private JPanel fontSizeController;
	private JTextField textArea;
	private JButton up, textAreaDown;
	public RightBottom() {
		init();
		bindListener();
	}
	private void bindListener() {
		up.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getComponent() == null)
					return;
				String textFontSize = textArea.getText();
				textFontSize = Integer.toString(Integer.parseInt(textFontSize) + 1);
				textArea.setText(textFontSize);
			}
		});
		textAreaDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getComponent() == null)
					return;
				String textFontSize = textArea.getText();
				if (textFontSize.equals("1"))
					return;
				textFontSize = Integer.toString(Integer.parseInt(textFontSize) - 1);
				textArea.setText(textFontSize);
			}
		
		});
	}
	private void init() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		this.setLayout(fl);
		fontSizeController = new JPanel();
		fontSizeController.setLayout(new GridLayout(2, 1));
		up = new JButton("¡ü");
		textAreaDown = new JButton("¡ý");
		fontSizeController.add(up);
		fontSizeController.add(textAreaDown);
		labelSize = new JLabel("Size");
		labelFillTheRegion = new JLabel("Fill the region");
		textArea = new JTextField("18");
		ftRegion = new JRadioButton();
		ftRegion.setSelected(false);
		this.add(labelSize);
		this.add(textArea);
		this.add(fontSizeController);
		this.add(ftRegion);
		this.add(labelFillTheRegion);
	}
	public int getFontSize() {
		String fontSize = textArea.getText();
		return Integer.parseInt(fontSize);
	}
	public boolean getWheTherFill() {
		return ftRegion.isSelected();
	}
}
