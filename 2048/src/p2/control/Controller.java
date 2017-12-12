package p2.control;

import java.io.IOException;
import java.util.Scanner;

import p2.control.commands.Command;
import p2.control.invoker.CommandParser;
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
	 * Constructor of the class Controller, which will control the game introduced as paramether.
	 *
	 * @param currentGame game to control.
	 */

	public Controller(Game  currentGame) {
		game = currentGame;
		in = new Scanner(System.in);
	}
	
	/**
	 * Reads the character to reun the game without the needd of introducing the whole move X command.
	 * 
	 * @return 0, the returning value is not really going to be used.
	 */

	private char readKey() {
		//java.io.InputStreamReader reader = new java.io.InputStreamReader(System.in);
		try {
			return (char) System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Method which runs the game depending on the introduced key, performing the corresponding actions.
	 * 
	 * @param key char which indicates the psoition to move.
	 */

	public void run(char key) {
		while (key != 'q') { //ESC
			switch (key) {
			case 'w':
				game.move(new Direction(DirectionOption.UP));
				System.out.print(game);
				break;
			case 's':
				game.move(new Direction(DirectionOption.DOWN));
				System.out.print(game);
				break;
			case 'a':
				game.move(new Direction(DirectionOption.LEFT));
				System.out.print(game);
				break;
			case 'd':
				game.move(new Direction(DirectionOption.RIGHT));
				System.out.print(game);
				break;
			case 'r': //the program returns to the initial state
				game.reset();
				System.out.print(game);
				break;
			case 'q': //exit
				break;
			case 'h': //displays the help text
				showHelp();
				break;
			default:
				break;
			}
			if (game.isWon()){
				System.out.println("CONGRATULATIONS YOU HAVE WON!");
				 break;
			}
			else if (game.isOver()) {
				System.out.println("CONGRATULATIONS YOU HAVE WON!");
				 break;
			}

			key = readKey();

		}
	}
	
	public void setOver(boolean x) {
		this.gameEnd = x;
	}
	
	public void setNoPrintGameState(boolean x) {
		this.gameState = x;
	}
	
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
			Command control = CommandParser.parseCommand(parts, this);
			if (control != null) 
				control.execute(game, this);
			else if (errorCode) {
				System.out.println(Controller.getErrorMessage(ErrorCode.BAD_COMMAND));
			}
			if (!this.gameState) {
				System.out.println(game);
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
	
	
	public static String getErrorMessage(ErrorCode code) {
		switch (code) {
		case BAD_COMMAND:
			return "Unknown command";
		case BAD_ARGUMENT:
			return "Unknown direction for move command";
		case NO_ARGUMENT:
			return "Move must be followed by a direction: up, down, left or right";
		default:
			return null;
		}
	}
 

	/**
	 * Shows the different helpp messages to help the user about the different possibilities of movement
	 */

	public void showHelp(){
		System.out.println("Move <direction>: execute a move in one of the fur directions, up, own,left, right");
		System.out.println("Reset: start a new game");
		System.out.println("Help: print this help message");
		System.out.println("Exit: terminate the program");
	}


}
