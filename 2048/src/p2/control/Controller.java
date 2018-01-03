package p2.control;

import java.io.IOException;
import java.util.Scanner;

import p2.control.commands.Command;
import p2.control.invoker.CommandParser;
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
	private boolean gameEnd, gameState, errorCode;

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
	 * Sets the value of the boolean attribute gameEnd which marks if the game has came to an end.
	 * 
	 * @param x the boolean value of this parameter will set the existing one of the attribute gameEnd.
	 */
	
	public void setOver(boolean x) {
		this.gameEnd = x;
	}
	
	/**
	 * Sets the value of the boolean attribute gameState which marks if the board of the game should be printed.
	 * 
	 * @param x the boolean value of this parameter will set the existing one of the attribute gameState.
	 */
	
	public void setNoPrintGameState(boolean x) {
		this.gameState = x;
	}
	
	/**
	 * Sets the value of the boolean attribute errorCode which indicates if any of the possible error (enumerated in the class ErrorCode) has happened.
	 * 
	 * @param x the boolean value of this parameter will set the existing one of the attribute errorCode.
	 */
	
	public void setErrorCode(boolean x) {
		this.errorCode = x;
	}

	/**
	 * Method which runs the game taking into account which commands are introduced and performing the corresponding actions.
	 */
	
	public void run(){
		//executes the game
		
		String input;
		while(!gameEnd){
			setNoPrintGameState(false);
			setErrorCode(true);
			System.out.print("Command > ");
			input = in.nextLine().toLowerCase();
			String[] parts = input.split("\\s+");
			Command control;
			try {
				control = CommandParser.parseCommand(parts, this);
				control.execute(game, this);
				if (!this.gameState) {
					System.out.println(game);
				}
				/*
				if (errorCode) {
					System.out.println(Controller.getErrorMessage(ErrorCode.BAD_COMMAND));
					this.setNoPrintGameState(true);
				}
				*/
			} catch (ParsingException e) {
				System.out.println(e.getMessage());
			} catch (ExecutionException e) {
				System.out.println(e.getMessage());
			}
				
			
		}
			/*
			String[] parts = input.split(" ");
			switch (parts[0]) {
				case "reset":
					game.reset();
					System.out.print(game);
				//the program returns to the initial state
					break;
				case "help": showHelp();
				//displays the help text
					break;
				case "exit": System.out.print("Game over");
					on = false;
				//terminates the program after printing the message "Game over"
					break;
				case "move":
					if(parts.length == 2){//check if the second term is an accepted command(up,down,right,left)
						//performs the movement in the parts[2] direction
						switch(parts[1]) {
						case "up":
							game.move(new Direction(DirectionOption.UP));
							break;
						case "down":
							game.move(new Direction(DirectionOption.DOWN));
							break;
						case "right":
							game.move(new Direction(DirectionOption.RIGHT));
							break;
						case "left":
							game.move(new Direction(DirectionOption.LEFT));
							break;
						default: System.out.println("choose a valid direction");
						}
						System.out.print(game);
						
						if (game.isWon()){
							System.out.println("CONGRATULATIONS YOU HAVE WON!");
							on = false;
							 break;
						}
						else if (game.isOver()) {
							System.out.println("SORRY... MORE LUCK NEXT TIME");
							on = false;
							 break;
						}
						break;
					}
				//default case that the command is invalid, we ask for a new command
				default: System.out.println("choose another option");
			}
			*/
	}
	
	/**
	 * Method which receives an error code and depending which, returns a string describing in deeper detailed
	 * what happened to notify the user about what went wrong.
	 * 
	 * @param code one of the possible error that can occur during the interaction with the program, enumerated in the class ErrorCode.
	 * @return A string with the text which explains more detailed what went wrong, in case that is not one of the error in the cases 
	 * (for example is the NO_ERROR) the method will return null.
	 */
	
	public static String getErrorMessage(ErrorCode code) {
		switch (code) {
		case BAD_COMMAND:
			return "Unknown command. Use ’help’ to see the available commands";
		case BAD_ARGUMENT:
			return "Unknown direction for move command";
		case NO_ARGUMENT:
			return "Move must be followed by a direction: up, down, left or right";
		default:
			return null;
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