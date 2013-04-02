import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class App extends JFrame {
	
	public App() {
		setTitle("Solver");
		setSize(new Dimension(930, 650));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		BoardPanel board		= new BoardPanel();
		Shared.board = board;
		RightPanel rightSidebar = new RightPanel();

		getContentPane().add(rightSidebar, BorderLayout.EAST);
		getContentPane().add(board, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		App app = new App();
		Thread.sleep(1000);
		Shared.board.blocks[0].move(200);
		Shared.board.blocks[2].move(200);
		JOptionPane.showMessageDialog(null, "Done.");
	}
	
}
