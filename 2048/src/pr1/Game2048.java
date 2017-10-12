package pr1;


/**
 * @author Pablo and Diego
 * @version 1.0
 * @since 1.0
 *
 *Contains the main of the application
 *Reads the the values passed via command line as attributes (2 or 3)
 *Creates a new game and a new controler and then invokes the controller�s method.
 */

public class Game2048 {

	public static void main(String[] args) {
		Board b = new Board(4);
		b.setCell(new Position(3, 2), 2);
		b.setCell(new Position(1, 1), 4);
		System.out.print(b);

	}

}
