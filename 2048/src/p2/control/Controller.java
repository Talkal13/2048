package p2.control;

import java.io.IOException;
import java.util.Scanner;

import p2.control.commands.Command;
import p2.control.invoker.CommandParser;
import p2.exceptions.EndException;
import p2.exceptions.ExecutionException;
import p2.exceptions.ParsingException;
import p2.logic.Game;
import p2.util.Direction;
import p2.util.DirectionOption;


/**
 * @author Pablo and Diego
 *
 * Class which instance the control which executes of the game.
 */

public class Controller {

	private Game game;
	private Scanner in;
	public int sizeBoard;
	public int numDigit;

	/**
	 * Constructor of the class Controller, which will control the game introduced as parameter.
	 *
	 * @param currentGame game to control.
	 */

	public Controller(Game  currentGame) {
		game = currentGame;
		in = new Scanner(System.in);
	}

	

	/**
	 * Method which runs the game taking into account which commands are introduced and performing the corresponding actions.
	 */
	
	public void run(){
		String input;
		while(true){
			System.out.print("Command > ");
			input = in.nextLine().toLowerCase();
			String[] parts = input.split("\\s+");
			Command control;
			try {
				control = CommandParser.parseCommand(parts, in);
				if (control.execute(game)) {
					System.out.println(game);
				}
			} catch (ParsingException e) {
				System.out.println(e.getMessage());
			} catch (ExecutionException e) {
				System.out.println(e.getMessage());
			} catch (EndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}
	
 
	/**
	 * Shows the different help messages to help the user about the different possibilities of movement
	 */

	public void showHelp(){
		System.out.println("Move <direction>: execute a move in one of the fur directions, up, own,left, right");
		System.out.println("Reset: start a new game");
		System.out.println("Help: print this help message");
		System.out.println("Exit: terminate the program");
	}
	
}