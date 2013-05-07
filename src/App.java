import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class App extends JFrame {
	
	public App() {
		Shared.app = this;
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
		new App();
	}
	
}
