package p2;

import java.util.Random;
import p2.control.Controller;
import p2.logic.Game;
import p2.logic.GameRules;
import p2.logic.multigames.Rules2048;
import p2.util.GameType;

/**
 * @author Pablo and Diego
 *
 *Contains the main of the application.
 *Reads the the values passed via command line as attributes (2 or 3).
 *Creates a new game and a new controller and then invokes the controllers method.
 */
public class Game2048 {
	
	/**
	 * Main method
	 *
	 * @param args the arguments of the game.
	 */
	
	public static void main(String[] args) {
		int dim = 4, nums = 2;
		long seed = -1;
		
		try {
			dim = Integer.parseInt(args[0]);
			nums = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: ./Game2048 <size> <initial-cells> [<seeds>]");
			return;
		} catch (NumberFormatException e) {
			System.out.println("The command-line arguments must be numbers");
			return;
		}
		
		if(args.length == 2){
			seed = new Random().nextInt(1000);
		}

		else if(args.length == 3){
			seed = Long.parseLong(args[2]);
		}
		GameType currentGame = GameType.ORIG;
		
		Random rand = new Random(seed);
		
		Game game = new Game(dim, nums, rand, currentGame);
		
		System.out.println(game);
		Controller control = new Controller(game);
		control.run();

	}

}