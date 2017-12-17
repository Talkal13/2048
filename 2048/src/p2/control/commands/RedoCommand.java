package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 * 
 * Class which extends the abstract class NoParamsCommand and represents the Redo command.
 *
 */

public class RedoCommand extends NoParamsCommand {

	/**
	 * Constructor of the class implements the NoParamsCommand parent class with it´s parameters.
	 * 
	 * @param commandInfo string containing the redo command.
	 * @param helpInfo explanation of what the redo command does.
	 */
	
	public RedoCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Executes the redo command, in case that is no possible to recover the next movement because the stack is empty the controller attribute NoPrintGameState 
	 * will be set to true and as well notification saying "Nothing to redo". If is not empty a message "Redoing one move..." will be shown instead and  
	 * the method redo from the class game of the package p2.logic called to recover that position of the tokens in the  board.
	 * 
	 * @param game current game which is taking place and which movement would be recovered.
	 * @param controller controller of the current game, it´s attributes will be modified as described below.
	 */
	
	public void execute(Game game, Controller controller) {
		if (game.isRedoStackEmpty()) {
			System.out.println("Nothing to redo");
			controller.setNoPrintGameState(true);
			return;
		}
		System.out.println("Redoing one move...");
		game.redo();
		
	}

}
