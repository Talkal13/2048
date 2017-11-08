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
	/**
	 *
	 * @param args the arguments of the game
	 */
	public static void main(String[] args) {

/*	REMOVE BLOCK COMMENT TO TAKE INTO ACOUNT THE THIRD PARAMETER

 		int dim = Integer.parseInt(args[0]);
		int nums = Integer.parseInt(args[1]);
		long seed = -1;

		if(args.length == 2){
			seed = new Random().nextInt(1000);
		}

		else if(args.length == 3){
			seed = Long.parseLong(args[2]);
		}

		Random rand = new Random(seed);

		Game game = new Game(dim, nums, rand);
		System.out.print(game);
		Controller control = new Controller(game);
		controller.run();
*/

		Game game = new Game(4, 2, new Random());
		System.out.print(game);
		Controller control = new Controller(game);
		GUI window = new GUI("2048", game.getBoard());
		control.run('r');

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
