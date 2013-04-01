import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Block extends JPanel {
	
	private int x, y;
	private final int width, height;
	private boolean orientation;
	public static final boolean HORIZONTAL = true;
	public static final boolean VERTICAL   = false;
	private JLabel lbl;
	
	public Block(int x, int y, int length, boolean orientation, char c) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.width = (orientation == HORIZONTAL)? 100 * length :
					 100;
		this.height = (orientation == HORIZONTAL)? 100 :
					  100 * length;
		setBounds(x, y, width, height);
		setPreferredSize(new Dimension(width, height));
		setBackground(new Color(random(), random(), random()));
		lbl = new JLabel(c+"");
		add(lbl);
	}
	
	public void setColor(Color color) {
		setBackground(color);
		lbl.setBackground(color);
	}
	
	private int random() {
		return (int)(Math.random() * 256);
	}
	
	public void move(int d) throws InterruptedException {
		if (orientation == HORIZONTAL)
			for (int i = 0; i < d; i++) {
				setBounds(x + i, y, width, height);
				Thread.sleep(10);
			}
		else
			for (int i = 0; i < d; i++) {
				setBounds(x, y + i, width, height);
				Thread.sleep(10);
			}
	}
	
}
