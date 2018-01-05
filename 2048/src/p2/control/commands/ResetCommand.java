package p2.control.commands;

import p2.control.Controller;
import p2.logic.Game;

/**
 * @author Pablo & Diego
 * 
 * Class which extends the abstract class NoParamsCommand and represents the Reset command.
 *
 */
public class ResetCommand extends NoParamsCommand {
	
	/**
	 * Constructor of the class implements the NoParamsCommand parent class with it�s parameters.
	 * 
	 * @param commandInfo string containing the redo command.
	 * @param helpInfo explanation of what the redo command does.
	 */
	
	public ResetCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Executes the reset command, calling reset method from the class game of the package p2.logic called to recover that position of the tokens in the  board.
	 * 
	 * @param game current game will be reset.
	 * @param controller controller of the current game.
	 */
	
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

}
