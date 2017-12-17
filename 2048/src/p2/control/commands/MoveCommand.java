package p2.control.commands;

import p2.control.Controller;
import p2.control.ErrorCode;
import p2.logic.Game;
import p2.util.Direction;
import p2.util.DirectionOption;

/**
 * @author Pablo & Diego
 * 
 * Class which extends the abstract class Command and represents the Move command.
 * 
 */

public class MoveCommand extends Command {

	//attribute of direction of the movement to perform
	private Direction dir;
	
	/**
	 * Constructor of the class implements the MoveCommand class Command with it�s parameters.
	 * 
	 * @param commandInfo string containing the move command and the direction of the movement.
	 * @param helpInfo explanation of what the move command does.
	 */
	
	public MoveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Executes the movement in the game in the direction indicated. this is performed through the move method from the class Game in the package  p2.logic
	 * 
	 * @param game current game which is taking place and where the direction move will be performed.
	 * @param controller controller of the current game.
	 */
	public void execute(Game game, Controller controller) {
		game.move(dir);
	}

	/**
	 * Parses a move command, which will be in the parameter commandWords, if in a correct format in the fist position is the move and in the second the direction.
	 * It will parse the direction and if is well defined the attribute dir will receive that direction. The cases only one word is included or the direction is 
	 * wrongly written is also contemplated.
	 * 
	 * @param commandWords String Array which contains the command move and the direction to perform the movement.
	 * @param controller controller of the current game.
	 * 
	 * @return the command itself with the dir attribute already contending the direction of the movement in the case that all went right.
	 * If the first word parsed wasn't move, a null will be returned. Also if the second word is not one of the possible directions, in this case in addition
	 * the errorCode and NoPrintGameSate attributes from controller will be settled and the error BAD_ARGUMENT will be shown.
	 */
	
	public Command parse(String[] commandWords, Controller controller) {
		
		if (!commandWords[0].equals(this.commandName)) {
			return null;
			
		} 
		else {
			try {
				switch (commandWords[1]) {
				case "up":
					dir = new Direction(DirectionOption.UP);
					break;
				case "down":
					dir = new Direction(DirectionOption.DOWN);
					break;
				case "right":
					dir = new Direction(DirectionOption.RIGHT);
					break;
				case "left":
					dir = new Direction(DirectionOption.LEFT);
					break;
				default:
					controller.setErrorCode(false);
					controller.setNoPrintGameState(true);
					System.out.println(Controller.getErrorMessage(ErrorCode.BAD_ARGUMENT));
					return null;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				controller.setErrorCode(false);
				controller.setNoPrintGameState(true);
				System.out.println(Controller.getErrorMessage(ErrorCode.NO_ARGUMENT));
				return null;
			}
		}
		return this;
	}

}
