package painter;

import javax.swing.*;
import java.awt.*;

public class ColorSelect extends JComboBox{
	private static Color[] colors = {Color.white, Color.red, Color.black, Color.gray, Color.blue, Color.yellow};
	public ColorSelect() {
		super(colors);
		Renderer renderer = new Renderer(); 
		renderer.setPreferredSize(new Dimension(15, 15));
		this.setRenderer(renderer);
	}
}

class Renderer extends JLabel implements ListCellRenderer{
	public Renderer() {
		setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
	}
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Color color = (Color)value;
		setBackground(color);
		return this;
	}
	
}