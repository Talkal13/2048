package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 * 
 * Class which extends the abstract class NoParamsCommand and represents the Undo command.
 *
 */

public class UndoCommand extends NoParamsCommand {

	/**
	 * Constructor of the class implements the NoParamsCommand parent class with it´s parameters.
	 * 
	 * @param commandInfo string containing the undo command.
	 * @param helpInfo explanation of what the undo command does.
	 */
	
	public UndoCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Executes the undo command, in case that is no possible to undo the  movement because the stack is empty the controller attribute NoPrintGameState 
	 * will be set to true and as well notification saying "Nothing to undo". If is not empty a message "Undoing one move..." will be shown instead and  
	 * the method undo from the class game of the package p2.logic called to undo the last movement in the board.
	 * 
	 * @param game current game which is taking place and which movement would be undone.
	 * @param controller controller of the current game, it´s attributes will be modified as described below.
	 */
	
	public void execute(Game game, Controller controller) {
		if (game.isUndoStackEmpty()) {
			System.out.println("Nothing to undo");
			controller.setNoPrintGameState(true);
			return;
		}
		System.out.println("Undoing one move...");
		game.undo();
		
	}

}
