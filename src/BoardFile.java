import java.io.File;

/**
 * This class is instantiated when adding a file to the ComboBox
 * in the right sidebar. It's used to show a nice printed option
 * and show the puzzle that the file encodes.
 */
public class BoardFile {
	
	private File file;
	private String name;
	
	/**
	 * Creates a new BoardFile, that saves a .puzzle File.
	 * @param f	.puzzle File
	 */
	public BoardFile(File f) {
		file = f;
		String[] nameParts = f.getName().split("-");
		name = nameParts[0] + " " +
				Integer.parseInt(nameParts[1].substring(0,2));
	}
	
	/**
	 * @return File associated to this BoardFile object.
	 */
	public File file() {
		return file;
	}
	
	public String toString() {
		return name;
	}
	
}
