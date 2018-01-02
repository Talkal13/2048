package p2.control.commands;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import p2.control.Controller;
import p2.exceptions.ParsingException;
import p2.logic.Game;
import p2.logic.multigames.*;
import p2.util.GameType;

/**
 * 
 * @author Pablo & Diego
 *
 *  Class which extends the abstract class Command and represents the Play command.
 */

public class PlayCommand extends Command {
	
	private GameType type;
	private int size = 4, initNumb = 2;
	private long seed;
	
	
	private final static int DEFAULT_SIZE = 4; // by default a 4*4 board with 2 initial tokens
	private final static int DEFAULT_INIT = 2; // the seed for the random placement of the tokens
	/**
	 * Constructor of the class, implements the parent class Command with i's parameters.
	 * 
	 * @param commandInfo string containing the play command and the game to play.
	 * @param helpInfo explanation of what the play command does.
	 */

	public PlayCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Switch to the game mode indicated in the attribute type.
	 * This is performed through the changeGame method from the class Game in the package p2.logic.
	 * 
	 * @param game current game which is taking place.
	 * @param controller controller of the current game.
	 */
	
	public void execute(Game game, Controller controller) {
		switch(type) {
		case ORIG:
			game.changeGame(size, initNumb, seed, new Rules2048());
			return;
		case INV:
			game.changeGame(size, initNumb, seed, new RulesInverse());
			return;
		case FIB:
			game.changeGame(size, initNumb, seed, new RulesFib());
			return;
		}
		
	}

	/**
	 * Parses a play command, which will be in the parameter commandWords, if in a correct format in the fist position is the play and in the second game modality.
	 * It will parse the modality and if is well defined the attribute type will receive that modality. The cases the second, third and fourth positions are not
	 * integers is also contemplated.
	 * 
	 * @param commandWords String Array which contains the command play and the modality of game to switch to, the number of initial cells, the size of
	 * the board and the seed for the random behavior.
	 * @param controller controller of the current game.
	 * 
	 * @return the command itself with the type attribute already contending the modality of the game to switch to, in the case that all went right.
	 * If the first word parsed wasn't play, a null will be returned. Also if the second word is not one of the possible games, or one of the other
	 * 3 Parameters to perform the switch is not in the correct format.
	 * @throws ParsingException 
	 */
	
	public Command parse(String[] commandWords, Controller controller) throws ParsingException {
		
		Scanner in = new Scanner(System.in);
		
		if (!commandWords[0].equals(commandName)) {
			return null;
		}
		else {
			if (commandWords.length > 1) {
				switch(commandWords[1]) {
				case "original":
					type = GameType.ORIG;
					break;
				case "inverse":
					type = GameType.INV;
					break;
				case "fib":
					type = GameType.FIB;
					break;
				default:
					controller.setErrorCode(false);
					controller.setNoPrintGameState(true);
					throw new ParsingException("Unknown game type for play command");
				}
			}
			else {
				controller.setErrorCode(false);
				controller.setNoPrintGameState(true);
				throw new ParsingException("Play must be followed by a game type: original, fib, inverse\n");
			}
			String s;
			size = -1;
			while (size < 0) {
				System.out.print("Please enter the size of the board: ");
				s = in.nextLine();
				if(s.equals("")) {
					size = DEFAULT_SIZE;
					System.out.println("\tUsing the default size of the board: " + size);
					break;
				}
				else {
					try {
						size = Integer.parseInt(s);
						if (size > 0)
							break;
						else
							System.out.println("\tThe size of the board must be positive");
					} catch (NumberFormatException e) {
						size = -1;
						System.out.println("\tPlease provide a single positive integer or press return");
					}
					
				}
			}
			initNumb = -1;
			while (initNumb < 0) {
				System.out.print("Please enter the number of initial cells: ");
				s = in.nextLine();
				if(s.equals("")) {
					initNumb = DEFAULT_INIT;
					System.out.println("\tUsing the default number of initial cells: " + initNumb);
					break;
				}
				else {
					try {
						initNumb = Integer.parseInt(s);
						if (initNumb > 0)
							break;
						else
							System.out.println("\tThe initial number of cells must be positive");
					} catch (NumberFormatException e) {
						System.out.println("\tPlease provide a single positive integer or press return");
						initNumb = -1;
					}
					
				}
			}
			seed = -1;
			while (seed < 0) {
				System.out.print("Please enter the seed for the pseudo-random number generator: ");
				s = in.nextLine();
				if(s.equals("")) {
					seed = new Random().nextInt(1000);
					System.out.println("\tUsing the default seed for the pseudo-random number generator: " + seed);
					break;
				}
				else {
					try {
						seed = Integer.parseInt(s);
						if (seed > 0)
							break;
						else
							System.out.println("\tThe seed must be positive");
					} catch (NumberFormatException e) {
						System.out.println("\tPlease provide a single positive integer or press return");
						seed = -1;
					}
					
				}
			}
			if (size < initNumb) {
				controller.setErrorCode(false);
				controller.setNoPrintGameState(true);
				throw new ParsingException("The number of initial cells must be less than the number of cells on the board");
			}
		}
		
		return this;
	}
	
	/**
	 * Getter method of the class attribute size, which  indicates the size of the board.
	 * 
	 * @return the attribute size of this class.
	 */
	
	public int getSize() {
		return size;
	}
	
	/**
	 * Getter of the class attribute initNumb which indicates the initial number of tokens.
	 * 
	 * @return the attribute initNumb of this class.
	 */
	
	public int getInit() {
		return initNumb;
	}
	
	/**
	 * Getter of the class attribute seed which is use for the random placement of the tokens.
	 * 
	 * @return the attribute seed of this class.
	 */
	
	public long getSeed() {
		return seed;
	}
	
	/**
	 * Getter of the class attribute type which contains the type of game to play.
	 * 
	 * @return the attribute type of this class.
	 */
	
	public GameType getType() {
		return type;
	}

}
