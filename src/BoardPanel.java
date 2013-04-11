import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Panel where the board and its blocks will be painted.
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	public Block[] blocks;
	
	/**
	 * Initialize the panel with adjusted size and other stuff.
	 */
	public BoardPanel() {
		setSize(630, 640);
		setLayout(null);
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		setBackground(new Color(222, 184, 135));
		blocks = null;
	}
	
	/**
	 * Loads a puzzle file
	 * @param f	The BoardFile to load (.puzzle)
	 * @throws FileNotFoundException	When the file to load doens't exist
	 */
	public void load(BoardFile f) throws FileNotFoundException {
		if (blocks != null) for (Block b : blocks) b.setVisible(false);
		Scanner in = new Scanner(f.file());
		int n = in.nextInt();
		blocks = new Block[n];
		String text = "";
		while (in.hasNextLine()) text += in.nextLine().replace(" ", "");
		int index = 0;
		addBlock('X', text, index++);
		for (char c = 'a'; c < 'a' + n - 1; c++)
			addBlock(c, text, index++);
		blocks[0].setColor(Color.RED);
		in.close();
	}
	
	/**
	 * Creates a Block, saves it and draws it.
	 * @param c		Character that represents the block to be added.
	 * @param text	The content of the file that is being loaded.
	 * @param index	The index of the block to be added.
	 */
	private void addBlock(char c, String text, int index) {
		ArrayList<Integer> indexes = new ArrayList<Integer>(3);
		int i = -1;
		while ((i = text.indexOf(c, i+1)) != -1)
			indexes.add(i);
		boolean orientation = (indexes.get(1) == indexes.get(0) + 1)?
							  Block.HORIZONTAL : Block.VERTICAL;
		int x = 100 * (indexes.get(0) % 6) + 15 + 1;
		int y = 100 * (indexes.get(0) / 6) + 15 + 1;
		blocks[index] = new Block(x, y, indexes.size(),orientation, c);
		this.add(blocks[index]);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 15; i <= 615; i += 100) {
			g.drawLine(i, 15, i, 615);
			g.drawLine(15, i, 615, i);
		}
		g.drawLine(515, 215, 615, 315);
		g.drawLine(615, 215, 515, 315);
	}
}
