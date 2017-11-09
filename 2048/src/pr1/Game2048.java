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
		
		int dim = 4, nums = 2;
		long seed = -1;
		
		try {
			dim = Integer.parseInt(args[0]);
			nums = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			seed = new Random().nextInt(1000);
		}
		
		
		
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
		control.run();

	}

}
