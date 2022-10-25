package painter;

import java.awt.*;
import javax.swing.*;

public class RightTop extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel labelText;
	private JTextField text;
	public RightTop() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		this.setLayout(fl);
		labelText = new JLabel("Text ");
		text = new JTextField("Type Text Here ");
		this.add(labelText);
		this.add(text);
	}
	public String getText() {
		return text.getText();
	}
}
