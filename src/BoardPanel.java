import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Panel where the board and its blocks will be painted.
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	/**
	 * Initialize the panel with adjusted size and other stuff.
	 */
	public BoardPanel() {
		setSize(630, 640);
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		setBackground(new Color(222, 184, 135));
	}
	
	/**
	 * Draw board and blocks.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 15; i <= 615; i += 100) {
			g.drawLine(i, 15, i, 615);
			g.drawLine(15, i, 615, i);
		}
	}
}
