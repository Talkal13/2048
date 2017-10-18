package pr1;

import java.util.Random;

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
		Game game = new Game(4, 2, new Random());
		System.out.print(game);
		game.move(new Direction(DirectionOption.UP));
		System.out.print(game);
		
		/*Board b = new Board(4);
		b.setCell(new Position(3, 2), 2);
		b.setCell(new Position(1, 1), 4);
		b.setCell(new Position(2, 2), 2);
		System.out.print(b);
		b.executeMove(new Direction(DirectionOption.UP));
		System.out.print(b);
		b.executeMove(new Direction(DirectionOption.RIGHT));
		System.out.print(b);
		b.setCell(new Position(3, 3), 8);
		b.setCell(new Position(3, 1), 16);
		System.out.print(b);
		b.executeMove(new Direction(DirectionOption.DOWN));
		System.out.print(b);
		b.executeMove(new Direction(DirectionOption.LEFT));
		System.out.print(b);*/
		
		/**
		 * At the end should be just something like this
		 *  the parametersintroduced on the game constructor are just random ones
		 *  
		 * Game game = new Game(6,2,null);
		 * WOULD BE NICE TO CHECK SOMEWHERE THAT (numCells > sizeBoard * sizeBoard) IN WHICH CASE THE GAME CANT TAKE PLACE
		 * Controller c = new Controller(game);
		 * c.run(); 
		 */

	}

}
